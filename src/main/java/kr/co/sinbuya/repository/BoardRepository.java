package kr.co.sinbuya.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import kr.co.sinbuya.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {	//기본 CRUD 자동 사용

	Page<Board> findByTitleContaining(String keyword, Pageable pabeable); //검색

	Page<Board> findAll(Pageable pageable);	// 페이징 포함 전체조회

	
	
}
