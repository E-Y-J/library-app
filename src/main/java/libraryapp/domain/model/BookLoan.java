package libraryapp.domain.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "book_loan")
public class BookLoan {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_loan_sequence")
	@SequenceGenerator(name = "book_loan_sequence", sequenceName = "book_loan_sequence", allocationSize = 1)
	private Long id;

	@Version
	private long version;

	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false)
	private MemberAccount account;

	@OneToOne
	@JoinColumn(name = "book_copy_id", nullable = false)
	private BookCopy bookCopy;

	@Temporal(TemporalType.DATE)
	@Column(name = "borrow_date")
	private Date borrowDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Date dueDate;
	
	protected BookLoan() {/*required by JPA specifications*/}

	public BookLoan(Long memberId, Long bookCopyId, Date borrowDate, Date dueDate) {
		this.account = new MemberAccount(memberId);
		this.bookCopy = new BookCopy(bookCopyId);
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MemberAccount getAccount() {
		return this.account;
	}

	public void setAccount(MemberAccount account) {
		this.account = account;
	}

	public BookCopy getBookCopy() {
		return this.bookCopy;
	}

	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

	public Date getBorrowDate() {
		return this.borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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
		return "BookLoan [id=" + id + ", member_id=" + account + ", book_copy_id=" + bookCopy + ", borrow_date="
				+ borrowDate + ", due_date=" + dueDate + "]";
	}
	
}
