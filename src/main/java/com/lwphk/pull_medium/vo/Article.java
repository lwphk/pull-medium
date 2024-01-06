package com.lwphk.pull_medium.vo;

public class Article {

	
	private String id;
	
	private String title;
	/**
	 * 点赞数
	 */
	private Integer clap;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getClap() {
		return clap;
	}
	public void setClap(Integer clap) {
		this.clap = clap;
	}
	
	
}
