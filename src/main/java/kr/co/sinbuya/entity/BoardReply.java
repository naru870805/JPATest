package kr.co.sinbuya.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@SuppressWarnings("serial") //노란색 밑줄 없앰
@NoArgsConstructor
@Getter //Getter 자동 생성
@Setter
@Entity // 테이블과 링크될 클래스 
@Table(name = "test_board_reply") //테이블 이름

public class BoardReply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //기본키를 자동 생성하겠다 (DB에 맡김!)
	@Column(name = "replyId", unique = true, nullable = false) //name의 컬럼을 사용하겠다.
	private long replyId;
	
	
	@JoinColumn(name = "boardId")	//board 테이블의 boardId으로 맵핑한다.
	private long boardId;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "replyContent")
	private String replyContent;
	
	@Column(name = "createdAt")
	private Date createdAt;
	
	
	@Column(name = "secretType")
	private boolean secretType;
	
	@Column(name = "depthNum")
	private int depthNum;
	
	
	@Column(name = "enabled")
	private boolean enabled;
	
	
}
