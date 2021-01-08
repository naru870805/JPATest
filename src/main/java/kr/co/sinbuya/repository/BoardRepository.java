package kr.co.sinbuya.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import kr.co.sinbuya.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {

	Page<Board> findByTitleContaining(String keyword, Pageable pabeable);	//검색

	Page<Board> findAll(Pageable pageable);	// 전체조회

	//Page<Board> findByTitle(String keyword, Pageable pageable);
	
	
}
