package ca.bytetube.communityApp.dao;


import ca.bytetube.communityApp.entity.Award;
import ca.bytetube.communityApp.entity.PersonInfo;
import ca.bytetube.communityApp.entity.Shop;
import ca.bytetube.communityApp.entity.UserAwardMap;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAwardMapDaoTest {
	@Autowired
	private UserAwardMapDao userAwardMapDao;

	/**
	 * test添加功能
	 */
	@Test
	public void testAInsertUserAwardMap() throws Exception {
		// 创建用户奖品映射信息1
		UserAwardMap userAwardMap = new UserAwardMap();
		PersonInfo customer = new PersonInfo();
		customer.setUserId(1L);
		userAwardMap.setUser(customer);
		userAwardMap.setOperator(customer);
		Award award = new Award();
		award.setAwardId(1L);
		userAwardMap.setAward(award);
		Shop shop = new Shop();
		shop.setShopId(1L);
		userAwardMap.setShop(shop);
		userAwardMap.setCreateTime(new Date());
		userAwardMap.setUsedStatus(1);
		userAwardMap.setPoint(1);
		int effectedNum = userAwardMapDao.insertUserAwardMap(userAwardMap);
		assertEquals(1, effectedNum);
		// 创建用户奖品映射信息2
		UserAwardMap userAwardMap2 = new UserAwardMap();
		PersonInfo customer2 = new PersonInfo();
		customer2.setUserId(1L);
		userAwardMap2.setUser(customer2);
		userAwardMap2.setOperator(customer2);
		Award award2 = new Award();
		award2.setAwardId(1L);
		userAwardMap2.setAward(award2);
		userAwardMap2.setShop(shop);
		userAwardMap2.setCreateTime(new Date());
		userAwardMap2.setUsedStatus(0);
		userAwardMap2.setPoint(1);
		effectedNum = userAwardMapDao.insertUserAwardMap(userAwardMap2);
		assertEquals(1, effectedNum);
	}

	/**
	 * test查询功能
	 */
	@Test
	public void testBQueryUserAwardMapList() throws Exception {
		UserAwardMap userAwardMap = new UserAwardMap();
		// testqueryUserAwardMapList
		List<UserAwardMap> userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMap, 0, 3);
		assertEquals(2, userAwardMapList.size());
		int count = userAwardMapDao.queryUserAwardMapCount(userAwardMap);
		assertEquals(2, count);
		PersonInfo customer = new PersonInfo();
		// 按用户名模糊查询
		customer.setName("bytetube");
		userAwardMap.setUser(customer);
		userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMap, 0, 3);
		assertEquals(2, userAwardMapList.size());
		count = userAwardMapDao.queryUserAwardMapCount(userAwardMap);
		assertEquals(2, count);
		// testqueryUserAwardMapById
		userAwardMap = userAwardMapDao.queryUserAwardMapById(userAwardMapList.get(0).getUserAwardId());
		assertEquals("test1", userAwardMap.getAward().getAwardName());
	}

	/**
	 * test更新功能
	 */
	@Test
	public void testCUpdateUserAwardMap() throws Exception {
		UserAwardMap userAwardMap = new UserAwardMap();
		PersonInfo customer = new PersonInfo();
		// 按用户名模糊查询
		customer.setName("bytetube");
		userAwardMap.setUser(customer);
		List<UserAwardMap> userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMap, 0, 1);
		assertTrue("Error, point inconsistency！", 0 == userAwardMapList.get(0).getUsedStatus());
		userAwardMapList.get(0).setUsedStatus(1);
		int effectedNum = userAwardMapDao.updateUserAwardMap(userAwardMapList.get(0));
		assertEquals(1, effectedNum);
	}
}
