package com.example.book_store.BookStore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookstoreRepository extends JpaRepository<Book,Long> {
}
