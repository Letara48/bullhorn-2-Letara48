package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    MessagesRepository messagesRepository;

    @RequestMapping("/")
    public String listMessagess(Model model){
        model.addAttribute("messagess", messagesRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String MessagesForm(Model model){
        model.addAttribute("messages", new messages());
        return "messagesform";
    }
    @PostMapping("/process")
    public String processForm(@Valid messages messages,
        BindingResult result) {
        if (result.hasErrors()){
            return "Messagesform";
        }
        messagesRepository.save(messages);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showMessages(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("messages", messagesRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateMessages(@PathVariable("id") long id,
             Model model){
        model.addAttribute("messages", messagesRepository.findById(id).get());
        return "messagesform";
    }
    @RequestMapping("/delete/{id}")
    public String delMessages(@PathVariable("id") long id){
        messagesRepository.deleteById(id);
        return "redirect:/";
    }
}

