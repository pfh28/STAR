package sample;

/**
 * Created by Patrick on 7/13/2015.
 */
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "persons")
public class PersonListWrapper
{
    private List<Person> persons;

    @XmlElement(name = "person")
    public List<Person> getPersons()
    {
        return persons;
    }

    public void setPersons(List<Person> persons)
    {
        this.persons = persons;
    }
}
