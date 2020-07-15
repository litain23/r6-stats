package me.r6_search.web.board;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
public class PostController {

    @GetMapping("/{type}")
    public Object getPostList(@PathVariable String type,
                              @RequestParam(defaultValue = "1", required = false) int page) {
        return "post list";
    }

    @GetMapping("/post/{id}")
    public Object getPost(@PathVariable int id) {
        return "post";
    }

    @PostMapping("/post")
    public Object makePost() {
        return "";
    }

    @PutMapping("/post/{id}")
    public Object modifyPost(@PathVariable int id) {
        return "";
    }

    @DeleteMapping("/post/{id}")
    public Object deletePost(@PathVariable int id) {
        return "";
    }

    @PostMapping("/post/recommend/{id}")
    public Object recommendPost(@PathVariable int id) {
        return "";
    }

    @DeleteMapping("/post/recommend/{id}")
    public Object cancelRecommendPost(@PathVariable int id) {
        return "";
    }
}
