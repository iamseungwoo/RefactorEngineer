package write.clean.service.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import write.clean.config.jwt.TokenProvider;
import write.clean.service.user.UserService;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)
                && refreshTokenService.findByRefreshToken(refreshToken) != null) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Authentication authentication = tokenProvider.getAuthentication(refreshToken);

        return tokenProvider.createToken(authentication);
    }
}
