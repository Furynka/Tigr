package com.dreamteam.mvc.controllers;

import com.dreamteam.dto.SpeciesDTO;
import com.dreamteam.facade.SpeciesFacade;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static com.dreamteam.mvc.controllers.SpeciesController.MODEL_ATTR_SPECIES_LIST;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
public class SpeciesControllerTest {

	@Mock
	private SpeciesFacade speciesFacade;

	private MockMvc mockMvc;

	private SpeciesDTO speciesDTO1;
	private SpeciesDTO speciesDTO2;

	@BeforeClass
	public void setup() {
		MockitoAnnotations.initMocks(this);
		SpeciesController speciesController = new SpeciesController();

		try {
			Field field = SpeciesController.class.getDeclaredField("speciesFacade");
			field.setAccessible(true);
			field.set(speciesController, speciesFacade);
			field.setAccessible(false);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}

		mockMvc = MockMvcBuilders.standaloneSetup(speciesController).build();
	}


	@BeforeMethod
	public void setUp() throws Exception {
		speciesDTO1 = new SpeciesDTO();
		speciesDTO1.setId(1L);
		speciesDTO1.setName("name1");
		speciesDTO1.setDescription("desc1");
		speciesDTO1.setInDanger(true);

		speciesDTO2 = new SpeciesDTO();
		speciesDTO2.setId(2L);
		speciesDTO2.setName("name2");
		speciesDTO2.setDescription("desc2");
		speciesDTO2.setInDanger(false);
	}

	@Test
	public void testList() throws Exception {
		when(speciesFacade.getAllSpecieses()).thenReturn(Arrays.asList(speciesDTO1, speciesDTO2));

		this.mockMvc.perform(get("/species").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists(MODEL_ATTR_SPECIES_LIST))
				.andExpect(model().attribute(MODEL_ATTR_SPECIES_LIST, Arrays.asList(speciesDTO1, speciesDTO2)));
	}

}