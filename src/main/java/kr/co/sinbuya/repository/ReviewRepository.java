package kr.co.sinbuya.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import kr.co.sinbuya.entity.ProductArticle;
import kr.co.sinbuya.entity.Review;

public interface ReviewRepository extends Repository<Review, Long> {

	Page<Review> findByArticle(ProductArticle productArticle, Pageable pageable);

}
