package libraryapp.interfaces;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import libraryapp.domain.model.Book;
import libraryapp.domain.model.BookCopy;
import libraryapp.domain.model.Isbn;
import libraryapp.domain.model.LoanPeriod;
import libraryapp.domain.model.MemberAccount;
import libraryapp.domain.model.MemberCategory;

import static libraryapp.utils.DateUtils.today;
import static libraryapp.utils.DateUtils.todayPlus;
import static libraryapp.utils.DateUtils.todayAsCalendar;

@SpringBootTest
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
@Rollback
@ActiveProfiles("test")
public class BorrowingServiceFacadeTests {
	@Autowired
	protected BorrowingServiceFacade service;

	@PersistenceUnit
	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;
	
	protected String johnAccountId; // department head
	protected String sallyAccountId; // undergraduate student
	protected String patAccountId; // graduate student

	@BeforeEach
	void setUp() {
		assertNotNull(
				service, "Please provide a service implementation");
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		try {
			setUpBooksAndCopies();
			setUpMemberCategories();
			setUpLoanPeriods();
			setUpMemberAccounts();
			entityManager.getTransaction().commit();
		} catch(Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("no work");
			throw e;
		} finally {
			entityManager.close();
		}
	}

	protected void setUpBooksAndCopies() {
		// TODO Set-up the following books (and their copies), and categories
		// NOTE: All these book copies are IN (not borrowed/checked-out).

		// An entityManager field has already been defined in the base class.
		// So, you can use it here.
		// entityManager.persist(entity);
		
		/*
		 * title: The Affair
		 * author: Lee Child
		 * published month: September
		 * published year: 2011
		 * isbn: 1409011445, 9781409011446
		 * category: Novel
		 * copies: 9781409011446-1, 9781409011446-2
		 */
		Book theAffair = new Book(
				"The Affair", 
				"Lee Child", 
				java.sql.Date.valueOf(LocalDate.of(2011, 9, 1)), 
				"Novel", 
				new Isbn("1409011445", "9781409011446"));
		BookCopy theAffair1 = new BookCopy(1L, "9781409011446-1");
		BookCopy theAffair2 = new BookCopy(1L, "9781409011446-2");
		
		entityManager.persist(theAffair);
		entityManager.persist(theAffair1);
		entityManager.persist(theAffair2);
		
		/*
		 * title: A Wanted Man
		 * author: Lee Child
		 * published month: August
		 * published year: 2012
		 * isbn: 037369606X, 9780373696062
		 * category: Novel
		 * copies: 9780373696062-1
		 */
		Book aWantedMan = new Book(
				"A Wanted Man", 
				"Lee Child", 
				java.sql.Date.valueOf(LocalDate.of(2012, 8, 1)), 
				"Novel", 
				new Isbn("037369606X", "9780373696062"));
		BookCopy aWantedMan1 = new BookCopy(2L, "9780373696062-1");
		
		entityManager.persist(aWantedMan);
		entityManager.persist(aWantedMan1);
		/*
		 * title: Inferno
		 * author: Dan Brown
		 * published month: May
		 * published year: 2013
		 * isbn: 0593075005, 9780593075005
		 * category: Novel
		 * copies: 9780593075005-1, 9780593075005-2
		 */
		Book inferno = new Book(
				"Inferno", 
				"Dan Brown", 
				java.sql.Date.valueOf(LocalDate.of(2013, 5, 1)), 
				"Novel", 
				new Isbn("0593075005", "9780593075005"));
		BookCopy inferno1 = new BookCopy(3L, "9780593075005-1");
		BookCopy inferno2 = new BookCopy(3L, "9780593075005-2");
		
		entityManager.persist(inferno);
		entityManager.persist(inferno1);
		entityManager.persist(inferno2);
		/*
		 * title: The Lost Symbol
		 * author: Dan Brown
		 * published month: September
		 * published year: 2009
		 * isbn: 055217002X, 9780552170024
		 * category: Novel
		 * copies: 9780552170024-1, 9780552170024-2
		 */
		Book theLostSymbol = new Book(
				"The Lost Symbol", 
				"Dan Brown", 
				java.sql.Date.valueOf(LocalDate.of(2009, 7, 1)), 
				"Novel", 
				new Isbn("055217002X", "9780552170024"));
		BookCopy theLostSymbol1 = new BookCopy(4L, "9780552170024-1");
		BookCopy theLostSymbol2 = new BookCopy(4L, "9780552170024-2");
		
		entityManager.persist(theLostSymbol);
		entityManager.persist(theLostSymbol1);
		entityManager.persist(theLostSymbol2);
		/*
		 * title: Diary of a Wimpy Kid
		 * author: Jeff Kinney
		 * published month: April
		 * published year: 2007
		 * isbn: 0141324902, 9780141324906
		 * category: Comedy
		 * copies: 9780141324906-1, 9780141324906-2
		 */
		Book diaryOfAWimpyKid = new Book(
				"Diary of a Wimpy Kid", 
				"Jeff Kinney", 
				java.sql.Date.valueOf(LocalDate.of(2007, 4, 1)), 
				"Comedy", 
				new Isbn("0141324902", "9780141324906"));
		BookCopy diaryOfAWimpyKid1 = new BookCopy(5L, "9780141324906-1");
		BookCopy diaryOfAWimpyKid2 = new BookCopy(5L, "9780141324906-2");
		
		entityManager.persist(diaryOfAWimpyKid);
		entityManager.persist(diaryOfAWimpyKid1);
		entityManager.persist(diaryOfAWimpyKid2);
		/*
		 * title: Advanced Grammar in Use
		 * author: Martin Hewings
		 * published year: 2005
		 * isbn: 3125341477, 9783125341470
		 * category: Reference
		 * copies: 9783125341470-1
		 */
		Book advancedGrammarInUse = new Book(
				"Advanced Grammar in Use", 
				"Martin Hewings", 
				java.sql.Date.valueOf(LocalDate.of(2005, 1, 1)),
				"Reference", 
				new Isbn("3125341477", "9783125341470"));
		BookCopy advancedGrammarInUse1 = new BookCopy(6L, "9783125341470-1");
		
		entityManager.persist(advancedGrammarInUse);
		entityManager.persist(advancedGrammarInUse1);
	}

