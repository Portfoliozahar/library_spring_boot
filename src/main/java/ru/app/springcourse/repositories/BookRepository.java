package ru.app.springcourse.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.app.springcourse.models.Book;
import ru.app.springcourse.models.Person;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{
    List<Book> findByTitle(String title);

    List<Book> findByOwner (Person owner);
}
