package write.clean.config.jwt;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
public class TokenProviderTest {

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    TokenProvider tokenProvider;

    @DisplayName("인증정보 가져오기")
    @Test
    void getAuthentication() {
        // given
        String sub = "hello@world.com";
        String token = JwtFactory.builder()
                            .jwtProperties(jwtProperties)
                            .subject(sub)
                            .build()
                            .createToken();


        // when
        Authentication authentication = tokenProvider.getAuthentication(token);

        // then
        assertThat(sub).isEqualTo(((UserDetails)authentication.getPrincipal()).getUsername());
    }
}
