package ru.alishev.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.BookRepository;
import ru.alishev.springcourse.repositories.PeopleRepository;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BookService(BookRepository booksRepository,
                       PeopleRepository peopleRepository) {
        this.bookRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findOne(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public void assign(int bookId, int personId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Person person = peopleRepository.findById(personId).orElse(null);

        if (book != null && person != null) {
            book.setOwner(person);
            bookRepository.save(book);
        }
    }

    public void release(int bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);

        if (book != null) {
            book.setOwner(null);
            bookRepository.save(book);
        }
    }
}