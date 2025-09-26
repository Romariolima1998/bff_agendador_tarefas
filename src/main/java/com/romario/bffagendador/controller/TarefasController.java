package com.romario.bffagendador.controller;


import com.romario.bffagendador.business.TarefasService;
import com.romario.bffagendador.business.dto.in.TarefasInDTO;
import com.romario.bffagendador.business.dto.out.TarefasDTO;
import com.romario.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import com.romario.bffagendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("tarefas")
@RequiredArgsConstructor
@Tag(name="tarefas", description = "cadastra tarefas de usuario")
@SecurityRequirement(name= SecurityConfig.SECURITY_SCHEME)
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "salvar tarefa de usuario", description = "cria um novo tarefa")
    @ApiResponse(responseCode = "201", description = "tarefa criado com sucesso")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<TarefasDTO> gravarTarefas(
            @RequestHeader(name = "Authorization",required = false) String token, @RequestBody TarefasInDTO dto
    ){
        return ResponseEntity.status(201)
                .body(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "busca eventos", description = "busca tarefas de um range de datas")
    @ApiResponse(responseCode = "200", description = "tarefas encontrada com sucesso")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<List<TarefasDTO>> buscaListaTarefasPorPeriodo(
            @RequestHeader(name = "Authorization",required = false) String token,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datainicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datafinal
            ){
        return ResponseEntity.ok(
                tarefasService.buscaTarefasAgendadaPorPeriodo(datainicial, datafinal, token)
        );
    }

    @GetMapping
    @Operation(summary = "busca tarefas por email", description = "busca tarefas por email")
    @ApiResponse(responseCode = "200", description = "tarefas encontrada com sucesso")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<List<TarefasDTO>> buscaTarefasPorEmail(
            @RequestHeader(name = "Authorization",required = false) String token
            ){
        return ResponseEntity.ok(
                tarefasService.buscaTarefasPorEmail(token)
        );
    }

    @DeleteMapping
    @Operation(summary = "deleta tarefas por id", description = "deleta tarefas por id")
    @ApiResponse(responseCode = "200", description = "tarefas deletada com sucesso")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<Void> deletaTarefaPorId(
            @RequestHeader(name = "Authorization",required = false) String token,
            @RequestParam("id") String id
    ){
        tarefasService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "atualiza status da tarefa", description = "atualiza status da tarefa por id")
    @ApiResponse(responseCode = "200", description = "tarefas atualizada com sucesso")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<TarefasDTO> alteraStatusNotificacao(
            @RequestHeader(name = "Authorization",required = false) String token,
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestParam("id") String id
            ){
        return ResponseEntity.ok(
                tarefasService.alteraStatus(status, id, token)
        );
    }

    @PutMapping
    @Operation(summary = "atualiza tarefa", description = "atualiza a tarefa por id")
    @ApiResponse(responseCode = "200", description = "tarefas atualizada com sucesso")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<TarefasDTO> atualizaNotificacao(
            @RequestHeader(name = "Authorization",required = false) String token,
            @RequestBody TarefasDTO dto,
            @RequestParam("id") String id
    ){
        return ResponseEntity.ok(
                tarefasService.updateTarefas(dto, id, token)
        );
    }
}
