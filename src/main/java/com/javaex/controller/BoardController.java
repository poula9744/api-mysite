package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@RestController
public class BoardController {
	
	@Autowired 
	private BoardService boardService;
	
	//게시판 리스트 
	@GetMapping("/api/boards")
	public List<BoardVo> list(){
		System.out.println("BoardController.list()");
		List<BoardVo> boardList = boardService.exeList();
		return boardList;
	}
	
	//read
	@GetMapping("/api/board/{no}")
	public BoardVo readOne(@PathVariable(value="no") int no) {
		System.out.println("BoardController.readOne()");
		BoardVo boardVo = boardService.exeSelectOne(no);
		return boardVo;
	}
}
