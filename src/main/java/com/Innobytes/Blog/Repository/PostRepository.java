package com.Innobytes.Blog.Repository;

import com.Innobytes.Blog.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
