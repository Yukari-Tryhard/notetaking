package com.cnpmm.notetaking.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cnpmm.notetaking.config.SecurityConfig;
import com.cnpmm.notetaking.model.User;
import com.cnpmm.notetaking.repository.UserRepository;
import com.cnpmm.notetaking.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    UserService userService;

    public CustomAuthorizationFilter(UserService userService){
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/v1/authenticate") || request.getServletPath().equals("/api/v1/authenticate/refresh-token") || request.getServletPath().equals("/login")){
            filterChain.doFilter(request,response);
        }else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null){
                String token = "";
                String refreshToken = "";
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("access_token")) {
                        token = cookie.getValue();
                    }
                    else if (cookie.getName().equals("refresh_token"))
                    {
                        refreshToken = cookie.getValue();
                    }
                }
                try {
                    if (token != ""){
                        Algorithm algorithm = Algorithm.HMAC256("HokiTuki".getBytes());
                        JWTVerifier verifier = JWT.require(algorithm).build();
                        DecodedJWT decodedJWT = verifier.verify(token);
                        String email = decodedJWT.getSubject();
                        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

                        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        stream(roles).forEach(role -> {
                            authorities.add(new SimpleGrantedAuthority(role));
                        });
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,null,authorities);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        filterChain.doFilter(request,response);
                    }
                    throw new JWTVerificationException("Token not found");
                }catch (JWTVerificationException ex){
                    try{
                        if (refreshToken != ""){
                            Algorithm algorithm = Algorithm.HMAC256("HokiTuki".getBytes());
                            JWTVerifier verifier = JWT.require(algorithm).build();
                            DecodedJWT decodedJWT = verifier.verify(refreshToken);
                            String email = decodedJWT.getSubject();
                            User user = userService.findByEmail(email);
                            String newAccessToken = JWT.create()
                                    .withSubject(email)
                                    .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                                    .withIssuer(request.getRequestURL().toString())
                                    .withClaim("roles",user.getRoles().stream().collect(Collectors.toList()))
                                    .sign(algorithm);
                            String newRefreshToken = JWT.create()
                                    .withSubject(email)
                                    .withExpiresAt(new Date(System.currentTimeMillis() + 30*60*1000))
                                    .withIssuer(request.getRequestURL().toString())
                                    .sign(algorithm);
                            final Boolean useSecureCookie = false;
                            final int expiryTime = 60 * 60 * 24 * 60;  // 24h in seconds
                            final String cookiePath = "/";

                            Cookie accessCookie = new Cookie("access_token", newAccessToken);
                            accessCookie.setSecure(useSecureCookie);  // determines whether the cookie should only be sent using a secure protocol, such as HTTPS or SSL
                            accessCookie.setMaxAge(expiryTime);  // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.
                            accessCookie.setPath(cookiePath);  // The cookie is visible to all the pages in the directory you specify, and all the pages in that directory's subdirectories

                            Cookie refreshCookie = new Cookie("refresh_token", newRefreshToken);
                            refreshCookie.setSecure(useSecureCookie);  // determines whether the cookie should only be sent using a secure protocol, such as HTTPS or SSL
                            refreshCookie.setMaxAge(expiryTime);  // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.
                            refreshCookie.setPath(cookiePath);

                            response.addCookie(accessCookie);
                            response.addCookie(refreshCookie);
                            filterChain.doFilter(request,response);
                        }
                        if (cookies != null)
                            for (Cookie cookie : cookies) {
                                cookie.setValue("");
                                cookie.setPath("/");
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                            }
                        response.sendRedirect("login");
                        return;
                    }catch (JWTVerificationException exRe){
                        log.info("Refresh exRe");
                        if (cookies != null)
                            for (Cookie cookie : cookies) {
                                cookie.setValue("");
                                cookie.setPath("/");
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                            }
                        response.sendRedirect("login");
                        return;
                    }
                    catch (Exception exRe2){
                        log.error("Error logging in: {}", exRe2.getMessage());
                        if (cookies != null)
                            for (Cookie cookie : cookies) {
                                cookie.setValue("");
                                cookie.setPath("/");
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                            }
                        response.sendRedirect("login");
                        return;
                    }

                }
                catch (Exception ex){

                    log.error("Error logging in: {}", ex.getMessage());
                    if (cookies != null)
                        for (Cookie cookie : cookies) {
                            cookie.setValue("");
                            cookie.setPath("/");
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    response.sendRedirect("login");
                    return;
                }
            }else{
                response.sendRedirect("login");
                return;
            }
        }
    }
}
