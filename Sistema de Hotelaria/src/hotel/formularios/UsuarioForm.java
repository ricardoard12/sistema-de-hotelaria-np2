package hotel.formularios;

import hotel.entidades.Usuario;
import hotel.persistencia.UsuarioDao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class UsuarioForm {
	private Usuario usuario = new Usuario();
	
	public String inserir() {
		UsuarioDao uDao = new UsuarioDao();
		uDao.insertNovoUsuario(usuario);
		
		return "sucesso";
	}
}
