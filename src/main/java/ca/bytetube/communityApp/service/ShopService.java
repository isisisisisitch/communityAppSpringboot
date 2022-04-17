package ca.bytetube.communityApp.service;

import ca.bytetube.communityApp.dto.ImageHolder;
import ca.bytetube.communityApp.dto.ShopExecution;
import ca.bytetube.communityApp.entity.Shop;
import ca.bytetube.communityApp.exceptions.ShopOperationException;

public interface ShopService {

	/**
	 * 根据shopCondition分页返回相应店铺列表
	 */
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);


	/**
	 * 通过店铺Id获取店铺信息
	 */
	Shop getByShopId(long shopId);


	/**
	 * 更新店铺信息，包括对图片的处理
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;


	/**
	 * 注册店铺信息，包括图片处理
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
