package backend.repository;

import backend.model.*;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserProfileRepository {

    UserProfile save(@NotNull UserProfile userProfile);

    UserProfile update(@NotNull UserProfile userProfile);

    List<UserProfile> findAll();

    UserProfile findById(@NotNull Long id_user_profile);

    void deleteById(@NotNull Long id_user_profile);
}