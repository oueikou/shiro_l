package com.yh.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm3 implements Realm {

	public String getName() {
		return "myrealm3";
	}

	public boolean supports(AuthenticationToken token) {
		//仅支持UsernamePasswordToken类型的Token
		return token instanceof UsernamePasswordToken;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal(); // 获取用户名
		String password = String.valueOf((char[])token.getCredentials()); // 获取密码
		if(!"qian".equals(userName)){
			throw new UnknownAccountException("用户名错误");
		}
		if(!"123".equals(password)){
			throw new IncorrectCredentialsException("密码错误");
		}
		return new SimpleAuthenticationInfo(userName, password, getName());
	}

}
