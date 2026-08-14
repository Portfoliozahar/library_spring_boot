package ru.app.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.app.springcourse.models.Book;
import ru.app.springcourse.models.Person;
import ru.app.springcourse.repositories.BookRepository;
import ru.app.springcourse.repositories.PeopleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
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

    public Page<Book> findAll(int page, int booksPerPage, boolean sortByYear) {

        Pageable pageable;

        if (sortByYear) {
            pageable = PageRequest.of(
                    page,
                    booksPerPage,
                    Sort.by("year").ascending()
            );
        } else {
            pageable = PageRequest.of(page, booksPerPage);
        }

        return bookRepository.findAll(pageable);
    }

    public List<Book> search(String title) {
        return bookRepository.findByTitleStartingWith(title);
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

    @Transactional
    public void assign(int bookId, int personId) {

        Book book = bookRepository.findById(bookId).orElse(null);
        Person person = peopleRepository.findById(personId).orElse(null);

        if (book != null && person != null) {
            book.setOwner(person);
            book.setTakenAt(LocalDateTime.now());

            bookRepository.save(book);
        }
    }

    @Transactional
    public void release(int bookId) {

        Book book = bookRepository.findById(bookId).orElse(null);

        if (book != null) {
            book.setOwner(null);
            book.setTakenAt(null);

            bookRepository.save(book);
        }
    }

    public boolean isOverdue(Book book) {

        if (book.getTakenAt() == null) {
            return false;
        }

        return book.getTakenAt()
                .plusDays(10)
                .isBefore(LocalDateTime.now());
    }
}