package libraryapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import libraryapp.domain.model.Book;
import libraryapp.domain.model.BookRepository;
import libraryapp.domain.model.Isbn;

@DataJpaTest
public class BookRepositoryTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookRepositoryTests.class);

	@Autowired
	private BookRepository bookRepository;

	// Every method and variables camelCase
	// Class names as such PascalCamel
	
	@Test
	void findOneBookById() throws Exception {
		Book book  = bookRepository.findById(1L);
		assertNotNull(book, "404 Book not found");
	}
	
	@Test
	void findOneBookByTitle() throws Exception {
		Book book  = bookRepository.findByTitle("The Affair");
		assertNotNull(book, "404 Book not found");
	}
	
	@Test
	void findAllBooks() throws Exception {
		List<Book> books  = bookRepository.findAll();
		assertNotEquals(books.size(), 0, "Empty SQL");
	}
	
	@Test
	void saveBooks() throws Exception {
		Book book = new Book(
				"A Wanted Man", 
				"Lee Child", 
				java.sql.Date.valueOf(LocalDate.of(2012, 8, 1)), 
				"Novel", 
				new Isbn("037369606X", "9780373696062")
				);
		bookRepository.save(book);
		Book findBook  = bookRepository.findById(4L);
		assertEquals(findBook.getTitle(), "A Wanted Man", "Book not uploaded");
	}
}
