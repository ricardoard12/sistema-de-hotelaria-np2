package hotel.formularios;

import hotel.entidades.Quarto;
import hotel.persistencia.QuartoDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class QuartoForm {
	Quarto quarto = new Quarto();

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	
	public String inserir(){
		QuartoDAO qDao = new QuartoDAO();
		qDao.inserir(quarto);
		return "sucesso";
		//TODO resolver o que fazer com o retorno. To pensando em voltar para a tela funcionario.xhtml
	}
}
