package com.lsj.stockSignal.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.lsj.stockSignal.dto.ArticleDTO;
import com.lsj.stockSignal.dto.ArticleImageDTO;

public interface ArticleDAO {
	
	
	@Results({
		@Result(property = "id", column = "article_id"),
		@Result(property = "title", column = "title"),
		@Result(property = "writer", column = "writer"),
		@Result(property = "viewCount", column = "view_count"),
		@Result(property = "createdAt", column = "createdAt"),
		@Result(property = "thumbnail", column = "article_id", javaType = ArticleImageDTO.class, one = @One(select="com.lsj.stockSignal.dao.ArticleImageDAO.getThumbnailImage"))
	})
	@Select("SELECT article_id, title, writer, view_count, createdAt "
			+ "FROM article "
			+ "WHERE board_id = #{boardId} "
			+ "ORDER BY article_id DESC "
			+ "OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY")
	List<ArticleDTO> findArticles(@Param("boardId") int boardId, @Param("offset") int offset, @Param("limit") int limit);
	
	
	
	@Results({
		@Result(property = "id", column = "article_id"),
		@Result(property = "title", column = "title"),
		@Result(property = "writer", column = "writer"),
		@Result(property = "viewCount", column = "view_count"),
		@Result(property = "createdAt", column = "createdAt"),
		@Result(property = "content", column = "content")
	})
	@Select("SELECT article_id, title, writer, view_count, createdAt, content "
			+ "FROM article "
			+ "WHERE article_id = #{articleId}")
	Optional<ArticleDTO> findArticleById(@Param("articleId") int articleId);
	
	
	@Results({
		@Result(property = "id", column = "article_id"),
		@Result(property = "title", column = "title"),
		@Result(property = "writer", column = "writer"),
		@Result(property = "content", column = "content"),
		@Result(property = "boardId", column = "board_id")
	})
	@Select("SELECT article_id, title, writer, content, board_id "
			+ "FROM article "
			+ "WHERE article_id = #{id} AND password = #{password}")
	Optional<ArticleDTO> findArticleByIdAndPassword(ArticleDTO article);
	
	
	@Select("SELECT count(article_id) FROM article WHERE board_id = #{boardId}")
	int countTotalArticleByBoardId(@Param("boardId") int boardId);
	
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "article_id")
	@Insert("INSERT INTO article(title, writer, password, content, board_id) VALUES(#{title},#{writer},#{password},#{content},#{boardId})")
	int saveArticle(ArticleDTO articleDTO);
	
	@Update("UPDATE article SET title = #{title}, content = #{content} WHERE article_id = #{id}")
	void updateArticle(ArticleDTO articleDTO);
	
	
	@SelectKey(keyProperty = "viewCount", keyColumn = "view_count", before=false, resultType = Integer.class, statement = "SELECT view_count FROM article WHERE article_id = #{id}")
	@Update("UPDATE article SET view_count = view_count + 1 WHERE article_id = #{id}")
	void updateArticleViewCount(ArticleDTO articleDTO);

}
