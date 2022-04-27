package ca.bytetube.communityApp.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


/**
 * 使用xiaomark的短链接服务生成短链接
 */

public class ShortNetAddressUtil {

    static String actionUrl = "https://api.xiaomark.com/v1/link/create";

    static String APIKEY = "34fa95f53dfce2e5bce3b141387183da";


    public static void main(String[] args) {
        String longUrl = "https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login";
        System.out.println(getShortURL(longUrl));

    }

    @SuppressWarnings("deprecation")
    public static String getShortURL(String longUrl) {
        //longUrl = java.net.URLEncoder.encode(longUrl);  xiaomark不需要处理原网址
        //传好json数据
        String apikey = APIKEY;
        JSONObject param = new JSONObject();
        param.put("apikey", apikey);
        param.put("origin_url", longUrl);
        //利用RestTemplate进行第三方接口的调用
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        //设置超时时间30s
        requestFactory.setConnectTimeout(30 * 1000);
        requestFactory.setReadTimeout(30 * 1000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        ResponseEntity<JSONObject> jsonObjectResponseEntity = restTemplate.postForEntity(actionUrl, param, JSONObject.class);
        //将得到的json对象的body部分进行处理
        JSONObject json = jsonObjectResponseEntity.getBody();
        if (json == null) {
            return "";
        }
        //读取多层嵌套的json数据
        return json.getJSONObject("data").getJSONObject("link").getString("url");
    }
}