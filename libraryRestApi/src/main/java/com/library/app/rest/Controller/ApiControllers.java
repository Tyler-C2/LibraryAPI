package com.library.app.rest.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.library.app.rest.Repo.BookRepo;
import com.library.app.rest.Models.Book;
import java.util.List;

@RestController
public class ApiControllers {
	
	@Autowired
	private BookRepo bookRepo;
	
	@GetMapping(value = "/")
	public String getPage() {
		return "Welcome to the library API";
	}
	
	@GetMapping(value = "/books")
	public List<Book> getBooks() {
		return bookRepo.findAll();
	}
	
	@GetMapping(value = "/books/{id}")
	public Book getBook(@PathVariable long id) {
		return bookRepo.findById(id).get();
	}
	
	@GetMapping(value = "books/title/{title}")
	public List<Book> titleSearch(@PathVariable String title) {
		return bookRepo.findByTitle(title);
	}
	
	@GetMapping(value = "books/author/{author}")
	public List<Book> authorSearch(@PathVariable String author) {
		return bookRepo.findByAuthor(author);
	}
	
	@GetMapping(value = "books/genre/{genre}")
	public List<Book> genreSearch(@PathVariable String genre) {
		return bookRepo.findByGenre(genre);
	}
	
	@GetMapping(value = "books/isbn/{isbn}")
	public List<Book> isbnSearch(@PathVariable String isbn) {
		String isbnNoDash = isbn.replaceAll("[^0-9]","");
		return bookRepo.findByIsbn(isbnNoDash);
	}
	
	@GetMapping(value = "books/publisher/{publisher}")
	public List<Book> publisherSearch(@PathVariable String publisher) {
		return bookRepo.findByPublisher(publisher);
	}
	
	@PostMapping(value = "/save")
	public String saveBook(@RequestBody Book book) {
		book.setIsbn(book.getIsbn().replaceAll("[^0-9]",""));

		bookRepo.save(book);
		return "The book has been saved!";
	}
	
	@PutMapping(value = "/update/{id}")
	public String updateBook(@PathVariable long id, @RequestBody Book book) {
		Book updatedBook = bookRepo.findById(id).get();
		updatedBook.setTitle(book.getTitle());
		updatedBook.setAuthor(book.getAuthor());
		updatedBook.setGenre(book.getGenre());
		updatedBook.setPublisher(book.getPublisher());
		updatedBook.setIsbn(book.getIsbn());
		updatedBook.setPageNum(book.getPageNum());
		bookRepo.save(updatedBook);
		return "The book was updated!";	
	}

	@DeleteMapping(value = "/delete/{id}")
	public String deleteBook(@PathVariable long id) {
		Book deleteBook = bookRepo.findById(id).get();
		bookRepo.delete(deleteBook);
		return "The Book was deleted!";
	}
	
	
}

