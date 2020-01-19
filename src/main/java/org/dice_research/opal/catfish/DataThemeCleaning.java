package org.dice_research.opal.catfish;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.DCAT;
import org.apache.jena.vocabulary.SKOS;
import org.apache.jena.rdf.model.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

public class DataThemeCleaning {

    ArrayList<Statement> deletingStatements = new ArrayList<Statement>();
    private static final Logger LOGGER = LogManager.getLogger();

    public void processModel(Model model, String datasetUri) throws Exception {

        Resource dataset = ResourceFactory.createResource(datasetUri);
        StmtIterator itr = model.listStatements(dataset, DCAT.theme,(RDFNode) null);
        ArrayList<String> prefLabels = new ArrayList<String>();


        while(itr.hasNext())
        {
            Statement stmt = itr.nextStatement();
            RDFNode object = stmt.getObject();

            if(prefLabels.contains(object.toString()))
                deletingStatements.add(stmt);
            else
                prefLabels.add(object.toString());

            if (object.isAnon())
            {
                Resource objectAsResource = (Resource) object ;
                //dcat.theme []
                if(!objectAsResource.hasProperty(SKOS.prefLabel))
                    deletingStatements.add(stmt);
            }
        }

        removeStatement();

        if (model.isEmpty()) {
            LOGGER.warn("Model is empty.");
        }
    }

    public void removeStatement(){
        for(Statement stmt : deletingStatements)
            stmt.remove();
    }
}

