package com.blog.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.repository.CommentJpaRepository;
import com.blog.vo.Comment;

@Service
public class CommentService {
    
    @Autowired
    CommentJpaRepository commentJpaRepository;

    public boolean saveComment(Comment comment){
        Comment result = commentJpaRepository.save(comment);
        boolean isSuccess = true;

        if(result == null){
            isSuccess = false;
        }

        return isSuccess;
    }

    public List<Comment> getCommentList(Long postId){
        List<Comment> commentList = commentJpaRepository.findAllByPostIdOrderByRegDateDesc(postId);
        return commentList;
    }

    public Comment getComment(Long id){
        Comment comment = commentJpaRepository.findOneById(id);
        return comment;
    }

    public boolean deleteComment(Long id){
        Comment deleteComment = commentJpaRepository.findOneById(id);
        boolean isSuccess = true;

        if(deleteComment == null){
            isSuccess = false;
        }

        commentJpaRepository.delete(deleteComment);

        return isSuccess;
    }

    public List<Comment> searchCommentsByPostIdAndQuery(Long postId, String query){
        List<Comment> comments = commentJpaRepository.findAllByPostIdAndCommentContainingOrderByRegDateDesc(postId, query);
        return comments;
    }
}
