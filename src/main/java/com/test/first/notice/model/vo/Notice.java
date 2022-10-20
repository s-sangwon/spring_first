package com.test.first.notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable{

	private static final long serialVersionUID = 1365978153616705231L;
	
	private int noticeno;
	private String noticetitle;
	private Date noticedate;
	private String noticewriter;
	private String noticecontent;
	private String original_filepath;
	private String rename_filepath;
	public Notice() {
		super();
	}
	public Notice(int noticeno, String noticetitle, Date noticedate, String noticewriter, String noticecontent,
			String original_filepath, String rename_filepath) {
		super();
		this.noticeno = noticeno;
		this.noticetitle = noticetitle;
		this.noticedate = noticedate;
		this.noticewriter = noticewriter;
		this.noticecontent = noticecontent;
		this.original_filepath = original_filepath;
		this.rename_filepath = rename_filepath;
	}
	public int getNoticeno() {
		return noticeno;
	}
	public void setNoticeno(int noticeno) {
		this.noticeno = noticeno;
	}
	public String getNoticetitle() {
		return noticetitle;
	}
	public void setNoticetitle(String noticetitle) {
		this.noticetitle = noticetitle;
	}
	public Date getNoticedate() {
		return noticedate;
	}
	public void setNoticedate(Date noticedate) {
		this.noticedate = noticedate;
	}
	public String getNoticewriter() {
		return noticewriter;
	}
	public void setNoticewriter(String noticewriter) {
		this.noticewriter = noticewriter;
	}
	public String getNoticecontent() {
		return noticecontent;
	}
	public void setNoticecontent(String noticecontent) {
		this.noticecontent = noticecontent;
	}
	public String getOriginal_filepath() {
		return original_filepath;
	}
	public void setOriginal_filepath(String original_filepath) {
		this.original_filepath = original_filepath;
	}
	public String getRename_filepath() {
		return rename_filepath;
	}
	public void setRename_filepath(String rename_filepath) {
		this.rename_filepath = rename_filepath;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Notice [noticeno=" + noticeno + ", noticetitle=" + noticetitle + ", noticedate=" + noticedate
				+ ", noticewriter=" + noticewriter + ", noticecontent=" + noticecontent + ", original_filepath="
				+ original_filepath + ", rename_filepath=" + rename_filepath + "]";
	}
	
	
}
