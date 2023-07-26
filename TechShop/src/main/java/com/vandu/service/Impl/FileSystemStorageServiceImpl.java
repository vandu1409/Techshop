package com.vandu.service.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;


import com.vandu.config.StorageProperties;
import com.vandu.exception.StorageException;
import com.vandu.service.FileSystemStorageService;

@Service
public class FileSystemStorageServiceImpl implements FileSystemStorageService {

	public final Path rootLocation;

	public FileSystemStorageServiceImpl(StorageProperties storageProperties) {
		this.rootLocation = Paths.get(storageProperties.getLocation());
	}

	// tạo tên cho file
	@Override
	public String getStorageFileName(MultipartFile file, String text) {
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());

		return "vandustore" + text + "." + ext;
	}

	@Override
	public void saveFile(MultipartFile multipartFile, String storageFilename) {
		try {

			if (multipartFile.isEmpty()) {
				throw new StorageException("Không tìm thấy file");
			}

			Path destinaltionFile = this.rootLocation.resolve(storageFilename).normalize().toAbsolutePath();

			System.err.println(destinaltionFile.getParent() + " rl" + this.rootLocation.toAbsolutePath());

			if (!destinaltionFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new StorageException("Không thể lưu file");
			}

			try (InputStream inputStream = multipartFile.getInputStream();) {
				Files.copy(inputStream, destinaltionFile, StandardCopyOption.REPLACE_EXISTING);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public Path load(String filename) {
		return this.rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			
			if(filename==null) {
				return null;
			}
			
			Path file = load(filename);

			Resource resource = new UrlResource(file.toUri());
//			System.err.println(file.toUri()+"dsjfdsfj");

			if (resource.exists() || resource.isReadable()) {
				return resource;
			}

			throw new StorageException("Đọc file thất bại");

		} catch (Exception e) {
			e.printStackTrace();
			throw new StorageException("Đọc file thất bại");
		}
	}

	@Override
	public void delete(String filename) throws IOException {
		Path destinaltionFile = this.rootLocation.resolve(filename).normalize().toAbsolutePath();

		Files.delete(destinaltionFile);
	}

	@Override
	public void init() {
		try {

			Files.createDirectories(rootLocation);

			System.err.println(rootLocation.toAbsolutePath());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
