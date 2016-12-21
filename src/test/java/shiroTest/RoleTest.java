package shiroTest;

import java.util.Arrays;

import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import shiro_l.utils.ShiroUtil;

public class RoleTest {

	private static final Logger logger = LoggerFactory.getLogger(RoleTest.class);
	
	@Test
	public void testHasRole(){
		Subject subject = ShiroUtil.doLogin("classpath:shiro-role.ini", "wang", "456");
		if (subject.hasRole("role1")) {
			logger.info("有role1角色。。。");
		} else {
			logger.info("没有role1角色。。。");
		}
		
		if (subject.hasAllRoles(Arrays.asList("role1", "role2"))) {
			logger.info("有role1和role2角色。。。");
		} else {
			logger.info("不同时有role1、role2两个角色。。。");
		}
		
	}
	
}
