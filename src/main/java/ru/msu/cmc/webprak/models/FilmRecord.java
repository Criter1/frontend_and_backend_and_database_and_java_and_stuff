package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film_record")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FilmRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person")
    @ToString.Exclude
    @NonNull
    private Person id_person;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_film_entity")
    @ToString.Exclude
    @NonNull
    private FilmEntity id_film_entity;

    @Column(nullable = false, name = "date_given")
    @NonNull
    private String date_given;

    @Column(nullable = false, name = "date_recieved")
    @NonNull
    private String date_recieved;

    @Column(nullable = false, name = "price")
    @NonNull
    private float price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmRecord other = (FilmRecord) o;
        return Objects.equals(id, other.id)
                && id_person.equals(other.id_person)
                && id_film_entity.equals(other.id_film_entity)
                && Objects.equals(date_given, other.date_given)
                && Objects.equals(date_recieved, other.date_recieved)
                && Objects.equals(price, other.price);
    }
}