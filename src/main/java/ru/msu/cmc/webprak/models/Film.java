package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private long id;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "author")
    @NonNull
    private String author;

    @Column(nullable = false, name = "company")
    @NonNull
    private String company;

    @Column(nullable = false, name = "year")
    @NonNull
    private long year;

    @Column(nullable = false, name = "cassete_price")
    @NonNull
    private float cassete_price;

    @Column(nullable = false, name = "disk_price")
    @NonNull
    private float disk_price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film other = (Film) o;
        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && author.equals(other.author)
                && Objects.equals(company, other.company)
                && Objects.equals(cassete_price, other.cassete_price)
                && Objects.equals(disk_price, other.disk_price)
                && Objects.equals(year, other.year);
    }
}