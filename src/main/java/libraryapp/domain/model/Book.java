package libraryapp.domain.model;

import java.sql.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
	@SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "date_published")
	private Date datePublished;
	
	@Column(name = "category")
	private String category;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "isbn10", column = @Column(name = "isbn10")),
		@AttributeOverride(name = "isbn13", column = @Column(name = "isbn13"))
	})
	private Isbn isbn;
	
	protected Book() {/*required by JPA specifications*/}

	public Book(String title, String author, Date datePublished, String category, Isbn isbn) {
		this.title = title;
		this.author = author;
		this.datePublished = datePublished;
		this.category = category;
		this.isbn = isbn;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDatePublished() {
		return this.datePublished;
	}

	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Isbn getIsbn() {
		return this.isbn;
	}

	public void setIsbn(Isbn isbn) {
		this.isbn = isbn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", date_published=" + datePublished
				+ ", category=" + category + ", isbn=" + isbn + "]";
	}
	
}
