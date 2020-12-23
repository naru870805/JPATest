package kr.co.sinbuya.www.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.co.sinbuya.entity.ProductArticle;
import kr.co.sinbuya.entity.Review;
import kr.co.sinbuya.repository.ReviewRepository;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired private ReviewRepository reviewRepository;

	@Override
	public Page<Review> findReviewsByArticle(long id, int page, int limit) {
		PageRequest request = new PageRequest(page, limit, Sort.Direction.DESC, "id");
		Page<Review> messages = reviewRepository.findByArticle(new ProductArticle(id), request);
		return messages;
	}

}
