package libraryapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import libraryapp.domain.model.Book;
import libraryapp.domain.model.BookRepository;

@DataJpaTest
public class BookRepositoryTests {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	void testFindOneBook() throws Exception {
		Book book  = bookRepository.findById(1L);
		System.out.println(book);
		assertNotNull(book, "404 Book not found");
	}
	
	@Test
	void testFindAllBooks() throws Exception {
		List<Book> books  = bookRepository.findAll();
		System.out.println(books);
		assertNotEquals(books.size(), 0, "Empty SQL");
	}
	
	@Test
	void testSaveBooks() throws Exception {
		Book book = new Book(
				"A Wanted Man", 
				"Lee Child", 
				java.sql.Date.valueOf(LocalDate.of(2012, 8, 1)), 
				"Novel", 
				"037369606X", 
				"9780373696062");
		bookRepository.save(book);
		Book findBook  = bookRepository.findById(4L);
		System.out.println(findBook);
		assertEquals(findBook.getTitle(), "A Wanted Man", "Book not uploaded");
	}
}
