package org.dice_research.opal.catfish;


import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.DCAT;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.RDF;
import org.dice_research.opal.catfish.Catfish;
import org.dice_research.opal.catfish.ModelHomogeneousLicenseChecker;
import org.junit.Assert;
import org.junit.Test;

public class LicenseCleaningTest {
	
	
	/**
	 * Tests if Licenses are heterogeneous.
	 */
	@Test
	public void test1() throws Exception {
		
		String datasetUri = "http://projekt-opal.de/dataset/https___europeandataportal_eu_set_data_birth_registrations_by_month_since_january_2009_to_june_2011";
		Catfish catfish = new Catfish();
		
		//Current model has heterogeneous licenses
		Model old_model = ModelFactory.createDefaultModel();
		old_model.read(getClass().getClassLoader().getResource("cleaning.ttl").getFile(), "TURTLE");
        
		ModelHomogeneousLicenseChecker check_licenses = new ModelHomogeneousLicenseChecker(); 
		
		Assert.assertTrue("A Model with heterogeneous licenses", check_licenses.AreLicensesHomogeneous(catfish.process(old_model, datasetUri), datasetUri));		
	}

	
}
