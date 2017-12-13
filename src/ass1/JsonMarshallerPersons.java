package ass1;

import people.generated.*;

import javax.xml.bind.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;


public class JsonMarshallerPersons {

	 public static void main(String[] args) throws Exception {
	        ObjectFactory factory = new ObjectFactory();
	        PeopleList peopleList = factory.createPeopleList();

	        peopleList = JAXBMarshallerPerson.populateSamplePersons(peopleList);   
	        
	        // Jackson Object Mapper
	        ObjectMapper mapper = new ObjectMapper();

	        // Adding the Jackson Module to process JAXB annotations
	        JaxbAnnotationModule module = new JaxbAnnotationModule();

	        // configure as necessary
	        mapper.registerModule(module);
	        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

	        String result = mapper.writeValueAsString(peopleList);
	        System.out.println(result);
	        mapper.writeValue(new File("newpersons.json"), peopleList);
	}
}
