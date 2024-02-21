package com.softwared.banco.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "DBC0F108FC15E418A45D8BBEB3D67981A1F8E0346C357D7B9B566C90E044E493";

	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	public String getUserName(String jwt) {

		return getClaim(jwt, Claims::getSubject);
	}

	private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {

		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);

	}

	private Claims getAllClaims(String token) {

		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return getExpiration(token).before(new Date());
	}

	private Date getExpiration(String token) {
		return getClaim(token, Claims::getExpiration);
	}

}
