/**
  * Copyright 2024 bejson.com 
  */
package com.lwphk.pull_medium.vo;
import java.util.List;

/**
 * Auto-generated: 2024-01-06 15:52:55
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class RecommendedPostsFeed {

    private List<Items> items;
    private PagingInfo pagingInfo;
    private String __typename;
    public void setItems(List<Items> items) {
         this.items = items;
     }
     public List<Items> getItems() {
         return items;
     }

    public void setPagingInfo(PagingInfo pagingInfo) {
         this.pagingInfo = pagingInfo;
     }
     public PagingInfo getPagingInfo() {
         return pagingInfo;
     }

    public void set__typename(String __typename) {
         this.__typename = __typename;
     }
     public String get__typename() {
         return __typename;
     }

}