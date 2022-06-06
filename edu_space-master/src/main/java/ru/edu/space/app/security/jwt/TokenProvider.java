package ru.edu.space.app.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import ru.edu.space.app.logger.ELogger;
import ru.edu.space.app.logger.IELog;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author agerus
 */
@Component
public class TokenProvider implements InitializingBean {

    private static final String PREFIX = "[TokenProvider] ";
    protected static IELog log = ELogger.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    private static final Long TIME_TO_MILLISECONDS = 1000L;

    private final String base64Key;
    private final Long tokenValidInSeconds;
    private final Long tokenValidInSecondsForRememberMe;

    private Key key;

    public TokenProvider(
            @Value("${base64-secret}") String base64Key,
            @Value("${token-validity-in-seconds}") Long tokenValidInSeconds,
            @Value("${token-validity-in-seconds-for-remember-me}") Long tokenValidInSecondsForRememberMe) {
        this.base64Key = base64Key;
        this.tokenValidInSeconds = tokenValidInSeconds * TIME_TO_MILLISECONDS;
        this.tokenValidInSecondsForRememberMe = tokenValidInSecondsForRememberMe * TIME_TO_MILLISECONDS;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(base64Key);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Long currentTime = new Date().getTime();

        Date maxValidTimeForToken;
        maxValidTimeForToken = rememberMe ? new Date(tokenValidInSeconds + currentTime)
                : new Date(tokenValidInSecondsForRememberMe + currentTime);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(maxValidTimeForToken)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error(PREFIX, "Invalid JWT signature trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.error(PREFIX, "Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.error(PREFIX, "Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.error(PREFIX, "JWT token compact of handler are invalid trace: {}", e);
        }
        return true;
    }
}
