/**
  * Copyright 2024 bejson.com 
  */
package com.lwphk.pull_medium.vo;

/**
 * Auto-generated: 2024-01-06 15:52:55
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Items {

    private String feedId;
    private int reason;
    private String moduleSourceEncoding;
    private Post post;
    private String __typename;
    public void setFeedId(String feedId) {
         this.feedId = feedId;
     }
     public String getFeedId() {
         return feedId;
     }

    public void setReason(int reason) {
         this.reason = reason;
     }
     public int getReason() {
         return reason;
     }

    public void setModuleSourceEncoding(String moduleSourceEncoding) {
         this.moduleSourceEncoding = moduleSourceEncoding;
     }
     public String getModuleSourceEncoding() {
         return moduleSourceEncoding;
     }

    public void setPost(Post post) {
         this.post = post;
     }
     public Post getPost() {
         return post;
     }

    public void set__typename(String __typename) {
         this.__typename = __typename;
     }
     public String get__typename() {
         return __typename;
     }

}