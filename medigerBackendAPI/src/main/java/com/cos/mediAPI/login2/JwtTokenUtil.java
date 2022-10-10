//package com.cos.mediAPI.login2;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Service
//public class JwtTokenUtil implements Serializable {
//    private static final long serialVersionUID = -798416586417070603L;
//    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    /**
//     * jwt 토큰에서 username 검색
//     * @param token
//     * @return
//     */
//    public String getUsernameFromToken(String token){
//        try{
//            return getClaimFromToken(token, Claims::getSubject);
//        }catch(Exception ex){
//            throw new UsernameFromTokenException("username from token exception");
//        }
//    }
//
//    /**
//     * jwt 토큰에서 날짜 만료 검색
//     * @param token
//     * @return
//     */
//    public Date getExpirationDateFromToken(String token){
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//    /**
//     * secret 키를 가지고 토큰에서 정보 검색
//     * @param token
//     * @return
//     */
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//    }
//
//    /**
//     * 토큰 만료 체크
//     * @param token
//     * @return
//     */
//    private Boolean isTokenExpired(String token){
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    /**
//     * username으로 토큰생성
//     * @param userDetails
//     * @return
//     */
//    public String generateToken(JwtUserDetails userDetails){
//        Map<String, Object> claims = new HashMap<>();
//        return doGenerateToken(claims, userDetails.getUsername());
//    }
//
//    /**
//     * 토큰을 생성하는 동안
//     * 1. 토큰, Issuer, Expiration, Subject, ID로 claims를 정의한다.
//     * 2. HS512알고리즘과 secret key를 가지고 JWT를 서명한다.
//     * 3. JWS Compact Serialization (https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)에
//     * 따라 JWT를 URL 안전 문자열로 압축
//     * @param claims
//     * @param username
//     * @return
//     */
//    private String doGenerateToken(Map<String, Object> claims, String username) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//                .signWith(SignatureAlgorithm.HS512,secret)
//                .compact();
//    }
//
//    /**
//     * 토큰 검증
//     * @param token
//     * @param userDetails
//     * @return
//     */
//    public Boolean validateToken(String token, JwtUserDetails userDetails){
//        final String username = getUsernameFromToken(token);
//        return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//    
//}
