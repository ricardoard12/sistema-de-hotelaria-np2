package hotel.persistencia;

import hotel.entidades.Quarto;

import java.util.List;

import javax.persistence.EntityManager;

public class QuartoDAO {
	public void inserir(Quarto q) {
		EntityManager em = JPAResourceBean.getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(q);
			em.getTransaction().commit();
		} catch(RuntimeException e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	public void atualizar(Quarto q) {
		EntityManager em = JPAResourceBean.getEntityManager();
		
		try {
			em.getTransaction().begin();
			
			Quarto qVelho = em.find(Quarto.class, q.getIdQuarto());
			qVelho.setNumero(q.getNumero());
			qVelho.setCapacidade(q.getCapacidade());
			qVelho.setValorDiaria(q.getValorDiaria());
			qVelho.setStatus(q.getStatus());
			
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
			
			Quarto q = em.find(Quarto.class, codigo);
			em.remove(q);
			
			em.getTransaction().commit();
		} catch(RuntimeException e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	public Quarto buscar(int codigo) {
		EntityManager em = JPAResourceBean.getEntityManager();
		return em.find(Quarto.class, codigo);
	}
	
	public List<Quarto> listar() {
		EntityManager em = JPAResourceBean.getEntityManager();		
		return em.createQuery("select q from Quarto q").getResultList();
	}
	
	public List<Quarto> listarDesocupados() {
		EntityManager em = JPAResourceBean.getEntityManager();		
		return em.createQuery("select q from Quarto q where q.status = 'desocupado'").getResultList();
	}
}
