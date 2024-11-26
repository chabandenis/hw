package ru.otus.hw.ex06.models;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(name = "Book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genres_id"))
    private List<Genre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "book", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();





/*    @ElementCollection
    @Column(name = "comment")
    @CollectionTable(name = "Book_comments", joinColumns = @JoinColumn(name = "book_id"))
    private List<Comment> comment = new ArrayList<>();

 */


}
