package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.GuestbookVo;

@RestController
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	//리스트
	@GetMapping("/api/guestbook")
	public List<GuestbookVo> addList() {
		System.out.println("GuestbookController.guestbookService()");
		List<GuestbookVo> guestList = guestbookService.exeAddList();
		System.out.println(guestList);
		return guestList;
	}

	//등록
	@PutMapping("/api/guestbook")
	public JsonResult add(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.add()");
		GuestbookVo gVo = guestbookService.exeAddandGuest(guestbookVo);
		if(gVo != null) {
			return JsonResult.success(gVo);
		} else {
			return JsonResult.fail("등록 실패");
		}
	}

	// 삭제
	@DeleteMapping("/api/guestbooks/{no}")
	public int delete(@PathVariable("no") int no, @RequestBody GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.delete()");
		int count = guestbookService.exeDeleteandGuest(guestbookVo);
		return count;
	}
}
