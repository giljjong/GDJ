package com.gdu.app05.sevice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

public class GalleryServiceImpl implements GalleryService {

	@Override
	public ResponseEntity<byte[]> displayImage(String path, String filename) {
		
		File file = new File(path, filename);
		
		ResponseEntity<byte[]> entity = null;
		
		try {
			
			String contentType = Files.probeContentType(file.toPath());
			
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", contentType);
			
			entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return entity;
	}

}
