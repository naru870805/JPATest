package kr.co.sinbuya.www.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import kr.co.sinbuya.entity.OtherLog;
import kr.co.sinbuya.entity.ProductArticle;
import kr.co.sinbuya.entity.ProductOption;

public interface ProductArticleService {


	List<ProductArticle> findArticlesByCategory(String term,long key);

	List<ProductArticle> findArticlesByCategory(long categoryId);

	List<ProductArticle> findArticlesByReview(Long categoryId,List<Long> ids);

	Long findPriceByArticleId(Long articleId,String mode);

	Page<ProductArticle> findArticlesByCategoryId(long categoryId, int page , int limit);

	List<ProductOption> findArticlesByOption(long articleId);

	OtherLog createLog(long id, String string, HttpServletRequest request);

	ProductArticle findArticleById(long id);

	List<ProductArticle> findrecommendBycategory(Long id, Long categoryId);
}
