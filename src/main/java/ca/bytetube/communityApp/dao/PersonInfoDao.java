package ca.bytetube.communityApp.dao;


import ca.bytetube.communityApp.entity.PersonInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonInfoDao {
	/**
	 * 根据查询条件分页返回用户信息列表
	 */
	List<PersonInfo> queryPersonInfoList(@Param("personInfoCondition") PersonInfo personInfoCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * 根据查询条件返回总数，配合queryPersonInfoList使用
	 */
	int queryPersonInfoCount(@Param("personInfoCondition") PersonInfo personInfoCondition);

	/**
	 * 通过用户Id查询用户
	 */
	PersonInfo queryPersonInfoById(long userId);

	/**
	 * 添加用户信息
	 */
	int insertPersonInfo(PersonInfo personInfo);

	/**
	 * 修改用户信息
	 */
	int updatePersonInfo(PersonInfo personInfo);

}
