package com.test.first.bill.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Bill implements Serializable {
	private static final long serialVersionUID = 874907408487708508L;
    private int id;
    private String userid;
    private Timestamp bill_timestamp;
    private int bill_price;
    private String bill_content;
    private String bill_category;
    private String bill_cardinfo;
    private String bill_storeinfo_name;
    private String bill_storeinfo_biznum;
	public Bill() {
		super();
	}
	public Bill(int id, String userid, Timestamp bill_timestamp, int bill_price, String bill_content,
			String bill_category, String bill_cardinfo, String bill_storeinfo_name, String bill_storeinfo_biznum) {
		super();
		this.id = id;
		this.userid = userid;
		this.bill_timestamp = bill_timestamp;
		this.bill_price = bill_price;
		this.bill_content = bill_content;
		this.bill_category = bill_category;
		this.bill_cardinfo = bill_cardinfo;
		this.bill_storeinfo_name = bill_storeinfo_name;
		this.bill_storeinfo_biznum = bill_storeinfo_biznum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Timestamp getBill_timestamp() {
		return bill_timestamp;
	}
	public void setBill_timestamp(Timestamp bill_timestamp) {
		this.bill_timestamp = bill_timestamp;
	}
	public int getBill_price() {
		return bill_price;
	}
	public void setBill_price(int bill_price) {
		this.bill_price = bill_price;
	}
	public String getBill_content() {
		return bill_content;
	}
	public void setBill_content(String bill_content) {
		this.bill_content = bill_content;
	}
	public String getBill_category() {
		return bill_category;
	}
	public void setBill_category(String bill_category) {
		this.bill_category = bill_category;
	}
	public String getBill_cardinfo() {
		return bill_cardinfo;
	}
	public void setBill_cardinfo(String bill_cardinfo) {
		this.bill_cardinfo = bill_cardinfo;
	}
	public String getBill_storeinfo_name() {
		return bill_storeinfo_name;
	}
	public void setBill_storeinfo_name(String bill_storeinfo_name) {
		this.bill_storeinfo_name = bill_storeinfo_name;
	}
	public String getBill_storeinfo_biznum() {
		return bill_storeinfo_biznum;
	}
	public void setBill_storeinfo_biznum(String bill_storeinfo_biznum) {
		this.bill_storeinfo_biznum = bill_storeinfo_biznum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", userid=" + userid + ", bill_timestamp=" + bill_timestamp + ", bill_price="
				+ bill_price + ", bill_content=" + bill_content + ", bill_category=" + bill_category
				+ ", bill_cardinfo=" + bill_cardinfo + ", bill_storeinfo_name=" + bill_storeinfo_name
				+ ", bill_storeinfo_biznum=" + bill_storeinfo_biznum + "]";
	}
    
    
}
