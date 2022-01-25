package ru.itis.pdfgenerator.authorization.repo;

public interface BlackListRepository {
	void save(String token);

	boolean exists(String token);
}
