package backend.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import backend.model.NotifCategory;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 public interface NotifCategoryRepository {
     NotifCategory save(NotifCategory notifCategory);
     NotifCategory update(Integer id, NotifCategory notifCategory);
     List<NotifCategory> findAll();
     NotifCategory findById(@NotNull Integer id);
     void deleteById(@NotNull Integer id);
 }