package com.group.libraryapp.dto.user.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreateRequest {

    private String name;
    private Integer age; // Integer는 null 가능
}
