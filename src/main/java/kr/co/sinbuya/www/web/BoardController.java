package kr.co.sinbuya.www.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sinbuya.entity.Board;
import kr.co.sinbuya.repository.BoardRepository;
import kr.co.sinbuya.www.service.BoardService;
import kr.co.sinbuya.www.vo.BoardVO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Controller
@RequestMapping("") // URL을 컨트롤러 메서드와 매핑한다.

public class BoardController {
	
	
	@Autowired private BoardService boardService;
	@Autowired private BoardRepository boardRepository;
	
	
	
	@RequestMapping("/details") //글목록
	public String articleList( ModelMap map) {
		List<Board> boards = boardService.getArticeList();
		map.addAttribute("articleList", boards);
	
		
		return "/default/details";
	}
	
	@GetMapping("/details")
	public String index(ModelMap map, @PageableDefault(size = 5, sort = "boardId", direction = Sort.Direction.DESC) Pageable pageable) {
		
		map.addAttribute("board", new BoardVO());
		map.addAttribute("articleList", boardService.findAll(pageable));
		map.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
		map.addAttribute("next", pageable.next().getPageNumber());
		
		return "/default/details";
	}
	
	@GetMapping("/viewArticle/{boardId}") // 글 상세보기
	public String detail(@PathVariable("boardId") long boardId, ModelMap map) {
		
		Board board = boardService.findById(boardId); 		
		
		map.addAttribute("board", board);
		map.addAttribute("id", boardId);
		
		
		return "/default/viewArticle";
	}
	
	@PostMapping("/details") //글쓰기
	public String index(ModelMap map, @ModelAttribute("boardVO") BoardVO boardVO, BindingResult result) {
		
		Board entity = boardService.save(boardVO);
		
		
		if(entity != null) {
			map.addAttribute("msg", "저장되었습니다.");
			map.addAttribute("url", "/details/");
			map.addAttribute("board", boardVO);
			return "/shared/redirect_with_message";
		}else {
			map.addAttribute("board", boardVO);
			return "/default/details";
		}
	}
	
	@PostMapping("/update/{boardId}")	// 글 수정하기
	public String update(@PathVariable("boardId") long boardId, ModelMap map,BoardVO boardVO) {		

		Board board = boardService.findById(boardId);
	
		if(!board.getPassword().equals(boardVO.getPassword())) {
			map.addAttribute("msg", "비밀번호가 틀립니다.");
			map.addAttribute("url", "/viewArticle/" + board.getBoardId());
			return("/shared/redirect_with_message");
		}
		board = boardService.updateById(boardId,boardVO);
		map.addAttribute("msg", "수정되었습니다.");
		map.addAttribute("url", "/viewArticle/" + board.getBoardId());
		return "/shared/redirect_with_message";			
	}
	
	@GetMapping("/details/{boardId}") //삭제
	public String delete(@PathVariable("boardId") long boardId, BoardVO boardVO, ModelMap map) {
		
		Board board = boardService.findById(boardId);
		
		if(!board.getPassword().equals(boardVO.getPassword())) {
			map.addAttribute("msg", "비밀번호가 틀립니다.");
			map.addAttribute("url", "/viewArticle/" + board.getBoardId());
			return("/shared/redirect_with_message");
		}
		
		board = boardService.deleteById(boardId);
		map.addAttribute("msg", "삭제되었습니다.");
		map.addAttribute("url", "/details/");
		
		return ("/shared/redirect_with_message");
	}


	@GetMapping("/searchArticle") //검색
	public String Search(@RequestParam(value="keyword") String keyword, ModelMap map) {
		
		List<Board> articleList = boardService.searchArticle(keyword);
		
		map.addAttribute("articleList", articleList);
		
		return "default/details";
	}
	
/*	@GetMapping("/boardPaging")	//페이징
	public String boardPaging(ModelMap map, @PageableDefault(size = 5, sort = "boardId", direction = Sort.Direction.DESC) Pageable pageable){
		System.out.println("여기는 오냐?");
		
		Page<Board> articleList = boardService.findAll(pageable);
		
		//map.addAttribute("articleList", boardService.findAll(pageable));
		map.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
		map.addAttribute("next", pageable.next().getPageNumber());
		
		
		
		return "/default/details";
	}*/
	
	
}
	
