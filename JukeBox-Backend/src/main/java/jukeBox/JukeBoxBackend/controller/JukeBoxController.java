package jukeBox.JukeBoxBackend.controller;

import java.io.InputStream;

import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
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


/**
 * Controller of the Api
 * Defines Get mapping for the search Api
 * @author Elias
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/jukebox")
public class JukeBoxController {
	/**
	 *  Spring bean autowiring to service class
	 */
	@Autowired
	private JukeBoxService service;
	
	/**
	 * Search for Juke boxes supporting the following parameters:
	 * @param settingId (Required) setting id of the desired setting configuration
	 * @param offset (Optional) Offset the paginated return list
	 * @param limit (Optional) Limit the number of jukeboxes in the returned paginated list
	 * @param model (Optional) Filter search result by model name
	 * @return Response Entity Containing paginated juke box list
	 */
	@GetMapping(value = { "/findBySettingId", "/findBySettingId/" })
	public ResponseEntity<?> getJukeBoxes(@RequestParam String settingId,@RequestParam(defaultValue = "0") String offset,@RequestParam(defaultValue = "NotSet") String limit,@RequestParam(defaultValue = "-1") String model) {

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
			ArrayList<JukeBox> jukes = service.getJukeBoxes(inputStream,inputStreamSettings,offset,limit,settingId,model);
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
	/**
	 * Data Transfer Obeject converter
	 * @param jukeBox JukeBox Object
	 * @return Dto of JukeBox Object
	 */
	private JukeBoxDto convertToDto(JukeBox jukeBox) {

		return new JukeBoxDto(jukeBox.getId(), jukeBox.getModel(), jukeBox.getComponents());
	}
}
