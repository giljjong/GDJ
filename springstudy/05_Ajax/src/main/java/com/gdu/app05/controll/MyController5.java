package com.gdu.app05.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app05.sevice.GalleryService;

@Controller
public class MyController5 {
	
	@GetMapping("gallery")	// 반환이 void인 경우 mapping을 뷰(JSP)로 인식한다
	public void gallery() {
		
	}
	
	@Autowired
	private GalleryService galleryService;
	
	@GetMapping("image/display")
	public ResponseEntity<byte[]> displayImage(@RequestParam String path, @RequestParam String filename) {
		return galleryService.displayImage(path, filename);
	}
}
