package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Songs;
import java.util.List;


public interface SongsRepository extends JpaRepository<Songs, Integer>
{
	public Songs findByName(String name);
	public Songs findById(int id);
}
