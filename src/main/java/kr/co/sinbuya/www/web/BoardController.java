package kr.co.sinbuya.www.web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sinbuya.entity.Board;
import kr.co.sinbuya.entity.BoardReply;
import kr.co.sinbuya.repository.BoardReplyRepository;
import kr.co.sinbuya.repository.BoardRepository;
import kr.co.sinbuya.www.service.BoardReplyService;
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
	@Autowired private BoardReplyService boardReplyService;
	@Autowired private BoardReplyRepository BoardReplyRepository;
	
	
	
	@RequestMapping("/details") //글목록
	public String articleList( ModelMap map, String keyword, Pageable pageable) {
		Page<Board> boards = boardService.getArticeList(pageable);
		List<BoardReply> reply = boardReplyService.getReplyList();
		map.addAttribute("articleList", boards);
		map.addAttribute("replyList", reply);
	
		
		return "/default/details";
	}
	
	@GetMapping("/details")	//기본페이지 + 페이징 + 검색
	public String index(ModelMap map, @PageableDefault(size = 5, sort = "boardId", direction = Sort.Direction.DESC) Pageable pageable, 
									  @RequestParam(required = false) String keyword) {
		
		map.addAttribute("board", new BoardVO());
		if(keyword == null) {
			map.addAttribute("articleList", boardService.findAll(pageable));
		}else {
			map.addAttribute("articleList", boardService.searchArticle(keyword, pageable));
		}
		map.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
		map.addAttribute("next", pageable.next().getPageNumber());
		map.addAttribute("limit", 5);
		map.addAttribute("page", pageable.getPageNumber());
		map.addAttribute("keyword", keyword);
		map.addAttribute("check", boardService.getListCheck(pageable));
		
		return "/default/details";
	}
	
/*	@GetMapping("/searchArticle") //검색			위의 기본 페이지에 흡수됨
	public String Search(ModelMap map, HttpServletRequest request,
			@PageableDefault(size = 5, sort = "boardId", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(required = false) String keyword) {

		map.addAttribute("articleList", boardService.searchArticle(keyword, pageable));
		map.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
		map.addAttribute("next", pageable.next().getPageNumber());
		map.addAttribute("limit", 5);
		map.addAttribute("page", pageable.getPageNumber());
		
		
		return "default/details";
	}*/
	
	
	@RequestMapping("/viewArticle/{boardId}") // 글 상세보기
	public String detail(@PathVariable("boardId") long boardId, 
			 			 ModelMap map, HttpServletRequest request) {
		
		String pNum =  request.getParameter("boardNum");
		System.out.println("글번호는 무엇일까----->" + pNum);
		
		map.addAttribute("pNum", pNum);
		
		Board board = boardService.findById(boardId);
		List<BoardReply> boardReply = boardReplyService.getReplyList();	//boardReply에 get한 리플리스트를 담겠다.
		map.addAttribute("board", board);
		map.addAttribute("id", boardId);
		map.addAttribute("replyList", boardReply);	//boardReply를 replyList라고 명하겠다. 
		
		
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
			map.addAttribute("msg", "비밀번호를 다시 입력하세요.");
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

}
	
