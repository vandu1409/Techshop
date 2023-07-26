package com.vandu.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vandu.service.FileSystemStorageService;

@RestController
public class ImageController {

	@Autowired
	private FileSystemStorageService fileSystemStorageService;

	@GetMapping("phonestore/images/{filename}")
	public ResponseEntity<Resource> getImage(@PathVariable("filename") String filename) {
//		System.err.println(filename + "hinh");
		System.err.println(filename);
		if (filename == null || filename.isBlank()||filename.equals("undefined")) {
		 return ResponseEntity.ok(null);
		}

		Resource resource = fileSystemStorageService.loadAsResource(filename);
//		System.err.println("đsaa");
		  MediaType mediaType = MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM);

	        return ResponseEntity.ok()
	                .contentType(mediaType)
	                .body(resource);
	}

}
