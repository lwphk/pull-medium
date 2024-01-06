/**
  * Copyright 2024 bejson.com 
  */
package com.lwphk.pull_medium.vo;
import java.util.List;

/**
 * Auto-generated: 2024-01-06 17:31:12
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class FyResult {

    private String from;
    private List<TransResult> trans_result;
    private String to;
    public void setFrom(String from) {
         this.from = from;
     }
     public String getFrom() {
         return from;
     }


    public List<TransResult> getTrans_result() {
		return trans_result;
	}
	public void setTrans_result(List<TransResult> trans_result) {
		this.trans_result = trans_result;
	}
	public void setTo(String to) {
         this.to = to;
     }
     public String getTo() {
         return to;
     }

}