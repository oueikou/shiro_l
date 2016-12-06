package shiroTest;

import org.apache.log4j.Logger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Assert;
import org.apache.shiro.util.Factory;
import org.junit.Test;


public class LoginLogoutTest {
	/**
	* Logger for this class
	*/
	private static final Logger logger = Logger.getLogger(LoginLogoutTest.class);

	/**
	 * 测试登录登出
	 */
	@Test
	public void testHelloWord(){
		// 1.获取SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 2.得到SecurityManager实例
		SecurityManager securityManager = factory.getInstance();
		// 3.绑定到SecurityUtils
		SecurityUtils.setSecurityManager(securityManager);
		// 4.得到subject
		Subject subject = SecurityUtils.getSubject();
		// 5.创建用户名/密码身份验证Token（即用户身份/凭证）
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		
		try {
			// 6.登录
			subject.login(token);
		} catch (AuthenticationException e) {
			logger.error("shiro登录异常!", e);
		}
		// 7.断言用户已登录
		Assert.isTrue(subject.isAuthenticated(), "用户登录失败");
		// 8.退出登录
		subject.logout();
	}
	
	/**
	 * 测试单realm
	 */
	@Test
	public void testCustomRealm(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "1234");
		try {
			subject.login(token);
			if(subject.isAuthenticated()){
				logger.info("登录成功！");
			}
			logger.info("开始登出。。。");
			subject.logout();
			if(!subject.isAuthenticated()){
				logger.info("登出成功！");
			}
		} catch (AuthenticationException e) {
			logger.error("shiro登录异常!"+e.getMessage(), e);
		}
	}
	
	/**
	 * 测试多realm
	 */
	@Test
	public void testCustomMultiRealm(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");
		try {
			subject.login(token);
			if(subject.isAuthenticated()){
				logger.info("登录成功！");
			}
			logger.info("开始登出。。。");
			subject.logout();
			if(!subject.isAuthenticated()){
				logger.info("登出成功！");
			}
		} catch (AuthenticationException e) {
			logger.error("shiro登录异常!"+e.getMessage(), e);
		}
	}
	
}
