package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

	/**
	 * 分页查询店铺，可输入的条件有：店铺名(模糊),店铺状态，店铺类别，区域Id,owner
	 * @param shopCondition
	 * @param rowIndex 从第几行开始取数据
	 * @param pageSize 返回的条数
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

	/**
	 * 返回queryShopList总数
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);

	Shop queryByShopId(long shopId);

	int insertShop(Shop shop);

	int updateShop(Shop shop);

}
