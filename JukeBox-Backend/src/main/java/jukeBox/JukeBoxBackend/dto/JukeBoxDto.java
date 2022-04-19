package jukeBox.JukeBoxBackend.dto;

import java.util.ArrayList;

import jukeBox.JukeBoxBackend.model.Components;

public class JukeBoxDto {
	//Attribute Fields
	private String id;
	private String model;
	//Associations
	private ArrayList<Components> components;
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
	public ArrayList<Components> getComponents() {
		return components;
	}
	/**
	 * @param components the components to set
	 */
	public void setComponents(ArrayList<Components> components) {
		this.components = components;
	}
	/**
	 * @param id
	 * @param model
	 * @param components
	 */
	public JukeBoxDto(String id, String model, ArrayList<Components> components) {
		super();
		this.id = id;
		this.model = model;
		this.components = components;
	}

	
}
