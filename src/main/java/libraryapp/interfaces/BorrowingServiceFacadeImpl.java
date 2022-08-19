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

	public BorrowingServiceFacadeImpl(BookCopyRepository bookCopyRepository,
									  MemberAccountRepository memberAccountRepository,
									  LoanPeriodRepository loanPeriodRepository,
									  BookLoanRepository bookLoanRepository) {
		this.bookCopyRepository = bookCopyRepository;
		this.memberAccountRepository = memberAccountRepository;
		this.loanPeriodRepository = loanPeriodRepository;
		this.bookLoanRepository = bookLoanRepository;
	}

	@Override
	public BorrowReceipt borrowBook(String barcode, String memberAccountId) {
		MemberAccount memberAccount = memberAccountRepository.findByMemberId(memberAccountId);
		BookCopy bookCopy = bookCopyRepository.findByBarcode(barcode);
		Book book = bookCopy.getBook();
		LoanPeriod memberLoanPeriod = loanPeriodRepository.findByMemberCategoryIdAndBookCategory(memberAccount.getCategory().getId(), book.getCategory());
		Date borrowDate = DateUtils.today();
		Date dueDate = DateUtils.todayPlus(memberLoanPeriod.getNumberOfDays());

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
		// TODO Auto-generated method stub
		return null;
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
