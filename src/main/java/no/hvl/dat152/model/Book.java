/**
 * 
 */
package no.hvl.dat152.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;

/**
 * @author tdoy
 */
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false, unique = true)
	private String isbn;
	
	@Column(nullable = false)
	private String title;
	
	@ManyToMany
	@JoinTable(name = "book_author",
			joinColumns = { @JoinColumn(name = "fk_book", referencedColumnName = "id")},
			inverseJoinColumns = { @JoinColumn(name = "fk_author", referencedColumnName = "authorId")})
	private Set<Author> authors = new HashSet<Author>();

	public Book() {
		// default constructor
	}
	
	public Book(String isbn, String title) {
		
		this.isbn = isbn;
		this.title = title;
	}
	
//	public Book(long id, String isbn, String title) {
//		
//		this.id = id;
//		this.isbn = isbn;
//		this.title = title;
//	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

    /**
	 * @return the authors
	 */
	public Set<Author> getAuthors() {
		return authors;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	
	public void addAuthor(Author author) {
		this.authors.add(author);
		author.getBooks().add(this);
	}
	
	public void removeAuthor(Author author) {
		this.authors.remove(author);
		author.getBooks().remove(this);
	}
	
	@Override
    public String toString() {
        return "Book [isbn=" + isbn + ", title=" + title  + "]";
    }
}
