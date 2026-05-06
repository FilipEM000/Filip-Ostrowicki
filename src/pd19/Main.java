package pd19;

import pd19.dto.*;
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

        BookDto book1 = new BookDto(1, "123-456", "Władca Pierścieni", "J.R.R. Tolkien", 1954, 3);
        BookDto book2 = new BookDto(2, "987-654", "Wiedźmin", "Andrzej Sapkowski", 1990, 1);
        bookService.addBook(book1);
        bookService.addBook(book2);
        System.out.println(bookService.findById(2).availableCopies());

        MemberDto member1 = new MemberDto(1, "Jan Kowalski", "jan@example.com");
        MemberDto member2 = new MemberDto(2, "Anna Nowak", "anna@example.com");
        memberService.register(member1);
        memberService.register(member2);
        System.out.println(memberService.findById(1).name() + ", " + memberService.findById(2).name());

        CreateLoanRequest loanRequest1 = new CreateLoanRequest(1, 2);
        LoanDto loanDto = loanService.borrow(loanRequest1);
        System.out.println("Wypożyczono: " + loanDto.book().title() + " dla czytelnika: " + loanDto.memberName());
        System.out.println(bookService.findById(2).availableCopies());

        CreateLoanRequest loanRequest2 = new CreateLoanRequest(2, 2);
        //loanService.borrow(loanRequest2);

        ReturnBookRequest returnRequest = new ReturnBookRequest(loanDto.id());
        loanService.returnBook(returnRequest);
        System.out.println(bookService.findById(2).availableCopies());

        System.out.println(memberService.getActiveLoans(1L).size());
    }
}
