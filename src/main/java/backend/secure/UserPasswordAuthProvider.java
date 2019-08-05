package backend.secure;

import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.Optional;

import backend.model.User;
import backend.model.UserDetail;
import backend.UserRepository;
import backend.helpers.MD5;

@Singleton
public class UserPasswordAuthProvider implements AuthenticationProvider {

    @Inject
    UserRepository userRepository;

    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest req) {
        String username = req.getIdentity().toString();
        String password = req.getSecret().toString();

        Optional<User> userData = userRepository.findByUsername(username);

        if (userData.isPresent()) {

            String passwordHash = MD5.getMd5(password);
            // compare password
            if(passwordHash.equals(user.getPassword())) {
                UserDetails details = new UserDetails(username, Collections.singletonList(userData.get().getRole()));
                return Flowable.just(details);
            } else {
                return Flowable.just(new AuthenticationFailed());
            }
        } else {
            return Flowable.just(new AuthenticationFailed());
        }
    }
}