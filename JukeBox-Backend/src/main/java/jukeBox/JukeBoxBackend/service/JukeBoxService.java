package jukeBox.JukeBoxBackend.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jukeBox.JukeBoxBackend.model.JukeBox;
import jukeBox.JukeBoxBackend.model.Requirement;
import jukeBox.JukeBoxBackend.model.Settings;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


@Service
public class JukeBoxService {
	private Integer limit;
	public ArrayList<JukeBox> getJukeBoxes(InputStream inputStream,InputStream inputStreamSettings,String shift,String max,String settingid,String model) throws Exception {
		try {
			Integer offset = Integer.parseInt(shift);
			try {
				if (max.equals("NotSet")) {
					limit =Integer.parseInt("-1");
				} else {
					limit = Integer.parseInt(max);
					if (limit<0) throw new Exception("Limit Value can't be negative");
				}
				
				try {
					ArrayList<JukeBox> allJukeBoxes = generateJukeBoxes(inputStream);
					try {
						ArrayList<Settings> settings = generateSettings(inputStreamSettings);
						ArrayList<JukeBox> filteredJukeBoxes = search(allJukeBoxes,settings,settingid,model,offset,limit);
						return filteredJukeBoxes;
					} catch (Exception e) {
						throw new Exception(e.getMessage());
					}
				} catch (Exception e) {
					throw new Exception(e.getMessage());
				}
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	private ArrayList<JukeBox> search(ArrayList<JukeBox> matches, ArrayList<Settings> settingsList, String settingid,String model,Integer offset,Integer limit) throws Exception {
		Settings matchesSettings = settingsList.stream().filter(setting-> setting.getId().equals(settingid))
	            .findFirst().orElse(null);
		ArrayList<Requirement> requirements=matchesSettings.getRequirements();
		
		for (Requirement req:requirements) {
			
			ArrayList<JukeBox> updatedMatches = (ArrayList<JukeBox>) matches.parallelStream()
	            .filter((element) -> element.getComponents().contains(req.getRequirement()))
	            .collect(Collectors.toList());
			matches=updatedMatches;
		}
		if (!model.equals("-1")) {
			matches = (ArrayList<JukeBox>) matches.parallelStream()
		            .filter((element) -> element.getModel().equals(model))
		            .collect(Collectors.toList());
		}
		if (offset>matches.size()) throw new Exception("OffSet Value is too Big");
		if (offset<0) throw new Exception("OffSet Value can't be negative");
		if (matches.size()>limit&&limit!=-1) {
			if (limit+offset>matches.size()) {
				return new ArrayList<JukeBox>(matches.subList(offset, matches.size()));
			}else {
				return new ArrayList<JukeBox>(matches.subList(offset, limit+offset));
			}
		}
		
			
		
		return new ArrayList<JukeBox>(matches.subList(offset, matches.size()));
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
			settings.add(setting);
		
		}
		return settings;
	}
	private ArrayList<JukeBox> generateJukeBoxes(InputStream inputStream) throws Exception{
		ArrayList<JukeBox> jukeBoxes = new ArrayList<JukeBox>();
		JsonReader fileReader = Json.createReader(inputStream);
		JsonArray array = fileReader.readArray();
		for (int j=0;j<array.size();j++) {
			ArrayList<String> components = new ArrayList<String>();
			JsonArray componentsToGet =array.getJsonObject(j).getJsonArray("components");
			for (int i=0;i<componentsToGet.size();i++) {
				components.add(componentsToGet.getJsonObject(i).getString("name"));
			}
			JukeBox juke = new JukeBox(array.getJsonObject(j).getString("id"),array.getJsonObject(j).getString("model"),components);
			jukeBoxes.add(juke);
		
		}
		return jukeBoxes;
	}

	
}
