package com.Innobytes.Blog.Controller;

import com.Innobytes.Blog.Entity.Comment;
import com.Innobytes.Blog.Services.CommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
//
//    private final CommentService commentService;
//
//    @PostMapping("/{postId}")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestBody Comment comment, Authentication authentication) {
//        String username = authentication.getName();
//        Comment createdComment = commentService.createComment(postId, comment, username);
//        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
//    }
//
//    @GetMapping
//    public List<Comment> getComments(@RequestParam Long postId) {
//        return commentService.getCommentsByPostId(postId);
//    }
//
//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('USER')")
//    public Comment updateComment(@PathVariable Long id, @RequestBody Comment comment, Authentication authentication) {
//        String username = authentication.getName();
//        return commentService.updateComment(id, comment, username);
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<Void> deleteComment(@PathVariable Long id, Authentication authentication) {
//        String username = authentication.getName();
//        commentService.deleteComment(id, username);
//        return ResponseEntity.noContent().build();
//    }



    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.createComment(comment));
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsByPostId(@RequestParam Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.updateComment(id, comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
