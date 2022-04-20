package ca.bytetube.communityApp.dao;


import ca.bytetube.communityApp.entity.Award;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AwardDaoTest {

	@Autowired
	private AwardDao awardDao;

	/**
	 * test添加功能
	 */
	@Test
	public void testAInsertAward() throws Exception {
		long shopId = 1;
		// 创建奖品一
		Award award1 = new Award();
		award1.setAwardName("test1");
		award1.setAwardImg("test1");
		award1.setPoint(5);
		award1.setPriority(1);
		award1.setEnableStatus(1);
		award1.setCreateTime(new Date());
		award1.setLastEditTime(new Date());
		award1.setShopId(shopId);
		int effectedNum = awardDao.insertAward(award1);
		assertEquals(1, effectedNum);
		// 创建奖品二
		Award award2 = new Award();
		award2.setAwardName("test2");
		award2.setAwardImg("test2");
		award2.setPoint(2);
		award2.setPriority(2);
		award2.setEnableStatus(0);
		award2.setCreateTime(new Date());
		award2.setLastEditTime(new Date());
		award2.setShopId(shopId);
		effectedNum = awardDao.insertAward(award2);
		assertEquals(1, effectedNum);
	}

	/**
	 * test查询列表功能
	 */
	@Test
	public void testBQueryAwardList() throws Exception {
		Award award = new Award();
		List<Award> awardList = awardDao.queryAwardList(award, 0, 3);
		assertEquals(2, awardList.size());
		int count = awardDao.queryAwardCount(award);
		assertEquals(2, count);
		award.setAwardName("test");
		awardList = awardDao.queryAwardList(award, 0, 3);
		assertEquals(2, awardList.size());
		count = awardDao.queryAwardCount(award);
		assertEquals(2, count);
	}

	/**
	 * test按照Id查询功能
	 */
	@Test
	public void testCQueryAwardByAwardId() throws Exception {
		Award awardCondition = new Award();
		awardCondition.setAwardName("test1");
		// 按照特定名字查询返回特定的奖品
		List<Award> awardList = awardDao.queryAwardList(awardCondition, 0, 1);
		assertEquals(1, awardList.size());
		// 通过特定名字查询返回的特定奖品的Id去test方法
		Award award = awardDao.queryAwardByAwardId(awardList.get(0).getAwardId());
		assertEquals("test1", award.getAwardName());
	}

	/**
	 * test更新功能
	 */
	@Test
	public void testDUpdateAward() throws Exception {
		Award awardCondition = new Award();
		awardCondition.setAwardName("test1");
		// 按照特定名字查询返回特定的奖品
		List<Award> awardList = awardDao.queryAwardList(awardCondition, 0, 1);
		// 修改该商品的名称
		awardList.get(0).setAwardName("first test award");
		int effectedNum = awardDao.updateAward(awardList.get(0));
		assertEquals(1, effectedNum);
		// 将修改名称后的奖品找出来并验证
		Award award = awardDao.queryAwardByAwardId(awardList.get(0).getAwardId());
		assertEquals("first test award", award.getAwardName());
	}

	/**
	 * test删除功能
	 */
	@Test
	public void testEDeleteAward() throws Exception {
		Award awardCondition = new Award();
		awardCondition.setAwardName("test");
		// 查询出所有test奖品并删除
		List<Award> awardList = awardDao.queryAwardList(awardCondition, 0, 2);
		assertEquals(2, awardList.size());
		for (Award award : awardList) {
			int effectedNum = awardDao.deleteAward(award.getAwardId(), award.getShopId());
			assertEquals(1, effectedNum);
		}
	}
}
