package kr.co.sinbuya.www.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	
	
	
	@GetMapping("/details") //글목록
	public String articleList( ModelMap map) {
		List<Board> boards = boardService.getArticeList();
		map.addAttribute("articleList", boards);
		
		System.out.println();
		
		return "/default/details";
	}
	
	
	
	@RequestMapping("/details")
	public String index(ModelMap map) {
		
		map.addAttribute("board", new BoardVO());
		
		return "/default/details";
	}
	
	@PostMapping("/details") //글 저장
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
	
	@GetMapping("/details/{boardId}") //삭제
	public String delete(@PathVariable("boardId") long boardId) {
		boardService.deleteById(boardId);
		
			
		
		return "/default/details";
	}

	@GetMapping("/viewArticle/{boardId}") // 글 상세보기
	public String detail(@PathVariable("boardId") long boardId, ModelMap map) {
		
		Board board = boardService.findById(boardId); 		
		
		map.addAttribute("board", board);
		System.out.println("board에 담긴 것----->" + board);
		map.addAttribute("id", boardId);
		
		
		return "/default/viewArticle";
	}


/*	@GetMapping("/details") //검색
	public String Search(@RequestParam(value="keyword") String keyword, ModelMap map) {
		
		List<BoardVO> boardVOList = boardService.searchArticle(keyword);
		
		map.addAttribute("boardVOList", boardVOList);
		
		return "default/details";
	}*/
}
	
