package kr.co.sinbuya.entity;

import java.io.Serializable;

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
@Table(name = "hall_details")
public class PAEHallDetails implements Serializable {

	private long id;
	private ProductArticle article;
	private String hallName;
	private String week;
	private String type;
	private String invite;
	private String foodMenu;
	private String interval;
	private String flower;
	private String tPrice;
	private String foodPrice;
	private String drinkPrice;
	private String addPrice;

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

	@Column(name = "week")
	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}
	@Column(name = "`type`")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "invite")
	public String getInvite() {
		return invite;
	}

	public void setInvite(String invite) {
		this.invite = invite;
	}

	@Column(name = "`interval`")
	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}
	@Column(name = "flower")
	public String getFlower() {
		return flower;
	}

	public void setFlower(String flower) {
		this.flower = flower;
	}
	@Column(name = "t_price")
	public String gettPrice() {
		return tPrice;
	}

	public void settPrice(String tPrice) {
		this.tPrice = tPrice;
	}
	@Column(name = "food_price")
	public String getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(String foodPrice) {
		this.foodPrice = foodPrice;
	}

	@Column(name = "drink_price")
	public String getDrinkPrice() {
		return drinkPrice;
	}

	public void setDrinkPrice(String drinkPrice) {
		this.drinkPrice = drinkPrice;
	}
	@Column(name = "add_price")
	public String getAddPrice() {
		return addPrice;
	}

	public void setAddPrice(String addPrice) {
		this.addPrice = addPrice;
	}
	@Column(name = "food_menu")
	public String getFoodMenu() {
		return foodMenu;
	}

	public void setFoodMenu(String foodMenu) {
		this.foodMenu = foodMenu;
	}
	@Column(name = "hall_name")
	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}

	
}