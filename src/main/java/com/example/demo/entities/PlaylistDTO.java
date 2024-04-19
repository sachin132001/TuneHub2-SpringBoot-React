package com.example.demo.entities;

import java.util.List;

public class PlaylistDTO {
	
	int id;
	String name;
	List<Songs> songs;
	public PlaylistDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlaylistDTO(int id, String name, List<Songs> songs) {
		super();
		this.id = id;
		this.name = name;
		this.songs = songs;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Songs> getSongs() {
		return songs;
	}
	public void setSongs(List<Songs> songs) {
		this.songs = songs;
	}
	@Override
	public String toString() {
		return "PlaylistDTO [id=" + id + ", name=" + name + ", songs=" + songs + "]";
	}
	
	
}
