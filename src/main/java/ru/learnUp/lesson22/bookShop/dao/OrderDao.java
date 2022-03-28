package ru.learnUp.lesson22.bookShop.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.learnUp.lesson22.bookShop.Order;

@Repository
public class OrderDao {

    private static final String FIND_BY_ID = "select * from public.order where id = :id";

    private static final String SAVE = "" +
            "insert into public.order (user_name, book_name, book_count) " +
            "values(:user_name, :book_name, :book_count)";

    private final NamedParameterJdbcTemplate template;

    public OrderDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public void save(Order order) {
        template.update(
                SAVE,
                new MapSqlParameterSource()
                        .addValue("user_name", order.getUserName())
                        .addValue("book_name", order.getBookName())
                        .addValue("book_count", order.getCount())
        );
    }

    public Order findById(int id) {
        return template.query(
                        FIND_BY_ID,
                        new MapSqlParameterSource("id", id),
                        (rs, rn) -> Order.builder()
                                .id(rs.getInt("id"))
                                .userName(rs.getString("user_name"))
                                .bookName(rs.getString("book_name"))
                                .count(rs.getInt("book_count"))
                                .build()
                ).stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("order with id = " + id + " is not found"));
    }
}
