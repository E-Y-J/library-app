package libraryapp.domain.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Isbn {

	private String isbn10;
	private String isbn13;
	
	protected Isbn() {}
	
	public Isbn(String isbn10, String isbn13) {
		this.isbn10 = isbn10;
		this.isbn13 = isbn13;
	}

	public String getIsbn10() {
		return this.isbn10;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	public String getIsbn13() {
		return this.isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn10, isbn13);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Isbn other = (Isbn) obj;
		return Objects.equals(isbn10, other.isbn10) && Objects.equals(isbn13, other.isbn13);
	}

	@Override
	public String toString() {
		return "Isbn [isbn10=" + isbn10 + ", isbn13=" + isbn13 + "]";
	}	
}
