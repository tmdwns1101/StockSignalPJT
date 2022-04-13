package com.lsj.stockSignal.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;



@Transactional
@Service
@Log4j
public class UploadImageFileService extends UploadService {
	
	//허용하는 확장자 명 배열
	private final String[] supportExts = {"jpg","jpeg","png"};

	@Override
	protected String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}

	@Override
	protected void saveFile(MultipartFile file, File uploadFolder, List<String> uploadFiles) throws IOException {
	
		
		//파일 확장자 명 추출
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		log.info("확장자 : " + ext);
		
		//파일 확장자 검증
		//검증이 실패시, 예외 발생
		if(!this.validExt(ext)) {
			throw new IllegalArgumentException("허용되지 않는 확장자 입니다.");
		}
		
		UUID uuid = UUID.randomUUID();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		Date date = new Date();
		String dateStr = sdf.format(date);
		String imageName =  dateStr + "_" + uuid.toString() + "." + ext;
	
		
		File savedFile = new File(uploadFolder, imageName);
		
		if(checkImagetype(savedFile)) {
			
			file.transferTo(savedFile);
			uploadFiles.add(imageName);
		}

	}
	
	private boolean checkImagetype(File file) throws IOException {
		String contentType = Files.probeContentType(file.toPath());
		return contentType.startsWith("image");
	}
	
	private boolean validExt(String ext) {
		return Arrays.asList(this.supportExts).contains(ext);
	}
	

}
