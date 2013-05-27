package hotel.persistencia;

import hotel.entidade.Usuario;

import javax.persistence.EntityManager;

public class UsuarioDao {

	public void inserirUsuario(Usuario u) {
		EntityManager em = JPAResourceBean.getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
		} catch(RuntimeException e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	public void atualizarUsuario(Usuario u) {
		EntityManager em = JPAResourceBean.getEntityManager();
		
		try {
			em.getTransaction().begin();
			
			Usuario velho = em.find(Usuario.class, u.getIdUsuario());
			velho.setNome( u.getNome() );
			//....
			
			em.getTransaction().commit();
		} catch(RuntimeException e) {
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
		} catch(RuntimeException e) {
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
