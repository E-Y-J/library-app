package libraryapp.domain.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "member_category")
public class MemberCategory {
	public enum Category {
		GRADUATE,
		UNDERGRADUATE,
		DEPARTMENT_HEAD,
		PROFESSOR
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_category_sequence")
	@SequenceGenerator(name = "member_category_sequence", sequenceName = "member_category_sequence", allocationSize = 1)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Category category;
	
	@Column(name = "number_of_books")
	private Integer numberOfBooks;

	// required by JPA specifications
	protected MemberCategory() {
	}

	public MemberCategory(Long id) {
		this.id = id;
	}

	public MemberCategory(Category category, Integer numberOfBooks) {
		this.category = category;
		this.numberOfBooks = numberOfBooks;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getNumberOfBooks() {
		return this.numberOfBooks;
	}

	public void setNumberOfBooks(Integer numberOfBooks) {
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
