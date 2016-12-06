package shiroTest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import junit.framework.Assert;

public class LoginLogoutTest {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 7.断言用户已登录
		Assert.assertEquals(true, subject.isAuthenticated());
		// 8.退出登录
		subject.logout();
	}
	
}
