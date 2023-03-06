package org.api.controllers;

import jakarta.validation.Valid;
import org.api.dtos.AnimacaoDtos;
import org.api.models.AnimacaoModel;
import org.api.services.AnimacaoServices;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
public class AnimacaoController {

    final AnimacaoServices animacaoService;

    public AnimacaoController(AnimacaoServices animacaoService) {
        this.animacaoService = animacaoService;
    }

    @GetMapping("/")
    public ModelAndView home(){
        AnimacaoModel animacaoModel = new AnimacaoModel();
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("anime", animacaoModel);
        return mv;
    }

    @PostMapping("/index")
    public String save (@Valid AnimacaoDtos animacaoDtos){
        AnimacaoModel animacaoModel = new AnimacaoModel();
        BeanUtils.copyProperties(animacaoDtos, animacaoModel);
        animacaoService.save(animacaoModel);
        return "redirect:/listagem";
    }

    @GetMapping("/erro")
    public String erro(){
        return "/index";
    }

    @GetMapping("/listagem")
    public ModelAndView index(){
        AnimacaoModel animacaoModel = new AnimacaoModel();
        List<AnimacaoModel> listas = new ArrayList<>();
        listas.addAll(animacaoService.findAll());
        long ids = listas.stream().count();
        System.out.println(ids);
        ModelAndView mv = new ModelAndView("listagem");
        mv.addObject("listas", listas);
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(value = "id") UUID id){
        Optional<AnimacaoModel> optionalAnimacaoModel = animacaoService.findById(id);
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("anime", optionalAnimacaoModel.get());
        System.out.println(optionalAnimacaoModel.get().getId());
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete (@PathVariable(value = "id") UUID id){
        List<AnimacaoModel> lista = new ArrayList<>();
        Optional<AnimacaoModel> optionalAnimacaoModel = animacaoService.findById(id);
        ModelAndView mv = new ModelAndView("listagem");
        if (!optionalAnimacaoModel.isPresent()){
            lista = animacaoService.findAll();
            mv.addObject("listas", lista);
            return mv;
        }
        animacaoService.delete(optionalAnimacaoModel.get());
        lista = animacaoService.findAll();
        mv.addObject("listas", lista);
        return mv;
    }
}
