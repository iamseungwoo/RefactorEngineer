package write.clean.config.jwt;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;

public class JwtFactory {
    private String subject = "default@gmail.com";
    private Key key;

    private JwtProperties jwtProperties;
    private long expire;

    @Builder
    public JwtFactory(String subject, long expire, JwtProperties jwtProperties) {
        this.subject = subject;
        this.expire = expire;
        this.jwtProperties = jwtProperties;
    }

    public String createToken() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        this.key = Keys.hmacShaKeyFor(keyBytes);
        long now = (new Date()).getTime();
        return Jwts.builder()
                .setSubject(subject)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .claim("auth", "user")
                .setExpiration(new Date(now + expire))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
