package com.group.libraryapp.domain.user;

import lombok.Getter;

@Getter
public class User {

    private String name;
    private Integer age;

    public User(String name, Integer age) {
        // 이름은 널이 되면 안 되니까 널 체크
        if(name == null || name.isBlank() ){
            throw new IllegalArgumentException(String.format("잘못된 name (%s)이 들어왔습니다.",name)); // 변수를 통해 동적으로 문자열 생성
        }
        this.name = name;
        this.age = age;
    }
}
