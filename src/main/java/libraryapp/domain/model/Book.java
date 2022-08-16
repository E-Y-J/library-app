package libraryapp.domain.model;

import java.sql.Date;

import javax.persistence.Column;
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
	private Date date_published;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "isbn10")
	private String isbn10;
	
	@Column(name = "isbn13")
	private String isbn13;
	
	protected Book() {}

	public Book(String title, String author, Date date_published, String category, String isbn10,
			String isbn13) {
		super();
		this.title = title;
		this.author = author;
		this.date_published = date_published;
		this.category = category;
		this.isbn10 = isbn10;
		this.isbn13 = isbn13;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate_published() {
		return date_published;
	}

	public void setDate_published(Date date_published) {
		this.date_published = date_published;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIsbn10() {
		return isbn10;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
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
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", date_published=" + date_published
				+ ", category=" + category + ", isbn10=" + isbn10 + ", isbn13=" + isbn13 + "]";
	}
	
}
