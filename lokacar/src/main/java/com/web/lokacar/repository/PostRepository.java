package com.web.lokacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.web.lokacar.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
     

}