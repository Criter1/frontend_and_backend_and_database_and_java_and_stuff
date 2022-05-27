package ru.msu.cmc.webprak.controllers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.msu.cmc.webprak.DAO.FilmDAO;
import ru.msu.cmc.webprak.DAO.FilmEntityDAO;
import ru.msu.cmc.webprak.models.FilmEntity;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = "api/filmentity")
public class FilmEntityController {

    @Autowired
    private FilmEntityDAO filmEntityDAO;
    @Autowired
    private FilmDAO filmDAO;

    @GetMapping("/")
    public String getFilmEntitys() {
        List<FilmEntity> filmEntityListAll = (List<FilmEntity>) filmEntityDAO.getAll();
        JSONArray message = new JSONArray();
        for (FilmEntity filmEntity : filmEntityListAll) {
            JSONObject o = new JSONObject();
            o.put("id", filmEntity.getId().toString());
            o.put("film_id", filmEntity.getFilm_id().getId());
            FilmEntity.carrier carrier_type_t = filmEntity.getCarrier_type();
            Long carrier_type = 0L;
            if (carrier_type_t == FilmEntity.carrier.NO_CARRIER) {
                carrier_type = 0L;
            } else if (carrier_type_t == FilmEntity.carrier.CASSETE) {
                carrier_type = 1L;
            } else if (carrier_type_t == FilmEntity.carrier.DISK) {
                carrier_type = 2L;
            } else if (carrier_type_t == FilmEntity.carrier.CASSETE_AND_DISK) {
                carrier_type = 3L;
            }
            o.put("carrier_type", carrier_type);
            o.put("is_vacant", filmEntity.getIs_vacant());
            message.add(o);
        }
        return message.toJSONString();
    }

    @GetMapping("/film/{film_id}")
    public String getFilmEntitiesByFilmID(@PathVariable("film_id") Long filmID) {
        List<FilmEntity> filmEntityListAll = filmEntityDAO.getFilmEntitiesByFilmID(filmID);
        JSONArray message = new JSONArray();
        for (FilmEntity filmEntity : filmEntityListAll) {
            JSONObject o = new JSONObject();
            o.put("id", filmEntity.getId().toString());
            o.put("film_id", filmEntity.getFilm_id().getId());
            FilmEntity.carrier carrier_type_t = filmEntity.getCarrier_type();
            Long carrier_type = 0L;
            if (carrier_type_t == FilmEntity.carrier.NO_CARRIER) {
                carrier_type = 0L;
            } else if (carrier_type_t == FilmEntity.carrier.CASSETE) {
                carrier_type = 1L;
            } else if (carrier_type_t == FilmEntity.carrier.DISK) {
                carrier_type = 2L;
            } else if (carrier_type_t == FilmEntity.carrier.CASSETE_AND_DISK) {
                carrier_type = 3L;
            }
            o.put("carrier_type", carrier_type);
            o.put("is_vacant", filmEntity.getIs_vacant());
            message.add(o);
        }
        return message.toJSONString();
    }

    @GetMapping("{filmEntity_id}")
    public String getFilmEntitys(@PathVariable("filmEntity_id") long filmEntityId) {
        FilmEntity filmEntity = filmEntityDAO.getById(filmEntityId);
        JSONObject o = new JSONObject();
        o.put("id", filmEntity.getId().toString());
        o.put("film_id", filmEntity.getFilm_id().getId());
        FilmEntity.carrier carrier_type_t = filmEntity.getCarrier_type();
        Long carrier_type = 0L;
        if (carrier_type_t == FilmEntity.carrier.NO_CARRIER) {
            carrier_type = 0L;
        } else if (carrier_type_t == FilmEntity.carrier.CASSETE) {
            carrier_type = 1L;
        } else if (carrier_type_t == FilmEntity.carrier.DISK) {
            carrier_type = 2L;
        } else if (carrier_type_t == FilmEntity.carrier.CASSETE_AND_DISK) {
            carrier_type = 3L;
        }
        o.put("carrier_type", carrier_type);
        o.put("is_vacant", filmEntity.getIs_vacant());
        return o.toJSONString();
    }

    @DeleteMapping("{filmEntity_id}")
    public void deleteFilmEntity(@PathVariable("filmEntity_id") long filmEntityId) {
        FilmEntity filmEntity = filmEntityDAO.getById(filmEntityId);
        filmEntityDAO.delete(filmEntity);
    }

    @PostMapping("/")
    public void addFilmEntity(@RequestBody String body) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject o = (JSONObject) parser.parse(body);
        Long id = Long.parseLong((String) o.get("id"));
        FilmEntity filmEntity = filmEntityDAO.getById(id);
        boolean is_new = false;
        if (filmEntity == null) {
            is_new = true;
            filmEntity = new FilmEntity();
            filmEntity.setId(id);
        }
        filmEntity.setFilm_id(filmDAO.getById(Long.parseLong((String) o.get("film_id"))));
        Long carrier_type = Long.parseLong((String) o.get("carrier_type"));
        if (carrier_type == 0L) {
            filmEntity.setCarrier_type(FilmEntity.carrier.NO_CARRIER);
        } else if (carrier_type == 1L) {
            filmEntity.setCarrier_type(FilmEntity.carrier.CASSETE);
        } else if (carrier_type == 2L) {
            filmEntity.setCarrier_type(FilmEntity.carrier.DISK);
        } else if (carrier_type == 3L) {
            filmEntity.setCarrier_type(FilmEntity.carrier.CASSETE_AND_DISK);
        }
        filmEntity.setIs_vacant(Boolean.parseBoolean((String) o.get("name")));
        if (is_new) filmEntityDAO.save(filmEntity);
        else filmEntityDAO.update(filmEntity);
    }
}
