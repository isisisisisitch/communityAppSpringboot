package ca.bytetube.communityApp.dao;



import ca.bytetube.communityApp.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {

	/**
	 * 列出某个商品的详情图列表
	 */
	List<ProductImg> queryProductImgList(long productId);

	/**
	 * 批量添加商品详情pic
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);

	/**
	 * 删除指定商品下的所有详情图
	 */
	int deleteProductImgByProductId(long productId);
}
