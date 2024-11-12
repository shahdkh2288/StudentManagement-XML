import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
import java.util.Scanner;

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


                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                DOMSource source = new DOMSource(doc);
                StreamResult streamResult = new StreamResult(file);
                transformer.transform(source, streamResult);

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


                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                DOMSource source = new DOMSource(doc);
                StreamResult streamResult = new StreamResult(file);
                transformer.transform(source, streamResult);

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
        System.out.print("Enter the number of students you want to store data about: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter details for student #" + (i + 1) + ":");

            System.out.print("ID: ");
            int ID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Gender: ");
            String gender = scanner.nextLine();

            System.out.print("GPA: ");
            double gpa = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Level: ");
            int level = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Address: ");
            String address = scanner.nextLine();


            Student student = new Student(ID, firstName, lastName, gender, gpa, level, address);
            students.add(student);
        }
    }
}

