package com.mybank.app.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String userName;
	
	private String password;
	
	@ManyToMany
	@JoinTable(
			name="users_roles",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"),
			uniqueConstraints={@UniqueConstraint(columnNames = {"user_id", "role_id"})}
			)
	private List<Role> roles;
	
	@Transient //indicar que no esta mapeado a la tabla
	private boolean admin;
}
