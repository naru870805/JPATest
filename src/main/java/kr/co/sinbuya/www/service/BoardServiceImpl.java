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
import org.springframework.ui.ModelMap;

import javassist.compiler.ast.Keyword;
import kr.co.sinbuya.entity.Board;
import kr.co.sinbuya.repository.BoardRepository;
import kr.co.sinbuya.www.vo.BoardVO;

@Service("Service")
public class BoardServiceImpl implements BoardService {
	
	

	
	@Autowired private BoardRepository boardRepository; //의존성 주입
	private static final int BLOCK_PAGE_NUM_COUNT = 5;	// 페이지 번호 5번까지 출력되고 나머지는 prev,next
	private static final int PAGE_POST_COUNT = 5;	// 한 페이지에 5개 씩 보여주겠다.
	
	@Transactional // 글 상세 보기
	@Override
	public Board findById(long boardId) {	// 자동생성된 find쿼리를 boardId로 찾겠다.
		return boardRepository.findOne(boardId);	// boardId 에  해당하는 1개의 정보를 찾겠다.
	}


	@Transactional	//수정
	@Override
	public Board updateById(long boardId, BoardVO vo) {	// boardId, boardVO에 담긴 정보에 update 쿼리를 사용하겠다.
		Board board = boardRepository.findOne(boardId); // find한 boardId를 board에 담겠다. 
		if(board != null) {	//	board가 널이 아니라면
			board.setContent(vo.getContent());	//	vo에 담긴 content를 board에 set 하겠다.
		}
		return boardRepository.save(board);	// 자동 CRUD 된 
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
	public Page<Board> getArticeList(Pageable pageable) {
		Page<Board> boards = (Page<Board>) boardRepository.findAll(pageable);
	
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
	public Page<Board> searchArticle(String keyword, Pageable pageable) {
		System.out.println(keyword);
		Page<Board> boards = boardRepository.findByTitleContaining(keyword, pageable);
		
		return boards;
	}
	
	@Transactional	//페이징
	@Override
	public Page<Board> findAll(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	@Transactional	//마지막 페이지 비활성화
	@Override
	public Boolean getListCheck(Pageable pageable) {
		Page<Board> saved = getArticeList(pageable);
		Boolean check = saved.hasNext();
		return check;
	}
	
}
