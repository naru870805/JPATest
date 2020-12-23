package kr.co.sinbuya.www.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ContactsVO {

	private String name;
	private String gender;
	private String tel1;
	private String tel2;
	private String tel3;
	private String marryAt;
	private String marriable;
	private String content;
	private String suffix;
	private String email;
	private String request;
	private String visitDate;
	private String visitTime;
	private List<String> hall;

	private String hopeDate;

	private String mktCookie;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}

	public String getPhone() {
		return tel1 + "-" + tel2 + "-" + tel3;
	}

	public String getMarryAt() {
		return marryAt;
	}

	public void setMarryAt(String marryAt) {
		this.marryAt = marryAt;
	}

	public Date getMarryAtByDate() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(this.marryAt);
		} catch (Exception e) {
			return null;
		}
	}

	public String getMarriable() {
		return marriable;
	}

	public void setMarriable(String marriable) {
		this.marriable = marriable;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public List<String> getHall() {
		return hall;
	}

	public void setHall(List<String> hall) {
		this.hall = hall;
	}

	public String getHopeDate() {
		return hopeDate;
	}

	public void setHopeDate(String hopeDate) {
		this.hopeDate = hopeDate;
	}

	public String getMktCookie() {
		return mktCookie;
	}

	public void setMktCookie(String mktCookie) {
		this.mktCookie = mktCookie;
	}

}
