package jukeBox.JukeBoxBackend.model;

import java.util.ArrayList;

public class JukeBox {
	private String id;
	private String model;
	private ArrayList<String> components;
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
	 * @return the model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the components
	 */
	public ArrayList<String> getComponents() {
		return components;
	}
	/**
	 * @param components the components to set
	 */
	public void setComponents(ArrayList<String> components) {
		this.components = components;
	}
	/**
	 * @param id
	 * @param model
	 * @param components
	 */
	public JukeBox(String id, String model, ArrayList<String> components) {
		this.id = id;
		this.model = model;
		this.components = components;
	}
	/**
	 * 
	 */
	public JukeBox() {
	}
	
	
	
}
