package libraryapp.domain.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "member_category")
public class MemberCategory {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_category_sequence")
	@SequenceGenerator(name = "member_category_sequence", sequenceName = "member_category_sequence", allocationSize = 1)
	private Long id;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "number_of_books")
	private Long numberOfBooks;
	
	protected MemberCategory() {/*required by JPA specifications*/}

	public MemberCategory(String category, Long numberOfBooks) {
		super();
		this.category = category;
		this.numberOfBooks = numberOfBooks;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getNumberOfBooks() {
		return this.numberOfBooks;
	}

	public void setNumberOfBooks(Long numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberCategory other = (MemberCategory) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "MemberCategory [id=" + id + ", category=" + category + ", numberOfBooks=" + numberOfBooks + "]";
	}
	
	
}
