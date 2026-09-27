/**
 * 
 */
package no.hvl.dat152.service;

import java.util.List;

import no.hvl.dat152.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat152.model.Author;
import no.hvl.dat152.repository.AuthorRepository;

/**
 * @author tdoy
 */
@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	public Author saveAuthor(Author author) {

		return authorRepository.save(author);
	}
	
	public List<Author> findAll(){
		
		return (List<Author>) authorRepository.findAll();
	}
	
	public Author findById(int id) {
		
		return authorRepository.findById(id).get();
	}
}
