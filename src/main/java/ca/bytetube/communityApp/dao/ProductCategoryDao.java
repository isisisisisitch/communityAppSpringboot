package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
	/**
	 * 通过shop id 查询店铺商品类别
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);

	/**
	 * 批量新增商品类别
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);

	/**
	 * 删除指定商品类别
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);

}
