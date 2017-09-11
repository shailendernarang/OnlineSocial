package com.ss.SocialistB.dao;

import java.util.List;

import com.ss.SocialistB.model.BlogComment;

public interface BlogCommentDAO {

	public boolean addBlogComment(BlogComment blogComment);
	public boolean editBlogComment(Integer blogCommentID);
	public boolean deleteBlogComment(Integer blogCommentID);
	public BlogComment getBlogComment(Integer blogCommentID);
	public List<BlogComment> getAllBlogComments();
	public boolean approveBlogComment(BlogComment blogComment);
}