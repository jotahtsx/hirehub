package com.jotahdev.hire_hub.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Value("${security.token.secret}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Pega o cabeçalho Authorization
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // remove "Bearer "

            try {
                // Valida o token
                Algorithm algorithm = Algorithm.HMAC256(secretKey);
                var verifier = JWT.require(algorithm)
                        .withIssuer("hirehub")
                        .build();

                var decodedJWT = verifier.verify(token);

                // Pega o ID da empresa do token
                String companyId = decodedJWT.getSubject();

                // Cria autenticação fake (só para liberar o acesso)
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(companyId, null, null);

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Define no contexto de segurança
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (JWTVerificationException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido ou expirado.");
                return;
            }
        }

        // Continua o fluxo normalmente
        filterChain.doFilter(request, response);
    }
}
