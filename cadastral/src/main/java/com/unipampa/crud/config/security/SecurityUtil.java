package com.unipampa.crud.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static String getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDatailsImpl userDetails) {
            return userDetails.getUserId();
        }

        throw new RuntimeException("Usuário não autenticado!");
    }

    public static boolean isAuthenticatedAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"))) {
            return true;
        }
        return false;
    }

    public static boolean isOwnerOrAdmin(String resourceUserId) {
        String authenticatedUserId = getAuthenticatedUserId();
        return isAuthenticatedAdmin() || authenticatedUserId.equals(resourceUserId);
    }


}