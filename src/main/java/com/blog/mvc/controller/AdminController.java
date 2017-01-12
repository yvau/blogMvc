package com.blog.mvc.controller;

import com.blog.mvc.model.Post;
import com.blog.mvc.pageWrapper.PageWrapper;
import com.blog.mvc.service.AppService;
import com.blog.mvc.service.PostService;
import com.blog.mvc.validators.PostValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * Created by Yvau on 1/4/2017.
 */
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(value = "/admin")
public class AdminController {

    Instant timestamp = Instant.now();

    private int fullPageSize = 5;

    @Autowired
    AppService appService;

    @Autowired
    PostService postService;

    @Autowired
    PostValidator postValidator;

    @GetMapping("")
    public String homePage(Model model, @RequestParam(value = "page", required = false) Integer page, HttpServletRequest request) {
        PageWrapper<Post> posts = new PageWrapper<>(postService.findAll(appService.pageable(page, fullPageSize)), "/admin" + appService.removeQueryStringParameter(request.getQueryString()));
        model.addAttribute("iterations", posts);
        return "admin/home";
    }

    @GetMapping("/new")
    public String newPost(Model model) {

        model.addAttribute("newPost", new Post());
        return "admin/new";
    }

    @PostMapping("/new")
    public String createPost(@ModelAttribute("newPost") Post post, BindingResult bindingResult) {
        postValidator.validate(post, bindingResult);
        if (bindingResult.hasErrors()) {

            return "admin/new";
        } else {
            if (post.getSlug().isEmpty()){
                post.setSlug(appService.makeSlug(post.getName()));
            }
            post.setCreated(Timestamp.from(timestamp));
            postService.save(post);
        }
        return "redirect:/admin/new";
    }

    @GetMapping("/{id}/edit")
    public String editPost(Model model, @PathVariable("id") int id) {

        model.addAttribute("editPost", postService.find(id));
        return "admin/edit";
    }

    @PutMapping("/{id}/edit")
    public String updatePost(@ModelAttribute("editPost") Post post, BindingResult bindingResult) {
        postValidator.validate(post, bindingResult);
        if (bindingResult.hasErrors()) {

            return "admin/edit";
        } else {
            if (post.getSlug().isEmpty()){
                post.setSlug(appService.makeSlug(post.getName()));
            }
            post.setCreated(Timestamp.from(timestamp));
            postService.save(post);
        }

        return "admin/edit";
    }

    @DeleteMapping("/{id}/delete")
    public String deletePost(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        if (null == postService.find(id)) {
            redirectAttrs.addFlashAttribute("error", "No Post found for ID " + id);
            return "redirect:/admin";
        }
        postService.delete(id);
        redirectAttrs.addFlashAttribute("message", "Account created!");
        return "redirect:/admin";
    }

}
