package com.group.libraryapp.repository.book;


import com.group.libraryapp.domain.book.Book;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary // 제어의 역전 ... 애노테이션으로 스프링컨테이너가 뭘 넣을지 우선순위를 알려서 넣도록함
@Repository
public class BookMySqlRepository  {


}
