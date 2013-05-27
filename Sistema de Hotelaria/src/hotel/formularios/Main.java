package hotel.formularios;

import java.util.Date;

import hotel.entidades.Usuario;
import hotel.persistencia.UsuarioDao;

public class Main {

	public static void main(String[] args) {
		Usuario usuario = new Usuario();
		UsuarioDao uDao = new UsuarioDao();
		usuario.setDataNascimento(new Date());
		usuario.setLogin("valmar");
		usuario.setPerfil("teste");
		usuario.setSenha("valms");
		usuario.setNome("Valdenia Junior");

		uDao.insertNewUser(usuario);
	}

}
