/**
 * 
 */
package no.hvl.dat152.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat152.exceptions.BookNotFoundException;
import no.hvl.dat152.exceptions.UpdateBookFailedException;
import no.hvl.dat152.model.Author;
import no.hvl.dat152.model.Book;
import no.hvl.dat152.repository.BookRepository;

/**
 * @author tdoy
 */
@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorService authorService;
	
	
	public Book saveBook(Book book) {
		
		return bookRepository.save(book);
		
	}
	
	public Book updateBook(String isbn, String title, String authorid, long id) 
			throws BookNotFoundException, UpdateBookFailedException {
		
		Book book = findBookById(id);
		
		Set<Author> authors = new HashSet<>();
		String[] authorids = authorid.split(",");
		for(String aid : authorids) {
			Author author = authorService.findById(Integer.valueOf(aid));
			authors.add(author);
		}
		
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthors(authors);
		
		// attempt to save book
		try {
			book = bookRepository.save(book);
		}catch(Exception e) {		// we can also collect the exception object and pass it to our custom exception
			throw new UpdateBookFailedException("Update of book id = "+id+" failed!");
		}
		
		return book;
	}
	
	public List<Book> findAll(){
		
		return (List<Book>) bookRepository.findAll();
		
	}
	
	public Book findById(long id) throws BookNotFoundException{
		
		Book book = findBookById(id);
		
		return book;
	}
	
	/**
	 * TODO
	 * @param id
	 * @throws BookNotFoundException
	 */
	public void deleteBookById(long id) throws BookNotFoundException {
		
		if (!bookRepository.existsById(id)) {
			throw new BookNotFoundException("Book with id = " + id + " not found!");
		}

		Book book = findBookById(id);

		bookRepository.delete(book);
	}
	
	private Book findBookById(long id) throws BookNotFoundException {
		
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book with id = "+id+" not found!"));
		
		return book;
	}
}
