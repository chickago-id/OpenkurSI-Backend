package backend.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import backend.model.Day;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

public interface DayRepository {
    Day save(Day day);
    Day update(Integer id, Day day);
    List<Day> findAll();
    Day findById(@NotNull Integer id);
    void deleteById(@NotNull Integer id);
}

