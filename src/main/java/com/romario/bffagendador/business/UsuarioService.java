package com.romario.bffagendador.business;


import com.romario.bffagendador.business.dto.in.EnderecoInDTO;
import com.romario.bffagendador.business.dto.in.LoginDTO;
import com.romario.bffagendador.business.dto.in.TelefoneInDTO;
import com.romario.bffagendador.business.dto.out.EnderecoDTO;
import com.romario.bffagendador.business.dto.out.TelefoneDTO;
import com.romario.bffagendador.business.dto.out.UsuarioDTO;
import com.romario.bffagendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;


    public String loginUsuario(LoginDTO dto){
        return usuarioClient.login(dto);
    }

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {

        return usuarioClient.salvaUsuario(usuarioDTO);
    }


    public UsuarioDTO buscaUsuarioPorEmail(String email, String token) {

        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTO atualizaDadosUsuario( UsuarioDTO dto, String token){

        return usuarioClient.atualizaDadosUsuario(dto, token);

    }

    public EnderecoDTO atualizaEndereco(Long id, EnderecoInDTO dto, String token){
        return usuarioClient.atualizaEndereco(dto,id,token);
    }

    public TelefoneDTO atualizaTelefone(Long id, TelefoneInDTO dto, String token){
        return usuarioClient.atualizaTelefone(dto, id, token);
    }

    public EnderecoDTO cadastraEndereco(String token, EnderecoInDTO dto){
        return usuarioClient.cadastraEndereco(dto, token);
    }

    public TelefoneDTO cadastraTelefone(String token, TelefoneInDTO dto){
        return usuarioClient.cadastraTelefone(dto, token);
    }
}
