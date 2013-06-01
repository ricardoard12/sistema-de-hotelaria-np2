package hotel.persistencia;

import hotel.entidades.Consumo;

import java.util.List;

import javax.persistence.EntityManager;

public class ConsumoDAO {
		public void inserir(Consumo c) {
			EntityManager em = JPAResourceBean.getEntityManager();
			
			try {
				em.getTransaction().begin();
				em.persist(c);
				em.getTransaction().commit();
			} catch(RuntimeException e) {
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
		}
		
		public void atualizar(Consumo c) {
			EntityManager em = JPAResourceBean.getEntityManager();
			
			try {
				em.getTransaction().begin();
				
				Consumo cVelho = em.find(Consumo.class, c.getIdConsumo());
				cVelho.setProduto(c.getProduto());
				cVelho.setAluguel(c.getAluguel());
				cVelho.setQtd(c.getQtd());
				
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
				
				Consumo c = em.find(Consumo.class, codigo);
				em.remove(c);
				
				em.getTransaction().commit();
			} catch(RuntimeException e) {
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
		}
		
		public Consumo buscar(int codigo) {
			EntityManager em = JPAResourceBean.getEntityManager();
			return em.find(Consumo.class, codigo);
		}
		
		public List<Consumo> listar() {
			EntityManager em = JPAResourceBean.getEntityManager();
			
			return em.createQuery("select c from Consumo c").getResultList();
		}
}
