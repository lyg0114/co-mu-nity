package com.comu.comunity.auth;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.comu.comunity.model.entity.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : leeyounggyo
 * @package : com.planmate.auth
 * @since : 2024. 10. 14.
 */
@Slf4j
@Component
public class JwtTokenProvider {

	private final String secretKey = "TTTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYOPSECRETKEYTTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYOPSECRETKEYTTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYTOPSECRETKEYOPSECRETKEY";
	private final long validityInMilliseconds = 3600000; // 1시간

	public String createToken(Member user) {
		Claims claims = Jwts.claims()
			.setSubject(user.getEmail());

		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(validity)
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	public String getUser(String token) {
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String extractJwtToken(String tokenWithBearer) {
		if (tokenWithBearer != null && tokenWithBearer.startsWith("Bearer ")) {
			return tokenWithBearer.substring(7);
		}

		return tokenWithBearer;
	}

}
