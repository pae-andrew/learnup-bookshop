package ru.learnUp.lesson22.bookShop.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.learnUp.lesson22.bookShop.Book;

@Repository
public class BookDao {

    private static final String FIND_BY_ID = "select * from public.book where book_name = :book_name";

    private static final String SAVE = "" +
            "insert into public.book (book_name, author, price) " +
            "values(:book_name, :author, :price)";

    private final NamedParameterJdbcTemplate template;

    public BookDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public void save(Book book) {
        template.update(
                SAVE,
                new MapSqlParameterSource()
                        .addValue("book_name", book.getBookName())
                        .addValue("author", book.getAuthorName())
                        .addValue("price", book.getPrice())
        );
    }

    public Book findByName(String bookName) {
        return template.query(
                        FIND_BY_ID,
                        new MapSqlParameterSource("book_name", bookName),
                        (rs, rn) -> Book.builder()
                                .bookName(rs.getString("book_name"))
                                .authorName(rs.getString("author"))
                                .price(rs.getInt("price"))
                                .build()
                ).stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("book with name = " + bookName + " is not found"));
    }
}
