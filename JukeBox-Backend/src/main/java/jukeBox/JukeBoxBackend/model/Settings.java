/**
 * 
 */
package jukeBox.JukeBoxBackend.model;

import java.util.ArrayList;

/**
 * @author Elias
 *
 */
public class Settings {
	private String settingId;
	private ArrayList<Requirement> requirements;
	/**
	 * @return the id
	 */
	public String getId() {
		return settingId;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.settingId = id;
	}
	/**
	 * @return the requirement
	 */
	public ArrayList<Requirement> getRequirements() {
		return requirements;
	}
	/**
	 * @param requirement the requirement to set
	 */
	public void setRequirements(ArrayList<Requirement> requirements) {
		this.requirements = requirements;
	}
	/**
	 * @param id
	 * @param requirement
	 */
	public Settings(String id, ArrayList<Requirement> requirements) {
		this.settingId = id;
		this.requirements = requirements;
	}
	/**
	 * @param id
	 */
	public Settings(String id) {
		this.settingId = id;
	}
	
}
