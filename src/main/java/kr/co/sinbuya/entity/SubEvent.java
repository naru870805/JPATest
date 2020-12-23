package kr.co.sinbuya.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "sub_event")
public class SubEvent implements Serializable {

	private long id;
	private Staff account;
	private String hopeDate;
	private Date marryAt;
	private String username;
	private String phoneNumber;
	private Date createdAt;
	private boolean attendance;
	private int exUserWhether;

	private String marryArea;
	private String referer;
	private String refererCookie;
	private String refererUri;
	
	private Long guestId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = true)
	public Staff getAccount() {
		return account;
	}

	public void setAccount(Staff account) {
		this.account = account;
	}

	@Column(name = "hope_date")
	public String getHopeDate() {
		return hopeDate;
	}

	public void setHopeDate(String hopeDate) {
		this.hopeDate = hopeDate;
	}

	@Column(name = "marry_at")
	public Date getMarryAt() {
		return marryAt;
	}

	public void setMarryAt(Date marryAt) {
		this.marryAt = marryAt;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "attendance")
	public boolean isAttendance() {
		return attendance;
	}

	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}

	@Column(name = "ex_user_whether")
	public int getExUserWhether() {
		return exUserWhether;
	}

	public void setExUserWhether(int exUserWhether) {
		this.exUserWhether = exUserWhether;
	}

	@Column(name = "marry_area")
	public String getMarryArea() {
		return marryArea;
	}

	public void setMarryArea(String marryArea) {
		this.marryArea = marryArea;
	}

	@Column(name = "referer")
	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}
	
	@Column(name = "referer_cookie")
	public String getRefererCookie() {
		return refererCookie;
	}

	public void setRefererCookie(String refererCookie) {
		this.refererCookie = refererCookie;
	}

	@Column(name = "referer_uri")
	public String getRefererUri() {
		return refererUri;
	}

	public void setRefererUri(String refererUri) {
		this.refererUri = refererUri;
	}

	@Column(name = "guest_id")
	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}
}
