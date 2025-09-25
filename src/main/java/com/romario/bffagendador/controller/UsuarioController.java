package com.romario.bffagendador.controller;


import com.romario.bffagendador.business.UsuarioService;
import com.romario.bffagendador.business.dto.EnderecoDTO;
import com.romario.bffagendador.business.dto.TelefoneDTO;
import com.romario.bffagendador.business.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(
                usuarioService.salvaUsuario(usuarioDTO)
        );
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody UsuarioDTO usuarioDTO){
    return ResponseEntity.status(201)
            .body(usuarioService.loginUsuario(usuarioDTO));
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(
            @RequestParam("email" ) String email,
            @RequestHeader("Authorization") String token
    ){
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(
            @PathVariable String email, @RequestHeader("Authorization") String token
    ){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario(
            @RequestBody UsuarioDTO dto, @RequestHeader("Authorization") String token
    ){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(dto,token));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualizaEndereco(
            @RequestBody EnderecoDTO dto, @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token
    ){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualizaTelefone(
            @RequestBody TelefoneDTO dto,
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token
    ){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDTO> cadastraEndereco(
            @RequestBody EnderecoDTO dto, @RequestHeader("Authorization") String token
    ){
        return ResponseEntity.status(201).body(
                usuarioService.cadastraEndereco(token, dto)
        );
    }


    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDTO> cadastraTelefone(
            @RequestBody TelefoneDTO dto, @RequestHeader("Authorization") String token
    ){
        return ResponseEntity.status(201).body(
                usuarioService.cadastraTelefone(token, dto)
        );
    }
}


