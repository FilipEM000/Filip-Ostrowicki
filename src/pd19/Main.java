package pd19;

import pd19.repository.BookRepository;
import pd19.repository.LoanRepository;
import pd19.repository.MemberRepository;
import pd19.service.imp.BookServiceImp;
import pd19.service.imp.LoanServiceImp;
import pd19.service.imp.MemberServiceImp;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        MemberRepository memberRepository = new MemberRepository();
        LoanRepository loanRepository = new LoanRepository();

        BookServiceImp bookService = new BookServiceImp(bookRepository);
        MemberServiceImp memberService = new MemberServiceImp(memberRepository);
        LoanServiceImp loanService = new LoanServiceImp(loanRepository, bookService, memberService);
    }
}
