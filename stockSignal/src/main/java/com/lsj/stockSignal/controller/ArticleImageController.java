package com.lsj.stockSignal.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lsj.stockSignal.service.UploadService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("image")
@Log4j
public class ArticleImageController {

	@Value("${uploadPath}")
	private String uploadPath;

	@Autowired
	private UploadService uploadService;

	@GetMapping("display/{imageName}.{ext}")
	public ResponseEntity<byte[]> getImage(@PathVariable String imageName, @PathVariable String ext) {
		log.info("imageName : " + imageName);
		File file;
		ResponseEntity<byte[]> result;
		try {
			String[] splitImageNames = imageName.split("_");
			String year = splitImageNames[0];
			String month = splitImageNames[1];
			String day = splitImageNames[2];

			String path = Paths.get(uploadPath, year, month, day).toString();
			log.info("path : " + path);
			file = new File(path, imageName + "." + ext);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("파일을 읽어오는데 실패하였습니다.");
		} catch (Exception e) {
			throw new RuntimeException("올바르지 않은 파일 이름 형식입니다.");
		}

		return result;
	}

	@PostMapping(value = "upload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<String>> uploadImage(MultipartFile[] file) throws IOException {

		log.info(file.length);
		List<String> images = this.uploadService.saveFiles(file);

		return new ResponseEntity<>(images, HttpStatus.CREATED);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public ResponseEntity<String> unSupportFileExtension(IllegalArgumentException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
