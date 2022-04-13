package com.lsj.stockSignal.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.lsj.stockSignal.dto.BoardDTO;

public interface BoardDAO {

	@Results({
		@Result(property = "id", column = "board_id"),
		@Result(property = "boardName", column = "board_name")
	})
	@Select("SELECT * FROM board")
	List<BoardDTO> findBoards();
	
	@Results({
		@Result(property = "id", column = "board_id"),
		@Result(property = "boardName", column = "board_name")
	})
	@Select("SELECT * FROM board WHERE board_id = #{boardId}")
	Optional<BoardDTO> findBoardById(@Param("boardId") int boardId);
}
