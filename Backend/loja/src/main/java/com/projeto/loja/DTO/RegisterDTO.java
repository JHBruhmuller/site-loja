package com.projeto.loja.DTO;

import com.projeto.loja.Model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
