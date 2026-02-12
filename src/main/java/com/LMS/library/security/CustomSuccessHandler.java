package com.LMS.library.security;

import com.LMS.library.model.MasterUser;
import com.LMS.library.repository.MasterUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    private final AuthUtil authUtil;
    private final MasterUserRepository masterUserRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws java.io.IOException {
        String username = authentication.getName();
        MasterUser user = masterUserRepository
                .findByName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Generate token with ID + NAME
        String token = authUtil.generateAccessToken(user.getId(), user.getName());

        response.setContentType("application/json");
        response.getWriter().write(
                "{\"token\": \"" + token + "\"}"
        );
    }
}
