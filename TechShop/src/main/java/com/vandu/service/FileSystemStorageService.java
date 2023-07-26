package com.vandu.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileSystemStorageService {

	void init();

	void delete(String filename) throws IOException;

	Resource loadAsResource(String filename);

	Path load(String filename);

	void saveFile(MultipartFile multipartFile, String storageFilename);

	String getStorageFileName(MultipartFile file, String text);

}