	protected void setUpMemberCategories() {
		/*
		 * Undergraduate students can borrow up to 3 books.
		 * Graduate students can borrow up to 2 books.
		 * Department heads can borrow up to 50 books.
		 * Professors can borrow up to 10 books.
		 */
		// An entityManager field has already been defined in the base class.
		// So, you can use it here.
		MemberCategory undergradBookRestriction = new MemberCategory(MemberCategory.Category.UNDERGRADUATE, 3);
		MemberCategory gradBookRestriction = new MemberCategory(MemberCategory.Category.GRADUATE, 2);
		MemberCategory depHeadBookRestriction = new MemberCategory(MemberCategory.Category.DEPARTMENT_HEAD, 50);
		MemberCategory profBookRestriction = new MemberCategory(MemberCategory.Category.PROFESSOR, 10);
		
		entityManager.persist(undergradBookRestriction);
		entityManager.persist(gradBookRestriction);
		entityManager.persist(depHeadBookRestriction);
		entityManager.persist(profBookRestriction);
	}

	protected void setUpLoanPeriods() {
		// TODO Set-up the following loan periods
		/*
		 * Undergraduate students can borrow novels for up to 10 days
		 * Undergraduate students can borrow reference books for up to 15 days
		 * Graduate students can borrow novels for up to 5 days
		 * Graduate students can borrow reference books for up to 5 days
		 * Department heads and professors can borrow reference books for up to 30 days,
		 * 	but cannot borrow novels
		 */
		// An entityManager field has already been defined in the base class.
		// So, you can use it here.
		LoanPeriod undergradNovelRestriction = new LoanPeriod(1L, "Novel", 10);
		LoanPeriod undergradReferenceRestriction = new LoanPeriod(1L, "Reference", 15);
		
		LoanPeriod gradNovelRestriction = new LoanPeriod(2L, "Novel", 5);
		LoanPeriod gradReferenceRestriction = new LoanPeriod(2L, "Reference", 5);
		
		LoanPeriod depHeadNovelRestriction = new LoanPeriod(3L, "Novel", 0);
		LoanPeriod depHeadReferenceRestriction = new LoanPeriod(3L, "Reference", 30);
		
		LoanPeriod profNovelRestriction = new LoanPeriod(4L, "Novel", 0);
		LoanPeriod profReferenceRestriction = new LoanPeriod(4L, "Reference", 30);
		
		entityManager.persist(undergradNovelRestriction);
		entityManager.persist(gradNovelRestriction);
		entityManager.persist(depHeadNovelRestriction);
		entityManager.persist(profNovelRestriction);
		
		entityManager.persist(undergradReferenceRestriction);
		entityManager.persist(gradReferenceRestriction);
		entityManager.persist(depHeadReferenceRestriction);
		entityManager.persist(profReferenceRestriction);
	}

