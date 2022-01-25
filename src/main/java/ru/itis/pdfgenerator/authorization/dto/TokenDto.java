package ru.itis.pdfgenerator.authorization.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {
	private String token;
}

