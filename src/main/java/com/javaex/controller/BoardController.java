package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.BoardService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.BoardVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 게시판 리스트
	@GetMapping("/api/boards")
	public List<BoardVo> list() {
		System.out.println("BoardController.list()");
		List<BoardVo> boardList = boardService.exeList();
		return boardList;
	}

	// read
	@GetMapping("/api/board/{no}")
	public BoardVo readOne(@PathVariable(value = "no") int no) {
		System.out.println("BoardController.readOne()");
		BoardVo boardVo = boardService.exeSelectOne(no);
		return boardVo;
	}

	// write
	@PostMapping("/api/board")
	public JsonResult write(@RequestBody BoardVo boardVo, HttpServletRequest request) {
		System.out.println("BoardController.write()");
		
		//헤더에서 사용자  no 추출
		int no = JwtUtil.getNoFromHeader(request);

		if (no != -1) {
			 boardVo.setUserNo(no);
			 int count = boardService.exeInsert(boardVo);
			System.out.println(count);
			return JsonResult.success(count);
		} else {
			// 토큰이 없거나(로그인상태 아님) 변조된 경우
			return JsonResult.fail("fail");
		}
	}
	
	//modify
	@PutMapping("/api/board")
	public JsonResult modify(@RequestBody BoardVo boardVo, HttpServletRequest request) {
		System.out.println("BoardController.modify()");
		
		//헤더에서 사용자  no 추출
		int no = JwtUtil.getNoFromHeader(request);
		
		if(no == boardVo.getUserNo()) {
			 int count = boardService.exeModify(boardVo);
			 System.out.println(count);
			return JsonResult.success(count);
		} else {
			return JsonResult.fail("fail");
		}
	}
	
	
}
