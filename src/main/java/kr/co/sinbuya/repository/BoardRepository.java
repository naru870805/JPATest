package kr.co.sinbuya.repository;
import org.springframework.data.repository.CrudRepository;

import kr.co.sinbuya.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {


}
