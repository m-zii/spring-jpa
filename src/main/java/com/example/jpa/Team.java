package com.example.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Team {

	@Id @GeneratedValue
	@Column(name = "team_id")
	private Long id;
	private String name;
	
	@OneToMany(mappedBy = "team")	// 어떤 변수랑 연결되어있는지?(team으로 매핑되어있다)
	private List<User> users = new ArrayList<User>(); //add 시 null point 발생하지 않음
	
	public void addUser(User user) {
		user.setTeam(this);
		users.add(user); 
	}
}
