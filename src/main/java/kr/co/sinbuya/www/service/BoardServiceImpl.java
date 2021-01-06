package kr.co.sinbuya.www.service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.sinbuya.entity.Board;
import kr.co.sinbuya.repository.BoardRepository;
import kr.co.sinbuya.www.vo.BoardVO;

@Service("Service")
public class BoardServiceImpl implements BoardService {
	
	

	
	@Autowired private BoardRepository boardRepository; //의존성 주입
	private static final int BLOCK_PAGE_NUM_COUNT = 5;
	private static final int PAGE_POST_COUNT = 5;
	
	@Transactional // 글 상세 보기
	@Override
	public Board findById(long boardId) {
		return boardRepository.findOne(boardId);
	}


	@Transactional	//수정
	@Override
	public Board updateById(long boardId,BoardVO vo) {
		Board board = boardRepository.findOne(boardId);
		if(board != null) {
			board.setContent(vo.getContent());
		}
		return boardRepository.save(board);
	}
	
	@Transactional	//글쓰기
	@Override
	public Board save(BoardVO boardVO) {
	
		Board result = new Board();
		
		result.setUserName(boardVO.getUserName());
		result.setPassword(boardVO.getPassword());
		result.setTitle(boardVO.getTitle());
		result.setContent(boardVO.getContent());
		result.setSecretType(true);
		result.setEnabled(true);
		result.setCreatedAt(new Date());
		
		List<Long> attaches = new ArrayList<Long>();
		
		return boardRepository.save(result);
	}
	
	@Transactional	//글 삭제
	@Override
	public Board deleteById(long boardId) {
		
		boardRepository.delete(boardId);
		return boardRepository.findOne(boardId);

	}

	@Transactional	//글 목록 보기
	@Override
	public List<Board> getArticeList() {
		List<Board> boards = (List<Board>) boardRepository.findAll();
	
		
/*		List<BoardVO> boardVOList = new ArrayList<>();		//이 코드가 위의 한 줄로 처리가 된다.
		
		for(Board board : boards) {
			BoardVO boardVO = BoardVO.builder()
					.boardId(board.getBoardId())
					.userName(board.getUserName())
					.title(board.getTitle())
					.createdAt(board.getCreatedAt())
					.build();
			boardVOList.add(boardVO);
			
			System.out.println("boardVOList-->" + boardVOList);
		}*/
		
		
		return boards;
	}


	@Transactional	//검색
	@Override
	public List<Board> searchArticle(String keyword) {
		List<Board> boards = (List<Board>) boardRepository.findByTitleContaining(keyword);		
		
		return boards;
	}
	
	@Transactional	//페이징
	@Override
	public Page<Board> findAll(Pageable pageable){
		
		return boardRepository.findAll(pageable);
	}
	
}
