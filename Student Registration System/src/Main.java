import java.util.ArrayList;
import java.util.Scanner;
class Student{
    private String name;
    private String id ;
    private double gpa;
    private int MaxHours;
    private int registeredHours;
    private ArrayList<String> courses= new ArrayList<>();
    public Student(String name , String id , double gpa){
        if (gpa <= 0 || gpa > 4) {throw new IllegalArgumentException("GPA must be between 0 and 4");
        }
        this.name=name;
        this.id=id;
        this.gpa=gpa;
        setMaxHours();
    }
    private void setMaxHours(){
        if( gpa<= 4 && gpa >= 3){
            MaxHours=21;
        } else if (gpa <= 3 && gpa >= 2) {
            MaxHours=18;

        } else if ( gpa<= 2 && gpa >= 1){
            MaxHours=15;

        } else if (gpa <= 1 && gpa > 0) {
            MaxHours=12;
        }


    }

    public void registeredHours(String courseName){
        if (registeredHours+3 <= MaxHours){
            if(courses.contains(courseName)){
                System.out.println("the course has been registered : "+courseName);
                return;
            }
            courses.add(courseName);
            registeredHours+=3;
            System.out.println("Registered course Successful : "+courseName+"\n");

        }else {
            System.out.println("Cannot register, you registered max credit hours.");
        }
    }
    public int getRemainingHours(){
        return MaxHours-registeredHours;
    }

    public void StudentInfo(){
        System.out.println("Name: "+name);
        System.out.println("ID: "+id);
        System.out.println("GPA: "+gpa);
        System.out.println("Max Hours: "+MaxHours);
        System.out.println("Registered Hours: "+registeredHours);
        System.out.println("Registered Courses: "+courses);

    }

}


public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Student Name : ");
        String name = input.nextLine();
        System.out.println("Enter Student ID : ");
        String id = input.nextLine();
        System.out.println("Enter GPA : ");
        double gpa = input.nextDouble();

        Student student=new Student(name,id,gpa);
        while (student.getRemainingHours() >=3 ){
            System.out.println("Avilable hours "+student.getRemainingHours()+" hours");
            System.out.println("Enter course name : ");
            String course =input.nextLine();
            if (course.equalsIgnoreCase("Exit")){
                break;
            }
            student.registeredHours(course);
        }

        student.StudentInfo();
        input.close();

    }
}
