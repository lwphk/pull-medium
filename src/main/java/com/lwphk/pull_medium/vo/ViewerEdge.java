/**
  * Copyright 2024 bejson.com 
  */
package com.lwphk.pull_medium.vo;

/**
 * Auto-generated: 2024-01-05 23:45:38
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ViewerEdge {

    private String id;
    private boolean isFollowing;
    private boolean isUser;
    private ViewerEdge viewerEdge;
    private RecommendedPostsFeed recommendedPostsFeed;
    private String __typename;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setIsFollowing(boolean isFollowing) {
         this.isFollowing = isFollowing;
     }
     public boolean getIsFollowing() {
         return isFollowing;
     }

    public void setIsUser(boolean isUser) {
         this.isUser = isUser;
     }
     public boolean getIsUser() {
         return isUser;
     }

    public void set__typename(String __typename) {
         this.__typename = __typename;
     }
     public String get__typename() {
         return __typename;
     }
	public ViewerEdge getViewerEdge() {
		return viewerEdge;
	}
	public void setViewerEdge(ViewerEdge viewerEdge) {
		this.viewerEdge = viewerEdge;
	}
	public RecommendedPostsFeed getRecommendedPostsFeed() {
		return recommendedPostsFeed;
	}
	public void setRecommendedPostsFeed(RecommendedPostsFeed recommendedPostsFeed) {
		this.recommendedPostsFeed = recommendedPostsFeed;
	}

     
     
}