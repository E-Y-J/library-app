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
@Table(name = "book_loan")
public class BookLoan {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_loan_sequence")
	@SequenceGenerator(name = "book_loan_sequence", sequenceName = "book_loan_sequence", allocationSize = 1)
	private Long id;
	
	@Column(name = "member_id")
	private Long member_id;
	
	@Column(name = "book_copy_id")
	private Long book_copy_id;
	
	@Column(name = "borrow_date")
	private Date borrow_date;

	@Column(name = "due_date")
	private Date due_date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMember_id() {
		return member_id;
	}

	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}

	public Long getBook_copy_id() {
		return book_copy_id;
	}

	public void setBook_copy_id(Long book_copy_id) {
		this.book_copy_id = book_copy_id;
	}

	public Date getBorrow_date() {
		return borrow_date;
	}

	public void setBorrow_date(Date borrow_date) {
		this.borrow_date = borrow_date;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
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
		BookLoan other = (BookLoan) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookLoan [id=" + id + ", member_id=" + member_id + ", book_copy_id=" + book_copy_id + ", borrow_date="
				+ borrow_date + ", due_date=" + due_date + "]";
	}
	
}
