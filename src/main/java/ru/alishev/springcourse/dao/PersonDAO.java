package ru.alishev.springcourse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class PersonDAO {



//    private final SessionFactory sessionFactory;
//
//
//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional
//    public List<Person> index() {
//        return sessionFactory.getCurrentSession()
//                .createQuery("from Person", Person.class)
//                .getResultList();
//    }
//
//
//    @Transactional
//    public Person show(int id) {
//        return sessionFactory.getCurrentSession()
//                .createQuery(
//                        "select p from Person p left join fetch p.books where p.id = :id",
//                        Person.class)
//                .setParameter("id", id)
//                .uniqueResult();
//    }
//
//
//    @Transactional
//    public void save(Person person) {
//        sessionFactory.getCurrentSession().save(person);
//    }
//
//
//    @Transactional
//    public void update(int id, Person updatedPerson) {
//        Session session = sessionFactory.getCurrentSession();
//        Person person = session.get(Person.class, id);
//
//        person.setName(updatedPerson.getName());
//        person.setAge(updatedPerson.getAge());
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//
//        session.delete(session.get(Person.class, id));
//
//
//    }
//
//    @Transactional
//    public Person findByName(String name) {
//        return sessionFactory.getCurrentSession()
//                .createQuery("from Person p where p.name = :name", Person.class)
//                .setParameter("name", name)
//                .uniqueResult();
//    }
}
