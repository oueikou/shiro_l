package shiroTest;

import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import shiro_l.utils.ShiroUtil;

public class PermissionTest {

	private static final Logger logger = LoggerFactory.getLogger(PermissionTest.class);
	
	@Test
	public void testIsPermitted(){
		Subject subject = ShiroUtil.doLogin("classpath:shiro-permission.ini", "zhangsan", "123");
		
		if (subject.isPermitted("user:create")) {
			logger.info("有user:create权限");
		} else {
			logger.info("没有user:create权限");
		}
		
		if (subject.isPermitted("user:view")) {
			logger.info("有user:view权限");
		} else {
			logger.info("没有user:view权限");
		}
		
		if (subject.isPermittedAll("user:create", "user:update")){
			logger.info("有user:create和user:update权限");
		} else {
			logger.info("不同时有user:create和user:update权限");
		}
		
		if (subject.isPermittedAll("user:create", "user:view")){
			logger.info("有user:create和user:view权限");
		} else {
			logger.info("不同时有user:create和user:view权限");
		}
		
	}
	
}
