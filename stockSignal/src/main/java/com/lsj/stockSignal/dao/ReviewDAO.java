package com.lsj.stockSignal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.lsj.stockSignal.dto.ReviewDTO;

public interface ReviewDAO {

	
	@Results({
		@Result(property = "id", column = "review_id"),
		@Result(property = "writer", column = "writer"),
		@Result(property = "content", column = "content"),
		@Result(property = "createdAt", column = "createdAt"),
	})
	@Select("SELECT review_id, writer, content, createdAt "
			+ "FROM review "
			+ "WHERE article_id = #{articleId} "
			+ "ORDER BY review_id")
	List<ReviewDTO> findReviews(@Param("articleId") int articleId);
	
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "review_id")
	@Insert("INSERT INTO review(writer, password, content, article_id) VALUES(#{writer}, #{password}, #{content}, #{articleId})")
	int createReview(ReviewDTO review);
}
