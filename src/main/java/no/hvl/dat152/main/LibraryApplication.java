package no.hvl.dat152.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import no.hvl.dat152.model.Author;
import no.hvl.dat152.model.Book;
import no.hvl.dat152.repository.AuthorRepository;
//import no.hvl.dat152.model.Book;
//import no.hvl.dat152.repository.AuthorRepository;
//import no.hvl.dat152.repository.BookRepository;
import no.hvl.dat152.repository.BookRepository;
import no.hvl.dat152.service.AuthorService;

@SpringBootApplication
@EnableJpaRepositories("no.hvl.dat152.repository")
@EntityScan("no.hvl.dat152.model")
@ComponentScan(basePackages = {"no.hvl.dat152.service", 
								"no.hvl.dat152.controller", 
								"no.hvl.dat152.exceptions",
								"no.hvl.dat152.auth"})
@Configuration
public class LibraryApplication {

	private static AuthorService authorService;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LibraryApplication.class, args);
		
		authorService = context.getBean(AuthorService.class);
		AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
		BookRepository bookRepository = context.getBean(BookRepository.class);
		
		authorRepository.saveAll(createDefaultAuthors());
		bookRepository.saveAll(creatDefaultBooks());
	}
	
	private static Iterable<Author> createDefaultAuthors() {
		
		List<Author> authors = new ArrayList<Author>();
		
		authors.add(new Author("Shari", "Pfleeger"));
		authors.add(new Author("Perry", "Lea"));
		authors.add(new Author("James", "Kurose"));
		authors.add(new Author("Keith", "Ross"));
		authors.add(new Author("Martin", "Kleppmann"));
		
		return authors;
	}
	
	private static Iterable<Book> creatDefaultBooks() {
		
		Author author1 = authorService.findById(1);
		Author author2 = authorService.findById(2);
		
		Set<Author> authors = new HashSet<Author>();
		authors.add(author1);

		Book book1 = new Book("abcde1234", "Software Engineering");
		book1.setAuthors(authors);
		
		Set<Author> authors2 = new HashSet<Author>();
		Book book2 = new Book("ghijk1234", "Computer Network");
		authors2.add(author1);
		authors2.add(author2);
		book2.setAuthors(authors2);
		
		List<Book> books =  new ArrayList<Book>();		
		books.add(book1);
		books.add(book2);
		
		return books;
	}

}
