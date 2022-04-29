package ca.bytetube.communityApp.service;


import ca.bytetube.communityApp.dto.UserProductMapExecution;
import ca.bytetube.communityApp.entity.UserProductMap;
import ca.bytetube.communityApp.exceptions.UserProductMapOperationException;

public interface UserProductMapService {
    /**
     * 通过传入的查询条件分页列出用户消费信息列表
     */
    UserProductMapExecution listUserProductMap(UserProductMap userProductCondition, Integer pageIndex,
                                               Integer pageSize);

    UserProductMapExecution addUserProductMap(UserProductMap userProductMap) throws UserProductMapOperationException;
}
