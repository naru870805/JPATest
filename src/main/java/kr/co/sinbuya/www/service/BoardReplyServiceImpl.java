package kr.co.sinbuya.www.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.sinbuya.entity.BoardReply;
import kr.co.sinbuya.repository.BoardReplyRepository;
import kr.co.sinbuya.www.vo.BoardVO;
import kr.co.sinbuya.www.vo.BoardReplyVO;


@Service("Board_replyService")
public class BoardReplyServiceImpl implements BoardReplyService{

	@Autowired private BoardReplyRepository boardReplyRepository;
	
	

	
	
	@Transactional
	@Override	// 리플 상세보기
	public BoardReply findById(long replyId) {		
		return boardReplyRepository.findOne(replyId);
	}
	
	@Transactional
	@Override	// 리플  수정
	public BoardReply updateById(long replyId, BoardReplyVO replyVO) {
		BoardReply board_reply = boardReplyRepository.findOne(replyId);
		
		if(board_reply != null) {
			board_reply.setReplyContent(replyVO.getReplyContent());
		}
		return boardReplyRepository.save(board_reply);
	}

	@Transactional
	@Override	// 리플 쓰기
	public BoardReply save(BoardReplyVO replyVO) {
		
		BoardReply reply = new BoardReply();
		
		reply.setBoardId(replyVO.getBoardId());
		reply.setUserName(replyVO.getUserName());
		reply.setPassword(replyVO.getPassword());
		reply.setCreatedAt(new Date());
		reply.setReplyContent(replyVO.getReplyContent());
		reply.setSecretType(true);
		reply.setEnabled(true);
		//reply.setDepthNum(replyVO.getDepthNum());
		
		System.out.println("reply에 담긴 것----->" + reply.toString());
		
		return boardReplyRepository.save(reply);
	}
	
	@Transactional
	@Override	// 리플 삭제
	public BoardReply delete(long replyId) {
		
		boardReplyRepository.delete(replyId);
		
		return boardReplyRepository.findOne(replyId);
	}
	
	@Transactional
	@Override	//리플 목록 보기
	public List<BoardReply> getReplyList(){
		List<BoardReply> replyList = (List<BoardReply>) boardReplyRepository.findAll();
		
		return replyList;
	}
	
	
}
