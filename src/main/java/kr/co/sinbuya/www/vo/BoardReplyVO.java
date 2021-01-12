package kr.co.sinbuya.www.vo;

import java.util.Date;

public class BoardReplyVO {
	
	private Long boardId;		// 게시글 고유 번호
	private Integer depthNum;		// 댓글번호?
	private String userName;	// 작성자 이름
	private String password;	// 비밀번호
	private String replyContent;// 댓글 내용
	private boolean secretType;	// 공개유무
	
	
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	public boolean isSecretType() {
		return secretType;
	}
	public void setSecretType(boolean secretType) {
		this.secretType = secretType;
	}
	public int getDepthNum() {
		return depthNum;
	}
	public void setDepthNum(int depthNum) {
		this.depthNum = depthNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "BoardReplyVO [boardId=" + boardId + ", depthNum=" + depthNum + ", userName=" + userName + ", password="
				+ password + ", replyContent=" + replyContent + ", secretType=" + secretType + "]";
	}

	
}
