package kr.co.sinbuya.www.service;

import java.util.List;

import kr.co.sinbuya.entity.BoardReply;
import kr.co.sinbuya.www.vo.BoardReplyVO;

public interface BoardReplyService {
	
	
	BoardReply findById(long replyId); //리플 조회

	BoardReply updateById(long replyId, BoardReplyVO replyVO);	//리플 생성
	
	BoardReply save(BoardReplyVO replyVO); //리플 저장
	
	BoardReply delete(long replyId); //리플 삭제
	
	List<BoardReply> getReplyList();
	

}
