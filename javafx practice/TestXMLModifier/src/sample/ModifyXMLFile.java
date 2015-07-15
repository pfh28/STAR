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
    public static void main(String argv[])
    {
        urlValue = "yas";
        generate();
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

            driverClassName.setAttribute("name", "driver");
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

            // lastname elements
            Element lastname = doc.createElement("lastname");
            lastname.appendChild(doc.createTextNode("mook kim"));
            dataSourceElement.appendChild(lastname);

            // nickname elements
            Element nickname = doc.createElement("nickname");
            nickname.appendChild(doc.createTextNode("mkyong"));
            dataSourceElement.appendChild(nickname);

            // salary elements
            Element salary = doc.createElement("salary");
            salary.appendChild(doc.createTextNode("200000"));
            dataSourceElement.appendChild(salary);

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