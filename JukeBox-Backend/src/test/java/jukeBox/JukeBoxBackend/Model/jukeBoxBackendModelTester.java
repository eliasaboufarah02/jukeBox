/**
 * 
 */
package jukeBox.JukeBoxBackend.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Elias
 *
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class jukeBoxBackendModelTester {
	
	@Test
	public void testIDJukeBox() {
		String id ="ef3f64g3i847rf438r78";
		JukeBox jukebox = new JukeBox();
		jukebox.setId(id);
		
		assertNotNull(jukebox);
		assertNotNull(jukebox.getId());
		assertEquals(jukebox.getId(),id);
	}
	@Test
	public void testModelJukeBox() {
		String model ="fusion";
		JukeBox jukebox = new JukeBox();
		jukebox.setModel(model);
		
		assertNotNull(jukebox);
		assertNotNull(jukebox.getModel());
		assertEquals(jukebox.getModel(),model);
	}
	@Test
	public void testNameComponents() {
		String name ="led_panel";
		Components component = new Components();
		component.setName(name);
		
		assertNotNull(component);
		assertNotNull(component.getName());
		assertEquals(component.getName(),name);
	}
	@Test
	public void testCompleteJukeBox() {
		String model ="fusion";
		String name ="led_panel";
		String id ="ef3f64g3i847rf438r78";
		JukeBox jukebox = new JukeBox();
		jukebox.setId(id);
		jukebox.setModel(model);
		Components component = new Components();
		component.setName(name);
		ArrayList<Components> listComponents =new ArrayList<Components>();
		listComponents.add(component);
		jukebox.setComponents(listComponents);
		assertNotNull(jukebox);
		assertNotNull(jukebox.getComponents());
		assertEquals(jukebox.getComponents().get(0).getName(),component.getName());
		
	}
	@Test
	public void testConstructorJukeBox() {
		String model ="fusion";
		String name ="led_panel";
		String id ="ef3f64g3i847rf438r78";
		Components component = new Components();
		component.setName(name);
		ArrayList<Components> listComponents =new ArrayList<Components>();
		listComponents.add(component);
		JukeBox jukebox = new JukeBox(id,model,listComponents);
		
		assertNotNull(jukebox);
		assertNotNull(jukebox.getComponents());
		assertEquals(jukebox.getComponents().get(0).getName(),component.getName());
		
	}
	@Test
	public void testConstructorComponents() {
		String name ="led_panel";
		Components component = new Components(name);
		assertNotNull(component);
		assertNotNull(component.getName());
		assertEquals(component.getName(),name);
	}
	@Test
	public void testRequirementComponents() {
		String req ="camera";
		Requirement requirement = new Requirement(req);
		String newreq = "speaker";
		requirement.setRequirement(newreq);
		
		assertNotNull(requirement);
		assertNotNull(requirement.getRequirement());
		assertEquals(requirement.getRequirement(),newreq);
	}
	@Test
	public void testConstructorSettings() {
		String id ="ef3f64g3i847rf438r78";
		String required = "camera";
		Requirement requirement = new Requirement(required);
		ArrayList<Requirement> listRequirements =new ArrayList<Requirement>();
		listRequirements.add(requirement);
		Settings setting =new Settings(id,listRequirements);
		
		
		assertNotNull(setting);
		assertNotNull(setting.getRequirements());
		assertEquals(setting.getRequirements().get(0).getRequirement(),requirement.getRequirement());
	}
	@Test
	public void testIDSettings() {
		String id ="ef3f64g3i847rf438r78";
		String required ="camera";
		Requirement requirement = new Requirement(required);
		ArrayList<Requirement> listRequirements =new ArrayList<Requirement>();
		listRequirements.add(requirement);
		Settings setting =new Settings(id);
		setting.setRequirements(listRequirements);
		String newId = "touchscreen";
		setting.setId(newId);
		assertNotNull(setting);
		assertNotNull(setting.getRequirements());
		assertEquals(setting.getRequirements().get(0).getRequirement(),requirement.getRequirement());
		assertEquals(setting.getId(),newId);
	}
	
}
