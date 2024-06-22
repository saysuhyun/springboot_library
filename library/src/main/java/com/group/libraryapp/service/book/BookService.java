package com.group.libraryapp.service.book;

import com.group.libraryapp.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void saveBook(){
        bookRepository.saveBook();
    }

}
