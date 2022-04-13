package com.lsj.stockSignal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lsj.stockSignal.dao.BoardDAO;
import com.lsj.stockSignal.dto.BoardDTO;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardDAO boardDAO;
	
	@Override
	public List<BoardDTO> getBoards() {
		return this.boardDAO.findBoards();
	}
	
	

}
