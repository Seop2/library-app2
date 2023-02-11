package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository 붙일 필요없이 jpa를 상속받으면서 알아서 스프링 빈으로 등록이 된다.
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);//이름을 기준으로 찾는 함수(By)

}
