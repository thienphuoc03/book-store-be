package com.htphuoc.bookstore.security;

import java.util.Base64;
import java.util.Date;
import com.htphuoc.bookstore.dto.CustomUserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {

	private final String JWT_SECRET = "thienphuoc";

	private final long JWT_EXPIRATION = 24 * 60 * 60 * 1000;

	public String generateToken(CustomUserDetails userDetails) {
		Date now = new Date();

		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

		String token = Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(JWT_SECRET.getBytes())).compact();

		return token;
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(Base64.getEncoder().encode(JWT_SECRET.getBytes()))
				.parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(Base64.getEncoder().encode(JWT_SECRET.getBytes())).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
		} catch (SignatureException ex) {
			System.out.println("signature is invalid !!!");
		}
		return false;
	}

}
