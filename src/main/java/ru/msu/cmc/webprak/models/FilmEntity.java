package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film_entity")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FilmEntity implements CommonEntity<Long> {
    public enum carrier {
        NO_CARRIER,
        CASSETE,
        DISK,
        CASSETE_AND_DISK
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    @ToString.Exclude
    private Film film_id;

    @Column(name = "carrier_type")
    private carrier carrier_type;

    @Column(name = "is_vacant")
    private Boolean is_vacant;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmEntity other = (FilmEntity) o;
        return Objects.equals(id, other.id)
                && Objects.equals(film_id, other.film_id)
                && Objects.equals(carrier_type, other.carrier_type)
                && Objects.equals(is_vacant, other.is_vacant);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long aLong) {
        id = aLong;
    }
}