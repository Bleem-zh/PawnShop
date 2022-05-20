package com.pawnshop.po;

public class product {
	private long propertyId;
	private long cateid;
	private String title;
	private String descriprion;
	private long num;
	private float price;
	private String cover;
	private String pics;
	private  int createtime;
	public long getpropertyId() {
		return propertyId;
	}
	public void setpropertyId(long propertyId) {
		this.propertyId = propertyId;
	}
	public long getCateid() {
		return cateid;
	}
	public void setCateid(long cateid) {
		this.cateid = cateid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescriprion() {
		return descriprion;
	}
	public void setDescriprion(String descriprion) {
		this.descriprion = descriprion;
	}
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	public int getCreatetime() {
		return createtime;
	}
	public void setCreatetime(int createtime) {
		this.createtime = createtime;
	}
	public product(long propertyId, long cateid, String title,
			String descriprion, long num, float price, String cover,
			String pics, int createtime) {
		super();
		this.propertyId = propertyId;
		this.cateid = cateid;
		this.title = title;
		this.descriprion = descriprion;
		this.num = num;
		this.price = price;
		this.cover = cover;
		this.pics = pics;
		this.createtime = createtime;
	}
}
