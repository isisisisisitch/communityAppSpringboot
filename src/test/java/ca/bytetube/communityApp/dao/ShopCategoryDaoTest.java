package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryDaoTest {
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Test
	public void testQueryShopCategory() {
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
		System.out.println(shopCategoryList.size());
	}
}
