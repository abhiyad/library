package com.springframework.services;

import com.springframework.domain.Book;
import com.springframework.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public void saveBook(String name, int copies, String author) {
        Book book = new Book(name, author, copies);
        repository.save(book);
    }
    public List<Book> listAll(){
        return repository.findAll();
    }
    public Book findByBookName(String name){
        return repository.findBookByName(name);
    }
}
