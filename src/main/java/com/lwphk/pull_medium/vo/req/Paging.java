package com.lwphk.pull_medium.vo.req;

public class Paging {

	private String from;
	
	private String to;
	
	private String limit;
	
	private String source = "";

	

	public String getFrom() {
		return from;
	}



	public void setFrom(String from) {
		this.from = from;
	}



	public String getTo() {
		return to;
	}



	public void setTo(String to) {
		this.to = to;
	}



	public String getLimit() {
		return limit;
	}



	public void setLimit(String limit) {
		this.limit = limit;
	}



	public String getSource() {
		return source;
	}



	public void setSource(String source) {
		this.source = source;
	}



	public Paging(String from, String to, String limit, String source) {
		super();
		this.from = from;
		this.to = to;
		this.limit = limit;
		this.source = source;
	}



	public Paging() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
