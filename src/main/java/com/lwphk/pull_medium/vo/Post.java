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
public class Post {

    private String id;
    private String title;
    private Creator creator;
    private Collection collection;
    private String mediumUrl;
    private int clapCount;
    private String __typename;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setCreator(Creator creator) {
         this.creator = creator;
     }
     public Creator getCreator() {
         return creator;
     }

    public void setCollection(Collection collection) {
         this.collection = collection;
     }
     public Collection getCollection() {
         return collection;
     }

    public void setMediumUrl(String mediumUrl) {
         this.mediumUrl = mediumUrl;
     }
     public String getMediumUrl() {
         return mediumUrl;
     }

    public void setClapCount(int clapCount) {
         this.clapCount = clapCount;
     }
     public int getClapCount() {
         return clapCount;
     }

    public void set__typename(String __typename) {
         this.__typename = __typename;
     }
     public String get__typename() {
         return __typename;
     }

}