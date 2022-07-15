package com.example.book_store.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookstoreNotFoundException extends RuntimeException{
    public BookstoreNotFoundException(String message){
        super(message);
    }
}
