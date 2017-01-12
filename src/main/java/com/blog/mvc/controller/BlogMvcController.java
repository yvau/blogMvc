package com.blog.mvc.controller;

import com.blog.mvc.model.Category;
import com.blog.mvc.model.Comment;
import com.blog.mvc.model.Post;
import com.blog.mvc.model.User;
import com.blog.mvc.pageWrapper.PageWrapper;
import com.blog.mvc.service.*;
import com.blog.mvc.validators.CommentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Yvau on 1/4/2017.
 */
@Controller
public class BlogMvcController {

    private int fullPageSize = 5;

    private int sidebarSize = 3;

    @Autowired
    AppService appService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    CommentValidator commentValidator;

    @Autowired
    CommentService commentService;

    /**
     * @return
     */
    @ModelAttribute("categoriesOnSide")
    public List<Category> getListCategorySidebar() {

        return categoryService.findAll();
    }

    /**
     * @return
     */
    @ModelAttribute("postsOnSide")
    public List<Post> getListLastPostsSidebar() {

        return postService.findAll(appService.pageable(1, sidebarSize)).getContent();
    }


    @GetMapping("/")
    public String homePage(Model model, @RequestParam(value = "page", required = false) Integer page, HttpServletRequest request) {

        PageWrapper<Post> iterationOfPost = new PageWrapper<>(postService.findAll(appService.pageable(page, fullPageSize)), "/" + appService.removeQueryStringParameter(request.getQueryString()));
        model.addAttribute("iterations", iterationOfPost);
        return "blog/home";
    }

    @GetMapping("/login")
    public String loginPage() {

        return "blog/login";
    }

    @GetMapping("/category/{slug}")
    public String showCategories(Model model, @PathVariable("slug") String slug, @RequestParam(value = "page", required = false) Integer page, HttpServletRequest request) {
        Category category = categoryService.findBySlug(slug);
        PageWrapper<Post> iterationOfCategories = new PageWrapper<>(postService.findByCategory(category, (appService.pageable(page, fullPageSize))), "/category/" + slug + "/" + appService.removeQueryStringParameter(request.getQueryString()));


        model.addAttribute("iterations", iterationOfCategories);
        return "blog/home";
    }

    @GetMapping("/author/{id}")
    public String showAuthors(Model model, @PathVariable("id") int id, @RequestParam(value = "page", required = false) Integer page, HttpServletRequest request) {
        User user = userService.find(id);
        PageWrapper<Post> iterationOfUser = new PageWrapper<>(postService.findByUser(user, (appService.pageable(page, fullPageSize))), "/author/" + id + "/" + appService.removeQueryStringParameter(request.getQueryString()));
        model.addAttribute("iterations", iterationOfUser);
        return "blog/home";
    }

    @GetMapping("/{slug}")
    public String showPost(Model model, @PathVariable("slug") String slug) throws Exception {
        try {
            model.addAttribute("showPost", postService.findBySlug(slug));
        }catch (Exception e){

        }
        return "blog/show";
    }

    @PostMapping("/{slug}")
    public String postComment(@ModelAttribute("newComment") Comment comment, BindingResult bindingResult, @PathVariable("slug") String slug) {
        commentValidator.validate(comment, bindingResult);
        if (bindingResult.hasErrors()) {

            return "blog/show";
        } else {

            commentService.save(comment);
        }

        return "redirect:/" + slug;
    }


    private Post getIdFromSlug(String slug) throws Exception {

        Post post = postService.findBySlug(slug);
        if(post.getId().equals(null)){

        }
        return post;
    }
}
