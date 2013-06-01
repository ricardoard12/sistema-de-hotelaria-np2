package hotel.persistencia;

import hotel.entidades.Aluguel;

import java.util.List;

import javax.persistence.EntityManager;

public class AluguelDAO {
		public void inserir(Aluguel a) {
			EntityManager em = JPAResourceBean.getEntityManager();
			
			try {
				em.getTransaction().begin();
				em.persist(a);
				em.getTransaction().commit();
			} catch(RuntimeException e) {
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
		}
		
		public void atualizar(Aluguel a) {
			EntityManager em = JPAResourceBean.getEntityManager();
			
			try {
				em.getTransaction().begin();
				
				Aluguel aVelho = em.find(Aluguel.class, a.getIdAluguel());
				aVelho.setQtdPessoas(a.getQtdPessoas());
				aVelho.setDataEntrada(a.getDataEntrada());
				aVelho.setDataSaida(a.getDataSaida());
				aVelho.setUsuario(a.getUsuario());
				aVelho.setQuarto(a.getQuarto());
				
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
				
				Aluguel a = em.find(Aluguel.class, codigo);
				em.remove(a);
				
				em.getTransaction().commit();
			} catch(RuntimeException e) {
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
		}
		
		public Aluguel buscar(int codigo) {
			EntityManager em = JPAResourceBean.getEntityManager();
			return em.find(Aluguel.class, codigo);
		}
		
		public List<Aluguel> listar() {
			EntityManager em = JPAResourceBean.getEntityManager();
			
			return em.createQuery("select a from Aluguel a").getResultList();
		}
}
