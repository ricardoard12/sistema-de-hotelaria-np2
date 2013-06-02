package hotel.formularios;

import hotel.entidades.Aluguel;
import hotel.entidades.Quarto;
import hotel.entidades.Usuario;
import hotel.persistencia.AluguelDAO;
import hotel.persistencia.QuartoDAO;
import hotel.persistencia.UsuarioDao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AluguelForm {
	private Aluguel aluguel = new Aluguel();

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public String inserir() {
		AluguelDAO aDao = new AluguelDAO();
		aDao.inserir(aluguel);
		return "funcionario";
	}

	public List<Quarto> getQuartos() {
		QuartoDAO qDao = new QuartoDAO();
		return qDao.listar();
	}

	public List<Quarto> getQuartosDesocupados() {
		QuartoDAO qDao = new QuartoDAO();
		return qDao.listarDesocupados();
	}

	public List<Usuario> getUsuarios() {
		UsuarioDao uDao = new UsuarioDao();
		return uDao.listar();
	}
}
