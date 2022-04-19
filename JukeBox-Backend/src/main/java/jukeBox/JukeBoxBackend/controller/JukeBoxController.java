package jukeBox.JukeBoxBackend.controller;

import java.io.BufferedReader;

import java.io.InputStream;

import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties.Settings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jukeBox.JukeBoxBackend.dto.JukeBoxDto;
import jukeBox.JukeBoxBackend.model.JukeBox;
import jukeBox.JukeBoxBackend.service.JukeBoxService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/juke")
public class JukeBoxController {
	@Autowired
	private JukeBoxService service;
	
	@GetMapping(value = { "/jukes", "/jukes/" })
	public ResponseEntity<?> getJukeBoxes(@RequestParam(defaultValue = "0") String offset,@RequestParam(defaultValue = "-1") String max) {

		ArrayList<JukeBoxDto> jukeBoxes = new ArrayList<JukeBoxDto>();
		URL url;
		try {
			url = new URL("http://my-json-server.typicode.com/touchtunes/tech-assignment/jukes");
			HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			InputStream inputStream = con.getInputStream();
			URL urlSettings = new URL("http://my-json-server.typicode.com/touchtunes/tech-assignment/settings");
			HttpURLConnection conSettings;
			conSettings = (HttpURLConnection) urlSettings.openConnection();
			conSettings.setRequestMethod("GET");
			InputStream inputStreamSettings = conSettings.getInputStream();
			ArrayList<JukeBox> jukes = service.getJukeBoxes(inputStream,inputStreamSettings,offset,max);
			for (JukeBox a : jukes) {
				if (a != null) {
					jukeBoxes.add(convertToDto(a));
				}
			}
			return new ResponseEntity<>(jukeBoxes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
	
	private JukeBoxDto convertToDto(JukeBox jukeBox) {

		return new JukeBoxDto(jukeBox.getId(), jukeBox.getModel(), jukeBox.getComponents());
	}
}
