package com.mongodb.controllers;

import com.mongodb.model.Post;
import com.mongodb.repository.PostRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class JobPostController {

  private final PostRepository repository;

  public JobPostController(PostRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/")
  public void redirect(HttpServletResponse response) throws IOException {
    response.sendRedirect("/swagger-ui.html");
  }

  @GetMapping("/posts")
  public List<Post> getAllJobPosts() {
    return repository.findAll();
  }
}
