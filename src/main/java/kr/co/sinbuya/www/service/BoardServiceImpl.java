package kr.co.sinbuya.www.service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.sinbuya.entity.Board;
import kr.co.sinbuya.repository.BoardRepository;
import kr.co.sinbuya.www.vo.BoardVO;

@Service("Service")
public class BoardServiceImpl implements BoardService {
	
	
	
	
	@Autowired private BoardRepository boardRepository; //의존성 주입

	@Transactional // 해당 메소드를 실행해 DB에 commit 하겠다.
	@Override
	public Board findById(long boardId) {
		return boardRepository.findOne(boardId);
	}

	@Transactional
	@Override
	public void updateById(long boardId) {
		Board board = boardRepository.findOne(boardId);
		if(board != null) {
			board.setContent("열심히 잘좀 해보세요!!");
			boardRepository.save(board);
		}
	}
	
	@Transactional
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
	
	@Transactional
	@Override
	public void deleteById(long boardId) {
		
		boardRepository.delete(boardId);

	}

	@Transactional
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

/*	@Override
	public List<BoardVO> searchArticle(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	

}
