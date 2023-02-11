package com.group.libraryapp.config;

import org.springframework.context.annotation.Configuration;

@Configuration//@Bean 사용
public class UserConfiguration {
//    @Bean
//    public UserRepository userRepository(JdbcTemplate jdbcTemplate){
//        return new UserRepository(jdbcTemplate);
//    }
}
/**
 * 언제 @Service @Repository를 사용해야 할까?
 * - 개발자가 직접 만든 클래스를 스프링 빈으로 등록할때
 */
/**
 * 언제 @Configuration + @Bean 을 사용해야 할까?
 * - 외부 라이브러리, 프레임워크에서 만든 클래스를 등록할 때
 *
 *
 * @Component
 * - 주어진 클래스를 컴포넌트로 간주한다.
 * - 이 클래스들은 스프링 서버가 뜰때 자동으로 감지됨.
 * @Component 덕분에 우리가 사용했던 어노테이션이 자동감지되었다.
 */