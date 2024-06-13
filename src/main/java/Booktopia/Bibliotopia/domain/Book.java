package Booktopia.Bibliotopia.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorValue("BOOK")
public class Book extends Item{
    private String isbn;
    @Enumerated(EnumType.STRING)
    private List<Genre> genres = new ArrayList<>();

}
