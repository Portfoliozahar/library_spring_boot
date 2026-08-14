package ru.app.springcourse.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.app.springcourse.models.Book;
import ru.app.springcourse.models.Person;
import ru.app.springcourse.repositories.PeopleRepository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {

        Person person = peopleRepository.findById(id).orElse(null);

        if (person != null) {
            for (Book book : person.getBooks()) {
                book.setOverdue(isOverdue(book));
            }
        }

        return person;
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Person findByName(String name) {
        List<Person> people = peopleRepository.findByName(name);
        return people.isEmpty() ? null : people.get(0);
    }

    private boolean isOverdue(Book book) {

        if (book.getTakenAt() == null) {
            return false;
        }

        return book.getTakenAt()
                .plusDays(10)
                .isBefore(LocalDateTime.now());
    }
}