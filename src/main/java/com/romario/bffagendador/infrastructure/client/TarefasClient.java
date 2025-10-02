package com.romario.bffagendador.infrastructure.client;

import com.romario.bffagendador.business.dto.in.TarefasInDTO;
import com.romario.bffagendador.business.dto.out.TarefasDTO;
import com.romario.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTO gravarTarefas(
            @RequestHeader("Authorization") String token, @RequestBody TarefasInDTO dto
    );

    @GetMapping("/eventos")
    List<TarefasDTO> buscaListaTarefasPorPeriodo(
            @RequestHeader("Authorization") String token,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datainicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datafinal
    );

    @GetMapping
    List<TarefasDTO> buscaTarefasPorEmail(
            @RequestHeader("Authorization") String token
    );

    @DeleteMapping
    void deletaTarefaPorId(
            @RequestHeader("Authorization") String token,
            @RequestParam("id") String id
    );

    @PatchMapping
    TarefasDTO alteraStatusNotificacao(
            @RequestHeader("Authorization") String token,
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestParam("id") String id
    );

    @PutMapping
    TarefasDTO atualizaNotificacao(
            @RequestHeader("Authorization") String token,
            @RequestBody TarefasDTO dto,
            @RequestParam("id") String id
    );
}
