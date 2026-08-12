package ru.app.springcourse.dao;

import org.springframework.stereotype.Repository;

@Repository
public class BookDAO {

//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public BookDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional
//    public List<Book> index() {
//        return sessionFactory.getCurrentSession()
//                .createQuery("from Book", Book.class)
//                .getResultList();
//    }
//
//    @Transactional
//    public Book show(int id) {
//        return sessionFactory.getCurrentSession().get(Book.class, id);
//    }
//
//    @Transactional
//    public void save(Book book) {
//        sessionFactory.getCurrentSession().save(book);
//    }
//
//    @Transactional
//    public void update(int id, Book updatedBook) {
//        Session session = sessionFactory.getCurrentSession();
//
//        Book book = session.get(Book.class, id);
//
//        book.setTitle(updatedBook.getTitle());
//        book.setAuthor(updatedBook.getAuthor());
//        book.setYear(updatedBook.getYear());
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//
//         session.remove(session.get(Book.class, id));
//
//
//    }
//
//    @Transactional
//    public void assign(int bookId, int personId) {
//        Session session = sessionFactory.getCurrentSession();
//
//        Book book = session.get(Book.class, bookId);
//        Person person = session.get(Person.class, personId);
//
//        book.setOwner(person);
//    }
//
//    @Transactional
//    public void release(int bookId) {
//        Session session = sessionFactory.getCurrentSession();
//
//        Book book = session.get(Book.class, bookId);
//
//        book.setOwner(null);
//    }
}