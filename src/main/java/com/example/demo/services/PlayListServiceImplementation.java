package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.PlayList;
import com.example.demo.repositories.PlayListRepository;
import com.example.demo.repositories.SongsRepository;

@Service
public class PlayListServiceImplementation implements PlayListService
{
	@Autowired
	PlayListRepository prepo;
	
	@Autowired 
	SongsRepository srepo;

	@Override
	public void addPlaylist(PlayList playlist) {
		prepo.save(playlist);	
	}

	@Override
	public List<PlayList> fetchPlaylists() {
		// TODO Auto-generated method stub
		return prepo.findAll();
	}
	
	
}
