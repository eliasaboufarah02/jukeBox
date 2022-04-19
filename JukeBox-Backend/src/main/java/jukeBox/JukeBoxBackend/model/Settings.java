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
	private String id;
	private ArrayList<Requirement> requirements;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
		this.id = id;
		this.requirements = requirements;
	}
	/**
	 * @param id
	 */
	public Settings(String id) {
		this.id = id;
	}
	
}
