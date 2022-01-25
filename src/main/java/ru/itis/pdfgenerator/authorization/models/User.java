package ru.itis.pdfgenerator.authorization.models;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account", schema = "pdf_generator")
public class User {

	private static final long serialVersionUID = 9132222312087392904L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String personalId;

	private String redisId;

	@Enumerated(value = EnumType.STRING)
	private State state;

	public enum State {
		ACTIVE, BANNED
	}

	public boolean isActive() {
		return this.state == State.ACTIVE;
	}

	public boolean isBanned() {
		return this.state == State.BANNED;
	}
}

