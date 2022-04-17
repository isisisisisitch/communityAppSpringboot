package ca.bytetube.communityApp.dao;


import ca.bytetube.communityApp.entity.WechatAuth;

public interface WechatAuthDao {
	/**
	 * 通过openId查询对应本平台的微信帐号
	 */
	WechatAuth queryWechatInfoByOpenId(String openId);

	/**
	 * 添加对应本平台的微信帐号
	 */
	int insertWechatAuth(WechatAuth wechatAuth);

}
