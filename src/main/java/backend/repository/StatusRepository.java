package backend.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import backend.model.Status;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 /**
  * StatusRepository
  */
 public interface StatusRepository {
 
     Status save(Status status);
     Status update(Integer id, Status status);
     List<Status> findAll();
     Status findById(@NotNull Integer id);
     void deleteById(@NotNull Integer id);
 }