	protected void setUpMemberAccounts() {
		// TODO Set-up member accounts
		/*
		 * 1. John is a department head
		 * 2. Sally is an undergraduate student
		 * 3. Pat is a graduate student
		 */
		// An entityManager field has already been defined in the base class.
		// So, you can use it here.

		// TODO Initialize the following member account IDs
		johnAccountId = "dp1";
		sallyAccountId = "ud1";
		patAccountId = "g1";
		
		MemberAccount john = new MemberAccount(johnAccountId, "John", 3L);
		MemberAccount sally = new MemberAccount(sallyAccountId, "Sally", 1L);
		MemberAccount pat = new MemberAccount(patAccountId, "Pat", 2L);
		
		entityManager.persist(john);
		entityManager.persist(sally);
		entityManager.persist(pat);
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void borrowBookCopyAndReturn() {
		String barcode = "9780593075005-1"; // Inferno by Dan Brown

		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		BorrowReceipt receipt = service.borrowBook(barcode, sallyAccountId);
		assertEquals("Inferno", receipt.getTitle());
		assertEquals(barcode, receipt.getBarcode());
		assertEquals(today(), receipt.getBorrowDate());
		assertEquals(
				todayPlus(10), receipt.getDueDate(),
				"Sally (an undergraduate student) can borrow novels for up to 10 days");

		Collection<BookLoanStatus> bookLoans = service.getBookLoanStatus(sallyAccountId);
		assertEquals(
				1, bookLoans.size(),
				"After borrowing, number of loans should be one");

		service.returnBook(barcode, sallyAccountId);
		bookLoans = service.getBookLoanStatus(sallyAccountId);
		assertEquals(
				0, bookLoans.size(),
				"After returning, number of loans should be zero");
	}
	
	@Test
	void borrowBookCopiesWithDifferentLoanPeriods() {
		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		String barcodeNovel = "9780593075005-1"; // Inferno by Dan Brown
		String barcodeReferenceBook = "9783125341470-1"; // Advanced Grammar in Use by Martin Hewings
		BorrowReceipt receipt;
		receipt = service.borrowBook(barcodeNovel, sallyAccountId);
		assertEquals(
				todayPlus(10), receipt.getDueDate(),
				"Sally (an undergraduate student) can borrow novels for up to 10 days");
		receipt = service.borrowBook(barcodeReferenceBook, sallyAccountId);
		assertEquals(
				todayPlus(15), receipt.getDueDate(),
				"Sally (an undergraduate student) can borrow reference books for up to 15 days");
	}

	@Test
	void cannotBorrowBeyondLimit() {
		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		// Given a graduate student (Pat) account
		// with two borrowed/loaned books...
		String barcode1 = "9780552170024-1"; // The Lost Symbol by Dan Brown
		String barcode2 = "9780593075005-2"; // Inferno by Dan Brown
		service.borrowBook(barcode1, patAccountId);
		service.borrowBook(barcode2, patAccountId);
		Collection<BookLoanStatus> bookLoans = service.getBookLoanStatus(patAccountId);
		assertEquals(2, bookLoans.size());

		// ... try borrowing a third one (Advanced Grammar in Use by Martin Hewings) 9783125341470-1
		try {
			service.borrowBook("9783125341470-1", patAccountId);
			fail("Should have thrown exception and prevent book from being borrowed");
		} catch (Exception e) {
			// Pat (a graduate student) can only borrow up to 2 books.
			// expected, pass!
			bookLoans = service.getBookLoanStatus(patAccountId);
			assertEquals(2, bookLoans.size());
		}
	}

	@Test
	void cannotBorrowAlreadyBorrowedBook() {
		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		String barcode = "9780552170024-1"; // The Lost Symbol by Dan Brown
		service.borrowBook(barcode, patAccountId);
		try {
			service.borrowBook(barcode, patAccountId);
			fail("Should have thrown exception, as the book has already been borrowed");
		} catch (Exception e) {
			// expected, pass!
		}
	}

	@Test
	void cannotBorrowCertainBookCategories() {
		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		// Department head (John) cannot borrow novels
		String barcode = "9780552170024-1"; // The Lost Symbol by Dan Brown
		try {
			service.borrowBook(barcode, johnAccountId);
			fail("Should have thrown exception and prevent book from being borrowed");
		} catch (Exception e) {
			// expected, pass!
			Collection<BookLoanStatus> bookLoans = service.getBookLoanStatus(johnAccountId);
			assertEquals(0, bookLoans.size());
		}
	}

	private static class BorrowBookCommand implements Runnable {
		private final String barcode;
		private final String memberAccountId;
		private final BorrowingServiceFacade service;

		public BorrowBookCommand(
				String barcode, String memberAccountId, BorrowingServiceFacade service) {
			this.barcode = barcode;
			this.memberAccountId = memberAccountId;
			this.service = service;
		}
		
		public void execute() {
			service.borrowBook(barcode, memberAccountId);
		}

		@Override
		public void run() {
			execute();
		}
	}

	@Test
	void preventConcurrentHacks() throws Exception {
		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		String barcode1 = "9780552170024-1"; // The Lost Symbol by Dan Brown
		String barcode2 = "9780593075005-2"; // Inferno by Dan Brown
		String barcode3 = "9780373696062-1"; // A Wanted Man by Lee Child

		// Launch threads simultaneously borrowing different books
		Thread t1 = new Thread(new BorrowBookCommand(
				barcode1, patAccountId, service),
				"Pat borrowing " + barcode1);
		Thread t2 = new Thread(new BorrowBookCommand(
				barcode2, patAccountId, service),
				"Pat borrowing " + barcode2);
		Thread t3 = new Thread(new BorrowBookCommand(
				barcode3, patAccountId, service),
				"Pat borrowing " + barcode3);

		// In the case of Pat (graduate student), this should still result
		// in an exception, and prevent Pat from exceeding the maximum
		// number of books borrowed/checked-out.
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();

		Collection<BookLoanStatus> bookLoans = service.getBookLoanStatus(patAccountId);
		assertEquals(
				2, bookLoans.size(),
				"Pat (a graduate student) is allowed to borrow up to 2 books");
	}

	@Test
	void reserveBook() {
		String isbn13 = "9783125341470"; // Advanced Grammar in Use by Martin Hewings
		String barcode = "9783125341470-1";

		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		// Borrow the one (1) and only available copy of the book
		service.borrowBook(barcode, sallyAccountId);

		// Now, John has no available copies to borrow. So, John reserves it.
		ReservationReceipt receipt =
				service.reserveBook(isbn13, johnAccountId);
		assertThat(receipt,
				allOf(hasProperty("title", equalTo("Advanced Grammar in Use")),
						hasProperty("isbn13", equalTo("9783125341470")),
						hasProperty("isbn10", equalTo("3125341477"))));

		// Sally returns the borrowed copy
//		service.returnBook(barcode, sallyAccountId);

		// Pat cannot borrow it, since John has it reserved.
//		try {
//			service.borrowBook(barcode, patAccountId);
//			fail("Pat should not be able to borrow, since John has it reserved");
//		} catch (Exception e) {
//			// expected, pass!
//		}

		// John can now borrow, since he reserved it.
//		service.borrowBook(barcode, johnAccountId);
//
//		service.returnBook(barcode, johnAccountId);

		// After John returns it, Pat can now borrow
		// (since John's reservation has been satisfied).
//		try {
//			service.borrowBook(barcode, patAccountId);
//		} catch (Exception e) {
//			fail("Pat should be able to borrow, after John has returned the book."
//					+ " If you add a reservation for John, have it removed when John gets to borrow the book.");
//		}
	}

	@Test
	public void bookReservationsAreFirstComeFirstServed() {
		String isbn13 = "9783125341470"; // Advanced Grammar in Use by Martin Hewings
		String barcode = "9783125341470-1";

		// TODO Comment out line below and make test pass
		// fail("Not yet implemented");
		service.reserveBook(isbn13, sallyAccountId);
		service.reserveBook(isbn13, johnAccountId);
		service.reserveBook(isbn13, patAccountId);

		// At this point, only Sally can borrow
		try {
			service.borrowBook(barcode, johnAccountId);
			fail("John should not be able to borrow, since Sally has it reserved first");
		} catch (Exception e) {
			// expected, pass!
		}
		try {
			service.borrowBook(barcode, patAccountId);
			fail("Pat should not be able to borrow, since Sally has it reserved first");
		} catch (Exception e) {
			// expected, pass!
		}

		// Sally borrows and returns the borrowed copy
		service.borrowBook(barcode, sallyAccountId);
		service.returnBook(barcode, sallyAccountId);

		// Pat still cannot borrow, since John reserved it first.
		try {
			service.borrowBook(barcode, patAccountId);
			fail("Pat should still not be able to borrow, since John has it reserved first");
		} catch (Exception e) {
			// expected, pass!
		}
		
		// John can now borrow, since he reserved it.
		service.borrowBook(barcode, johnAccountId);
		service.returnBook(barcode, johnAccountId);

		// After John returns it, Pat can now borrow
		// and satisfy his reservation.
		service.borrowBook(barcode, patAccountId);
		service.returnBook(barcode, patAccountId);
	}

	@Test
	public void cannotReserveSameBookMoreThanOnce() {
		// fail("Not yet implemented");
		String isbn13 = "9783125341470"; // Advanced Grammar in Use by Martin Hewings
		service.reserveBook(isbn13, patAccountId);
		try {
			service.reserveBook(isbn13, patAccountId);
			fail("Cannot reserve same book more than once");
		} catch (Exception e) {
			// expected, pass!
		}
	}
}
