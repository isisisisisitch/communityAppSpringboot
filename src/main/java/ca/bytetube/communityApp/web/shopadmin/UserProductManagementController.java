package ca.bytetube.communityApp.web.shopadmin;

import ca.bytetube.communityApp.dto.ShopAuthMapExecution;
import ca.bytetube.communityApp.dto.UserAccessToken;
import ca.bytetube.communityApp.dto.UserProductMapExecution;
import ca.bytetube.communityApp.dto.WechatInfo;
import ca.bytetube.communityApp.entity.*;
import ca.bytetube.communityApp.enums.UserProductMapStateEnum;
import ca.bytetube.communityApp.service.*;
import ca.bytetube.communityApp.util.HttpServletRequestUtil;
import ca.bytetube.communityApp.util.wechat.WechatUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/shopadmin")
public class UserProductManagementController {
    @Autowired
    private UserProductMapService userProductMapService;
    @Autowired
    private ProductSellDailyService productSellDailyService;

    @RequestMapping(value = "/listuserproductmapsbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listUserProductMapsByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 获取分页信息
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        // 获取当前的店铺信息
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        // 空值校验，主要确保shopId不为空
        if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null) && (currentShop.getShopId() != null)) {
            // 添加查询条件
            UserProductMap userProductMapCondition = new UserProductMap();
            userProductMapCondition.setShop(currentShop);
            String productName = HttpServletRequestUtil.getString(request, "productName");
            if (productName != null) {
                // 若前端想按照商品名模糊查询，则传入productName
                Product product = new Product();
                product.setProductName(productName);
                userProductMapCondition.setProduct(product);
            }
            // 根据传入的查询条件获取该店铺的商品销售情况
            UserProductMapExecution ue = userProductMapService.listUserProductMap(userProductMapCondition, pageIndex,
                    pageSize);
            modelMap.put("userProductMapList", ue.getUserProductMapList());
            modelMap.put("count", ue.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }


}
