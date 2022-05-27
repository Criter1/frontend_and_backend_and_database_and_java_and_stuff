package ru.msu.cmc.webprak.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.webprak.DAO.FilmDAO;
import ru.msu.cmc.webprak.models.Film;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "api/film")
public class FilmController {

    @Autowired
    private FilmDAO filmDAO;

    @GetMapping("/")
    public String getFilms() {
        List<Film> filmListAll = (List<Film>) filmDAO.getAll();
        JSONArray message = new JSONArray();
        for (Film film : filmListAll) {
            JSONObject o = new JSONObject();
            o.put("id", film.getId().toString());
            o.put("name", film.getName());
            o.put("author", film.getAuthor());
            o.put("company", film.getCompany());
            o.put("year", film.getYear());
            o.put("disk_price", film.getDisk_price());
            o.put("cassete_price", film.getCassete_price());
            message.add(o);
        }
        return message.toJSONString();
    }

    @GetMapping("{film_id}")
    public String getFilms(@PathVariable("film_id") long filmId) {
        Film film = filmDAO.getById(filmId);
        JSONObject o = new JSONObject();
        o.put("id", film.getId().toString());
        o.put("name", film.getName());
        o.put("author", film.getAuthor());
        o.put("company", film.getCompany());
        o.put("year", film.getYear());
        o.put("disk_price", film.getDisk_price());
        o.put("cassete_price", film.getCassete_price());
        return o.toJSONString();
    }

    @DeleteMapping("{film_id}")
    public void deleteFilm(@PathVariable("film_id") long filmId) {
        Film film = filmDAO.getById(filmId);
        filmDAO.delete(film);
    }

    @PostMapping("/")
    public void addFilm(@RequestBody String body) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject o = (JSONObject) parser.parse(body);
        Long id = (Long) o.get("id");
        Film film = filmDAO.getById(id);
        boolean is_new = false;
        if (film == null) {
            is_new = true;
            film = new Film();
            film.setId(id);
        }
        film.setCompany((String) o.get("company"));
        film.setDisk_price(Float.parseFloat((String) o.get("disk_price")));
        film.setName((String) o.get("name"));
        film.setYear(Long.parseLong((String) o.get("year")));
        film.setCassete_price(Float.parseFloat((String) o.get("cassete_price")));
        film.setAuthor((String) o.get("author"));
        if (is_new) filmDAO.save(film);
        else filmDAO.update(film);
    }
}
