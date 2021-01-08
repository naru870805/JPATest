package kr.co.sinbuya.www.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javassist.compiler.ast.Keyword;
import kr.co.sinbuya.entity.Board;
import kr.co.sinbuya.www.vo.BoardVO;

public interface BoardService {
	
	Board findById(long boardId); //R//boardId로 조회를 하겠다.

	Board updateById(long boardId, BoardVO boardVO); //U//boardId로 업데이트(수정)하겠다.
	
	Board save(BoardVO boardVO); //C// boardVO로 저장하겠다.
	
	Board deleteById(long boardId); //D//boardId로 삭제하겠다.

	Page<Board> getArticeList(Pageable pageable); //boardVO에 담긴 모든 것들을 가져오겠다.

	Page<Board> searchArticle(String keyword, Pageable pageable); //board에 담긴 것 중에 keyword 이름으로 submit한 값을 찾겠다.

	Page<Board> findAll(Pageable pageable); // 페이징

	Boolean getListCheck(Pageable pageable); //마지막 페이지 비활성화




}