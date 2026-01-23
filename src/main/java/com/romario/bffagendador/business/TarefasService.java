package com.romario.bffagendador.business;


import com.romario.bffagendador.business.dto.in.TarefasInDTO;
import com.romario.bffagendador.business.dto.out.TarefasDTO;
import com.romario.bffagendador.infrastructure.client.TarefasClient;
import com.romario.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;


    public TarefasDTO gravarTarefa(String token, TarefasInDTO dto){
     return tarefasClient.gravarTarefas(token, dto);
    }

    public List<TarefasDTO> buscaTarefasAgendadaPorPeriodo(
            LocalDateTime dataInicial, LocalDateTime dataFinal, String token
    ){
        return tarefasClient.buscaListaTarefasPorPeriodo(token, dataInicial, dataFinal);
    }
    public List<TarefasDTO> buscaTarefasPorEmail(String token){
        return tarefasClient.buscaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token){
        tarefasClient.deletaTarefaPorId(token, id);
    }

    public TarefasDTO alteraStatus(StatusNotificacaoEnum status, String id, String token){
        return tarefasClient.alteraStatusNotificacao(token, status, id);
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id, String token){
        return tarefasClient.atualizaNotificacao(token, dto, id);
    }
}
