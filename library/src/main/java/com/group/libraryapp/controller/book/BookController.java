package com.group.libraryapp.controller.book;

import com.group.libraryapp.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void saveBook(){
        bookService.saveBook();
    }
}
