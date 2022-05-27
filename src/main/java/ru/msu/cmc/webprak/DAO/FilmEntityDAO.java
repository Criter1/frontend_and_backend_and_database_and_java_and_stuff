package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.FilmEntity;

import java.util.List;

public interface FilmEntityDAO extends CommonDAO<FilmEntity, Long> {
    public List<FilmEntity> getFilmEntitiesByFilmID(Long id);
}