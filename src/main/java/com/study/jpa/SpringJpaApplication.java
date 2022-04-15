package com.study.jpa;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpa.User;
import com.example.jpa.RoleType;
import com.example.jpa.Team;

@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Team team = new Team();
			team.setName("TeamA");
			//역방향(주인이 아닌 방향)만 연관관계 설정
//			em.persist(team);
			
			User user = new User();
			user.setUsername("user1");
//			user.changeTeam(team);	// 연관관계의 주인에 값을 넣어줌 team.getUsers().add(user);

			team.addUser(user);
			

			em.persist(team);
			em.persist(user);
			
			em.flush();
			em.clear();
			
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
			
//			Example member = new Example();
//			member.setUsername("D");
//			member.setRoleType(RoleType.GUEST);
//			
//			System.out.println("===================");
//			
//			em.persist(member);
//			
//			System.out.println("member.id = " + member.getId());
//			System.out.println("===================");
			
			
			//////// example
			/*
			Team team = new Team();
			team.setName("TeamA");
			//역방향(주인이 아닌 방향)만 연관관계 설정
			em.persist(team);
			
			User user = new User();
			user.setUsername("user1");
			user.changeTeam(team);	// 연관관계의 주인에 값을 넣어줌
			em.persist(user);
			
//			team.addUser(user);
			
//			team.getUsers().add(user); User.java 70라인 참고
			
			em.flush();
			em.clear();
			
			Team findTeam = em.find(Team.class, team.getId());
			
			List<User> users = findTeam.getUsers();
			
			for(User u : users) {
				System.out.println(u.getUsername());
			}
			*/
			
			
			tx.commit(); //데이터베이스에 쿼리가 수행되는 시점
		} catch(Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}

}
