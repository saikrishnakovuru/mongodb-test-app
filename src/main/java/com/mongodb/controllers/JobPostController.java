package com.mongodb.controllers;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.model.Post;
import com.mongodb.repository.PostRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class JobPostController {

  private final PostRepository postRepository;
  private final MongoClient client;
  private final MongoConverter converter;

  public JobPostController(PostRepository repository, MongoClient client, MongoConverter converter) {
    this.postRepository = repository;
    this.client = client;
    this.converter = converter;
  }

  @GetMapping("/")
  public void redirect(HttpServletResponse response) throws IOException {
    response.sendRedirect("/swagger-ui.html");
  }

  @GetMapping("/posts")
  public List<Post> getAllJobPosts() {
    return postRepository.findAll();
  }

  @PostMapping("/savePost")
  public Post saveJobPost(@RequestBody Post post) {
    return postRepository.save(post);
  }

  @GetMapping("/posts/{text}")
  public List<Post> getSelectedPosts(@PathVariable String text) {
//    return postRepository.findAll().stream()
//            .filter(post -> post.getDesc().equalsIgnoreCase(text)
//                    || post.getProfile().equalsIgnoreCase(text)
//                    || Arrays.stream(post.getTechs()).anyMatch(val -> val.equalsIgnoreCase(text)))
//            .sorted((post1, post2) -> Integer.compare(post2.getExp(), post1.getExp()))
//            .limit(5)
//            .collect(Collectors.toList());
    final List<Post> posts = new ArrayList<>();

    MongoDatabase database = client.getDatabase("TestMongoDB");
    MongoCollection<Document> collection = database.getCollection("JobPost");

    AggregateIterable<Document> result = collection.aggregate(
            Arrays.asList(
                    new Document("$search",
                            new Document("text",
                                    new Document("query", text)
                                            .append("path", Arrays.asList("techs", "desc", "profile")))),
                    new Document("$sort", new Document("exp", -1L)),
                    new Document("$limit", 5L))
    );

    result.forEach(doc -> posts.add(converter.read(Post.class, doc)));

    return posts;
  }

}
