package ca.bytetube.communityApp.service.impl;

import ca.bytetube.communityApp.dao.ProductSellDailyDao;
import ca.bytetube.communityApp.entity.ProductSellDaily;
import ca.bytetube.communityApp.service.ProductSellDailyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;


@Service
public class ProductSellDailyServiceImpl implements ProductSellDailyService {
	private static final Logger log = LoggerFactory.getLogger(ProductSellDailyServiceImpl.class);
	@Autowired
	private ProductSellDailyDao productSellDailyDao;

	@Override
	public List<ProductSellDaily> listProductSellDaily(ProductSellDaily productSellDailyCondition, Date beginTime,
													   Date endTime) {
		return productSellDailyDao.queryProductSellDailyList(productSellDailyCondition, beginTime, endTime);
	}

	@Override
	public void dailyCalculate() {
		System.out.println("quartz is running");
	}
}
