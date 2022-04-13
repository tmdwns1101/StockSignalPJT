package com.lsj.stockSignal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.lsj.stockSignal.dto.ArticleImageDTO;

public interface ArticleImageDAO {

	
	@Results({
		@Result(property = "id", column = "image_id"),
		@Result(property = "imageName", column = "image_name"),
		@Result(property = "isThumbnail", column = "isthumbnail")
	})
	@Select("SELECT image_id, image_name, isthumbnail "
			+ "FROM article_image "
			+ "WHERE article_id = #{articleId} AND "
			+ "isthumbnail = 1")
	ArticleImageDTO getThumbnailImage(@Param("articleId") int articleId);
	

	@Insert({
	        "INSERT INTO article_image(image_name, isthumbnail, article_id) VALUES",
	        "(#{imageName},#{isThumbnail},#{articleId})",
	})
	void insertImages(ArticleImageDTO image);
}
