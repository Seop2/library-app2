package com.group.libraryapp.controller.user;
import java.util.List;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.fruit.FruitService;

import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;



/**
 * getter -> json으로 응답
 */
@RestController//api진입지점
public class UserController {
    //    UserController는 jdbc템플릿에 의존하고 있다!
    // build springDataJpa를 추가함으로 인해 import가 가능함!
//    public UserController(JdbcTemplate jdbcTemplate) {
//        this.userService = new UserService(jdbcTemplate);
//    }
    //@Autowired -> 필드에 직접 써서 주입할 수도 있다. -> 테스트 코드의 작성이 어렵다.
//    private UserService userService;
//    @Autowired
//    public void setUserService(UserService userService) {//setter + @Autowired를 사용하여 스프링 빈 등록
    // 하지만 누군가 setter를 사용할 경우 오작동할 수 있다.
//        this.userService = userService;
//    }
    /**
     * @RestController는 UserController 클래스를 스프링 빈으로 등록시킨다.
     *  1. API의 진입 지점으로서 HTTP Body를 객체로 변환 @Controller
     *  2. 현재 유저가 있는지 없는 지 확인하고 예외처리 @Service
     *  3. sql을 활용해 실제 DB와 통신 @Repository
     * 도메인별로 컨트롤러를 나누는 것이 좋다.
     * 유저 등록 api 개발
     * HTTP method : post
     * HTTP path : /user
     * HTTP Body : json
     * 결과 반환x
     *
     * 스프링 컨테이너는 서로 관계있는 스프링 빈을 연결시켜주는 역할을 한다.
     * 서버 시작 시 1. 스프링 컨테이너 시작 -> 2. 스프링 빈 등록(기본) -> 3. 스프링 빈 등록(개인) -> 4. 필요한 의존성 자동으로 설정
     */
    private final UserServiceV2 userService;
    private final FruitService fruitService;

//    public UserController(UserService userService) {//생성자 사용(@Autowired 생략 가능)
//        this.userService = userService;
//    }

    /**
     * @Qualifier - 스프링 빈을 사용하거나, 스프링 빈을 등록하는 쪽 모두 사용가능!
     * @Qualifier vs @Primary -> 보다 직접적인 @Qualifier 승!
     */
    public UserController(UserServiceV2 userService, @Qualifier("main") FruitService fruitService) {
        this.userService = userService;
        this.fruitService = fruitService;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        userService.saveUser(request);
    }
    //get 방식의 유저 조회 api 개발
    /**
     * HTTP method : GET
     * HTTP path : /user
     * 쿼리 없음
     * 결과 반환
     * GET API 변경
     *      * - jdbcTemplate.query(sql, RowMapper 구현 익명클래스)
     *      * - sql 데이터를 userResponse 객체로 변환
     */
    @GetMapping("/user")
    public List<UserResponse> getUsers(){
        return userService.getUsers();
    }
    // 유저 업데이트 api
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        userService.updateUser(request);
    }
    //유저 삭제 api
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        userService.deleteUser(name);
    }




}
/**
 * api 동작과정
 * -cpu (연산) , RAM(메모리, 단기기억) , DISK(장기기록)
 * - 서버가 종료되면 RAM에 있는 모든 정보는 사라짐
 * - 유저 정보를 저장해도 종료 후 다시 시작하면 초기화가 됨
 * - 서버를 통해 disk에 저장하기 위해서는 데이터베이스를 사용한다.
 *  -bigint : 8바이트 정수
 *  > id는 혹시나 21억건을 넘을 수도 있으 가장 큰 bigint을 사용한다.
 *  varchar(A) : 최대 A 글자가 들어갈 수 있는 문자열
 */
