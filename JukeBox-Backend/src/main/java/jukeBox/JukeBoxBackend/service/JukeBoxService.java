package jukeBox.JukeBoxBackend.service;

import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import jukeBox.JukeBoxBackend.model.Components;
import jukeBox.JukeBoxBackend.model.JukeBox;
import jukeBox.JukeBoxBackend.model.Requirement;
import jukeBox.JukeBoxBackend.model.Settings;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


@Service
public class JukeBoxService {

	public ArrayList<JukeBox> getJukeBoxes(InputStream inputStream,InputStream inputStreamSettings,String shift,String max) throws Exception {
		try {
			Integer offset = Integer.parseInt(shift);
			try {
				Integer limit = Integer.parseInt(max);
				try {
					ArrayList<JukeBox> allJukeBoxes = generateJukeBoxes(inputStream);
					try {
						ArrayList<Settings> settings = generateSettings(inputStreamSettings);
						return allJukeBoxes;
						//if (offset>array.size()) throw new Exception("OffSet Value is too Big");
					} catch (Exception e) {
						throw new Exception(e.getMessage());
					}
				} catch (Exception e) {
					throw new Exception(e.getMessage());
				}
		} catch(Exception e) {
			throw new Exception("Limit value is not an acceptable number. "+e.getMessage());
		}
		
		} catch(Exception e) {
			throw new Exception("Offset value is not an acceptable number. "+e.getMessage());
		}
		
	}
	private ArrayList<Settings> generateSettings(InputStream inputStreamSettings) {
		ArrayList<Settings> settings = new ArrayList<Settings>();
		JsonReader fileReader = Json.createReader(inputStreamSettings);
		JsonObject object = fileReader.readObject();
		JsonArray array=object.getJsonArray("settings");
		for (int j=0;j<array.size();j++) {
			ArrayList<Requirement> requirements = new ArrayList<Requirement>();
			JsonArray requirementsToGet =array.getJsonObject(j).getJsonArray("requires");
			for (int i=0;i<requirementsToGet.size();i++) {
				requirements.add(new Requirement(requirementsToGet.getString(i)));
			}
			Settings setting = new Settings(array.getJsonObject(j).getString("id"),requirements);
		    //offset and limit
			settings.add(setting);
		
		}
		return settings;
	}
	private ArrayList<JukeBox> generateJukeBoxes(InputStream inputStream) throws Exception{
		ArrayList<JukeBox> jukeBoxes = new ArrayList<JukeBox>();
		JsonReader fileReader = Json.createReader(inputStream);
		JsonArray array = fileReader.readArray();
		for (int j=0;j<array.size();j++) {
			ArrayList<Components> components = new ArrayList<Components>();
			JsonArray componentsToGet =array.getJsonObject(j).getJsonArray("components");
			for (int i=0;i<componentsToGet.size();i++) {
				components.add(new Components(componentsToGet.getJsonObject(i).getString("name")));
			}
			JukeBox juke = new JukeBox(array.getJsonObject(j).getString("id"),array.getJsonObject(j).getString("model"),components);
		    //offset and limit
			jukeBoxes.add(juke);
		
		}
		return jukeBoxes;
	}

	
}
