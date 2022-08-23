package libraryapp.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "book_copy")
public class BookCopy {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_copy_sequence")
	@SequenceGenerator(name = "book_copy_sequence", sequenceName = "book_copy_sequence", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;
	
	@Column(name = "barcode")
	private String barcode;

	// Required by JPA specifications
	protected BookCopy() {
	}

	protected BookCopy(Long id) {
		this.id = id;
	}

	public BookCopy(Long bookId, String barcode) {
		this.book = new Book(bookId);
		this.barcode = barcode;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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
		BookCopy other = (BookCopy) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookCopy [id=" + id + ", book=" + book + ", barcode=" + barcode + "]";
	}
}
