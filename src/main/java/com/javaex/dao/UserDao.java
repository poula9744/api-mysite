package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public UserVo userSelectByIdPw(UserVo userVo) {
		System.out.println("UserDao.userSelectByIdPw()");
		UserVo authUser = sqlSession.selectOne("user.selectByIdPw", userVo);
		return authUser;
	}

	// 조회no(회원정보수정 폼)
	public UserVo userSelectOneByNo(int no) {
		System.out.println("UserDao.userSelectOneByNo()");

		UserVo userVo = sqlSession.selectOne("user.selectOneByNo", no);
		return userVo;
	}

	// 회원 정보 수정
	public int userUpdate(UserVo userVo) {
		System.out.println("UserDao.userModify()");

		int count = sqlSession.update("user.update", userVo);
		System.out.println(count);

		return count;
	}
	
	//회원 가입
	public int userJoin(UserVo userVo) {
		System.out.println("UserDao.userJoin()");
		int count = sqlSession.insert("user.join", userVo);
		return count;
	}

}
