package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UserController {
	
	@Autowired
	private UserService userSerivce;
	
	//로그인
	@PostMapping("/api/user/login")
	public UserVo login(@RequestBody UserVo userVo, HttpServletResponse response) {
		System.out.println("UserController.login()");
		
		//no name                                id pw
		UserVo authUser = userSerivce.exeLogin(userVo);
		System.out.println(authUser);
		
		if(authUser != null) {
			//토큰 발급 헤더에 실어 보낸다
			JwtUtil.createTokenAndSetHeader(response, ""+authUser.getNo());
		}
		
		
		return authUser;
	}
}
