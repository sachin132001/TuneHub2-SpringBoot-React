package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Songs;
import com.example.demo.entities.SongsDTO;
import com.example.demo.services.SongsService;

@CrossOrigin("*")
@RestController
public class SongsController {
	@Autowired
	SongsService songserv;
	
	@PostMapping("/addsongs")
    public String addSongs(@RequestBody SongsDTO songsDTO) {
        Songs song = new Songs();
        song.setName(songsDTO.getName());
        song.setArtist(songsDTO.getArtist());
        song.setGenre(songsDTO.getGenre());
        song.setLink(songsDTO.getLink());

        boolean status = songserv.songExists(song.getName());
        if (!status) {
            songserv.addSongs(song);
            return "adminhome";
        } else {
            return "Song already exists";
        }
    }


    @GetMapping("/map-viewsongs")
    public List<SongsDTO> viewSongs() {
        List<Songs> songsList = songserv.fetchAllSongs();
        List<SongsDTO> songsDTOList = new ArrayList<>();

        for (Songs song : songsList) {
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


	@GetMapping("/viewsongs")
	public String viewCustomerSongs(Model model) {
		boolean primeCustomerStatus=true;
		if(primeCustomerStatus==true) {
			List<Songs> songslist = songserv.fetchAllSongs();
			model.addAttribute("songslist", songslist);
			return "displaysongs";
		}
		else {
			return "makepayment";
		}

	}

}

















