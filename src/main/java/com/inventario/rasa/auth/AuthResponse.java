package com.inventario.rasa.auth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthResponse {
    
    private final String token;
}
