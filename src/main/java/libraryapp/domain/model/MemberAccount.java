package libraryapp.domain.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "member_account")
public class MemberAccount {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_sequence")
	@SequenceGenerator(name = "member_sequence", sequenceName = "member_sequence", allocationSize = 1)
	private Long id;
	
	@Column(name = "member_id")
	private String memberId;
	
	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private MemberCategory category;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "member_id", updatable = false, insertable = false)
	private Set<BookLoan> bookLoans = new HashSet<BookLoan>();

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "member_id", updatable = false, insertable = false)
	private Set<BookReserve> bookReserves = new HashSet<BookReserve>();

	// Required by JPA specifications
	protected MemberAccount() {
	}

	public MemberAccount(Long id) {
		this.id = id;
	}

	public MemberAccount(String memberId, String name, Long categoryId) {
		this.memberId = memberId;
		this.name = name;
		this.category = new MemberCategory(categoryId);
	}

	public void setCategory(MemberCategory category) {
		this.category = category;
	}

	public Set<BookLoan> getBookLoans() {
		return bookLoans;
	}

	public void setBookLoans(Set<BookLoan> bookLoans) {
		this.bookLoans = bookLoans;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MemberCategory getCategory() {
		return this.category;
	}

	public void setCategory(Long categoryId) {
		this.category = new MemberCategory(categoryId);
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
		return "MemberAccount [id=" + id + ", library_id=" + memberId + ", name=" + name + ", category=" + category
				+ "]";
	}
}