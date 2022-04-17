package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.entity.Area;
import ca.bytetube.communityApp.entity.PersonInfo;
import ca.bytetube.communityApp.entity.Shop;
import ca.bytetube.communityApp.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest {
	@Autowired
	private ShopDao shopDao;

	@Test
	public void testQueryShopListAndCount() {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
//		ShopCategory childCategory = new ShopCategory();
//		ShopCategory parentCategory = new ShopCategory();
//		parentCategory.setShopCategoryId(1L);
//		childCategory.setParent(parentCategory);
//		shopCondition.setShopCategory(childCategory);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 6);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("shop list size ：" + shopList.size());
		System.out.println("shop count ：" + count);
	}

	@Test
	public void testQueryByShopId() {
		long shopId = 1;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("areaId: " + shop.getArea().getAreaId());
		System.out.println("areaName: " + shop.getArea().getAreaName());
	}

	@Test
	public void testInsertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("test shop");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(0);
		shop.setAdvice("pending");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}


	@Test

	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(84L);
		shop.setShopDesc("test desc");
		shop.setShopAddr("test address");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
}
