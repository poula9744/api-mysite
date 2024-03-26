package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@RestController
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@GetMapping("/api/guestbook")
	public List<GuestbookVo> addList(){
		System.out.println("GuestbookController.guestbookService()");
		List<GuestbookVo> guestList = guestbookService.exeAddList();
		System.out.println(guestList);
		return guestList;
	}
}
