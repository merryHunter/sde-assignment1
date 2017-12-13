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

public class JAXBMarshallerPerson {
	 public void generateXMLDocument(File xmlDocument) {
        try {

	            JAXBContext jaxbContext = JAXBContext.newInstance("people.generated");
	            Marshaller marshaller = jaxbContext.createMarshaller();
	            marshaller.setProperty("jaxb.formatted.output", new Boolean(true));
	            ObjectFactory factory = new people.generated.ObjectFactory();
	            
	            PeopleList peopleList = factory.createPeopleList();
	            
	            peopleList = JAXBMarshallerPerson.populateSamplePersons(peopleList);           
	        	
	            JAXBElement<PeopleList> peopleElement = factory.createPeople(peopleList);
	            marshaller.marshal(peopleElement,
	                    new FileOutputStream(xmlDocument));

	            marshaller.marshal(peopleElement, System.out);

	        } catch (JAXBException | DatatypeConfigurationException | IOException e) {
	            System.out.println(e.toString());
	        }

	    } 

	public static PeopleList populateSamplePersons(PeopleList peopleList) {
		// person 1
        ActivityPreference pref = factory.createActivityPreference();
    	pref.setDescription("Hunting on bears in a forest.");
    	pref.setName("Hunting");
    	pref.setPlace("Koriukivka");
    	pref.setStartdate("2017-10-13T11:50:00.0");
    	
    	PersonType person = factory.createPersonType();
    	person.setActivitypreference(pref);
    	person.setId(new Long(0));
    	person.setFirstname("Ivan");
    	person.setLastname("Chernukha");
    	person.setBirthdate( DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(new GregorianCalendar(1995, 07, 03)));
    	peopleList.getPerson().add(person);
    	
    	// person 2
    	pref = factory.createActivityPreference();
    	pref.setDescription("Running in Gocciadoro.");
    	pref.setName("Running");
    	pref.setPlace("Trento");
    	pref.setStartdate("2016-10-13T11:50:00.0");
    	
    	person = factory.createPersonType();
    	person.setActivitypreference(pref);
    	person.setId(new Long(1));
    	person.setFirstname("Pedro");
    	person.setLastname("Mancini");
    	person.setBirthdate( DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(new GregorianCalendar(1993, 14, 18)));
    	peopleList.getPerson().add(person);
    	// person 3
    	pref = factory.createActivityPreference();
    	pref.setDescription("Biking in Dolomities.");
    	pref.setName("Cycling");
    	pref.setPlace("Bolzano");
    	pref.setStartdate("2017-04-13T10:50:00.0");
    	
    	person = factory.createPersonType();
    	person.setActivitypreference(pref);
    	person.setId(new Long(2));
    	person.setFirstname("Davide");
    	person.setLastname("Peroni");
    	person.setBirthdate( DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(new GregorianCalendar(1995, 11, 11)));
    	peopleList.getPerson().add(person);
	}
	 
    public static void main(String[] argv) {
		String xmlDocument = "newpersons.xml";
		JAXBMarshallerPerson jaxbMarshaller = new JAXBMarshallerPerson();
		jaxbMarshaller.generateXMLDocument(new File(xmlDocument));
	}
}
