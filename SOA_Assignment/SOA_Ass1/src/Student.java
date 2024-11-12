public class Student {

    private int StudentID;
    private String FirstName;
    private String LastName;
    private String Gender;
    private double GPA;
    private int level;
    private String Address;

    public Student(int studentID,String firstName,String lastName,String gender, double Gpa, int Level,String address) {
        StudentID = studentID;
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        GPA=Gpa;
        level = Level;
        Address = address;
    }



    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

}
