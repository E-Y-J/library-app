package libraryapp.domain.model;

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
@Table(name = "member_account")
public class MemberAccount {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_sequence")
	@SequenceGenerator(name = "member_sequence", sequenceName = "member_sequence", allocationSize = 1)
	private Long id;
	
	@Column(name = "library_id")
	private String libraryId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "category")
	private Long category;

	protected MemberAccount() {/*required by JPA specifications*/}
	
	public MemberAccount(String libraryId, String name, Long category) {
		super();
		this.libraryId = libraryId;
		this.name = name;
		this.category = category;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibraryId() {
		return this.libraryId;
	}

	public void setLibraryId(String libraryId) {
		this.libraryId = libraryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategory() {
		return this.category;
	}

	public void setCategory(Long category) {
		this.category = category;
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
		MemberAccount other = (MemberAccount) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MemberAccount [id=" + id + ", library_id=" + libraryId + ", name=" + name + ", category=" + category
				+ "]";
	}
	
}