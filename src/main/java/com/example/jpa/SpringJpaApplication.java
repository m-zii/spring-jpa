package com.example.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			//회원 등록
//			Member member = new Member();
//			member.setId(2L);
//			member.setName("HelloB");
//			em.persist(member);
			
			//회원 찾기
//			Member findMember = em.find(Member.class, 1L);
			
			//회원 삭제
//			em.remove(findMember);
			
			//회원 수정
//			findMember.setName("HelloJPA");
			
//			List<Member> result = em.createQuery("select m from Member as m", Member.class)
//					.setFirstResult(1)
//					.setMaxResults(10)
//					.getResultList();
//			
//			for(Member member : result) {
//				System.out.println("member.name = " + member.getName());
//			}
			
			//영속
//			Member mem1 = new Member(200L, "C");
//			Member mem2 = new Member(100L, "B");
//			
//			em.persist(mem1);
//			em.persist(mem2);
			
			Example member = new Example();
//			member.setId("ID_A");
			member.setUsername("D");
			member.setRoleType(RoleType.GUEST);
			
			System.out.println("===================");
			
			em.persist(member);
			
			System.out.println("member.id = " + member.getId());
			System.out.println("===================");
			
			tx.commit(); //데이터베이스에 쿼리가 수행되는 시점
		} catch(Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}
