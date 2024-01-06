package com.lwphk.pull_medium.vo.req;

import java.util.HashMap;
import java.util.Map;

public class VariablesVo {
	
	public static final String tagslugval = "software-engineering";

	private String postId;
	
	private Map<String, String> postMeteringOptions = new HashMap<String, String>();
	
	
	private String tagSlug;
	
	private Paging paging;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public Map<String, String> getPostMeteringOptions() {
		return postMeteringOptions;
	}

	public void setPostMeteringOptions(Map<String, String> postMeteringOptions) {
		this.postMeteringOptions = postMeteringOptions;
	}

	public VariablesVo(String postId) {
		super();
		this.postId = postId;
	
	}
	
	public VariablesVo() {
	
	}

	public String getTagSlug() {
		return tagSlug;
	}

	public void setTagSlug(String tagSlug) {
		this.tagSlug = tagSlug;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	
	
}
