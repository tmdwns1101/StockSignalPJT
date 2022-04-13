package com.lsj.stockSignal.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;


public abstract class UploadService {

	@Value("${uploadPath}")
	private String uploadPath;

	public List<String> saveFiles(MultipartFile[] files) throws IOException {

		List<String> uploadedFiles = new ArrayList<>();

		File uploadFolder = new File(uploadPath, this.getFolder());

		if (!uploadFolder.exists()) {
			uploadFolder.mkdirs();
		}

		for (MultipartFile file : files) {
			saveFile(file, uploadFolder, uploadedFiles);
		}
		return uploadedFiles;

	}


	protected abstract String getFolder();

	protected abstract void saveFile(MultipartFile file, File uploadFolder, List<String> uploadFiles) throws IOException;

}
