package br.com.senai.autoescolas164.config.security.filter;

import br.com.senai.autoescolas164.application.port.out.UsuarioRepository;
import br.com.senai.autoescolas164.config.security.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        System.out.println("========== SECURITY FILTER ==========");
        System.out.println("REQUISIÇÃO: "
                + request.getMethod()
                + " "
                + request.getRequestURI());

        String authHeader = request.getHeader("Authorization");

        System.out.println("AUTHORIZATION HEADER: [" + authHeader + "]");

        String tokenJWT = recuperarToken(request);

        System.out.println("TOKEN JWT: [" + tokenJWT + "]");

        if (tokenJWT != null) {

            String subject = tokenService.getSubject(tokenJWT);

            System.out.println("SUBJECT: " + subject);

            UserDetails usuario = repository.findByLogin(subject);

            System.out.println("USUARIO: " + usuario.getUsername());
            System.out.println("AUTHORITIES: " + usuario.getAuthorities());

            var authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            usuario,
                            null,
                            usuario.getAuthorities()
                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authenticationToken);

            System.out.println("AUTHENTICATED: "
                    + SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .isAuthenticated());
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7).trim();
        }

        return null;
    }
}