package com.example.CSS.Security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "MoayadMoayadMoayadMoayadMoayadMoayadMoayadMoayad";
    public String generateToken(String username, int userType) throws JOSEException {

        //String secretKeyString = "MoayadMoayadMoayadMoayadMoayad";
        SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        JWSHeader header = buildJWSHeader();

        JWTClaimsSet claimsSet = buildJWTClaimsSet(username,userType);

        JWSSigner signer = new MACSigner(secretKey);
        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }
    private JWSHeader buildJWSHeader(){
        return new JWSHeader.Builder(JWSAlgorithm.HS256)
                .contentType("JWT")
                .build();
    }
    private JWTClaimsSet buildJWTClaimsSet(String username, int userType){
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + 3600000;
        Date exp = new Date(expMillis);

        return new JWTClaimsSet.Builder()
                .claim("username",username)
                .claim("userType",userType)
                .expirationTime(exp)
                .build();
    }
    public JWTClaimsSet extractAllClaims(String token) throws ParseException {
        return SignedJWT.parse(token).getJWTClaimsSet();
    }

    public String extractUsername(String token) throws ParseException {
        return (String) extractAllClaims(token).getClaims().get("username");
    }

    public int extractUserType(String token) throws ParseException {
        return (int) extractAllClaims(token).getClaims().get("userType");
    }

    public boolean isTokenExpired(String token) throws ParseException {
        //return extractAllClaims(token).getClaims().get("exp").before(new Date());
        return false;
    }

    public boolean validateToken(String token, String username) throws ParseException {
        return (extractUsername(token).equals(username) && !isTokenExpired(token));
    }
}