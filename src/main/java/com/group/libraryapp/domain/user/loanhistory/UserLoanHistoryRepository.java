package com.group.libraryapp.domain.user.loanhistory;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//대출 기록 리포지토리
public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
    //select * from user_loan_history where book_name=? and is_return = ?
    //책의 이름과 대출중인지 아닌지 확인
    boolean existsByBookNameAndIsReturn(String name, boolean isReturn);
//    Optional<UserLoanHistory>findByUserIdAndBookName(long userId, String bookName);
}
