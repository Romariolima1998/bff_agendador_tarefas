package com.romario.bffagendador.infrastructure.client.config;

import com.romario.bffagendador.infrastructure.exceptions.BusinessException;
import com.romario.bffagendador.infrastructure.exceptions.ConflictException;
import com.romario.bffagendador.infrastructure.exceptions.ResourceNotFoundException;
import com.romario.bffagendador.infrastructure.exceptions.UnaltorizedException;
import com.romario.bffagendador.infrastructure.exceptions.IllegalArgumentException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        String messageError = messageError(response);
        switch (response.status()){
            case 409:
                return new ConflictException("erro: " + messageError);
            case 403 :
                return new ResourceNotFoundException("erro: " + messageError);
            case 401:
                return new UnaltorizedException("erro: " + messageError);
            case 400:
                return new IllegalArgumentException("erro: " + messageError);
            default:
                return new BusinessException("erro: " + messageError);
        }
    }

    private String messageError(Response response){
        try {
            if(Objects.isNull(response.body())){
                return "";
            }
            String messageErro = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            return messageErro;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
