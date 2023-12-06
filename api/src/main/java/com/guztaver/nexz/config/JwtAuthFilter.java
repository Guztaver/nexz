package com.guztaver.nexz.config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.*;
import org.jetbrains.annotations.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import java.io.*;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = req.getHeader("AUTHORIZATION");
        if (authHeader == null || !authHeader.startsWith("Baerer")) {
            filterChain.doFilter(req, res);
        }

        assert authHeader != null;
        final String jwtToken = authHeader.substring(7);
        final String username = jwtUtil.extractUsername(jwtToken);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userDetailsService.loadUserByUsername(username);
            final boolean isTokenValid;
            if(jwtUtil.isTokenValid(jwtToken, userDetails)) {
                var authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
    }

}
