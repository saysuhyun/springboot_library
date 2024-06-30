package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserCreateRequest request){
        //JPA상속 받은 레포지토리가 있으니까 기본 제공 함수로 sql없이 가능
        // User 객체를 반환
        User user= userRepository.save(new User(request.getName(),request.getAge()));

    }

    public List<UserResponse> getUsers(){

        // 모든 정보를 가지고 리스트로 반환하는 메서드
        List<User> users = userRepository.findAll();

        // 전체 User정보를 가지고 있다가 그걸 스트림으로 UserReponse객체에 넣어서 반환
        return users.stream().map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public void updateUser(UserUpdateRequest request){

        // id로 존재하는지 찾고 리턴으로 Optional<User>로 받는데 이 함수 중 없으면 에러 던지는 메서드를 사용해서 참거짓 파악
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        // 유저 이름 변경
        user.updateName(request.getName());

        // 변경된 유저로 새로 업데이트 -> save인데 바뀐거 기준으로 update 쿼리 날려줌
        userRepository.save(user);
    }

}

