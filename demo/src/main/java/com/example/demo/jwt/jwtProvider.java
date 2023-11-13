// package com.example.demo.jwt;

// import java.util.Base64;

// import java.security.Key;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import io.jsonwebtoken.security.Keys;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Component
// public class jwtProvider {
//     private final Key key;

//     public jwtProvider(@Value("${jwt.secret}") String secretKey) {
//         byte[] secretByteKey = Base64.getDecoder().decode(secretKey);
//         this.key = Keys.hmacShaKeyFor(secretByteKey);
//     }
// }