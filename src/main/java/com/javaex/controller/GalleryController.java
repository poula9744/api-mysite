package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.GalleryVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class GalleryController {

	@Autowired
	private GalleryService galleryService;

	// 갤러리 메인
	@GetMapping("/api/gallery")
	public List<GalleryVo> list() {
		System.out.println("GalleryController.list()");
		List<GalleryVo> galleryList = galleryService.exeList();
		return galleryList;
	}

	// 갤러리 이미지 등록
	@PostMapping("/api/gallery")
	public JsonResult upload(@RequestParam MultipartFile file, @ModelAttribute GalleryVo galleryVo,
			HttpServletRequest request) {
		System.out.println("GalleryController.upload()");
		System.out.println("fileName: " + file.getOriginalFilename());

		// 헤더에서 사용자 no 추출
		int no = JwtUtil.getNoFromHeader(request);

		if (no != -1) {
			galleryVo.setUserNo(no);
			System.out.println(galleryVo);
			String saveName = galleryService.exeUpload(file, galleryVo);
			return JsonResult.success(saveName);
		} else {
			// 토큰이 없거나(로그인상태 아님) 변조된 경우
			return JsonResult.fail("fail");
		}
	}

	// 갤러리 이미지 삭제
	@DeleteMapping("/api/gallery/{no}")
	public JsonResult delete(@PathVariable("no") int no, HttpServletRequest request) {
		System.out.println("GalleryController.delete()");
		
		System.out.println(no);
		// 헤더에서 사용자 no 추출
		int hNo = JwtUtil.getNoFromHeader(request);
		System.out.println(hNo);
		if (hNo != -1) {
			int count = galleryService.exeDelete(no);
			System.out.println(count);
			return JsonResult.success(count);
		} else {
			// 토큰이 없거나(로그인상태 아님) 변조된 경우
			return JsonResult.fail("fail");
		}
	}
}
