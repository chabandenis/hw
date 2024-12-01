package ru.otus.hw.ex06.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@jakarta.persistence.Table(name = "Books")
@Table(name = "books")

@NamedEntityGraph(name = "book-genre-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("genres")
                //,@NamedAttributeNode("commentBook")
        })

@NamedEntityGraph(name = "book-comment-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("commentBook")
        })

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(name = "Books_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres = new ArrayList<>();


    @OneToMany(mappedBy = "book", orphanRemoval = true)
    private List<CommentBook> commentBook;
}
