package hotel.formularios;

import java.util.List;

import hotel.entidades.Usuario;
import hotel.persistencia.UsuarioDao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@RequestScoped
public class UsuarioForm {
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String autenticarUser() {
		UsuarioDao uDao = new UsuarioDao();
		List<Usuario> usuLista = uDao.listar();

		for (Usuario usu : usuLista) {
			if (usuario.getLogin().equals(usu.getLogin())
					&& usuario.getSenha().equals(usu.getSenha())) {
				if (usu.getPerfil().equals("funcionario")) {
					return "funcionario";
				} else {
					return "cliente";
				}
			}		
		}
		return "erro";
	}

	public String inserir() {
		UsuarioDao uDao = new UsuarioDao();
		uDao.insertNovoUsuario(usuario);
		return "sucesso";
		//TODO resolver o que fazer com o retorno. To pensando em voltar para a tela funcionario.xhtml
	}
}
