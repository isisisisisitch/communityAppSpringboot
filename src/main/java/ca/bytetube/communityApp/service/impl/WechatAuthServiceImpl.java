package ca.bytetube.communityApp.service.impl;

import ca.bytetube.communityApp.dao.PersonInfoDao;
import ca.bytetube.communityApp.dao.WechatAuthDao;
import ca.bytetube.communityApp.dto.WechatAuthExecution;
import ca.bytetube.communityApp.entity.PersonInfo;
import ca.bytetube.communityApp.entity.WechatAuth;
import ca.bytetube.communityApp.enums.WechatAuthStateEnum;
import ca.bytetube.communityApp.exceptions.WechatAuthOperationException;
import ca.bytetube.communityApp.service.WechatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WechatAuthServiceImpl implements WechatAuthService {
	private static Logger log = LoggerFactory.getLogger(WechatAuthServiceImpl.class);
	@Autowired
	private WechatAuthDao wechatAuthDao;
	@Autowired
	private PersonInfoDao personInfoDao;

	@Override
	public WechatAuth getWechatAuthByOpenId(String openId) {
		return wechatAuthDao.queryWechatInfoByOpenId(openId);
	}

	@Override
	@Transactional
	public WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException {
		//空值判断
		if (wechatAuth == null || wechatAuth.getOpenId() == null) {
			return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);
		}
		try {
			//设置创建时间
			wechatAuth.setCreateTime(new Date());
			//如果微信帐号里夹带着用户信息并且用户Id为空，则认为该用户第一次使用平台(且通过微信登录)
			//则自动创建用户信息
			if (wechatAuth.getPersonInfo() != null && wechatAuth.getPersonInfo().getUserId() == null) {
				try {					
					wechatAuth.getPersonInfo().setCreateTime(new Date());
					wechatAuth.getPersonInfo().setEnableStatus(1);
					PersonInfo personInfo = wechatAuth.getPersonInfo();
					int effectedNum = personInfoDao.insertPersonInfo(personInfo);
					wechatAuth.setPersonInfo(personInfo);
					if (effectedNum <= 0) {
						throw new WechatAuthOperationException("Failed to add user information");
					}
				} catch (Exception e) {
					log.error("insertPersonInfo error:" + e.toString());
					throw new WechatAuthOperationException("insertPersonInfo error: " + e.getMessage());
				}
			}
			//创建专属于本平台的微信帐号
			int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
			if (effectedNum <= 0) {
				throw new WechatAuthOperationException("Account creation failed.");
			} else {
				return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS, wechatAuth);
			}
		} catch (Exception e) {
			log.error("insertWechatAuth error:" + e.toString());
			throw new WechatAuthOperationException("insertWechatAuth error: " + e.getMessage());
		}
	}

}
