package kr.co.sinbuya.www.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sinbuya.entity.Board;
import kr.co.sinbuya.entity.BoardReply;
import kr.co.sinbuya.repository.BoardRepository;
import kr.co.sinbuya.repository.BoardReplyRepository;
import kr.co.sinbuya.www.service.BoardService;
import kr.co.sinbuya.www.service.BoardReplyService;
import kr.co.sinbuya.www.vo.BoardReplyVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Controller
@RequestMapping("") // URL을 컨트롤러 메서드와 매핑한다.

public class BoardReplyController {

	@Autowired private BoardReplyService replyService;
	@Autowired private BoardReplyRepository replyRepository;
	
	
	@RequestMapping(value = "/viewArticle", method = RequestMethod.GET)	// 기본
	public String index(ModelMap map) {
		map.addAttribute("board", new BoardReplyVO());
		return "/default/viewArticle/{boardId}/{boardNum}";	
	}
	
	
	@PostMapping("/viewArticle")		// 리플 쓰기
	public String index(BoardReplyVO replyVO, 
						HttpServletRequest request, BindingResult result, ModelMap map) {
		
		BoardReply entity = replyService.save(replyVO);
		map.addAttribute(entity);
		
		String pNum = request.getParameter("boardNum");
		
		map.addAttribute("pNum", pNum);

		
		if(!entity.equals(null)) {
			map.addAttribute("url", "/viewArticle/" + entity.getBoardId() + "?boardNum=" + pNum);
			return ("/shared/redirect_with_message");
		}	
		return "default/viewArticle";
	}
	
	
	@RequestMapping(value = "replyList/{boardId}", method = RequestMethod.GET)	//리플 목록
	public String replyList(ModelMap map) {
		
		List<BoardReply> reply = replyService.getReplyList();
		map.addAttribute("replyList", reply);
			
		return "/default/viewArticle";
	}
	
	
	@PostMapping("/updateReply/{replyId}")		//리플 수정
	public String updateReply(@PathVariable("replyId") long replyId, ModelMap map, BoardReplyVO replyVO, HttpServletRequest request) {
		
		System.out.println("여기는 옵니까");
		
		BoardReply reply = replyService.findById(replyId);
		String pNum = request.getParameter("boardNum");
		
		
		if(!reply.getPassword().equals(replyVO.getPassword())) {
			map.addAttribute("msg", "비밀번호를 다시 입력하세요.");
			map.addAttribute("url", "/viewArticle/" + reply.getBoardId() + "?boardNum=" + pNum);
			return("/shared/redirect_with_message");
		}else {

			reply = replyService.updateById(replyId, replyVO);
			map.addAttribute("msg", "수정되었습니다.");
			map.addAttribute("url", "/viewArticle/" + reply.getBoardId() + "?boardNum=" + pNum);
			return "/shared/redirect_with_message";
		}
	}
	
	
	@PostMapping("/delete/{replyId}")	//삭제
	public String delete(@PathVariable("replyId") long replyId, ModelMap map, BoardReplyVO replyVO,HttpServletRequest request) {
		
		
		
		BoardReply reply = replyService.findById(replyId);

		String pNum = request.getParameter("boardNum");
		
		if(!reply.getPassword().equals(replyVO.getPassword())) {
			map.addAttribute("msg", "비밀번호를 다시 입력하세요.");
			map.addAttribute("url", "/viewArticle/" + reply.getBoardId() + "?boardNum=" + pNum);
			return "/shared/redirect_with_message";
		}else {
			Long boardId = reply.getBoardId(); 
			reply = replyService.delete(replyId);			
			map.addAttribute("msg", "삭제되었습니다.");
			map.addAttribute("url", "/viewArticle/" + boardId + "?boardNum=" + pNum);
		}return  "/shared/redirect_with_message";
	}
	
	
	
}
