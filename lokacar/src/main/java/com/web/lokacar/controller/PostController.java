package com.web.lokacar.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.web.lokacar.enums.Localizacao;
import com.web.lokacar.enums.Tipo;
import com.web.lokacar.model.Post;
import com.web.lokacar.repository.PostRepository;
import com.web.lokacar.service.ServicePost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private ServicePost servicePost;


    @GetMapping("")
    public ModelAndView index(
            @RequestParam(
                value = "page",
                required = false,
                defaultValue = "0") int page,
            @RequestParam(
                value = "size",
                required = false,
                defaultValue = "10") int size) {
        ModelAndView mv = new ModelAndView();
        Page<Post> posts = servicePost.findAll(page,size);
        mv.addObject("posts", posts);
        mv.setViewName("post/posts");

        int totalPages = posts.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            mv.addObject("pageNumbers",pageNumbers);
        }
        return mv;
    }
    
    
    @GetMapping("/buscar")
    public ModelAndView buscar(
            @RequestParam("localizacao") Localizacao localizacao,
            @RequestParam("tipo") Tipo tipo,
            @RequestParam(
                value = "page",
                required = false,
                defaultValue = "0") int page,
            @RequestParam(
                value = "size",
                required = false,
                defaultValue = "10") int size) {
        ModelAndView mv = new ModelAndView();
        Page<Post> posts = servicePost.findByLocalizacaoAndTipo(localizacao, tipo, page, size);
        mv.addObject("posts", posts);
        mv.setViewName("post/posts");

        int totalPages = posts.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            mv.addObject("pageNumbers",pageNumbers);
        }
        return mv;
    }

}
    