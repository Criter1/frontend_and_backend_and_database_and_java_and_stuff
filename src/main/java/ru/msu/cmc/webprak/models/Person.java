package ru.msu.cmc.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "phone")
    @NonNull
    private String phone;

    @Column(nullable = false, name = "address")
    @NonNull
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person other = (Person) o;
        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && phone.equals(other.phone)
                && Objects.equals(address, other.address);
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