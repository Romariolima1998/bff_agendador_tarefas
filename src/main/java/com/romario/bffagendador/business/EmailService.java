package com.romario.bffagendador.business;


import com.romario.bffagendador.business.dto.out.TarefasDTO;
import com.romario.bffagendador.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailClient emailClient;


    public void enviaEmail(TarefasDTO dto){
        emailClient.enviarEmail(dto);
    }

}
