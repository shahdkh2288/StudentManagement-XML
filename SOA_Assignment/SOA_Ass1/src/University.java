import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class University {
    private ArrayList<Student> students = new ArrayList<>();

    public void createXMLDocument() {
        try {
            File file = new File("students.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc;

            if (file.exists()) {

                doc = docBuilder.parse(file);
                doc.getDocumentElement().normalize();
                System.out.println("XML file already exists. Parsed the existing data.");


                Element rootElement = doc.getDocumentElement();
                removeWhitespaceNodes(doc);



                for (Student student : students) {

                    Element studentElement = doc.createElement("Student");
                    studentElement.setAttribute("ID", String.valueOf(student.getStudentID()));
                    rootElement.appendChild(studentElement);


                    Element firstName = doc.createElement("FirstName");
                    firstName.appendChild(doc.createTextNode(student.getFirstName()));
                    studentElement.appendChild(firstName);

                    Element lastName = doc.createElement("LastName");
                    lastName.appendChild(doc.createTextNode(student.getLastName()));
                    studentElement.appendChild(lastName);

                    Element gender = doc.createElement("Gender");
                    gender.appendChild(doc.createTextNode(student.getGender()));
                    studentElement.appendChild(gender);

                    Element gpa = doc.createElement("GPA");
                    gpa.appendChild(doc.createTextNode(String.valueOf(student.getGPA())));
                    studentElement.appendChild(gpa);

                    Element level = doc.createElement("Level");
                    level.appendChild(doc.createTextNode(String.valueOf(student.getLevel())));
                    studentElement.appendChild(level);

                    Element address = doc.createElement("Address");
                    address.appendChild(doc.createTextNode(student.getAddress()));
                    studentElement.appendChild(address);
                }


                saveXMLDocument(doc, file);

                System.out.println("New students added to the existing XML file.");

            } else {

                doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("University");
                doc.appendChild(rootElement);


                for (Student student : students) {
                    Element studentElement = doc.createElement("Student");
                    studentElement.setAttribute("ID", String.valueOf(student.getStudentID()));
                    rootElement.appendChild(studentElement);

                    Element firstName = doc.createElement("FirstName");
                    firstName.appendChild(doc.createTextNode(student.getFirstName()));
                    studentElement.appendChild(firstName);

                    Element lastName = doc.createElement("LastName");
                    lastName.appendChild(doc.createTextNode(student.getLastName()));
                    studentElement.appendChild(lastName);

                    Element gender = doc.createElement("Gender");
                    gender.appendChild(doc.createTextNode(student.getGender()));
                    studentElement.appendChild(gender);

                    Element gpa = doc.createElement("GPA");
                    gpa.appendChild(doc.createTextNode(String.valueOf(student.getGPA())));
                    studentElement.appendChild(gpa);

                    Element level = doc.createElement("Level");
                    level.appendChild(doc.createTextNode(String.valueOf(student.getLevel())));
                    studentElement.appendChild(level);

                    Element address = doc.createElement("Address");
                    address.appendChild(doc.createTextNode(student.getAddress()));
                    studentElement.appendChild(address);
                }


                saveXMLDocument(doc, file);

                System.out.println("XML file created successfully and students added.");
            }
        } catch (ParserConfigurationException e) {
            System.err.println("Parser configuration error: " + e.getMessage());
        } catch (SAXException | IOException e) {
            System.err.println("Error reading XML file: " + e.getMessage());
        } catch (TransformerException e) {
            System.err.println("Error transforming XML document: " + e.getMessage());
        }
    }

    public void getStudentDataFromUser() {
        Scanner scanner = new Scanner(System.in);
        File file = new File("students.xml");
        Document doc = null;
        Set<Integer> currentRunIds = new HashSet<>();

        try {
            if (file.exists()) {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                doc = docBuilder.parse(file);
                doc.getDocumentElement().normalize();
            }
        } catch (Exception e) {
            System.err.println("Error loading XML file: " + e.getMessage());
        }

        System.out.print("Enter the number of students you want to store data about: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter details for student #" + (i + 1) + ":");

            int ID;
            while (true) {
                System.out.print("ID: ");
                ID = scanner.nextInt();
                scanner.nextLine();

                if ((doc == null || !isStudentExists(String.valueOf(ID), doc) && (!currentRunIds.contains(ID)) )) {
                    currentRunIds.add(ID);
                    break;
                } else {
                    System.out.println("This ID already exists in the XML file or in memory. Please enter a unique ID.");
                }
            }

            System.out.print("First Name: ");
            String firstName = scanner.nextLine();
            while (firstName.isEmpty()) {
                System.out.print("First Name cannot be empty. Enter First Name: ");
                firstName = scanner.nextLine();
            }

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();
            while (lastName.isEmpty()) {
                System.out.print("Last Name cannot be empty. Enter Last Name: ");
                lastName = scanner.nextLine();
            }

            System.out.print("Gender (Male/Female): ");
            String gender = scanner.nextLine();
            while (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")) {
                System.out.print("Invalid gender. Enter Male or Female: ");
                gender = scanner.nextLine();
            }

            System.out.print("GPA: ");
            double gpa;
            while (true) {
                try {
                    gpa = Double.parseDouble(scanner.nextLine());
                    if (gpa >= 0.0 && gpa <= 4.0) break;
                    else System.out.print("GPA must be between 0.0 and 4.0. Enter GPA: ");
                } catch (NumberFormatException e) {
                    System.out.print("Invalid GPA. Enter a number between 0.0 and 4.0: ");
                }
            }

            System.out.print("Level (1-4): ");
            int level;
            while (true) {
                try {
                    level = Integer.parseInt(scanner.nextLine());
                    if (level >= 1 && level <= 4) break;
                    else System.out.print("Level must be between 1 and 4. Enter Level: ");
                } catch (NumberFormatException e) {
                    System.out.print("Invalid level. Enter an integer between 1 and 4: ");
                }
            }

            System.out.print("Address: ");
            String address = scanner.nextLine();
            while (address.isEmpty()) {
                System.out.print("Address cannot be empty. Enter Address: ");
                address = scanner.nextLine();
            }

            Student student = new Student(ID, firstName, lastName, gender, gpa, level, address);
            students.add(student);
        }
    }


    //---------------------------------------------------------------------------------------------

    public void searchInXML(String criteria, String value) {
        try {
            File file = new File("students.xml");
            if (!file.exists()) {
                System.out.println("No XML file found to search.");
                return;
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList studentNodes = doc.getElementsByTagName("Student");
            boolean found = false;

            for (int i = 0; i < studentNodes.getLength(); i++) {
                Element student = (Element) studentNodes.item(i);
                String matchValue;

                if ("GPA".equalsIgnoreCase(criteria)) {
                    matchValue = student.getElementsByTagName("GPA").item(0).getTextContent();
                } else if ("FirstName".equalsIgnoreCase(criteria)) {
                    matchValue = student.getElementsByTagName("FirstName").item(0).getTextContent();
                } else {
                    System.out.println("Invalid search criteria. Use 'GPA' or 'FirstName'.");
                    return;
                }

                if (matchValue.equalsIgnoreCase(value)) {
                    System.out.println("Student Found:");
                    System.out.println("ID: " + student.getAttribute("ID"));
                    System.out.println("First Name: " + student.getElementsByTagName("FirstName").item(0).getTextContent());
                    System.out.println("Last Name: " + student.getElementsByTagName("LastName").item(0).getTextContent());
                    System.out.println("Gender: " + student.getElementsByTagName("Gender").item(0).getTextContent());
                    System.out.println("GPA: " + student.getElementsByTagName("GPA").item(0).getTextContent());
                    System.out.println("Level: " + student.getElementsByTagName("Level").item(0).getTextContent());
                    System.out.println("Address: " + student.getElementsByTagName("Address").item(0).getTextContent() + "\n");
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No student found with " + criteria + ": " + value);
            }

        } catch (Exception e) {
            System.err.println("Error searching XML file: " + e.getMessage());
        }
    }


    public void deleteStudent(String studentID) {
        try {
            File file = new File("students.xml");
            if (!file.exists()) {
                System.out.println("No XML file found to delete a record.");
                return;
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList studentNodes = doc.getElementsByTagName("Student");
            boolean deleted = false;

            for (int i = 0; i < studentNodes.getLength(); i++) {
                Element student = (Element) studentNodes.item(i);

                if (student.getAttribute("ID").equals(studentID)) {
                    student.getParentNode().removeChild(student);
                    deleted = true;
                    break;
                }
            }

            if (deleted) {

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);

                System.out.println("Student record with ID " + studentID + " has been deleted.");
            } else {
                System.out.println("No student found with ID: " + studentID);
            }

        } catch (Exception e) {
            System.err.println("Error deleting from XML file: " + e.getMessage());
        }
    }
    private void saveXMLDocument(Document doc, File file) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(file);
        transformer.transform(source, streamResult);
    }


    private void removeWhitespaceNodes(Node node) {
        NodeList childNodes = node.getChildNodes();
        for (int i = childNodes.getLength() - 1; i >= 0; i--) {
            Node child = childNodes.item(i);
            if (child.getNodeType() == Node.TEXT_NODE && child.getNodeValue().trim().isEmpty()) {
                node.removeChild(child);
            } else if (child.getNodeType() == Node.ELEMENT_NODE) {
                removeWhitespaceNodes(child);
            }
        }
    }

    private static boolean isStudentExists(String id, Document doc){
        NodeList studentNodes = doc.getElementsByTagName("Student");
        for (int i = 0; i < studentNodes.getLength(); i++) {
            Element student = (Element) studentNodes.item(i);
            if (student.getAttribute("ID").equals(id)) {
                return true;
            }
        }
        return false;
    }

}

