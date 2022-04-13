package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Film;

import java.util.List;

public interface FilmDAO extends CommonDAO<Film, Long> {
    List<Film> getFilmsByName(String filmName);
    Film getFilmByName(String filmName);
}