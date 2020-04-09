package com.mai.projects.plm.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
	private Long id;

	private String userName;

	private String firstName;

	private String lastName;

	private String email;
}
