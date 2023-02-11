package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 영속성 컨텍스트란? 테이블과 매핑된 Entity 객체를 관리/보관하는 역할
 * 스프링에서는 트랜잭션을 종료하면 영속성 컨텍스트가 생겨나고, 트랜잭션이 종료되면 영속성 컨텍스트가 종료된다.
 * 영속성 컨텍스트의 특수 능력
 * 1. 변경 감지 (save메서드 없이도 자동으로 수정한 값이 저장됨)
 * 2. 쓰기 지연 (DB에 쿼리를 한번에 날림(commit))
 * - 여러번의 저장이 아니라 한번에 영속성 컨텍스트가 기억해서 한꺼번에 저장한다.(id를 기준으로 기억)
 * 3. 1차 캐싱
 * 4. 지연 로딩 : 꼭 필요한 순간에 데이터를 로딩한다.
 */
@Service
public class UserServiceV2 {
    private final UserRepository userRepository;//interface(jpa상속)

    public UserServiceV2(UserRepository userRepository) {//생성자를 통해 주입
        this.userRepository = userRepository;
    }
    //유저 저장 기능
    //@Transactional : 아래 있는 함수가 시작될 때 start transaction 을 해준다.
    // 함수가 예외 없이 잘 끝났다면 commit
    // 혹시라도 문제가 있다면 rollback
    @Transactional//트랜잭션 적용
    public void saveUser(UserCreateRequest request) {
        //insert 쿼리 자동 생성
        userRepository.save(new User(request.getName(), request.getAge()));
    }
    //유저 조회
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                //findAll : 주어지는 객체가 매핑된 테이블의 모든 데이터를 가져온다.
                .map(user -> new UserResponse(user.getId(),user.getName(),user.getAge()))
                .collect(Collectors.toList());//매핑


    }
    //유저 업데이트 기능
    @Transactional
    public void updateUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId())
                //findById : id를 기준으로 특정한 1개의 데이터를 가져온다.
                .orElseThrow(IllegalAccessError::new);//optional
        //User가 없다면 예외 호출
        user.updateName(request.getName());
        userRepository.save(user);//객체를 업데이트 후 save메서드를 호출하여 저장한다.
    }
//유저 삭제 기능
    //유저가 있는지 확인 후 삭제
    @Transactional
    public void deleteUser(String name) {
        //select * from user WHERE name =?
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);//delete sql
    }
}
