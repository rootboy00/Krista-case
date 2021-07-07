import model.Catalog;
import model.Plant;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static Connection con = null;
    static Statement statement = null;

    public static void main(String[] args) throws SQLException, ParseException {
        SQL_Connect();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the path where the XML documents are located");
        String fileName = in.next();
        if (fileName.equals("1")) fileName = "plants__000.xml";
        System.out.println(fileName);
        XMLParse(fileName);
    }

    public static void SQL_Connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_test", "root", "vertrigo");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            statement = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void XMLParse(String fileName) throws SQLException, ParseException {
        Catalog catalog = new Catalog();
        Document doc;
        try {
            doc = buildDocument(fileName);
        } catch (Exception e) {
            System.out.println("Open parsing Error " + e);
            return;
        }
        Node rootNode = doc.getFirstChild();
        NodeList rootChilds = rootNode.getChildNodes();
        Node plantnode = null;
        List<Plant> plantlist = new ArrayList<>();
        NodeList ChildNode = null;
        for (int i = 0; i < rootChilds.getLength(); i++) {
            if (rootChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            plantnode = rootChilds.item(i);
            String common = "";
            String botanical = "";
            Integer zone = 0;
            String light = "";
            float price = 0;
            String availability = "";
            ChildNode = plantnode.getChildNodes();
            for (int j = 0; j < ChildNode.getLength(); j++) {
                if (ChildNode.item(j).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                switch (ChildNode.item(j).getNodeName()) {
                    case "COMMON": {
                        common = ChildNode.item(j).getTextContent();
                        break;
                    }
                    case "BOTANICAL": {
                        botanical = ChildNode.item(j).getTextContent();
                        break;
                    }
                    case "ZONE": {
                        Integer tmp_zone;
                        try {
                            switch (ChildNode.item(j).getTextContent()) {
                                case "0":
                                case "1":
                                case "2":
                                case "3":
                                case "4":
                                case "5":
                                case "6":
                                case "7":
                                case "8":
                                case "9":
                                case "10":
                                case "11":
                                case "12":
                                case "Годичный":
                                    tmp_zone = Integer.valueOf(ChildNode.item(j).getTextContent());
                                    break;
                                default:
                                    tmp_zone = 0;
                                    break;
                            }

                            zone = tmp_zone;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case "LIGHT": {
                        light = ChildNode.item(j).getTextContent();
                        break;
                    }
                    case "PRICE": {
                        Float str_price = Float.valueOf(ChildNode.item(j).getTextContent().substring(1));
                        price = str_price;
                        break;
                    }
                    case "AVAILABILITY": {
                        availability = ChildNode.item(j).getTextContent();
                        break;
                    }
                }
            }
            Plant plant = new Plant(common, botanical, zone, light, price, availability);
            plantlist.add(plant);
        }
        catalog.setPlant(plantlist);
        catalog.setUuid(rootNode.getAttributes().getNamedItem("uuid").getNodeValue());
        catalog.setDate(rootNode.getAttributes().getNamedItem("date").getNodeValue());
        catalog.setCompany(rootNode.getAttributes().getNamedItem("company").getNodeValue());
        catalog.setPlant(plantlist);
        System.out.println("Root " + catalog);
        ToSql(catalog);
    }

    private static void ToSql(Catalog catalog) throws SQLException {
        String UUID = catalog.getUuid();
        Integer ID = 0;
        while (ID == 0) {
            ResultSet resultSet = statement.executeQuery("select id from d_cat_catalog where UUID = '" + UUID + "'");
            if (resultSet.next()) {
                ID = resultSet.getInt(1);
            } else {
                ID = 0;
            }
            if (ID == 0) {
                statement.executeUpdate("INSERT INTO d_cat_catalog(UUID,COMPANY,DELIVERY_DATE) VALUES ( '" + catalog.getUuid() + "','" + catalog.getCompany() + "','" + catalog.getDate() + "' )");
            }
        }

        for (int i = 0; i < catalog.getPlant().size(); i++) {
            statement.executeUpdate("INSERT INTO f_cat_plantsf_cat_plants(CATALOG_ID,COMMON,BOTANICAL,ZONE,LIGHT,PRICE,AVAILABILITY)" +
                    "VALUES ('" + ID + "'," +
                    "'" + catalog.getPlant().get(i).getCommon() + "'," +
                    "'" + catalog.getPlant().get(i).getBotanical() + "'," +
                    "'" + catalog.getPlant().get(i).getZone().toString() + "'," +
                    "'" + catalog.getPlant().get(i).getLight() + "'," +
                    "'" + catalog.getPlant().get(i).getPrice() + "', " +
                    "'" + catalog.getPlant().get(i).getAvailability() + "')");
        }


    }

    private static Document buildDocument(String fileName) throws Exception {
        File file = new File(fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        return dbf.newDocumentBuilder().parse(file);
    }
}
