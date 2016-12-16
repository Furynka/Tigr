package com.dreamteam.sampledata;

import com.dreamteam.dto.SpeciesDTO;
import com.dreamteam.dto.WorkerDTO;
import com.dreamteam.facade.SpeciesFacade;
import com.dreamteam.facade.WorkerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by khudiakov on 15.12.2016.
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private WorkerFacade workerFacade;
	@Autowired
	private SpeciesFacade speciesFacade;

    @Override
    @SuppressWarnings("unused")
    public void loadData() {
        worker("test@test.ru", "password", true);
		worker("test1@test.ru", "password", false);

		species("Species1", "Species1Descrition", true);
		species("Species2", "Species2Descrition", false);
		species("Species3", "Species3Descrition", true);
	}

	private void species(String name, String description, boolean inDanger) {
		SpeciesDTO speciesDTO = new SpeciesDTO();
		speciesDTO.setName(name);
		speciesDTO.setDescription(description);
		speciesDTO.setInDanger(inDanger);
		speciesFacade.createSpecies(speciesDTO);
	}

    private void worker(String email, String password, boolean admin) {
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setEmail(email);
        workerDTO.setAdministrator(admin);
        workerFacade.registerWorker(workerDTO, password);
    }
}
