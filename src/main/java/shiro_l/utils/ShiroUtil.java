package shiro_l.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroUtil {
	/**
	* Logger for this class
	*/
	private static final Logger logger = LoggerFactory.getLogger(ShiroUtil.class);

	public static Subject doLogin(String iniResourcePath, String userName, String password) {
		if (logger.isDebugEnabled()) {
			logger.debug("doLogin(String, String, String) - start"); //$NON-NLS-1$
		}

		// 1.获取SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniResourcePath);
		// 2.得到SecurityManager实例
		SecurityManager securityManager = factory.getInstance();
		// 3.绑定到SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		// 4.得到subject
		Subject subject = SecurityUtils.getSubject();
		// 5.创建用户名/密码身份验证Token（即用户身份/凭证）
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

		try {
			// 6.登录
			subject.login(token);
		} catch (UnknownAccountException uae) {
			logger.error("--账号不正确--");
			uae.printStackTrace();
		} catch (IncorrectCredentialsException ice) {
			logger.error("--密码不正确--");
			ice.printStackTrace();
		} catch (LockedAccountException lae) {
			logger.error("--账号已被锁--");
			lae.printStackTrace();
		} catch (ExcessiveAttemptsException eae) {
			logger.error("--过多尝试异常--");
			eae.printStackTrace();
		} catch (AuthenticationException ae) {
			logger.error("--认证异常--");
			ae.printStackTrace();
		} catch (Exception e) {
			logger.error("--登录发生未知异常--");
			e.printStackTrace();
		}
		return subject;
	}

}
