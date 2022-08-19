package libraryapp.domain.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends Repository<Book, Long> {
	@Query("FROM Book a WHERE a.id = :id")
	Book findById(@Param("id") long id);
	
	@Query("FROM Book a WHERE a.title = :title")
	Book findByTitle(@Param("title") String title);

	List<Book> findAll();
	
	void save(Book book);
}
