package ca.bytetube.communityApp.util.wechat;

import ca.bytetube.communityApp.dto.UserAccessToken;
import ca.bytetube.communityApp.dto.WechatUser;
import ca.bytetube.communityApp.entity.PersonInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;

/**
 * 微信工具类
 */
public class WechatUtil {

	private static Logger log = LoggerFactory.getLogger(WechatUtil.class);

	/**
	 * 获取UserAccessToken实体类
	 */
	public static UserAccessToken getUserAccessToken(String code) throws IOException {
		// 测试号信息里的appId
		String appId = "wx8e9c4bd828891561";
		log.debug("appId:" + appId);
		// 测试号信息里的appsecret
		String appsecret = "c70d18897649f2daf10b280b088a1b49";
		log.debug("secret:" + appsecret);
		// 根据传入的code,拼接出访问微信定义好的接口的URL
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appsecret
				+ "&code=" + code + "&grant_type=authorization_code";
		// 向相应URL发送请求获取token json字符串
		String tokenStr = httpsRequest(url, "GET", null);
		log.debug("userAccessToken:" + tokenStr);
		UserAccessToken token = new UserAccessToken();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// 将json字符串转换成相应对象
			token = objectMapper.readValue(tokenStr, UserAccessToken.class);
		} catch (JsonParseException e) {
			log.error("Failed to get user accessToken: " + e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			log.error("Failed to get user accessToken: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Failed to get user accessToken: " + e.getMessage());
			e.printStackTrace();
		}
		if (token == null) {
			log.error("Failed to get user accessToken.");
			return null;
		}
		return token;
	}

	/**
	 * 获取WechatUser实体类
	 */
	public static WechatUser getUserInfo(String accessToken, String openId) {
		// 根据传入的accessToken以及openId拼接出访问微信定义的端口并获取用户信息的URL
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId
				+ "&lang=zh_CN";
		// 访问该URL获取用户信息json 字符串
		String userStr = httpsRequest(url, "GET", null);
		log.debug("user info :" + userStr);
		WechatUser user = new WechatUser();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// 将json字符串转换成相应对象
			user = objectMapper.readValue(userStr, WechatUser.class);
		} catch (JsonParseException e) {
			log.error("Failed to get user information: " + e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			log.error("Failed to get user information: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Failed to get user information: " + e.getMessage());
			e.printStackTrace();
		}
		if (user == null) {
			log.error("Failed to get user information.");
			return null;
		}
		return user;
	}

	/**
	 * 将WechatUser里的信息转换成PersonInfo的信息并返回PersonInfo实体类
	 */
	public static PersonInfo getPersonInfoFromRequest(WechatUser user) {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setName(user.getNickName());
		personInfo.setGender(user.getSex() + "");
		personInfo.setProfileImg(user.getHeadimgurl());
		personInfo.setEnableStatus(1);
		return personInfo;
	}

	/**
	 * 发起https请求并获取结果
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			log.debug("https buffer:" + buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return buffer.toString();
	}
}
