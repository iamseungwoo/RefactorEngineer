package write.clean.config.jwt;

import static org.assertj.core.api.Assertions.*;

import java.time.Duration;

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
                            .expire(Duration.ofDays(2).getSeconds())
                            .subject(sub)
                            .build()
                            .createToken();

        System.out.println(token);
        // when
        Authentication authentication = tokenProvider.getAuthentication(token);

        // then
        assertThat(sub).isEqualTo(((UserDetails)authentication.getPrincipal()).getUsername());
    }

    @DisplayName("기간이 만료된 토큰")
    @Test
    void expireToken() {
        // given
        String token = JwtFactory.builder()
                        .jwtProperties(jwtProperties)
                        .expire(-Duration.ofDays(3).getSeconds())
                        .build()
                        .createToken();
        // when
        boolean result = tokenProvider.validateToken(token);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("기간이 만료되지 않은 토큰")
    @Test
    void nonExpireToken() {
        // given
        String token = JwtFactory.builder()
                        .jwtProperties(jwtProperties)
                        .expire(Duration.ofDays(3).getSeconds())
                        .build()
                        .createToken();
        // when
        boolean result = tokenProvider.validateToken(token);

        // then
        assertThat(result).isTrue();
    }
}
