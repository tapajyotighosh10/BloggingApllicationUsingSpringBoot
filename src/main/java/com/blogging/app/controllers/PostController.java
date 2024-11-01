package com.blogging.app.controllers;

import com.blogging.app.entites.Post;
import com.blogging.app.payloads.PostDto;
import com.blogging.app.payloads.PostResponse;
import com.blogging.app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    // create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId, @PathVariable int categoryId){
        PostDto cp = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(cp, HttpStatus.CREATED);
    }

    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable int userId){
       List<PostDto> posts = this.postService.getPostsByUser(userId);
       return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    // get by category

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int categoryId){
        List<PostDto> posts = this.postService.getPostsByUser(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    // get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
                                                   @RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy,
                                                   @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir){
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    // get posts by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable int postId){
        PostDto Post = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(Post,HttpStatus.OK);
    }
}


