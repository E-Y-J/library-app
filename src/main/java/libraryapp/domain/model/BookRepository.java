package libraryapp.domain.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends Repository<Book, Long> {
	Book findById(long id);

	Book findByTitle(String title);

	@Query("FROM Book b WHERE b.isbn.isbn10 = :isbn "
			+ "OR b.isbn.isbn13 = :isbn")
	Book findByIsbn( @Param("isbn") String isbn);

	List<Book> findAll();
	
	void save(Book book);
}
