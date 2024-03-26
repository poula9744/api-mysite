package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> addList(){
		System.out.println("GuestbookDao.addList()");
		List<GuestbookVo> guestList = sqlSession.selectList("guestbook.select");
		return guestList;
	}
}
