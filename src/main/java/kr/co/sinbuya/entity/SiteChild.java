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

import org.hibernate.annotations.Where;

@SuppressWarnings("serial")
@Entity
@Table(name = "site_child")
@Where(clause = "`enabled` = 1")
public class SiteChild implements Serializable {

	private long id;
	private ProductArticle article;

	private Staff hallAccount;

	private Area area;
	
	private String host;
	private String title;
	private String content;
	private String metaData;
	private String galleries;
	private String url;

	private boolean official;
	private String skin;

	private String subTitle;

	private String homeContent;

	private Long logo;
	private long bgImg;
	private long subLogo;
	private String metaTitle;
	private String metaDesc;
	private Date createdAt;
	private boolean enabled;
	
	private int status;

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
	@JoinColumn(name = "article_id")
	public ProductArticle getArticle() {
		return article;
	}

	public void setArticle(ProductArticle article) {
		this.article = article;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Column(name = "host")
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hall_account_id")
	public Staff getHallAccount() {
		return hallAccount;
	}

	public void setHallAccount(Staff hallAccount) {
		this.hallAccount = hallAccount;
	}	

	@Column(name = "title")
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "metaData")
	public String getMetaData() {
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	@Column(name = "galleries")
	public String getGalleries() {
		return galleries;
	}

	public void setGalleries(String galleries) {
		this.galleries = galleries;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "official")
	public boolean isOfficial() {
		return official;
	}

	public void setOfficial(boolean official) {
		this.official = official;
	}

	@Column(name = "skin")
	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	@Column(name = "child_logo")
	public Long getLogo() {
		return logo;
	}

	public void setLogo(Long logo) {
		this.logo = logo;
	}

	@Column(name = "bg_img")
	public long getBgImg() {
		return bgImg;
	}

	public void setBgImg(long bgImg) {
		this.bgImg = bgImg;
	}

	@Column(name = "home_content")
	public String getHomeContent() {
		return homeContent;
	}

	public void setHomeContent(String homeContent) {
		this.homeContent = homeContent;
	}

	@Column(name = "sub_title")
	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	@Column(name = "sub_logo")
	public long getSubLogo() {
		return subLogo;
	}

	public void setSubLogo(long subLogo) {
		this.subLogo = subLogo;
	}

	@Column(name = "meta_title")
	public String getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	@Column(name = "meta_desc")
	public String getMetaDesc() {
		return metaDesc;
	}

	public void setMetaDesc(String metaDesc) {
		this.metaDesc = metaDesc;
	}

	@Column(name = "enabled")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name = "status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
