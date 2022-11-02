package com.gdu.app05.sevice;

import org.springframework.http.ResponseEntity;

public interface GalleryService {
	public ResponseEntity<byte[]> displayImage(String path, String filename);
}
