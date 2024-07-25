package com.group.libraryapp.domain.user;

import lombok.Getter;

import javax.persistence.*;

@Entity // 객체와 테이블 매핑
public class User {

    @Id // 프라이머리 키 설정 test
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가
    private Long id = null; // bigint = long

    @Column(nullable = false, length = 25, name = "name") // 널x, 길이제한, 매핑될 컬럼명 지정
    private String name;

    // 그외 제약조건 없으면 @Column 생략가능
    private Integer age;

    protected User() {
    }

    public User(String name, Integer age) {
        // 이름은 널이 되면 안 되니까 널 체크
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name (%s)이 들어왔습니다.", name)); // 변수를 통해 동적으로 문자열 생성
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name) {
        this.name = name;
    }


}
