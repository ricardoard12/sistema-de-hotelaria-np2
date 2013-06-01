package hotel.persistencia;

import hotel.entidades.Produto;

import java.util.List;

import javax.persistence.EntityManager;

public class ProdutoDAO {
			public void inserir(Produto p) {
			EntityManager em = JPAResourceBean.getEntityManager();
			
			try {
				em.getTransaction().begin();
				em.persist(p);
				em.getTransaction().commit();
			} catch(RuntimeException e) {
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
		}
		
		public void atualizar(Produto p) {
			EntityManager em = JPAResourceBean.getEntityManager();
			
			try {
				em.getTransaction().begin();
				
				Produto pVelho = em.find(Produto.class, p.getIdProduto());
				pVelho.setNome(p.getNome());
				pVelho.setValor(p.getValor());
				
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
				
				Produto p = em.find(Produto.class, codigo);
				em.remove(p);
				
				em.getTransaction().commit();
			} catch(RuntimeException e) {
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
		}
		
		public Produto buscar(int codigo) {
			EntityManager em = JPAResourceBean.getEntityManager();
			return em.find(Produto.class, codigo);
		}
		
		public List<Produto> listar() {
			EntityManager em = JPAResourceBean.getEntityManager();
			
			return em.createQuery("select p from Produto p").getResultList();
		}
}
