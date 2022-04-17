package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCategoryDao {
	/**
	 * 根据传入的查询条件返回店铺类别列表
	 */
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);


}
