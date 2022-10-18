# JPA

## 연관관계 매핑
### 1. 다대일 단방향

다대일 단방향은 가장 많이 사용하는 연관관계이다. 외래키를 가지고 있는 테이블을 연관관계의 주인으로 매핑한다. 연관관계 주인의 반대편은 외래키에 영향을 주지 않으며 단순 데이터 조회만 가능하다.
![image](https://user-images.githubusercontent.com/22049906/196313223-cf6fcb4b-bfd7-4405-b8f5-a230c1a85878.png)
위의 예시를 보면, 외래키가 있는 ```Member``` 라는 객체에 ```Team```이라는 참조를 걸고 연관관계 매핑을 설정한다.
```Team``` 객체에서도 ```Member```를 참조하고 싶은 경우 양방향 관계로 구현한다. ```Team```객체에서 추가만 한다.

```java
@Entity
public class Member {
    
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
```
```java
@Entity
public class Team {
    
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
  
    private String name;

    /* 다대일 양방향 시 추가
    @OneToMany(mappedBy = "team")	// team라는 객체에 의해 매핑이 되어진다. 매핑되는 컬럼 작성(읽기만 가능)
    private List<Member> members = new ArrayList<Member>();
    */
}
```

### 2. 일대다 단방향
- 일대다 단방향은 일대다(1:N)에서 일(1)이 연관관계의 주인
- 테이블 일대다 관계는 항상 다(N) 쪽에 외래 키가 있음
![image](https://user-images.githubusercontent.com/22049906/196318466-903ae10d-0ced-4ac8-833d-867797e33e32.png)

```java
@Entity
public class Member {
    
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;
    
    /* 일대다 양뱡향 시 추가 (공식적으로 존재하지 않음, 다대일 양방향 사용권장)
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable=false, updatable=false)
    private Team team;
    */
}
```
```java
@Entity
public class Team {
    
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
  
    private String name;

    @OneToMany
    @JoinColumn(name= "TEAM_ID")  // 반드시 @JoinColumn을 사용해야 한다. 사용하지 않으면 default로 @JoinTable로 동작하여 중간 테이블이 하나 더 생성된다.
    private List<Member> members = new ArrayList<Member>();
}
```
객체를 두 개 생성하고 각각의 객체를 영속성으로 관리한 후 커밋하면 다대일 관계에서는 2개의 Insert 쿼리가 발생한다. 하지만, 이 경우에는 ```Team``` 내에 ```members```를 조작하면 ```Member``` 테이블에 쿼리가 발생해야 하므로 1개의 Update 쿼리가 더 발생한다.
```java
Member member = new Member();
member.setUsername("member1");

em.persist(member);  //Member insert

Team team = new Team();
team.setName("teamA");
team.getMembers().add(member);

em.persist(team);  // Team insert
                   // Member update

transaction.commit();
```
#### 단점
- 엔티티가 관리하는 외래 키가 다른 테이블에 있음
- 연관관계 관리를 위해 추가로 UPDATE SQL 실행   

일대다 관계는 잘 사용하지 않는다. 필요하다면 다대일 단방향관계에서 양방향관계를 추가한다. __(다대일 양방향관계를 사용)__

