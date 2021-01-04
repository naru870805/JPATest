package kr.co.sinbuya.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import kr.co.sinbuya.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {

	List<Board> findByTitleContaining(String keyword);

	Page<Board> findAll(Pageable pageable);
	
	
}
