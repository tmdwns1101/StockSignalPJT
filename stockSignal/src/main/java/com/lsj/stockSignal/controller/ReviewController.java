package com.lsj.stockSignal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lsj.stockSignal.dto.ReviewDTO;
import com.lsj.stockSignal.service.ReviewService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("api/reviews")
@Log4j
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody ReviewDTO review) {
		ReviewDTO result = this.reviewService.createReivew(review);
		result.setPassword(null);
		log.info("review" + result);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String,String>> deleteReview(@RequestBody ReviewDTO review) {
		
		this.reviewService.deleteReivew(review);
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "success");
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
