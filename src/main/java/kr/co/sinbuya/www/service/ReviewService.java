package kr.co.sinbuya.www.service;

import org.springframework.data.domain.Page;

import kr.co.sinbuya.entity.Review;

public interface ReviewService {
	
	Page<Review> findReviewsByArticle(long id, int page, int limit);

}
