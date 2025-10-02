package com.romario.bffagendador.business;

import com.romario.bffagendador.business.dto.in.LoginDTO;
import com.romario.bffagendador.business.dto.out.TarefasDTO;
import com.romario.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){
        String token = login(converteParaLoginDTO());
        LocalDateTime horaFutura = LocalDateTime.now();
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1);
        List<TarefasDTO> listaTarefas = tarefasService.buscaTarefasAgendadaPorPeriodo(
                horaFutura, horaFuturaMaisCinco, token
        );

        listaTarefas.forEach(
                tarefa -> {emailService.enviaEmail(tarefa);
                tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);}
        );
    }

    public String login(LoginDTO dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginDTO converteParaLoginDTO(){
        return LoginDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}
