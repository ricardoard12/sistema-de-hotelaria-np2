package hotel.persistencia;

import java.util.List;

import hotel.entidades.Usuario;

import javax.persistence.EntityManager;

public class UsuarioDao {

	public void insertNovoUsuario(Usuario novoUsuario) {
		EntityManager entityManager = JPAResourceBean.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(novoUsuario);
			entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	public void atualizarUsuario(Usuario novoDadoUsuario) {
		EntityManager em = JPAResourceBean.getEntityManager();

		try {
			em.getTransaction().begin();

			Usuario oldUserData = em.find(Usuario.class,
					novoDadoUsuario.getIdUsuario());
			oldUserData.setPerfil(novoDadoUsuario.getPerfil());
			oldUserData.setNome(novoDadoUsuario.getNome());
			oldUserData.setLogin(novoDadoUsuario.getLogin());
			oldUserData.setSenha(novoDadoUsuario.getSenha());
			oldUserData.setDataNascimento(novoDadoUsuario.getDataNascimento());

			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public void excluir(int codigo) {
		EntityManager em = JPAResourceBean.getEntityManager();

		try {
			em.getTransaction().begin();

			Usuario u = em.find(Usuario.class, codigo);
			em.remove(u);

			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	// Metodo de busca em.find(class, primary key);
	public Usuario buscar(int codigo) {
		EntityManager em = JPAResourceBean.getEntityManager();
		return em.find(Usuario.class, codigo);
	}

	public List<Usuario> listar() {
		EntityManager em = JPAResourceBean.getEntityManager();
		return em.createQuery("select u from Usuario u").getResultList();
	}
	
	//Teoricamente este metodo busca no BD pelo login e senha
	public List<Usuario> findByLoginSenha(String login, String senha) {
		EntityManager em = JPAResourceBean.getEntityManager();
		return em.createQuery("select u.login, u.senha from Usuario u where u.login = :login and u.senha = :senha ").getResultList();
	}

}
