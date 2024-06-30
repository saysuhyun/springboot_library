package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// 객체 User , 아이디가 Long
// @Repository 없어도 JpaRepository 상속시 빈 등록 됨
public interface UserRepository extends JpaRepository<User, Long> {

}
