package pd19.service.imp;

import pd19.entity.Book;
import pd19.entity.Loan;
import pd19.entity.Member;
import pd19.exception.LoanLimitExceededException;
import pd19.exception.LoanNotFoundException;
import pd19.repository.LoanRepository;
import pd19.service.BookService;
import pd19.service.LoanService;
import pd19.service.MemberService;

import java.time.LocalDate;
import java.util.List;

public class LoanServiceImp implements LoanService {
    LoanRepository loanRepository;
    BookService bookService;
    MemberService memberService;

    public LoanServiceImp(LoanRepository loanRepository, BookServiceImp bookService, MemberServiceImp memberService) {
    }

    @Override
    public Loan borrow(long memberId, long bookId) {
        Member member = memberService.findById(memberId);
        Book book = bookService.findById(bookId);

        if (!member.canBorrow()) {
            throw new LoanLimitExceededException("Ta osoba osiągnęła limit wypożyczeń");
        } else {
            book.borrow();
            Loan loan = new Loan(loanRepository.findAll().size() + 1, book, member, LocalDate.now(), LocalDate.now().plusDays(14), null);
            loanRepository.save(loan);
            return loan;
        }
    }

    @Override
    public void returnBook(long loanId) {
        Loan loan = loanRepository.findAll().stream()
                .filter(loan1 -> loan1.getId() == loanId)
                .findFirst()
                .orElseThrow(() -> new LoanNotFoundException("Nie znaleziono takiego wypożyczenia"));

        loan.setReturnedAt(LocalDate.now());
        loan.getBook().returnBack();
    }

    @Override
    public List<Loan> findOverdue() {
        return loanRepository.findAll().stream()
                .filter(Loan::isOverdue)
                .toList();
    }
}
