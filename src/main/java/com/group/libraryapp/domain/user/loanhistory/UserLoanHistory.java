package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

/**
 * 연관관계의 주인 : Table을 보았을 때 누가 관계의 주도권을 갖고 있는가?
 * 연관관계의 주인이 아닌 쪽에 mappedBy 옵션을 달아 주어야 한다.
 * 연관관계의 주인의 값이 설정되어야만 진정한 데이터가 저장된다.
 * 연관관계의 주인효과 : 객체가 연결되는 기준이 된다.
 * @JoinColumn : 연관관계의 주인이 활용할 수 있는 어노테이션
 * - 필드의 이름이나 null여부 , 유일성 여부 , 업데이트 여부 등을 지정
 * @ManyToMany : 구조가 복잡하고, 테이블이 직관적으로 매핑되지 않아 사용하지 않는 것을 추천
 * CASCADE 옵션 : 한 객체가 저장되거나 삭제될 때,
 * 그 변경이 폭포처럼 흘러 연결되어있는 객체도 함께 저장되거나 삭제되는 기능
 */
@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @JoinColumn(nullable = false)
    @ManyToOne
    private User user;
//    private long userId;
    private String bookName;
    private boolean isReturn;//tinyint 매핑
    protected UserLoanHistory(){

    }
    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;//대출
    }
    public void doReturn(){
        this.isReturn = true;//반납
    }

    public String getBookName() {
        return this.bookName;
    }
}
