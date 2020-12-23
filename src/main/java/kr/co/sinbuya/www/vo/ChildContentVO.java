package kr.co.sinbuya.www.vo;

import java.util.List;

import kr.co.sinbuya.entity.PAEHallDetails;
import kr.co.sinbuya.entity.ProductArticle;

public class ChildContentVO {

	private String title;
	private ProductArticle article;
	private List<PAEHallDetails> details;
	private String content;
	private int point;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ProductArticle getArticle() {
		return article;
	}

	public void setArticle(ProductArticle article) {
		this.article = article;
	}

	public List<PAEHallDetails> getDetails() {
		return details;
	}

	public void setDetails(List<PAEHallDetails> details) {
		this.details = details;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
