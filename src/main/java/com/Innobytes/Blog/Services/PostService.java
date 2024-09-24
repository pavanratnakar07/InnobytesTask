package com.Innobytes.Blog.Services;

import com.Innobytes.Blog.Entity.Post;
import com.Innobytes.Blog.Entity.User;
import com.Innobytes.Blog.Repository.PostRepository;
import com.Innobytes.Blog.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
//    @Autowired
//    private final PostRepository postRepository;
//    private final UserRepository userRepository;
//
//    public Post createPost(Post post, String username) {
//        User author = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        post.setAuthor(author);
//        return postRepository.save(post);
//    }
//
//    public List<Post> getAllPosts() {
//        return postRepository.findAll();
//    }
//
//    public Post getPostById(Long id) {
//        return postRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
//    }
//
//    public Post updatePost(Long id, Post updatedPost, String username) {
//        Post post = getPostById(id);
//        if (!post.getAuthor().getUsername().equals(username)) {
//            throw new AccessDeniedException("You are not the author of this post");
//        }
//        post.setTitle(updatedPost.getTitle());
//        post.setContent(updatedPost.getContent());
//        return postRepository.save(post);
//    }
//
//    public void deletePost(Long id, String username) {
//        Post post = getPostById(id);
//        if (!post.getAuthor().getUsername().equals(username)) {
//            throw new AccessDeniedException("You are not the author of this post");
//        }
//        postRepository.delete(post);
//    }

    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post updatePost(Long id, Post updatedPost) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(updatedPost.getTitle());
            post.setContent(updatedPost.getContent());
            post.setUpdatedAt(LocalDateTime.now());
            return postRepository.save(post);
        }
        throw new EntityNotFoundException("Post not found with id: " + id);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
