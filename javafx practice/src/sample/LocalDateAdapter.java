package sample;

/**
 * Created by Patrick on 7/13/2015.
 *
 * this class allows for the conversion of localdate to and form string, so that it can be stored
 */
import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate>
{
    @Override
    public LocalDate unmarshal(String v) throws Exception
    {
        return LocalDate.parse(v);
    }

    @Override
    public String marshal(LocalDate v)throws Exception
    {
        return v.toString();
    }
}
