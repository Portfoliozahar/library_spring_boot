package ru.app.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.app.springcourse.models.Person;
import java.util.List;


@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {
    List<Person> findByName(String name);
}
