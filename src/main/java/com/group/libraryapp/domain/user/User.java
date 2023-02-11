package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//스프링이 User 객체와 User 테이블을 같은 것으로 바라본다.
public class User {
    @Id//이 필드를 primary Key로 간주한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Mysql auto_increment 와 동일
    //DB 종류마다 자동생성 전략이 다르다
    private Long id = null;
    @Column(nullable = false, length = 20, name="name")//객체의 필드와 Table 의 필드를 매핑 (notnull 적용시에만 사용)
    private String name;
    private Integer age;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)//한 사용자당 여러 대출이 가능 , mappedBy -> 연관관계 주인의 필드
    //cascade 옵션 설정 : 유저가 삭제되면 해당 유저관련 DB도 삭제된다.
    //orphan(고아)Removal : 객체 간의 관계가 끊어진 데이터를 자동으로 제거하는 옵션
    private List<UserLoanHistory>userLoanHistories = new ArrayList<>();
//jpa 를 사용하기 위해서는 기본 생성자가 필요하다.
    protected User(){}
    public User(String name, Integer age) {
        if (name==null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
    public void updateName(String name){
        this.name = name;
    }
    //대출기능 리펙토링
    public void loanBook(String bookName){
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }
    public void returnBook(String bookName){
        //유저대출기록을 뒤져서 책이름을 비교하며 반납처리
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history->history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();

    }
}
