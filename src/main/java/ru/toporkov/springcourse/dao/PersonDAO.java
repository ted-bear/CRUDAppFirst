package ru.toporkov.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.toporkov.springcourse.models.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from Person where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into Person values(1, ?, ?, ?)", person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("update Person set name=?, age=?, email=? where id=?",
                updatedPerson.getName(),
                updatedPerson.getAge(),
                updatedPerson.getEmail(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id=?", id);
    }
}
