package com.romario.bffagendador.controller;


import com.romario.bffagendador.business.UsuarioService;
import com.romario.bffagendador.business.dto.in.EnderecoInDTO;
import com.romario.bffagendador.business.dto.in.LoginDTO;
import com.romario.bffagendador.business.dto.in.TelefoneInDTO;
import com.romario.bffagendador.business.dto.out.EnderecoDTO;
import com.romario.bffagendador.business.dto.out.TelefoneDTO;
import com.romario.bffagendador.business.dto.out.UsuarioDTO;
import com.romario.bffagendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
@Tag(name="usuario", description = "cadastro e login de usuario")
@SecurityRequirement(name= SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {
    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "salvar usuario", description = "cria um novo usuario")
    @ApiResponse(responseCode = "200", description = "usuario criado com sucesso")
    @ApiResponse(responseCode = "400", description = "usuario ja existe")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(
                usuarioService.salvaUsuario(usuarioDTO)
        );
    }

    @PostMapping("/login")
    @Operation(summary = "login de usuario", description = "cria um token apartir do email e senha de um usuario")
    @ApiResponse(responseCode = "201", description = "token criado com sucesso")
    @ApiResponse(responseCode = "401", description = "email ou senha incorreto")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<String> login(@RequestBody LoginDTO usuarioDTO){
    return ResponseEntity.status(201)
            .body(usuarioService.loginUsuario(usuarioDTO));
    }

    @GetMapping
    @Operation(summary = "busca usuario", description = "busca dados do usuario por email")
    @ApiResponse(responseCode = "200", description = "usuario encontrado")
    @ApiResponse(responseCode = "404", description = "email nao existe")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(
            @RequestParam("email" ) String email,
            @RequestHeader(name = "Authorization", required = false) String token
    ){
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "deleta usuario", description = "deleta usuario por email")
    @ApiResponse(responseCode = "200", description = "usuario deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "email nao encontrado")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(
            @PathVariable String email, @RequestHeader(name = "Authorization", required = false) String token
    ){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "atualiza usuario", description = "atualiza os dados do usuario")
    @ApiResponse(responseCode = "200", description = "usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario(
            @RequestBody UsuarioDTO dto, @RequestHeader(name = "Authorization", required = false) String token
    ){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(dto,token));
    }

    @PutMapping("/endereco")
    @Operation(summary = "atualiza endereco", description = "atualiza endereco por id")
    @ApiResponse(responseCode = "200", description = "endereco atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "endereco nao encontrado")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<EnderecoDTO> atualizaEndereco(
            @RequestBody EnderecoInDTO dto, @RequestParam("id") Long id,
            @RequestHeader(name = "Authorization", required = false) String token
    ){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "atualiza telefone", description = "atualiza telefone por id")
    @ApiResponse(responseCode = "200", description = "telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "telefone nao encontrado")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<TelefoneDTO> atualizaTelefone(
            @RequestBody TelefoneInDTO dto,
            @RequestParam("id") Long id,
            @RequestHeader(name = "Authorization", required = false) String token
    ){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "salvar telefone", description = "cria um novo telefone")
    @ApiResponse(responseCode = "200", description = "telefone criado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<EnderecoDTO> cadastraEndereco(
            @RequestBody EnderecoInDTO dto, @RequestHeader(name = "Authorization", required = false) String token
    ){
        return ResponseEntity.status(201).body(
                usuarioService.cadastraEndereco(token, dto)
        );
    }


    @PostMapping("/telefone")
    @Operation(summary = "salvar telefone", description = "cria um novo telefone")
    @ApiResponse(responseCode = "200", description = "usuario criado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "erro no servidor")
    public ResponseEntity<TelefoneDTO> cadastraTelefone(
            @RequestBody TelefoneInDTO dto, @RequestHeader(name = "Authorization", required = false) String token
    ){
        return ResponseEntity.status(201).body(
                usuarioService.cadastraTelefone(token, dto)
        );
    }
}


