package com.gamestore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroUsuarioDTO {
    private String nombre;
    private String email;
    private String password;
}
