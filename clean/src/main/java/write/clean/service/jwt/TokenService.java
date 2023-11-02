package write.clean.service.jwt;

import java.time.Duration;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import write.clean.config.jwt.TokenProvider;
import write.clean.domain.Jwt.RefreshToken;
import write.clean.domain.Jwt.RefreshTokenRepository;
import write.clean.domain.User.User;
import write.clean.service.user.UserService;

@RequiredArgsConstructor
@Service
public class TokenService {
    
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        if (!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
