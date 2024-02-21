package com.guztaver.nexz.config;

import com.guztaver.nexz.repositories.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.*;
import org.jetbrains.annotations.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import java.io.*;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final AppUserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest req, @NotNull HttpServletResponse res, @NotNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = req.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(req, res);
            return; // Importante adicionar esse return para sair do método após passar pelo filtro
        }

        final String jwtToken = authHeader.substring(7);
        final String username = jwtUtil.extractUsername(jwtToken);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userRepository.findByUsernameIs(username);
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
