package com.romario.bffagendador.business.dto.in;

import com.romario.bffagendador.business.dto.out.EnderecoDTO;
import com.romario.bffagendador.business.dto.out.TelefoneDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ NoArgsConstructor
@Builder
public class LoginDTO {
    private String email;
    private String senha;

}