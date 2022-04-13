package com.lsj.stockSignal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lsj.stockSignal.dto.ArticlePagination;
import com.lsj.stockSignal.dto.BoardDTO;
import com.lsj.stockSignal.service.ArticleService;
import com.lsj.stockSignal.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("board")
@Log4j
public class BoardController {

	@Autowired
	private BoardService boardSerivce;
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping
	public void board(@RequestParam(defaultValue = "1") int boardId, @RequestParam(defaultValue = "1") int page, Model model) {
		
		List<BoardDTO> boards = this.boardSerivce.getBoards();
		
		ArticlePagination articlePagination = this.articleService.getArticles(boardId, page);
		
		log.info(articlePagination.isFirst());
		log.info(articlePagination.isLast());
		model.addAttribute("boards", boards);
		model.addAttribute("articlePagination", articlePagination);
		model.addAttribute("isFirst", articlePagination.isFirst());
		model.addAttribute("isLast", articlePagination.isLast());
		model.addAttribute("boardId", boardId);
	}
}
