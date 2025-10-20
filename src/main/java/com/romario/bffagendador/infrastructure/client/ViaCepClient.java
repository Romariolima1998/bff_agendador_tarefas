package com.romario.bffagendador.infrastructure.client;

import com.romario.bffagendador.business.dto.out.ViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "via-cep", url = "${viacep.url}")
public interface ViaCepClient {
    @GetMapping("/endereco/{cep}")
    ViaCepDTO buscarDadosCep(@PathVariable("cep") String cep);

}
