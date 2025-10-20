package com.romario.bffagendador.business;


import com.romario.bffagendador.business.dto.out.ViaCepDTO;
import com.romario.bffagendador.infrastructure.client.ViaCepClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient viaCepClient;

    public ViaCepDTO buscaDadosEndereco(String cep){
        return viaCepClient.buscarDadosCep(cep);
    }


}
