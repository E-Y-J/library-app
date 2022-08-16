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
@Table(name = "loan_period")
public class LoanPeriod {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loan_period_sequence")
	@SequenceGenerator(name = "loan_period_sequence", sequenceName = "loan_period_sequence", allocationSize = 1)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "member_category")
	private MemberCategory member_category;
	
	@Column(name = "book_category")
	private String book_category;
	
	@Column(name = "number_of_days")
	private Integer number_of_days;

	protected LoanPeriod() {}

	public LoanPeriod(MemberCategory member_category, String book_category, Integer number_of_days) {
		super();
		this.member_category = member_category;
		this.book_category = book_category;
		this.number_of_days = number_of_days;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MemberCategory getMember_category() {
		return member_category;
	}

	public void setMember_category(MemberCategory member_category) {
		this.member_category = member_category;
	}

	public String getBook_category() {
		return book_category;
	}

	public void setBook_category(String book_category) {
		this.book_category = book_category;
	}

	public Integer getNumber_of_days() {
		return number_of_days;
	}

	public void setNumber_of_days(Integer number_of_days) {
		this.number_of_days = number_of_days;
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
		LoanPeriod other = (LoanPeriod) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LoanPeriod [id=" + id + ", member_category=" + member_category + ", book_category=" + book_category
				+ ", number_of_days=" + number_of_days + "]";
	}
	
}
