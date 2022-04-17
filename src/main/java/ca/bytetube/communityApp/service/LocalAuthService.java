package ca.bytetube.communityApp.service;

import ca.bytetube.communityApp.dto.LocalAuthExecution;
import ca.bytetube.communityApp.entity.LocalAuth;
import ca.bytetube.communityApp.exceptions.LocalAuthOperationException;

public interface LocalAuthService {
	/**
	 * 通过帐号和密码获取平台帐号信息
	 */
	LocalAuth getLocalAuthByUsernameAndPwd(String userName, String password);

	/**
	 * 通过userId获取平台帐号信息
	 */
	LocalAuth getLocalAuthByUserId(long userId);

	/**
	 * 绑定微信，生成平台专属的帐号
	 */
	LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

	/**
	 * 修改平台帐号的登录密码
	 */
	LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword)
			throws LocalAuthOperationException;
}
