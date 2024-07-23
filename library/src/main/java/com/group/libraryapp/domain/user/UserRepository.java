package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 객체 User , 아이디가 Long
// @Repository 없어도 JpaRepository 상속시 빈 등록 됨
// 굳이 구현 안 해도 기본적으로 제공해주는 거 사용 가능
public interface UserRepository extends JpaRepository<User, Long> {

    // 규칙에 맞춰서 함수이름을 작성하면 알아서 만들어줌
    // 이름을 기준으로 찾아서 있으면 User반환 없으면 null 반환
    Optional<User> findByName(String name);
}
