package ru.learnUp.lesson22.bookShop.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.learnUp.lesson22.bookShop.Author;

@Repository
public class AuthorDao {

    private static final String FIND_BY_ID = "select * from public.author where id = :id";

    private static final String SAVE = "" +
            "insert into public.author (name, surname, books) " +
            "values(:name, :surname, :books)";

    private final NamedParameterJdbcTemplate template;

    public AuthorDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public void save(Author author) {
        template.update(
                SAVE,
                new MapSqlParameterSource()
                        .addValue("name", author.getName())
                        .addValue("surname", author.getSurname())
                        .addValue("books", author.getBooks())
        );
    }

    public Author findById(int id) {
        return template.query(
                        FIND_BY_ID,
                        new MapSqlParameterSource("id", id),
                        (rs, rn) -> Author.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .surname(rs.getString("surname"))
                                .books(rs.getString("books"))
                                .build()
                ).stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("author with id = " + id + " is not found"));
    }
}
