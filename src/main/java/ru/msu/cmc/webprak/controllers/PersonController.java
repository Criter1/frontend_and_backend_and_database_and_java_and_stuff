package ru.msu.cmc.webprak.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.webprak.DAO.PersonDAO;
import ru.msu.cmc.webprak.models.Person;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "api/person")
public class PersonController {

    @Autowired
    private PersonDAO personDAO;

    @GetMapping("/")
    public String getPersons() {
        List<Person> personListAll = (List<Person>) personDAO.getAll();
        JSONArray message = new JSONArray();
        for (Person person : personListAll) {
            JSONObject o = new JSONObject();
            o.put("id", person.getId().toString());
            o.put("name", person.getName());
            o.put("phone", person.getPhone());
            o.put("address", person.getAddress());
            message.add(o);
        }
        return message.toJSONString();
    }

    @GetMapping("{person_id}")
    public String getPersons(@PathVariable("person_id") long personId) {
        Person person = personDAO.getById(personId);
        JSONObject o = new JSONObject();
        o.put("id", person.getId().toString());
        o.put("name", person.getName());
        o.put("phone", person.getPhone());
        o.put("address", person.getAddress());
        return o.toJSONString();
    }

    @DeleteMapping("{person_id}")
    public void deletePerson(@PathVariable("person_id") long personId) {
        Person person = personDAO.getById(personId);
        personDAO.delete(person);
    }

    @PostMapping("/")
    public void addPerson(@RequestBody String body) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject o = (JSONObject) parser.parse(body);
        Long id = (Long) o.get("id");
        Person person = personDAO.getById(id);
        boolean is_new = false;
        if (person == null) {
            is_new = true;
            person = new Person();
            person.setId(id);
        }
        person.setName((String) o.get("name"));
        person.setAddress((String) o.get("address"));
        person.setPhone((String) o.get("phone"));
        if (is_new) personDAO.save(person);
        else personDAO.update(person);
    }
}
