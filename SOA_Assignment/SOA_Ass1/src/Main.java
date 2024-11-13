import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        University university = new University();
        Scanner in = new Scanner(System.in);

        System.out.println("1-Add new student(s) \n2-Search for Student \n3-Delete Student");
        int choice = in.nextInt();
        in.nextLine();

        switch (choice) {
            case 1:
                university.getStudentDataFromUser();
                university.createXMLDocument();
                break;

            case 2:
                System.out.println("1-Search by GPA \n2-Search by First Name");
                choice = in.nextInt();
                in.nextLine();

                if (choice == 1) {
                    System.out.println("Enter Student GPA");
                    String GPA = in.nextLine();
                    university.searchInXML("GPA", GPA);
                } else if (choice == 2) {
                    System.out.println("Enter Student First Name");
                    String firstName = in.nextLine();
                    university.searchInXML("FirstName", firstName);
                }
                break;

            case 3:
                System.out.println("Enter Student ID");
                String id = in.nextLine();
                university.deleteStudent(id);
                break;

            default:
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
        }

        in.close();
    }
}
