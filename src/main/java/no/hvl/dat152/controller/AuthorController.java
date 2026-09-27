/**
 * 
 */
package no.hvl.dat152.controller;


import no.hvl.dat152.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import no.hvl.dat152.service.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 */
@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/addauthor")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(Model model) {

        return "addauthor";
    }

    @PostMapping("/addauthor")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(@RequestParam String firstname, @RequestParam String lastname) {

        Author author = new Author(firstname, lastname);
        authorService.saveAuthor(author);

        return "redirect:/";

    }
	
}
