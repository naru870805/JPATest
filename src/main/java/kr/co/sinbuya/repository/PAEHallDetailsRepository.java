package kr.co.sinbuya.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import kr.co.sinbuya.entity.PAEHallDetails;
import kr.co.sinbuya.entity.ProductArticle;

public interface PAEHallDetailsRepository extends Repository<PAEHallDetails, Long> {

	List<PAEHallDetails> findByArticle(ProductArticle article);
	
}
