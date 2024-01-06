package com.lwphk.pull_medium.component;

import java.awt.SystemColor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lwphk.pull_medium.config.SystemConfig;
import com.lwphk.pull_medium.util.PullDatUtil;

@Component
public class VPNCheck {

	@Autowired
	private SystemConfig config;
	
	@PostConstruct
	public void vpnCheck() {
		boolean need =  !PullDatUtil.testPing("",0,3);
		System.out.println("魔法测试结果:"+ need);
		config.setNeedVpn(need);
	}
}
