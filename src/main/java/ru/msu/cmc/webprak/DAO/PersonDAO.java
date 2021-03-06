package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Person;

import java.util.List;

public interface PersonDAO extends CommonDAO<Person, Long> {
    List<Person> getPersonsByName(String personName);
    Person getPersonByName(String personName);
}