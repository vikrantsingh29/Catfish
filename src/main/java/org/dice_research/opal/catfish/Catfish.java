package org.dice_research.opal.catfish;

import org.apache.jena.rdf.model.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dice_research.opal.common.interfaces.ModelProcessor;


public class Catfish implements ModelProcessor {

	private static final Logger LOGGER = LogManager.getLogger();
	Datecleaning dateclean = new Datecleaning();
	
	public void processModel(Model model, String datasetUri) throws Exception {
		if (model.isEmpty()) {
			LOGGER.warn("Model is empty.");
		dateclean.processModel(model, datasetUri);
	}
	}
	public Model process(Model model, String datasetUri) throws Exception {
		processModel( model,  datasetUri);
		return model;
	}
}