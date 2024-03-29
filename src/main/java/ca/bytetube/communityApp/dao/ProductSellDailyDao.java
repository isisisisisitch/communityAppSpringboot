package ca.bytetube.communityApp.dao;


import ca.bytetube.communityApp.entity.ProductSellDaily;
import org.apache.ibatis.annotations.Param;
import java.util.Date;
import java.util.List;

public interface ProductSellDailyDao {
	/**
	 * 根据查询条件返回商品日销售的统计列表
	 */
	List<ProductSellDaily> queryProductSellDailyList(
            @Param("productSellDailyCondition") ProductSellDaily productSellDailyCondition,
            @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	/**
	 * 统计平台所有商品的日销售量
	 */
	int insertProductSellDaily();

	/**
	 * 统计平台当天销量的商品，补全信息，将他们的销量置为0
	 */
	int insertDefaultProductSellDaily();
}
