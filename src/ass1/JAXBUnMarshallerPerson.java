package ass1;
import people.generated.*;
import org.xml.sax.SAXException;

import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.List;

public class JAXBUnMarshallerPerson {
    public void unMarshall(File xmlDocument) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance("people.generated");

            Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
            SchemaFactory schemaFactory = SchemaFactory
                    .newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = schemaFactory.newSchema(new File(
                    "people.xsd"));
            unMarshaller.setSchema(schema);
            CustomValidationEventHandler validationEventHandler = new CustomValidationEventHandler();
            unMarshaller.setEventHandler(validationEventHandler);

            @SuppressWarnings("unchecked")
            JAXBElement<PeopleList> peopleElement = (JAXBElement<PeopleList>) unMarshaller
                    .unmarshal(xmlDocument);

            PeopleList people = peopleElement.getValue();

            List<PersonType> peopleList = people.getPerson();
            for (int i = 0; i < peopleList.size(); i++) {
            	System.out.println(">>>");
                PersonType person = (PersonType) peopleList.get(i);
                System.out.println("Name: " + person.getFirstname() + " " + person.getLastname());
                System.out.println("Birth date: " + person.getBirthdate());
                System.out.println("ID: " + person.getId());
                System.out.println("AcitivityPreference: " + person.getActivitypreference().getName());
                System.out.println("Description: " + person.getActivitypreference().getDescription());
                System.out.println("Place: " + person.getActivitypreference().getPlace());
                System.out.println("Start date: " + person.getActivitypreference().getStartdate());
                
            }
        } catch (JAXBException e) {
            System.out.println(e.toString());
        } catch (SAXException e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String[] argv) {
        File xmlDocument = new File("newpersons.xml");
        JAXBUnMarshallerPerson jaxbUnmarshaller = new JAXBUnMarshallerPerson();

        jaxbUnmarshaller.unMarshall(xmlDocument);

    }

    class CustomValidationEventHandler implements ValidationEventHandler {
        public boolean handleEvent(ValidationEvent event) {
            if (event.getSeverity() == ValidationEvent.WARNING) {
                return true;
            }
            if ((event.getSeverity() == ValidationEvent.ERROR)
                    || (event.getSeverity() == ValidationEvent.FATAL_ERROR)) {

                System.out.println("Validation Error:" + event.getMessage());

                ValidationEventLocator locator = event.getLocator();
                System.out.println("at line number:" + locator.getLineNumber());
                System.out.println("Unmarshalling Terminated");
                return false;
            }
            return true;
        }

    }
}
