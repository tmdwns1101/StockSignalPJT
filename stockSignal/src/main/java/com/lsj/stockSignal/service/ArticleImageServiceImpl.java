package com.lsj.stockSignal.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsj.stockSignal.dao.ArticleImageDAO;
import com.lsj.stockSignal.dto.ArticleDTO;
import com.lsj.stockSignal.dto.ArticleImageDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;


@Service
@Log4j
@RequiredArgsConstructor
public class ArticleImageServiceImpl implements ArticleImageService {

	private final ArticleImageDAO articleImageDAO;
	
	@Transactional
	@Override
	public void createArticleImages(ArticleDTO article) {

		try {
			List<String> imageNames = this.extractImageNames(article.getContent());
			
			List<ArticleImageDTO> articleImages = new ArrayList<>();
			
			for(int i=0; i < imageNames.size(); i++) {
				String imageName = imageNames.get(i);
				ArticleImageDTO articleImage = ArticleImageDTO.builder()
						.imageName(imageName)
						.articleId(article.getId())
						.build();
				if(i == 0) articleImage.setThumbnail(true);
				
				articleImages.add(articleImage);
			}
			
			for(ArticleImageDTO articleImage: articleImages) {
				this.articleImageDAO.insertImages(articleImage);
			}
			
			
		} catch (URISyntaxException e) {
			
			throw new IllegalArgumentException("올바른 URL 형식이 아닙니다.");
		}

	}

	private List<String> extractImageNames(String html) throws URISyntaxException {
		List<String> imageNames = new ArrayList<>();
		Document doc = Jsoup.parse(html);
	
		Elements elements = doc.select("img");
		for (Element element : elements) {
			String imgUrlSrc = element.attr("src");
		
			String path = new URI(imgUrlSrc).getPath();
			String imageName = path.substring(path.lastIndexOf("/")+1);
			log.info(imageName);
			imageNames.add(imageName);
		}
		return imageNames;
	}

}
