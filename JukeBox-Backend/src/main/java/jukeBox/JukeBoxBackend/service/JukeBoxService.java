package jukeBox.JukeBoxBackend.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import jukeBox.JukeBoxBackend.model.Components;
import jukeBox.JukeBoxBackend.model.JukeBox;


@Service
public class JukeBoxService {

	public ArrayList<JukeBox> processJukeBoxes(InputStream responseStream) {
//		JsonParser jsonParser;
		ArrayList<JukeBox> jukeBoxes = new ArrayList<JukeBox>();
//		try {
//			jsonParser = new JsonFactory().createParser(responseStream);
//			while(jsonParser.nextToken() != JsonToken.END_OBJECT) {
//				jsonParser.getCurrentName() + ":" +      jsonParser.getCurrentValue());
		Components component = new Components("led_panel");
		ArrayList<Components> components = new ArrayList<Components>();
		components.add(component);
		    JukeBox juke = new JukeBox("1","fus",components);
//		}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		    //off set and limit
		jukeBoxes.add(juke);
		return jukeBoxes;
	}
	
}
