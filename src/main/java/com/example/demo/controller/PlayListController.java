package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.PlayList;
import com.example.demo.entities.PlaylistDTO;
import com.example.demo.entities.Songs;
import com.example.demo.entities.SongsDTO;
import com.example.demo.services.PlayListService;
import com.example.demo.services.SongsService;


import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class PlayListController {

    @Autowired
    private PlayListService playListService;

    @Autowired
    private SongsService sserv;

    @GetMapping("/createplaylist")
    public List<SongsDTO> createPlaylist() {
        List<Songs> songs = sserv.fetchAllSongs();
        List<SongsDTO> songsDTOList = new ArrayList<>();
        
        for (Songs song : songs) {
            SongsDTO songsDTO = new SongsDTO();
            songsDTO.setId(song.getId());
            songsDTO.setName(song.getName());
            songsDTO.setArtist(song.getArtist());
            songsDTO.setGenre(song.getGenre());
            songsDTO.setLink(song.getLink());
            songsDTOList.add(songsDTO);
        }
        
        return songsDTOList;
    }

    @PostMapping("/addplaylist")
    public String addPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        // Convert PlaylistDTO to PlayList
        PlayList playlist = new PlayList();
        playlist.setName(playlistDTO.getName());
        playlist.setSongs(playlistDTO.getSongs());
        playListService.addPlaylist(playlist);
       
      //update song table
      		List<Songs> songsList=playlistDTO.getSongs();
      		for(Songs songs :songsList) {
      			songs = sserv.findById(songs.getId());
      			songs.getPlaylist().add(playlist);
      			sserv.updateSong(songs);
      		}

       

        return "adminhome";
    }

    @GetMapping("/viewPlaylists")
    public List<PlaylistDTO> viewPlaylists() {
        List<PlayList> playlists = playListService.fetchPlaylists();
        List<PlaylistDTO> playlistDTOs = new ArrayList<>();
        
        for (PlayList playlist : playlists) {
            PlaylistDTO playlistDTO = new PlaylistDTO();
            playlistDTO.setId(playlist.getId());
            playlistDTO.setName(playlist.getName());
            playlistDTO.setSongs(playlist.getSongs());
            // You may need to map other fields as needed
            
            // Add the playlist DTO to the list
            playlistDTOs.add(playlistDTO);
        }

        return playlistDTOs;
    }



}
