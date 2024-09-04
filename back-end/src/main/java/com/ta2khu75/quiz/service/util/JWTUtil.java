package com.ta2khu75.quiz.service.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.configuration.SecurityJwtConfig;
import com.ta2khu75.quiz.model.response.AccountAuthResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JWTUtil {
	@Value("${jwt.expiration}")
	private long expiration;
	@Value("${jwt.refresh.expiration}")
	private long refreshExpiration;
	private final JwtEncoder jwtEncoder;
	private final JwtDecoder jwtDecoder;

	public String createToken(AccountAuthResponse response) {
//		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
		Instant now = Instant.now();
		Instant validity = now.plus(this.expiration, ChronoUnit.SECONDS);
		JwtClaimsSet claims = JwtClaimsSet.builder().issuer("com.ta2khu75").issuedAt(now).expiresAt(validity)
				.subject(response.getEmail()).claim("scope", "ROLE_" + response.getRole()).build();// .build();
//				.claim("account", accountResponse).build();
		JwsHeader jwsHeader = JwsHeader.with(SecurityJwtConfig.JWT_ALGORITHM).build();
		return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
	}

	public String createRefreshToken(AccountAuthResponse response) {
		Instant now = Instant.now();
		Instant validity = now.plus(this.refreshExpiration, ChronoUnit.SECONDS);
		JwtClaimsSet claims = JwtClaimsSet.builder().issuer("com.ta2khu75").issuedAt(now).expiresAt(validity)
				.subject(response.getEmail()).build();
		JwsHeader jwsHeader = JwsHeader.with(SecurityJwtConfig.JWT_ALGORITHM).build();
		return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
	}

	public Jwt validateToken(String token) {
		return jwtDecoder.decode(token);
	}
}
