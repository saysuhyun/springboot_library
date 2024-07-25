package com.group.libraryapp.domain.user.loanHistory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private long userId;

    private String bookName;

    // tinyint 0 = false , 1 = true
    private boolean isReturn;


    protected UserLoanHistory() {
    }

    public UserLoanHistory(long userId, String bookName) {
        this.userId = userId;
        this.bookName = bookName;
        // 대출중이라는 건 false가 고정
        this.isReturn = false;
    }

    public void doReturn() {
        this.isReturn = true;
    }
}
