package ru.msu.cmc.webprak.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.webprak.DAO.FilmEntityDAO;
import ru.msu.cmc.webprak.DAO.FilmRecordDAO;
import ru.msu.cmc.webprak.DAO.PersonDAO;
import ru.msu.cmc.webprak.models.FilmEntity;
import ru.msu.cmc.webprak.models.FilmRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "api/filmrecord")
public class FilmRecordController {

    @Autowired
    private FilmRecordDAO filmRecordDAO;
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private FilmEntityDAO filmEntityDAO;

    @GetMapping("/")
    public String getFilmRecords() {
        List<FilmRecord> filmRecordListAll = (List<FilmRecord>) filmRecordDAO.getAll();
        JSONArray message = new JSONArray();
        for (FilmRecord filmRecord : filmRecordListAll) {
            JSONObject o = new JSONObject();
            o.put("id", filmRecord.getId());
            o.put("id_person", filmRecord.getId_person().getId());
            o.put("id_film_entity", filmRecord.getId_film_entity().getId());
            o.put("date_given", filmRecord.getDate_given().toString().split(" ")[0]);
            java.util.Date date = filmRecord.getDate_recieved();
            if (date != null) o.put("date_recieved", filmRecord.getDate_recieved().toString().split(" ")[0]);
            else o.put("date_recieved", "null");
            o.put("price", filmRecord.getPrice());
            message.add(o);
        }
        return message.toJSONString();
    }

    @GetMapping("/person/{filmRecord_id}")
    public String getFilmEntitys(@PathVariable("filmRecord_id") long filmRecordID) {
        List<FilmRecord> filmRecordList = filmRecordDAO.getFilmRecordsByPersonID(filmRecordID);
        JSONArray message = new JSONArray();
        for (FilmRecord filmRecord : filmRecordList) {
            JSONObject o = new JSONObject();
            o.put("id", filmRecord.getId());
            o.put("id_person", filmRecord.getId_person().getId());
            o.put("id_film_entity", filmRecord.getId_film_entity().getId());
            o.put("date_given", filmRecord.getDate_given().toString().split(" ")[0]);
            java.util.Date date = filmRecord.getDate_recieved();
            if (date != null) o.put("date_recieved", filmRecord.getDate_recieved().toString().split(" ")[0]);
            else o.put("date_recieved", "null");
            o.put("price", filmRecord.getPrice());
            message.add(o);
        }
        return message.toJSONString();
    }

    @GetMapping("{filmRecord_id}")
    public String getFilmRecords(@PathVariable("filmRecord_id") long filmRecordId) {
        FilmRecord filmRecord = filmRecordDAO.getById(filmRecordId);
        JSONObject o = new JSONObject();
        o.put("id", filmRecord.getId().toString());
        o.put("id_person", filmRecord.getId_person().getId());
        o.put("id_film_entity", filmRecord.getId_film_entity().getId());
        o.put("date_given", filmRecord.getDate_given().toString().split(" ")[0]);
        java.util.Date date = filmRecord.getDate_recieved();
        if (date != null) o.put("date_recieved", filmRecord.getDate_recieved().toString().split(" ")[0]);
        else o.put("date_recieved", "null");
        o.put("price", filmRecord.getPrice());
        return o.toJSONString();
    }

    @DeleteMapping("{filmRecord_id}")
    public void deleteFilmRecord(@PathVariable("filmRecord_id") long filmRecordId) {
        FilmRecord filmRecord = filmRecordDAO.getById(filmRecordId);
        filmRecordDAO.delete(filmRecord);
    }

    @PostMapping("/")
    public void addFilmRecord(@RequestBody String body) throws ParseException, java.text.ParseException {
        JSONParser parser = new JSONParser();
        JSONObject o = (JSONObject) parser.parse(body);
        Long id = Long.parseLong((String) o.get("id"));
        FilmRecord filmRecord = filmRecordDAO.getById(id);
        boolean is_new = false;
        if (filmRecord == null) {
            is_new = true;
            filmRecord = new FilmRecord();
            filmRecord.setId(id);
        }
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        filmRecord.setId_person(personDAO.getById(Long.parseLong((String) o.get("id_person"))));
        FilmEntity fe = filmEntityDAO.getById(Long.parseLong((String) o.get("id_film_entity")));
        filmRecord.setId_film_entity(fe);
        filmRecord.setPrice(Float.parseFloat((String) o.get("price")));
        filmRecord.setDate_given(formatter.parse((String) o.get("date_given")));
        String date_recieved = (String) o.get("date_recieved");
        if (!Objects.equals(date_recieved, "null")) filmRecord.setDate_recieved(formatter.parse(date_recieved));
        else filmRecord.setDate_recieved(null);
        if (is_new) filmRecordDAO.save(filmRecord);
        else filmRecordDAO.update(filmRecord);
    }
}
