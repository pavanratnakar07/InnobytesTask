package com.Innobytes.Blog.Services;

import com.Innobytes.Blog.Entity.Comment;
import com.Innobytes.Blog.Entity.Post;
import com.Innobytes.Blog.Entity.User;
import com.Innobytes.Blog.Repository.CommentRepository;
import com.Innobytes.Blog.Repository.PostRepository;
import com.Innobytes.Blog.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
//    @Autowired
//    private final CommentRepository commentRepository;
//    private final PostRepository postRepository;
//    private final UserRepository userRepository;
//
//    public Comment createComment(Long postId, Comment comment, String username) {
//        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
//        User author = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        comment.setPost(post);
//        comment.setAuthor(author);
//        return commentRepository.save(comment);
//    }
//
//    public List<Comment> getCommentsByPostId(Long postId) {
//        return commentRepository.findByPostId(postId);
//    }
//
//    public Comment updateComment(Long id, Comment updatedComment, String username) {
//        Comment comment = commentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
//        if (!comment.getAuthor().getUsername().equals(username)) {
//            throw new AccessDeniedException("You are not the author of this comment");
//        }
//        comment.setContent(updatedComment.getContent());
//        return commentRepository.save(comment);
//    }
//
//    public void deleteComment(Long id, String username) {
//        Comment comment = commentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
//        if (!comment.getAuthor().getUsername().equals(username)) {
//            throw new AccessDeniedException("You are not the author of this comment");
//        }
//        commentRepository.delete(comment);
//    }

    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(Comment comment) {
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment updateComment(Long id, Comment updatedComment) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setContent(updatedComment.getContent());
            return commentRepository.save(comment);
        }
        throw new EntityNotFoundException("Comment not found with id: " + id);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
