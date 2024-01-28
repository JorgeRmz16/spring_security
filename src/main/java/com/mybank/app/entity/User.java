package com.mybank.app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybank.app.validation.ExistsByUsername;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
	
	@ExistsByUsername
	@NotBlank
	@Size(min=4, max=15)
	@Column(unique=true)
	private String username;
	
	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // no enviar password en JSON
	private String password;
	
	@ManyToMany
	@JoinTable(
			name="users_roles",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"),
			uniqueConstraints={@UniqueConstraint(columnNames = {"user_id", "role_id"})}
			)
	private List<Role> roles;
	
	private boolean enabled; 
	@PrePersist
	public void prePersist() {
		enabled = true;
	}
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Transient //indicar que no esta mapeado a la tabla
	private boolean admin;
}
