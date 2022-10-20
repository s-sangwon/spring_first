package com.test.first.common;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;


//@Component("searchDate") //클래스 자동 등록 : DI 사용 가능
public class SearchDate implements Serializable {
	private static final long serialVersionUID = 5880625008368618729L;
	
	private Date begin;
	private Date end;
	public SearchDate(Date begin, Date end) {
		super();
		this.begin = begin;
		this.end = end;
	}
	public SearchDate() {
		super();
	}
	@Override
	public String toString() {
		return "SearchDate [begin=" + begin + ", end=" + end + "]";
	}
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
