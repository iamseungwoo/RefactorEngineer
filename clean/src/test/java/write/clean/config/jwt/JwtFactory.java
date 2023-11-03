package write.clean.config.jwt;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtFactory {
    private String subject;
    private Key key;
    private JwtProperties jwtProperties;

    @Builder
    public JwtFactory(String subject, JwtProperties jwtProperties) {
        this.subject = subject;
        this.jwtProperties = jwtProperties;
    }

    public String createToken() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        this.key = Keys.hmacShaKeyFor(keyBytes);
        long now = (new Date()).getTime();
        Date expire = new Date(now + jwtProperties.getTokenValidityInSeconds());
        return Jwts.builder()
                .setSubject(subject)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .claim("auth", "user")
                .setExpiration(expire)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
