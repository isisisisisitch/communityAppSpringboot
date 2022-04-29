package ca.bytetube.communityApp.service;


import ca.bytetube.communityApp.dto.AwardExecution;
import ca.bytetube.communityApp.dto.ImageHolder;
import ca.bytetube.communityApp.entity.Award;

public interface AwardService {

    /**
     * 根据传入的条件分页返回奖品列表，并返回该查询条件下的总数
     */
    AwardExecution getAwardList(Award awardCondition, int pageIndex, int pageSize);

    /**
     * 根据awardId查询奖品信息
     */
    Award getAwardById(long awardId);

    /**
     * 添加奖品信息，并添加奖品图片
     */
    AwardExecution addAward(Award award, ImageHolder thumbnail);

    /**
     * 根据传入的奖品实例修改对应的奖品信息， 若传入图片则替换掉原先的图片
     */
    AwardExecution modifyAward(Award award, ImageHolder thumbnail);

}
