package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.entity.ProductImg;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest {
	@Autowired
	private ProductImgDao productImgDao;

	@Test
	public void testABatchInsertProductImg() throws Exception {
		// productId为1的商品里添加两个详情pic记录
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("pic1");
		productImg1.setImgDesc("testpic1");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(1L);
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("pic2");
		productImg2.setPriority(1);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(1L);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
	}

	@Test
	public void testBQueryProductImgList() {
		// 检查productId为1的商品是否有且仅有两张商品详情pic
		List<ProductImg> productImgList = productImgDao.queryProductImgList(1L);
		assertEquals(2, productImgList.size());
	}

	@Test
	public void testCDeleteProductImgByProductId() throws Exception {
		// 删除新增的两条商品详情pic记录
		long productId = 1;
		int effectedNum = productImgDao.deleteProductImgByProductId(productId);
		assertEquals(2, effectedNum);
	}
}
