package write.clean.controller.jwt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import write.clean.dtos.jwt.CreateAccessTokenRequest;
import write.clean.dtos.jwt.CreateAccessTokenResponse;
import write.clean.service.jwt.TokenService;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class TokenApiController {
    
    private final TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.created(null).body(new CreateAccessTokenResponse(newAccessToken));
    }
}
