package ru.alishev.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.services.PeopleService;

/**
 * @author Neil Alishev
 */
@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
//        Person person = (Person) target;
//
//        Person foundPerson = peopleService.findByName(person.getName());
//
//        if (foundPerson != null && foundPerson.getId() != person.getId()) {
//            errors.rejectValue("name", "", "Человек с таким именем уже существует");
//        }
    }

}