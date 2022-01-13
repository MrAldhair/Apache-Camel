package com.microservice.camelmicroservice.xml;

import com.microservice.camelmicroservice.model.SaveSale;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public static void writerXML(SaveSale sale) throws JDOMException, IOException {
        Document doc = null;
        Element root = null;

        File xmlFile = new File("C:/sales.xml");
        if(xmlFile.exists()){
            FileInputStream inputStream = new FileInputStream(xmlFile); //crecion de archivo
            SAXBuilder saxBuilder = new SAXBuilder(); //parseador a xml
            doc = saxBuilder.build(inputStream);
            root = doc.getRootElement();
            inputStream.close();
        }else{
            doc = new Document();
            root = new Element("list-sales");
        }
        //inyeccion de datos desde modelo Sale
        Element child = new Element("sale");
        Element folio = new Element("folio").setText(sale.getFolio());
        Element idEmployee = new Element("id_employee").setText(Integer.toString(sale.getId_employee()));
        Element idBranch = new Element("id_branch_office").setText(Integer.toString(sale.getId_branch_office()));
        Element nameBranch = new Element("name_branch_office").setText(sale.getName_branch_office());
        Element totalSale = new Element("total_sale").setText(Double.toString(sale.getTotal_sale()));
        Element description = new Element("description").setText(sale.getDescription());
        Element date = new Element("date_sale").setText(sale.getDate_sale());
        Element nameEmployee = new Element("name_employee").setText(sale.getName_employee());
        Element state = new Element("state_branch_office").setText(sale.getState_branch_office());
        Element city = new Element("city_branch_office").setText(sale.getCity_branch_office());
        Element street = new Element("street_branch_office").setText(sale.getStreet_branch_office());
        Element numberBranchOffice = new Element("number_branch_office").setText(Integer.toString(sale.getNumber_branch_office()));
        Element zipCode = new Element("zip_code_branch_office").setText(Integer.toString(sale.getZip_code_branch_office()));
        //cada child es un elemento hijo de sale, y sale es un elemento hijo de list-sales
        child.addContent(folio);
        child.addContent(idEmployee);
        child.addContent(idBranch);
        child.addContent(nameBranch);
        child.addContent(totalSale);
        child.addContent(description);
        child.addContent(date);
        child.addContent(nameEmployee);
        child.addContent(state);
        child.addContent(city);
        child.addContent(street);
        child.addContent(numberBranchOffice);
        child.addContent(zipCode);
        //se agrega cada sale a list-sales
        root.addContent(child);
        //se agrega todo el xml al archivo doc
        doc.setContent(root);

        try{
            FileWriter writer = new FileWriter("C:/sales.xml");
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(doc,writer);
            // outputter.output(doc,System.out);
            writer.close(); //cierra la conexion con el archivo en caso de requerir modificarlo mientras se ejecuta el programa
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
