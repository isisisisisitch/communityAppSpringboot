package ca.bytetube.communityApp.service.impl;

import ca.bytetube.communityApp.dao.UserAwardMapDao;
import ca.bytetube.communityApp.dao.UserShopMapDao;
import ca.bytetube.communityApp.dto.UserAwardMapExecution;
import ca.bytetube.communityApp.entity.UserAwardMap;
import ca.bytetube.communityApp.entity.UserShopMap;
import ca.bytetube.communityApp.enums.UserAwardMapStateEnum;
import ca.bytetube.communityApp.exceptions.UserAwardMapOperationException;
import ca.bytetube.communityApp.service.UserAwardMapService;
import ca.bytetube.communityApp.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserAwardMapServiceImpl implements UserAwardMapService {
    @Autowired
    private UserAwardMapDao userAwardMapDao;
    @Autowired
    private UserShopMapDao userShopMapDao;

    @Override
    public UserAwardMapExecution listUserAwardMap(UserAwardMap userAwardCondition, Integer pageIndex,
                                                  Integer pageSize) {
        // 空值判断
        if (userAwardCondition != null && pageIndex != null && pageSize != null) {
            // 页转行
            int beginIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
            // 根据查询条件分页返回用户与奖品的映射信息列表(用户领取奖品的信息列表)
            List<UserAwardMap> userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardCondition, beginIndex,
                    pageSize);
            // 返回总数
            int count = userAwardMapDao.queryUserAwardMapCount(userAwardCondition);
            UserAwardMapExecution ue = new UserAwardMapExecution();
            ue.setUserAwardMapList(userAwardMapList);
            ue.setCount(count);
            return ue;
        } else {
            return null;
        }
    }

    @Override
    public UserAwardMapExecution listReceivedUserAwardMap(UserAwardMap userAwardCondition, Integer pageIndex,
                                                          Integer pageSize) {
        // 空值判断
        if (userAwardCondition != null && pageIndex != null && pageSize != null) {
            // 页转行
            int beginIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
            // 根据查询条件分页返回用户与奖品的映射信息列表(用户领取奖品的信息列表)
            List<UserAwardMap> userAwardMapList = userAwardMapDao.queryReceivedUserAwardMapList(userAwardCondition, beginIndex,
                    pageSize);
            // 返回总数
            int count = userAwardMapDao.queryUserAwardMapCount(userAwardCondition);
            UserAwardMapExecution ue = new UserAwardMapExecution();
            ue.setUserAwardMapList(userAwardMapList);
            ue.setCount(count);
            return ue;
        } else {
            return null;
        }
    }

    @Override
    public UserAwardMap getUserAwardMapById(long userAwardMapId) {
        return userAwardMapDao.queryUserAwardMapById(userAwardMapId);
    }

    @Override
    @Transactional
    public UserAwardMapExecution addUserAwardMap(UserAwardMap userAwardMap) throws UserAwardMapOperationException {
        // 空值判断，主要是确定userId和shopId不为空
        if (userAwardMap != null && userAwardMap.getUser() != null && userAwardMap.getUser().getUserId() != null
                && userAwardMap.getShop() != null && userAwardMap.getShop().getShopId() != null) {
            // 设置默认值
            userAwardMap.setCreateTime(new Date());
            userAwardMap.setUsedStatus(0);
            try {
                int effectedNum = 0;
                // 若该奖品需要消耗积分，则将tb_user_shop_map对应的用户积分抵扣
                if (userAwardMap.getPoint() != null && userAwardMap.getPoint() > 0) {
                    // 根据用户Id和店铺Id获取该用户在店铺的积分
                    UserShopMap userShopMap = userShopMapDao.queryUserShopMap(userAwardMap.getUser().getUserId(),
                            userAwardMap.getShop().getShopId());
                    // 判断该用户在店铺里是否有积分
                    if (userShopMap != null) {
                        // 若有积分，必须确保店铺积分大于本次要兑换奖品需要的积分
                        if (userShopMap.getPoint() >= userAwardMap.getPoint()) {
                            // 积分抵扣
                            userShopMap.setPoint(userShopMap.getPoint() - userAwardMap.getPoint());
                            // 更新积分信息
                            effectedNum = userShopMapDao.updateUserShopMapPoint(userShopMap);
                            if (effectedNum <= 0) {
                                throw new UserAwardMapOperationException("Failed to update point information");
                            }
                        } else {
                            throw new UserAwardMapOperationException("Not enough points to claim");
                        }
                    } else {
                        // 在店铺没有积分，则抛出异常
                        throw new UserAwardMapOperationException("There are no points in this shop, can not exchange awards");
                    }
                }
                // 插入礼品兑换信息
                effectedNum = userAwardMapDao.insertUserAwardMap(userAwardMap);
                if (effectedNum <= 0) {
                    throw new UserAwardMapOperationException("Failure to get rewards");
                }
                return new UserAwardMapExecution(UserAwardMapStateEnum.SUCCESS, userAwardMap);
            } catch (Exception e) {
                throw new UserAwardMapOperationException("Failure to get rewards:" + e.toString());
            }
        } else {
            return new UserAwardMapExecution(UserAwardMapStateEnum.NULL_USERAWARD_INFO);
        }
    }

    @Override
    @Transactional
    public UserAwardMapExecution modifyUserAwardMap(UserAwardMap userAwardMap) throws UserAwardMapOperationException {
        // 空值判断，主要是检查传入的userAwardId以及领取状态是否为空
        if (userAwardMap == null || userAwardMap.getUserAwardId() == null || userAwardMap.getUsedStatus() == null) {
            return new UserAwardMapExecution(UserAwardMapStateEnum.NULL_USERAWARD_ID);
        } else {
            try {
                // 更新可用状态
                int effectedNum = userAwardMapDao.updateUserAwardMap(userAwardMap);
                if (effectedNum <= 0) {
                    return new UserAwardMapExecution(UserAwardMapStateEnum.INNER_ERROR);
                } else {
                    return new UserAwardMapExecution(UserAwardMapStateEnum.SUCCESS, userAwardMap);
                }
            } catch (Exception e) {
                throw new UserAwardMapOperationException("modifyUserAwardMap error: " + e.getMessage());
            }
        }
    }

}
