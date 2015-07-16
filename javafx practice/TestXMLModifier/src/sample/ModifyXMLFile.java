package sample;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ModifyXMLFile {

    private static String urlValue;
    private static String usernameValue;
    private static String passwordValue;
    public static void main(String argv[])
    {
        urlValue = "database.url";
        usernameValue = "user";
        passwordValue = "guest";
        generate();
    }

    public static void setUrlValue(String url)
    {
        urlValue = url;
    }

    public static void setUsernameValue(String username)
    {
        usernameValue = username;
    }

    public static void setPasswordValue(String password)
    {
        passwordValue = password;
    }

    private static void generate()
    {
        try
        {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Beans");
            doc.appendChild(rootElement);

            Attr xmlns = doc.createAttribute("xmlns");
            xmlns.setValue("http://www.springframework.org/schema/beans");
            rootElement.setAttributeNode(xmlns);

            Attr mid = doc.createAttribute("xmlns:xsi");                    //wasn't sure what to call this attribute
            mid.setValue("http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttributeNode(mid);

            Attr location = doc.createAttribute("xsi:schemaLocation");
            location.setValue("http://www.springframework.org/schema/beans");           //http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
            rootElement.setAttributeNode(location);                         //appears in original on newline,no other spacing

            // datasource element
            Element dataSourceElement = doc.createElement("bean");
            rootElement.appendChild(dataSourceElement);

            // set attribute to datasource element
            Attr id = doc.createAttribute("id");
            id.setValue("dataSource");
            dataSourceElement.setAttributeNode(id);

            Attr eleClass = doc.createAttribute("class");
            eleClass.setValue("org.springframework.jdbc.datasource.DriverManagerDataSource");
            dataSourceElement.setAttributeNode(eleClass);

            // shorten way
            // staff.setAttribute("id", "1");

            // driverClassName property
            Element driverClassName = doc.createElement("property");
            dataSourceElement.appendChild(driverClassName);

            driverClassName.setAttribute("name", "driverClassName");
            driverClassName.setAttribute("value","org.postgresql.Driver");

            //url property
            Element url = doc.createElement("property");
            dataSourceElement.appendChild(url);

            url.setAttribute("name","url");
            url.setAttribute("value",urlValue);

            //username property
            Element username = doc.createElement("property");
            dataSourceElement.appendChild(username);

            username.setAttribute("name","username");
            username.setAttribute("value",usernameValue);

            //password property
            Element password = doc.createElement("property");
            dataSourceElement.appendChild(password);

            password.setAttribute("name", "password");
            password.setAttribute("value",passwordValue);

            // jdbcTemplate element
            Element jdbcTemplate = doc.createElement("bean");
            rootElement.appendChild(jdbcTemplate);

            jdbcTemplate.setAttribute("id", "JDBCTemplate");
            jdbcTemplate.setAttribute("class","fnirstool.JDBCTemplate");

            //datasource property
            Element dataSourceProperty = doc.createElement("property");
            jdbcTemplate.appendChild(dataSourceProperty);

            dataSourceProperty.setAttribute("name", "dataSource");
            dataSourceProperty.setAttribute("ref","dataSource");

            // subjectJDBCTemplate element
            Element subjectJDBCTemplate = doc.createElement("bean");
            rootElement.appendChild(subjectJDBCTemplate);

            subjectJDBCTemplate.setAttribute("id", "SubjectJDBCTemplate");
            subjectJDBCTemplate.setAttribute("class","GUI.model.JDBCAccess.SubjectJdbcTemplate");

            //dataSource property 2
            Element dataSourceProperty2 = doc.createElement("property");         //it seems that an element cannot be a child to two
            subjectJDBCTemplate.appendChild(dataSourceProperty2);               //different elements.

            dataSourceProperty2.setAttribute("name","dataSource");
            dataSourceProperty2.setAttribute("ref","dataSource");

            //ChannelJDBCTemplate element
            Element channelJDBCTemplate = doc.createElement("bean");
            rootElement.appendChild(channelJDBCTemplate);

            channelJDBCTemplate.setAttribute("id", "ChannelJDBCTemplate");
            channelJDBCTemplate.setAttribute("class","GUI.model.JDBCAccess.ChannelJdbcTemplate");

            //dataSource property 3
            Element dataSourceProperty3 = doc.createElement("property");
            channelJDBCTemplate.appendChild(dataSourceProperty3);

            dataSourceProperty3.setAttribute("name","dataSource");
            dataSourceProperty3.setAttribute("ref", "dataSource");


            // DetectorJDBCTemplate element
            Element detectorJDBCTemplate = doc.createElement("bean");
            rootElement.appendChild(detectorJDBCTemplate);

            detectorJDBCTemplate.setAttribute("id", "DetectorJDBCTemplate");
            detectorJDBCTemplate.setAttribute("class","GUI.model.JDBCAccess.DetectorJdbcTemplate");

            //dataSource property 4
            Element dataSourceProperty4 = doc.createElement("property");
            detectorJDBCTemplate.appendChild(dataSourceProperty4);

            dataSourceProperty4.setAttribute("name","dataSource");
            dataSourceProperty4.setAttribute("ref", "dataSource");

            // lightSourceJDBCTemplate element
            Element lightSourceJDBCTemplate = doc.createElement("bean");
            rootElement.appendChild(lightSourceJDBCTemplate);

            lightSourceJDBCTemplate.setAttribute("id", "LightSourceJDBCTemplate");
            lightSourceJDBCTemplate.setAttribute("class","GUI.model.JDBCAccess.LightSourceJdbcTemplate");

            //dataSource property 5
            Element dataSourceProperty5 = doc.createElement("property");
            lightSourceJDBCTemplate.appendChild(dataSourceProperty5);

            dataSourceProperty5.setAttribute("name","dataSource");
            dataSourceProperty5.setAttribute("ref", "dataSource");

            // rawDataJDBCTemplate element
            Element rawDataJDBCTemplate = doc.createElement("bean");
            rootElement.appendChild(rawDataJDBCTemplate);

            rawDataJDBCTemplate.setAttribute("id", "RawDataJDBCTemplate");
            rawDataJDBCTemplate.setAttribute("class","GUI.model.JDBCAccess.RawDataJdbcTemplate");

            //dataSource property 6
            Element dataSourceProperty6 = doc.createElement("property");
            rawDataJDBCTemplate.appendChild(dataSourceProperty6);

            dataSourceProperty6.setAttribute("name","dataSource");
            dataSourceProperty6.setAttribute("ref", "dataSource");

            // stimMarkerJDBCTemplate element
            Element stimMarkerJDBCTemplate = doc.createElement("bean");
            rootElement.appendChild(stimMarkerJDBCTemplate);

            stimMarkerJDBCTemplate.setAttribute("id", "StimMarkerJDBCTemplate");
            stimMarkerJDBCTemplate.setAttribute("class","GUI.model.JDBCAccess.StimMarkerJdbcTemplate");

            //dataSource property 7
            Element dataSourceProperty7 = doc.createElement("property");
            stimMarkerJDBCTemplate.appendChild(dataSourceProperty7);

            dataSourceProperty7.setAttribute("name","dataSource");
            dataSourceProperty7.setAttribute("ref", "dataSource");



            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Users\\Patrick\\Desktop\\file.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}