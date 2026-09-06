package com.oauth_demo.oauth_demo.controllers;

import com.oauth_demo.oauth_demo.entities.User;
import com.oauth_demo.oauth_demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login-uri/google")
    public ResponseEntity<String> getGoogleLoginUri() {
        return ResponseEntity.ok("""
                Login at: http://localhost:9000/oauth2/authorization/google
        """);
    }

    @GetMapping("/login-uri/github")
    public ResponseEntity<String> getGithubLoginUri() {
        return ResponseEntity.ok("""
                Login at: http://localhost:9000/oauth2/authorization/github
        """);
    }

    @GetMapping("/error")
    public ResponseEntity<Map<String, String>> oauthError() {
        Map<String, String> response = new HashMap<>();
        response.put("error", "OAuth login failed");
        response.put("message", "Authentication was unsuccessful. Please try again.");
        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getUserProfile(OAuth2AuthenticationToken authentication) {
        OAuth2User oauth2User = authentication.getPrincipal();
        String provider = authentication.getAuthorizedClientRegistrationId();
        // Same value stored in User.providerSubject
        String subject = oauth2User.getName();

        User user = userService.getUserByProviderInformation(provider, subject);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("User ID in DB", user.getId());
        responseMap.put("provider", provider);
        responseMap.put("providerSubject", subject);
        responseMap.put("Email", oauth2User.getAttribute("email"));
        responseMap.put("Name", oauth2User.getAttribute("name"));

        if (oauth2User instanceof OidcUser oidcUser) {
            responseMap.put("profile", oidcUser.getProfile());
            responseMap.put("Profile picture", oidcUser.getPicture());
        } else {
            responseMap.put("Profile picture", oauth2User.getAttribute("avatar_url"));
            responseMap.put("login", oauth2User.getAttribute("login"));
        }

        return ResponseEntity.ok(responseMap);
    }
}
