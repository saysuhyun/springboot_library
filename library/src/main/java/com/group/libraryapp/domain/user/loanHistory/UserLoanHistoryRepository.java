package com.group.libraryapp.domain.user.loanHistory;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

    // select * from user_loan_history where book_name = ? and is_reutrn=? ;
    boolean existsByBookNameAndIsReturn(String name, boolean isReutrn);

    Optional<UserLoanHistory> findByUserIdAndBookName(long userId, String bookName);
}