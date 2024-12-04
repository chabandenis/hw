package ru.otus.hw.ex07.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.hw.ex07.models.Genre;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Repository
public class JpaGenreRepository implements GenreRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Genre> findAll() {
        var query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public List<Genre> findAllByIds(Set<Long> ids) {
        var query = em.createQuery("select g from Genre g where g.id in :genre");
        query.setParameter("genre", ids);

        return query.getResultList();
    }
}
