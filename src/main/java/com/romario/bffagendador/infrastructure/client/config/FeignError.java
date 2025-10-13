package com.romario.bffagendador.infrastructure.client.config;

import com.romario.bffagendador.infrastructure.exceptions.BusinessException;
import com.romario.bffagendador.infrastructure.exceptions.ConflictException;
import com.romario.bffagendador.infrastructure.exceptions.ResourceNotFoundException;
import com.romario.bffagendador.infrastructure.exceptions.UnaltorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()){
            case 409:
                return new ConflictException("erro atributo ja existente");
            case 403 :
                return new ResourceNotFoundException("erro atributo nao encontrado");
            case 401:
                return new UnaltorizedException("erro usuario nao autorizado");
            default:
                return new BusinessException("Erro de servidor");
        }
    }
}
