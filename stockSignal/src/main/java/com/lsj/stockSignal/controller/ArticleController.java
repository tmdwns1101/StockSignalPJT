package com.lsj.stockSignal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lsj.stockSignal.dto.ArticleDTO;
import com.lsj.stockSignal.dto.BoardDTO;
import com.lsj.stockSignal.dto.ReviewDTO;
import com.lsj.stockSignal.service.ArticleService;
import com.lsj.stockSignal.service.BoardService;
import com.lsj.stockSignal.service.ReviewService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("article")
@Log4j
public class ArticleController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ReviewService reviewService;

	@GetMapping("/{id}")
	public String article(@PathVariable int id, @RequestParam int boardId, Model model) {
		ArticleDTO article = this.articleService.getArticle(id);	
		List<BoardDTO> boards = this.boardService.getBoards();
		
		List<ReviewDTO> reviews = this.reviewService.getReivews(id);
		
		model.addAttribute("boards", boards);
		model.addAttribute("boardId", boardId);
		model.addAttribute("article", article);
		model.addAttribute("reviews", reviews);
		return "article";
	}
	
	
	@GetMapping("write")
	public String articleWrite(@RequestParam int boardId, Model model) {
		
		List<BoardDTO> boards = this.boardService.getBoards();
		
		model.addAttribute("boards", boards);
		model.addAttribute("boardId", boardId);
		
		return "articleCreateForm";
	}
	
	@GetMapping("edit/{id}")
	public String articleEdit(@PathVariable int id, @RequestParam int boardId, Model model) {
		List<BoardDTO> boards = this.boardService.getBoards();
		
		
		ArticleDTO article = this.articleService.getArticle(id);
		
		model.addAttribute("boards", boards);
		model.addAttribute("boardId", boardId);
		model.addAttribute("article", article);
		
		return "articleEditForm";
		
	}
	
	@PostMapping(value = "write", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String, String>> createArticle(@RequestBody @Valid ArticleDTO article) {
		
		
		log.info(article);
		
		this.articleService.createArticle(article);
		
		String redirectURL = "/board?boardId="+article.getBoardId();
		
		Map<String, String> result = new HashMap<>();
		result.put("redirectURL", redirectURL);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	

	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> editArticle(@PathVariable int id, @RequestBody @Valid ArticleDTO article) {
	
		article.setId(id);
		
		log.info(article);
		
		this.articleService.updateArticle(article);
		
		String redirectURL = "/article/" + article.getId() + "?boardId=" + article.getBoardId();
		
		Map<String, String> result = new HashMap<>();
		result.put("update","success");
		result.put("redirectURL", redirectURL);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
