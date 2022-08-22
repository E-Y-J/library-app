package libraryapp.interfaces;

import java.util.*;

import libraryapp.domain.model.*;
import libraryapp.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BorrowingServiceFacadeImpl implements BorrowingServiceFacade {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final BookCopyRepository bookCopyRepository;
	private final MemberAccountRepository memberAccountRepository;
	private final LoanPeriodRepository loanPeriodRepository;
	private final BookLoanRepository bookLoanRepository;
	private final BookRepository bookRepository;
	private final BookReserveRepository bookReserveRepository;

	public BorrowingServiceFacadeImpl(BookCopyRepository bookCopyRepository,
									  MemberAccountRepository memberAccountRepository,
									  LoanPeriodRepository loanPeriodRepository,
									  BookLoanRepository bookLoanRepository,
									  BookRepository bookRepository,
									  BookReserveRepository bookReserveRepository) {
		this.bookCopyRepository = bookCopyRepository;
		this.memberAccountRepository = memberAccountRepository;
		this.loanPeriodRepository = loanPeriodRepository;
		this.bookLoanRepository = bookLoanRepository;
		this.bookRepository = bookRepository;
		this.bookReserveRepository = bookReserveRepository;
	}

	@Override
	public BorrowReceipt borrowBook(String barcode, String memberAccountId) {
		MemberAccount memberAccount = memberAccountRepository.findByMemberId(memberAccountId);
		BookCopy bookCopy = bookCopyRepository.findByBarcode(barcode);
		Book book = bookCopy.getBook();
		LoanPeriod memberLoanPeriod = loanPeriodRepository.findByMemberCategoryIdAndBookCategory(memberAccount.getCategory().getId(), book.getCategory());
		Date borrowDate = DateUtils.today();
		Date dueDate = DateUtils.todayPlus(memberLoanPeriod.getNumberOfDays());
		Collection<BookLoan> databaseBookLoans = bookLoanRepository.findManyBookLoanByMember(memberAccount);
		MemberCategory memberCategory = memberAccount.getCategory();

		if(memberLoanPeriod.getNumberOfDays() == 0){
			throw new RuntimeException("Member is not allowed to borrow this type of books");
		}

		for (BookLoan bl : databaseBookLoans) {
			if(bl.getBookCopy().getBarcode().equals(barcode)){
				throw new RuntimeException("Book has been borrowed");
			}
		}
		if(databaseBookLoans.size() >= memberCategory.getNumberOfBooks()){
			throw new RuntimeException("Maximum borrowed books allowed have been reached.");
		}

		BookLoan bookLoan = new BookLoan(memberAccount.getId(), bookCopy.getId(), borrowDate, dueDate);
		bookLoanRepository.save(bookLoan);

		return new BorrowReceipt(bookCopy.getBook().getTitle(),
				bookCopy.getBarcode(),
				borrowDate, dueDate);
	}

	@Override
	public void returnBook(String barcode, String memberAccountId) {
		MemberAccount memberAccount = memberAccountRepository.findByMemberId(memberAccountId);
		BookCopy bookCopy = bookCopyRepository.findByBarcode(barcode);
		BookLoan bookLoan = bookLoanRepository.findOneBookLoanByBook(bookCopy);
		bookLoanRepository.delete(bookLoan);
	}

	@Override
	public ReservationReceipt reserveBook(String isbn, String memberAccountId) {
		MemberAccount memberAccount = memberAccountRepository.findByMemberId(memberAccountId);
		Book book = bookRepository.findByIsbn(isbn);

		System.out.println(book);

		ReservationReceipt reservationReceipt = new ReservationReceipt(book.getTitle(), book.getIsbn().getIsbn13(), book.getIsbn().getIsbn10());
		BookReserve bookReserve = new BookReserve(memberAccount, book);
		bookReserveRepository.save(bookReserve);

		return reservationReceipt;
	}

	@Override
	public Collection<BookLoanStatus> getBookLoanStatus(String memberAccountId) {
		MemberAccount memberAccount = memberAccountRepository.findByMemberId(memberAccountId);
		Collection<BookLoan> databaseBookLoans = bookLoanRepository.findManyBookLoanByMember(memberAccount);
		Set<BookLoanStatus> bookLoans = new HashSet<BookLoanStatus>();

		log.debug(databaseBookLoans.toString());

		for (BookLoan loan: databaseBookLoans) {
			String barcode = loan.getBookCopy().getBarcode();
			Book book = loan.getBookCopy().getBook();
			String title = book.getTitle();
			BookLoanStatus bookLoanStatus = new BookLoanStatus(title,barcode,loan.getBorrowDate(), loan.getDueDate());
			bookLoans.add(bookLoanStatus);
		}

		log.debug(bookLoans.toString());

		return bookLoans;
	}

}
