package com.group.libraryapp.dto.book.request;
//dto는 데이터 전송을 위해 생성되는 객체
//dto는 개발과 업데이트가 용이하다
//dto는 비즈니스 로직이 아닌 데이터만 저장해야 하며, 한 가지 작업만 수행하는 것이어야 한다.
public class BookCreateRequest {
    private String name;

    public String getName() {
        return name;
    }
}
