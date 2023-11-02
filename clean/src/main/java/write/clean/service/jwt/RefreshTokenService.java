package write.clean.service.jwt;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import write.clean.domain.Jwt.RefreshToken;
import write.clean.domain.Jwt.RefreshTokenRepository;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }
}
