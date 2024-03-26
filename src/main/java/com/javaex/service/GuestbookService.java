package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;

	public List<GuestbookVo> exeAddList() {
		System.out.println("GuestbookService.exeAddList()");
		List<GuestbookVo> guestList = guestbookDao.addList();
		return guestList;
	}

	// ajax등록
	public GuestbookVo exeAddandGuest(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeAddandGuest()");

		// 저장
		guestbookDao.insertSelectKey(guestbookVo);

		// 한 명 데이터 가져오기
		GuestbookVo gVo = guestbookDao.guestbookSelectOne(guestbookVo.getNo());

		return gVo;
	}

	// ajax 삭제
	public int exeDeleteandGuest(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeDeleteandGuest()");
		System.out.println(guestbookVo);
		// 삭제
		int count = guestbookDao.guestbookDelete(guestbookVo);

		return count;
	}
}
