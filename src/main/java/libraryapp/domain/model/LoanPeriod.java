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
	
	@Column(name = "member_category")
	private Long memberCategory;
	
	@Column(name = "book_category")
	private String bookCategory;
	
	@Column(name = "number_of_days")
	private Integer numberOfDays;

	protected LoanPeriod() {/*required by JPA specifications*/}

	public LoanPeriod(Long memberCategory, String bookCategory, Integer numberOfDays) {
		this.memberCategory = memberCategory;
		this.bookCategory = bookCategory;
		this.numberOfDays = numberOfDays;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberCategory() {
		return this.memberCategory;
	}

	public void setMemberCategory(Long memberCategory) {
		this.memberCategory = memberCategory;
	}

	public String getBookCategory() {
		return this.bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public Integer getNumberOfDays() {
		return this.numberOfDays;
	}

	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
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
		return "LoanPeriod [id=" + id + ", member_category=" + memberCategory + ", book_category=" + bookCategory
				+ ", number_of_days=" + numberOfDays + "]";
	}
	
}
