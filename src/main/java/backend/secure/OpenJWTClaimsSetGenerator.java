package backend.secure;

import io.micronaut.security.authentication.*;
import javax.annotation.Nullable;
import javax.inject.Singleton;

import io.micronaut.security.token.jwt.generator.claims.*;
import io.micronaut.security.token.config.*;
import io.micronaut.runtime.ApplicationConfiguration;

import com.nimbusds.jwt.JWTClaimsSet;
import io.micronaut.context.annotation.Replaces;

import backend.secure.OpenUserDetails;

@Singleton
@Replaces(bean = JWTClaimsSetGenerator.class)
public class OpenJWTClaimsSetGenerator extends JWTClaimsSetGenerator {

    public OpenJWTClaimsSetGenerator(TokenConfiguration tokenConfiguration,
                                       @Nullable JwtIdGenerator jwtIdGenerator,
                                       @Nullable ClaimsAudienceProvider claimsAudienceProvider,
                                       @Nullable ApplicationConfiguration applicationConfiguration) {
        super(tokenConfiguration, jwtIdGenerator, claimsAudienceProvider, applicationConfiguration);
    }

    @Override
    protected void populateWithUserDetails(JWTClaimsSet.Builder builder, UserDetails userDetails) {
        super.populateWithUserDetails(builder, userDetails);
        if (userDetails instanceof OpenUserDetails) {
            builder.claim("userId", ((OpenUserDetails)userDetails).getUserId());
        }
    }
}