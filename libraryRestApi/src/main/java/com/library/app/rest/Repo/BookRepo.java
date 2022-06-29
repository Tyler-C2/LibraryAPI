package com.library.app.rest.Repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.library.app.rest.Models.Book;

public interface BookRepo extends JpaRepository<Book, Long> {
	List<Book> findByTitle(String title);
	List<Book> findByAuthor(String author);
	List<Book> findByGenre(String genre);
	List<Book> findByIsbn(String isbn);
	List<Book> findByPublisher(String publisher);
}
