package hotel.persistencia;

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
			oldUserData.setNome(novoDadoUsuario.getNome());

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

	public Usuario buscar(int codigo) {
		EntityManager em = JPAResourceBean.getEntityManager();
		return em.find(Usuario.class, codigo);
	}

}
