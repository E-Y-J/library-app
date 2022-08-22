package libraryapp.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "book_reserve")
public class BookReserve {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_reserve_sequence")
    @SequenceGenerator(name = "book_reserve_sequence", sequenceName = "book_reserve_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberAccount account;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    protected BookReserve() {/*required by JPA specifications*/}

    public BookReserve(MemberAccount account, Book book) {
        this.account = account;
        this.book = book;
    }

    public MemberAccount getAccount() {
        return this.account;
    }

    public void setAccount(MemberAccount account) {
        this.account = account;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookReserve that = (BookReserve) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookReserve{" +
                "account=" + account +
                ", book=" + book +
                '}';
    }
}
