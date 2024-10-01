package sps.codeinterview.reto2.controller;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import sps.codeinterview.reto1.exception.ResourceNotFoundException;
import sps.codeinterview.reto2.model.Post;

@RestController
@RequestMapping("/api") //  http://127.0.0.1:8080/api/user/3
@Validated
public class PostsController {
    
    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts";

    private final RestTemplate restTemplate;

    @Autowired
    public PostsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //busca en el json por userId y ordena
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId) {
        Post[] allPosts = restTemplate.getForObject(API_URL, Post[].class);
        
        if (allPosts == null) {
            throw new ResourceNotFoundException("no se puede crear  la lista revisar la url: " + API_URL );
        }
        
        List<Post> filteredPosts = Arrays.stream(allPosts)
                                         .filter(post -> post.getUserId().equals(userId))
                                         .collect(Collectors.toList());
        
        if (filteredPosts.isEmpty()) {
            throw new ResourceNotFoundException("No hay post del id usuario: " + userId);
        }
        
        return ResponseEntity.ok(filteredPosts);
    }
}