package com.example.service;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Long id, Book updatedBook) {
        Optional<Book> existingBookOptional = bookRepository.findById(id);
        Book newBook = new Book();
        if(existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();
            newBook.setAuthor(existingBook.getAuthor());
            newBook.setGenre(existingBook.getGenre());
            newBook.setTitle(existingBook.getTitle());
            newBook.setPublishedYear(existingBook.getPublishedYear());
            bookRepository.save(newBook);
        }
    }

    public boolean deleteBook(Long id) {
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
