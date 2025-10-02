package com.romario.bffagendador.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoInDTO {
    private String rua;
    private Long numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
}
