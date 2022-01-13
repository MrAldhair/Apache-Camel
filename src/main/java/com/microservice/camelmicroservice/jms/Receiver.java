package com.microservice.camelmicroservice.jms;

import ConnectionDB.ConnDBH2;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.underscore.lodash.U;
import com.microservice.camelmicroservice.model.SaveSale;
//import org.jdom2.Attribute;
//import org.w3c.dom.Document;
import com.microservice.camelmicroservice.xml.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


@Service
public class Receiver {
    
    // Generar la conexion
    private static final ConnDBH2 sql = new ConnDBH2();
    private static final Connection conn = sql.connectionDbH2(); // Query
    private static String querySqlInsert = "";   
    
    Logger log = LoggerFactory.getLogger(Receiver.class);
    @JmsListener(destination="${jms.queue}")
    public void jsonMsg(String msg){

        ObjectMapper mapper = new ObjectMapper();
        //Conversion de JSON a XML
        try {
            log.info("venta recibida: "+ msg);
            String xml = U.jsonToXml(msg);
            SaveSale sale = mapper.readValue(msg, SaveSale.class);
            //System.out.println("Sales ="+sale.toString());
            //metodo para crear archivo xml en c:/, debe ejecutarse como administrador el IDE
            Writer.writerXML(sale);
            
            //Mapping XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(new InputSource(new StringReader(xml))); 
            org.w3c.dom.Element formato = document.getDocumentElement();

            String city_branch_office = getValueTag("city_branch_office",formato);
            String date_sale = getValueTag("date_sale",formato);
            String description = getValueTag("description",formato);
            String folio = getValueTag("folio",formato);
            String id_branch_office = getValueTag("id_branch_office",formato);
            String id_employee = getValueTag("id_employee",formato);
            String name_branch_office = getValueTag("name_branch_office",formato);
            String number_branch_office = getValueTag("number_branch_office",formato);
            String name_employee = getValueTag("name_employee",formato);
            String state_branch_office = getValueTag("state_branch_office",formato);
            String street_branch_office = getValueTag("street_branch_office",formato);
            String total_sale = getValueTag("total_sale",formato);
            String zip_code_branch_office = getValueTag("zip_code_branch_office",formato);
            
            //String mensaje = getValueTag("name_employee",formato);


            querySqlInsert = "INSERT INTO SAVE_SALE(city_branch_office, date_sale, description, folio, id_branch_office, id_employee, name_branch_office, name_employee, number_branch_office, state_branch_office, street_branch_office, total_sale, zip_code_branch_office) "
                           + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //PreparedStatment
            //bucar para que sirve esto
            PreparedStatement preparedStatement = conn.prepareStatement(querySqlInsert);
            preparedStatement.setString(1, city_branch_office);
            preparedStatement.setString(2, date_sale);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, folio);
            preparedStatement.setInt(5, Integer.parseInt(id_branch_office));
            preparedStatement.setInt(6, Integer.parseInt(id_employee));
            preparedStatement.setString(7, name_branch_office);
            preparedStatement.setString(8, name_employee);
            preparedStatement.setInt(9, Integer.parseInt(number_branch_office));
            preparedStatement.setString(10, state_branch_office);
            preparedStatement.setString(11, street_branch_office);
            preparedStatement.setDouble(12, Double.parseDouble(total_sale));
            preparedStatement.setInt(13, Integer.parseInt(zip_code_branch_office));
            
            preparedStatement.execute();
            
            
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }



    
   private String getValueTag(String tagName, org.w3c.dom.Element element) { 
        String res = ""; 
        NodeList list = element.getElementsByTagName(tagName); 
          if (list != null && list.getLength() > 0) {
          NodeList subList = list.item(0).getChildNodes(); 
              if (subList != null && subList.getLength() > 0) { 
              res = subList.item(0).getNodeValue(); 
              } 
          } 
          if(res.equals("")){ 
             res = "Etiqueta no encontrada"; 
             } 
          return res; 
      }
    
}
