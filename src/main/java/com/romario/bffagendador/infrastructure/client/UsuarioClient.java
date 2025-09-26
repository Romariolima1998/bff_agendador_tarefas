package com.romario.bffagendador.infrastructure.client;


import com.romario.bffagendador.business.dto.in.EnderecoInDTO;
import com.romario.bffagendador.business.dto.in.LoginDTO;
import com.romario.bffagendador.business.dto.in.TelefoneInDTO;
import com.romario.bffagendador.business.dto.out.EnderecoDTO;
import com.romario.bffagendador.business.dto.out.TelefoneDTO;
import com.romario.bffagendador.business.dto.out.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {
    @GetMapping
    UsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email,
                                    @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTO salvaUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTO usuarioDTO);


    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(
            @PathVariable String email, @RequestHeader("Authorization") String token
    );

    @PutMapping
    UsuarioDTO atualizaDadosUsuario(
            @RequestBody UsuarioDTO dto, @RequestHeader("Authorization") String token
    );

    @PutMapping("/endereco")
    EnderecoDTO atualizaEndereco(
            @RequestBody EnderecoInDTO dto, @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token
    );

    @PutMapping("/telefone")
    TelefoneDTO atualizaTelefone(
            @RequestBody TelefoneInDTO dto,
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token
    );

    @PostMapping("/endereco")
    EnderecoDTO cadastraEndereco(
            @RequestBody EnderecoInDTO dto, @RequestHeader("Authorization") String token
    );


    @PostMapping("/telefone")
    TelefoneDTO cadastraTelefone(
            @RequestBody TelefoneInDTO dto, @RequestHeader("Authorization") String token
    );

}
