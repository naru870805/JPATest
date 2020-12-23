package kr.co.sinbuya.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import kr.co.sinbuya.entity.ProductArticle;
import kr.co.sinbuya.entity.ProductOption;

public interface ProductOptionRepository extends Repository<ProductOption, Long> {

	List<ProductOption> findByArticle(ProductArticle productArticle);

	@Query("select o from ProductOption o WHERE o.article = :article AND o.title like :title")
	Page<ProductOption> findByArticleAndLikeTitle(@Param("article") ProductArticle article, @Param("title") String title, Pageable request);

}
