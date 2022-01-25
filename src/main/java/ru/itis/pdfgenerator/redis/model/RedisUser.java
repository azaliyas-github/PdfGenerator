package ru.itis.pdfgenerator.redis.model;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.redis.core.*;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("user")
public class RedisUser {
	@Id
	private String id;
	private List<String> tokens;
	private Long userId;
}
