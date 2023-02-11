package com.group.libraryapp.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Domain -> Repository - > DTO ( API 스펙 확인 )
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book>findByName(String name);
/**
 * Optional 은 null을 반환하면 오류가 발생할 가능성이 매우 높은 경우에 '결과없음'을 명확하게 드러내기 위해
 * 메소드의 반환타입으로 사용되도록 매우 제한적인 경우로 설계
 * 잘못 사용할 경우 NoSuchElementException 문제나 오히려 코드의 가독성이 떨어질수 있으므로 잘 사용해야 한다.
 *
 * 올바른 사용법
 * Optional 변수에 Null을 할당하지 말아라
 * 값이 없을때 Optional.orElseX()로 기본값을 반환하라
 * 단순히 값을 얻으려는 목적으로만 Optional 넘기지 마라
 * 생성자, 수정자, 메소드 파라미터 등으로 Optional을 넘기지 마라
 * Collection의 경우 Optional이 아닌 빈 Collection을 사용하라
 * 반환타입으로만 사용하라
 *
 */
}
