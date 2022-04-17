package ca.bytetube.communityApp.service;

import ca.bytetube.communityApp.dto.WechatAuthExecution;
import ca.bytetube.communityApp.entity.WechatAuth;
import ca.bytetube.communityApp.exceptions.WechatAuthOperationException;

public interface WechatAuthService {

	/**
	 * 通过openId查找平台对应的微信帐号

	 */
	WechatAuth getWechatAuthByOpenId(String openId);

	/**
	 * 注册本平台的微信帐号

	 */
	WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;

}
