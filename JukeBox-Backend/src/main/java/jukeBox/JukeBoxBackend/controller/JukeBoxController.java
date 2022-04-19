package jukeBox.JukeBoxBackend.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<?> getAllAccounts() {

		ArrayList<JukeBoxDto> jukeBoxes = new ArrayList<JukeBoxDto>();
		URL url;
		try {
			url = new URL("http://my-json-server.typicode.com/touchtunes/tech-assignment/jukes");
			HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			InputStream responseStream=con.getInputStream();
			ArrayList<JukeBox> jukes = service.processJukeBoxes(responseStream);
			for (JukeBox a : jukes) {
				if (a != null) {
					jukeBoxes.add(convertToDto(a));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<>(jukeBoxes, HttpStatus.OK);
	}

	private JukeBoxDto convertToDto(JukeBox jukeBox) {

		return new JukeBoxDto(jukeBox.getId(), jukeBox.getModel(), jukeBox.getComponents());
	}
}
