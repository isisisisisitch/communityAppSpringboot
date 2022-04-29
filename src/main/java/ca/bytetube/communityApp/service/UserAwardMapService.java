package ca.bytetube.communityApp.service;


import ca.bytetube.communityApp.dto.UserAwardMapExecution;
import ca.bytetube.communityApp.entity.UserAwardMap;
import ca.bytetube.communityApp.exceptions.UserAwardMapOperationException;

public interface UserAwardMapService {

    /**
     * 根据传入的查询条件分页获取映射列表及总数
     */
    UserAwardMapExecution listUserAwardMap(UserAwardMap userAwardCondition, Integer pageIndex, Integer pageSize);


    /**
     * 根据传入的查询条件分页获取映射列表及总数
     */
    UserAwardMapExecution listReceivedUserAwardMap(UserAwardMap userAwardCondition, Integer pageIndex, Integer pageSize);

    /**
     * 根据传入的Id获取映射信息
     */
    UserAwardMap getUserAwardMapById(long userAwardMapId);

    /**
     * 领取奖品，添加映射信息
     */
    UserAwardMapExecution addUserAwardMap(UserAwardMap userAwardMap) throws UserAwardMapOperationException;

    /**
     * 修改映射信息，这里主要修改奖品领取状态
     */
    UserAwardMapExecution modifyUserAwardMap(UserAwardMap userAwardMap) throws UserAwardMapOperationException;

}
