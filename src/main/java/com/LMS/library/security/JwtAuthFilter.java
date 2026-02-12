package com.LMS.library.security;

import com.LMS.library.model.MasterUser;
import com.LMS.library.repository.MasterUserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthUtil authUtil;
    private final MasterUserRepository userRepository;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.contains("/login")
                || uri.contains("/doLogin")
                || uri.contains("/signUp")
                || uri.contains("/css/")
                || uri.contains("/js/");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {

        try {

            String header = request.getHeader("Authorization");

            if (header == null || !header.startsWith("Bearer ")) {
                chain.doFilter(request, response);
                return;
            }

            String token = header.substring(7);

            String username = authUtil.getUserNameFromToken(token);

            MasterUser user = userRepository.findByName(username)
                    .orElseThrow(() -> new RuntimeException("User Not Found"));

            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                if (!authUtil.validateToken(token, user)) {
                    logoutUser(request, response);
                    return;
                }

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            chain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            logoutUser(request, response);
        } catch (Exception e) {
            logoutUser(request, response);
        }
    }

    private void logoutUser(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {

        SecurityContextHolder.clearContext();

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        response.sendRedirect(request.getContextPath() + "/login");
    }

}
