package com.group.libraryapp.domain.user.loanHistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    // 내가 다수 상대방이 하나일 때  즉 대출기록은 여러개 사용자는 하나
    @ManyToOne
    // 주인이 상대방 세팅제한 조건을 넣을 수있음
    @JoinColumn(nullable = false)
    private User user;

    private String bookName;

    // tinyint 0 = false , 1 = true
    private boolean isReturn;


    protected UserLoanHistory() {
    }

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        // 대출중이라는 건 false가 고정
        this.isReturn = false;
    }

    public void doReturn() {
        this.isReturn = true;
    }

    public String getBookName() {
        return this.bookName;
    }
}
