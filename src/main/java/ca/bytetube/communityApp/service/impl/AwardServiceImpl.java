package ca.bytetube.communityApp.service.impl;


import ca.bytetube.communityApp.dao.AwardDao;
import ca.bytetube.communityApp.dto.AwardExecution;
import ca.bytetube.communityApp.dto.ImageHolder;
import ca.bytetube.communityApp.entity.Award;
import ca.bytetube.communityApp.enums.AwardStateEnum;
import ca.bytetube.communityApp.exceptions.AwardOperationException;
import ca.bytetube.communityApp.service.AwardService;
import ca.bytetube.communityApp.util.ImageUtil;
import ca.bytetube.communityApp.util.PageCalculator;
import ca.bytetube.communityApp.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {

    @Autowired
    private AwardDao awardDao;

    @Override
    public AwardExecution getAwardList(Award awardCondition, int pageIndex, int pageSize) {
        // 页转行
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        // 根据查询条件分页取出奖品列表信息
        List<Award> awardList = awardDao.queryAwardList(awardCondition, rowIndex, pageSize);
        // 根据相同的查询条件返回该查询条件下的奖品总数
        int count = awardDao.queryAwardCount(awardCondition);
        AwardExecution ae = new AwardExecution();
        ae.setAwardList(awardList);
        ae.setCount(count);
        return ae;
    }

    @Override
    public Award getAwardById(long awardId) {
        return awardDao.queryAwardByAwardId(awardId);
    }

    @Override
    @Transactional
    // 1.处理缩略图，获取缩略图相对路径并赋值给award
    // 2.往tb_award写入奖品信息
    public AwardExecution addAward(Award award, ImageHolder thumbnail) {
        // 空值判断
        if (award != null && award.getShopId() != null) {
            // 给award赋上初始值
            award.setCreateTime(new Date());
            award.setLastEditTime(new Date());
            // award默认可用，即出现在前端展示系统中
            award.setEnableStatus(1);
            if (thumbnail != null) {
                // 若传入的图片信息不为空则更新图片
                addThumbnail(award, thumbnail);
            }
            try {
                // 添加奖品信息
                int effectedNum = awardDao.insertAward(award);
                if (effectedNum <= 0) {
                    throw new AwardOperationException("Failed to create award");
                }
            } catch (Exception e) {
                throw new AwardOperationException("Failed to create award:" + e.toString());
            }
            return new AwardExecution(AwardStateEnum.SUCCESS, award);
        } else {
            return new AwardExecution(AwardStateEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    // 1.若缩略图参数有值，则处理缩略图，
    // 若原先存在缩略图则先删除再添加新图，之后获取缩略图相对路径并赋值给award
    // 2.更新tb_award的信息
    public AwardExecution modifyAward(Award award, ImageHolder thumbnail) {
        // 空值判断
        if (award != null && award.getAwardId() != null) {
            award.setLastEditTime(new Date());
            if (thumbnail != null) {
                // 通过awardId取出对应的实体类信息
                Award tempAward = awardDao.queryAwardByAwardId(award.getAwardId());
                // 如果传输过程中存在图片流，则删除原有图片
                if (tempAward.getAwardImg() != null) {
                    ImageUtil.deleteFileOrPath(tempAward.getAwardImg());
                }
                // 存储图片流，获取相对路径
                addThumbnail(award, thumbnail);
            }
            try {
                // 根据传入的实体类修改相应的信息
                int effectedNum = awardDao.updateAward(award);
                if (effectedNum <= 0) {
                    throw new AwardOperationException("Failed to update award info");
                }
                return new AwardExecution(AwardStateEnum.SUCCESS, award);
            } catch (Exception e) {
                throw new AwardOperationException("Failed to update award info:" + e.toString());
            }
        } else {
            return new AwardExecution(AwardStateEnum.EMPTY);
        }
    }

    /**
     * 存储图片流至指定路径并返回相对路径
     *
     * @param award
     * @param thumbnail
     */
    private void addThumbnail(Award award, ImageHolder thumbnail) {
        String dest = PathUtil.getShopImagePath(award.getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        award.setAwardImg(thumbnailAddr);
    }

}
