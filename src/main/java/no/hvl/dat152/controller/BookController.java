package no.hvl.dat152.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import no.hvl.dat152.exceptions.BookNotFoundException;
import no.hvl.dat152.exceptions.UpdateBookFailedException;
import no.hvl.dat152.model.Author;
import no.hvl.dat152.model.Book;
import no.hvl.dat152.service.AuthorService;
import no.hvl.dat152.service.BookService;

/**
 * @author tdoy
 */
@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/")
	public String defaultView() {
		
		return "index";
	}
	
	@GetMapping("/viewbooks")
	public String findAll(Model model) {
		List<Book> books = (List<Book>) bookService.findAll();
		model.addAttribute("books", books);
		
		return "viewbooks";
	}
		
	@GetMapping("/viewbook")
	public String findOne(@RequestParam Long id, Model model) throws BookNotFoundException {
		
		Book book = bookService.findById(id);
		model.addAttribute("book", book);
		
		return "viewbook";
	}
	
	@GetMapping("/addbook")
	@PreAuthorize("hasRole('ADMIN')")
	public String create(Model model) {
		
		List<Author> authors = authorService.findAll();
		model.addAttribute("authors", authors);
		
		return "addbook";
	}
	
	@PostMapping("/addbook")
	@PreAuthorize("hasRole('ADMIN')")
	public String create(@RequestParam String isbn, @RequestParam String title,
			@RequestParam int authorid) {
		
		Book book = new Book(isbn, title);
		Author author = authorService.findById(authorid);
		book.addAuthor(author);
		bookService.saveBook(book);
		
		return "redirect:viewbooks";
	}
	
	// TODO - deleteBook()
	@GetMapping("/deletebook")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteBook(@RequestParam Long id, Model model) throws BookNotFoundException {

		bookService.deleteBookById(id);
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);

		return "viewbooks";
	}

	
	@GetMapping("/updatebook")
	@PreAuthorize("hasRole('ADMIN')")
	public String updateBook(@RequestParam Long id, Model model) throws BookNotFoundException {

		Book book = bookService.findById(id);
		model.addAttribute("book", book);
		List<Author> authors = authorService.findAll();
		model.addAttribute("authors", authors);
		
		return "updatebook";
	}
	
	@PostMapping("/updatebook")
	@PreAuthorize("hasRole('ADMIN')")
	public String updateBook(@RequestParam String isbn, 
			@RequestParam String title,
			@RequestParam String authorid,
			@RequestParam Long id,
			Model model) throws BookNotFoundException, UpdateBookFailedException {

		Book book = bookService.updateBook(isbn, title, authorid, id);
		model.addAttribute("book", book);
		
		return "viewbook";
	}
}
