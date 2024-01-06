package com.lwphk.pull_medium.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import com.lwphk.pull_medium.vo.Article;

@Component
public class SystemConfig {
	
	/**
	 * 是否需要魔法访问目标网站
	 */
	private Boolean needVpn = true;
	
	private String vpnHost;
	
	private int vpnPort;
	
	private List<Article> articles = new ArrayList<Article>();
	
	
	private Integer pullSize = 0;
	
	/**
	 * 1:socket   2:http 代理
	 */
	private int vpnType;

	public Boolean getNeedVpn() {
		return needVpn;
	}

	public void setNeedVpn(Boolean needVpn) {
		this.needVpn = needVpn;
	}

	public String getVpnHost() {
		return vpnHost;
	}

	public void setVpnHost(String vpnHost) {
		this.vpnHost = vpnHost;
	}

	public int getVpnPort() {
		return vpnPort;
	}

	public void setVpnPort(int vpnPort) {
		this.vpnPort = vpnPort;
	}

	public int getVpnType() {
		return vpnType;
	}

	public void setVpnType(int vpnType) {
		this.vpnType = vpnType;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Integer getPullSize() {
		return pullSize;
	}

	public void setPullSize(Integer pullSize) {
		this.pullSize = pullSize;
	}

	
	
	
}
