package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.Film;
import ru.msu.cmc.webprak.models.Person;

import java.util.List;

public interface FilmDAO {
    void addFilm(Film film);
    void updateFilm(Film film);
    void deleteFilm(Film film);

    Film getFilmById(Long filmId);
    List<Film> getFilmByName(String filmName);
    List<Film> getFilmAll();
}