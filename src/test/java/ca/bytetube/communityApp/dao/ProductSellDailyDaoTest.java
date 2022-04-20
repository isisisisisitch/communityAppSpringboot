package ca.bytetube.communityApp.dao;


import ca.bytetube.communityApp.entity.ProductSellDaily;
import ca.bytetube.communityApp.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductSellDailyDaoTest {
	@Autowired
	private ProductSellDailyDao productSellDailyDao;

	/**
	 * 测试添加功能
	 */
	@Test
	public void testAInsertProductSellDaily() throws Exception {
		// 创建商品日销量统计
		int effectedNum = productSellDailyDao.insertProductSellDaily();
		assertEquals(0, effectedNum);
	}
	
	/**
	 * 测试添加功能
	 */
	@Test
	public void testBInsertDefaultProductSellDaily() throws Exception {
		// 创建商品日销量统计
		int effectedNum = productSellDailyDao.insertDefaultProductSellDaily();
		assertEquals(0, effectedNum);
	}

	/**
	 * 测试查询功能
	 */
	@Test
	//@Ignore
	public void testCQueryProductSellDaily() throws Exception {
		ProductSellDaily productSellDaily = new ProductSellDaily();
		// 叠加店铺去查询
		Shop shop = new Shop();
		shop.setShopId(1L);
		productSellDaily.setShop(shop);
		List<ProductSellDaily> productSellDailyList = productSellDailyDao.queryProductSellDailyList(productSellDaily,
				null, null);
		System.out.println(productSellDailyList.size());
	}
}
