package kr.co.sinbuya.www.vo;

import java.util.Date;

import lombok.Builder;

public class BoardVO {

	private long boardId;
	private String userName;
	private String password;
	private String title;
	private String content;
	private boolean enabled; 
	private boolean secretType; 
	private Date createdAt;
	
	
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public long getBoardId() {
		return boardId;
	}
	public void setBoardId(long boardId) {
		this.boardId = boardId;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isSecretType() {
		return secretType;
	}
	public void setSecretType(boolean secretType) {
		this.secretType = secretType;
	}
	
}
