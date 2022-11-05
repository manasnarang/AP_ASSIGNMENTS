package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class FutureBuilder {
    private ArrayList<Student> added_Students;
    private ArrayList<Company> added_Companies;
    private Scanner sc;
    private PlacementCell placementCell;

    public FutureBuilder(){
        this.added_Students=new ArrayList<>();
        this.added_Companies=new ArrayList<>();
        this.placementCell=new PlacementCell();
        this.sc=new Scanner(System.in);
    }
    public void startApp(){
        int main_menu_input;
        int app_menu_input;
        int  int_input;
        int student_input;
        int company_input;
        int placement_cell_input;
        float float_input;
        String str_input;
        String[] arr;
        while (true){
            this.print_main_menu();
            main_menu_input=this.sc.nextInt();
            while (main_menu_input==1){
                this.print_app_menu();
                app_menu_input=this.sc.nextInt();
                if (app_menu_input==1) {
                    while (true) {
                        this.print_student_menu();
                        int_input = Integer.parseInt(this.sc.next());
                        if (int_input == 1) {
                            str_input = this.sc.nextLine();
                            arr = str_input.split(" ");
                            int a = arr.length;
                            int roll_no = Integer.parseInt(arr[a - 1]);
                            Student st=null;
                            int flag = 0;
                            for (Student s : this.added_Students) {
                                if (s.getRoll_no() == roll_no) {
                                    st = s;
                                    flag = 1;
                                    break;
                                }
                            }
                            if (flag == 0) {
                                System.out.println("Student not found! Please enter correct details!");
                                break;
                            } else {
                                while (true) {
                                    flag = 0;
                                    this.print_student_login(arr[0]);
                                    student_input = this.sc.nextInt();
                                    if (student_input == 1) {
                                        st.RegisterForPlacementDrive();
                                    } else if (student_input == 2) {
                                        str_input = sc.nextLine();
                                        Company cm;
                                        for (Company c : placementCell.get_registered_companies()) {
                                            if (c.getName().equals(str_input)) {
                                                cm = c;
                                                if (cm.getPlacement_package() >= (3 * st.getPlacement_package())) {
                                                    cm.register_student(st);
                                                } else {
                                                    System.out.println("Cannot register since offered ctc is less than 3*current ctc");
                                                }
                                                flag = 1;
                                                break;
                                            }
                                        }
                                        if (flag == 0) {
                                            System.out.println("Cannot register! Company " + str_input + " is not registered with placement cell!");
                                        }
                                    } else if (student_input == 3) {
                                        System.out.println("The available companies are:");
                                        this.get_added_companies();
                                    } else if (student_input == 4) {
                                        System.out.println("Current status is: " + st.getStatus());
                                    } else if (student_input == 5) {
                                        System.out.println("Enter new cgpa:");
                                        float_input = sc.nextFloat();
                                        st.updateCGPA(st.getCGPA(), float_input);
                                    } else if (student_input == 6) {
                                        //accept offer
                                        st.setStatus("Placed");
                                        st.setPlacement_package(st.getOffered_company().getPlacement_package());
                                        System.out.println("Congratulations " + st.getName() + " You have accepted the offer by GooFlix!!");
                                    } else if (student_input == 7) {
                                        //reject offer
                                        st.setStatus("Blocked");

                                    } else if (student_input == 8) {
                                        break;
                                    }
                                }
                            }
                        } else if (int_input == 2) {
                            System.out.println("Number of students to add");
                            int_input = this.sc.nextInt();
                            this.add_students(int_input);
                        } else if (int_input == 3) {
                            break;
                        } else {
                            System.out.println("Invalid input! Enter again");
                        }
                    }
                }
                else if(app_menu_input==2) {
                    while (true) {
                        this.print_company_menu();
                        int_input = sc.nextInt();
                        if (int_input == 1) {
                            this.add_company();
                        } else if (int_input == 2) {
                            System.out.println("Choose To enter into mode of Available Companies:-");
                            this.get_added_companies();
                            company_input = sc.nextInt();
                            Company comp = this.added_Companies.get(company_input - 1);
                            while (true){
                            this.print_company_login(comp);
                            company_input = sc.nextInt();
                            System.out.print("\n");
                            if (company_input == 1) {
                                str_input = sc.nextLine();
                                comp.setRole(str_input);
                            } else if (company_input == 2) {
                                float_input = sc.nextFloat();
                                comp.setPlacement_package(float_input);
                            } else if (company_input == 3) {
                                float_input = sc.nextFloat();
                                comp.setCgpa_criteria(float_input);
                            } else if (company_input == 4) {
                                comp.RegisterToInstituteDrive();
                            } else if (company_input == 5) {
                                break;
                            } else {
                                System.out.println("Invalid input! Enter again");
                            }}
                        } else if (int_input == 3) {
                            System.out.println("The Available Companies are:");
                            this.get_added_companies();
                        } else if (int_input == 4) {
                            break;
                        } else {
                            System.out.println("Invalid input! Enter again");
                        }
                    }
                }
                else if(app_menu_input==3){
                    while (true){
                    this.print_placement_cell_menu();
                    int_input= sc.nextInt();
                    if(int_input==1){
                        System.out.println("Fill in the details:-\n" +
                                "1) Set the Opening time for Student registrations\n" +
                                "2) Set the Ending time for Student registrations. \n"+
                                "Please follow the format dd/mm/yyyy HH:mm:ss");
                        placementCell.open_student_registrations();
                    }
                    else if(int_input==2){
                        System.out.println("Fill in the details:-\n" +
                                "1) Set the Opening time for Company registrations \n" +
                                "2) Set the Ending time for Company registrations. \n" +
                                "Please follow the format dd/mm/yyyy HH:mm:ss");
                        placementCell.open_company_registrations();
                    }
                    else if(int_input==3){
                        System.out.println(this.placementCell.get_number_of_students());
                    }
                    else if(int_input==4){
                        System.out.println(this.placementCell.get_number_of_companies());
                    }
                    else if(int_input==5){
                        this.placementCell.get_number_of_offered_unoffered_blocked();
                    }
                    else if(int_input==6){
                        int fl=0;
                        System.out.println("Enter name of the student:");
                        str_input= sc.nextLine();
                        for(Student stu:this.placementCell.get_registered_students()){
                            if(stu.getName().equals(str_input)){
                                stu.getStudentDetails();
                                fl=1;
                                break;
                            }
                        }
                        if(fl==0){
                            System.out.println("Student not registered with placement cell!");
                        }
                    }
                    else if(int_input==7){
                        int fl=0;
                        System.out.println("Enter name of the company:");
                        str_input= sc.nextLine();
                        for(Company cp:this.placementCell.get_registered_companies()){
                            if(cp.getName().equals(str_input)){
                                cp.getDetails();
                                fl=1;
                                break;
                            }
                        }
                        if(fl==0){
                            System.out.println("Company not registered with placement cell!");
                        }
                    }
                    else if(int_input==8){
                        float sum=0;
                        int num=0;
                        for(Student s:this.placementCell.get_registered_students()){
                            if(s.getStatus().equals("Placed")){
                                sum+=s.getPlacement_package();
                                num++;
                            }
                        }
                        float average=sum/num;
                        System.out.println("Average package is: "+average+" LPA");
                    }
                    else if(int_input==9){
                        int fl=0;
                        str_input=sc.nextLine();
                        for(Company cp:this.placementCell.get_registered_companies()){
                            if(cp.getName().equals(str_input)){
                                cp.get_selected_students();
                                fl=1;
                                break;
                            }
                        }
                        if(fl==0){
                            System.out.println("Company not registered with placement cell!");
                        }
                    }
                    else if(int_input==10){
                        break;
                    }
                    else{
                        System.out.println("Invalid input! Enter again!");
                    }
                }
                }
                else if(app_menu_input==4){
                    break;
                }
                else{
                    System.out.println("Invalid input! Enter again");
                }
            }
            if (main_menu_input==2){
                System.out.println("Thanks For Using FutureBuilder!!!!!!");
                break;
            }
            else{
                System.out.println("Invalid input! Enter again");
            }

        }
    }
    public void print_main_menu(){
        System.out.println("Welcome to FutureBuilder:\n" +
                "1) Enter the Application\n" +
                "2) Exit the Application");
    }
    public void print_app_menu(){
        System.out.println("Choose The mode you want to Enter in:-\n" +
                "1) Enter as Student Mode\n" +
                "2) Enter as Company Mode\n" +
                "3) Enter as Placement Cell Mode\n" +
                "4) Return To Main Application");
    }
    public void print_student_menu(){
        System.out.println("Choose the Student Query to perform-\n" +
                "1) Enter as a Student(Give Student Name, and Roll No.)\n" +
                "2) Add students\n" +
                "3) Back");
    }
    public void print_student_login(String name){
        System.out.println("Welcome "+name+"\n" +
                "1) Register For Placement Drive\n" +
                "2) Register For Company\n" +
                "3) Get All available companies\n" +
                "4) Get Current Status\n" +
                "5) Update CGPA\n" +
                "6) Accept offer\n" +
                "7) Reject offer\n" +
                "8) Back");
    }
    public void add_students(int num_of_students){
        System.out.println("Please add students Name, Roll No, CGPA, Branch(in order):\n");
        Scanner s=new Scanner(System.in);
        String name;
        int roll_no;
        float cgpa;
        String branch;
        for(int i=0;i<num_of_students;i++){
            name=s.nextLine();
            roll_no=s.nextInt();
            System.out.print("\n");
            cgpa=s.nextFloat();
            System.out.print("\n");
            branch=s.nextLine();
            System.out.print("\n");
            Student student=new Student(name,roll_no,cgpa,branch);
            this.added_Students.add(student);
        }
    }
    public void print_company_menu(){
        System.out.println("Choose the Company Query to perform-\n" +
                "1) Add Company and Details\n" +
                "2) Choose Company\n" +
                "3) Get Available Companies\n" +
                "4) Back");
    }

    public void add_company(){
        System.out.print("\n");
        Scanner s=new Scanner(System.in);
        String name;
        String role;
        float cgpa_criteria;
        float ctc;
        name=s.nextLine();
        role=s.nextLine();
        ctc=s.nextFloat();
        cgpa_criteria=s.nextFloat();
        Company comp=new Company(name,role,cgpa_criteria,ctc,this.placementCell);
        this.added_Companies.add(comp);
    }
    public void get_added_companies(){
        for(int i=0;i<this.added_Companies.size();i++){
            System.out.println((i+1)+") "+this.added_Companies.get(i).getName());
        }
    }
    public void print_company_login(Company c){
        System.out.println("Welcome "+c.getName()+"\n" +
                "1) Update Role\n" +
                "2) Update Package\n" +
                "3) Update CGPA criteria\n" +
                "4) Register To Institute Drive\n" +
                "5) Back");
    }
    public void print_placement_cell_menu(){
        System.out.println("Welcome IIITD Placement Cell\n" +
                "1) Open Student Registrations\n" +
                "2) Open Company Registrations\n" +
                "3) Get Number of Student Registrations\n" +
                "4) Get Number of Company Registrations\n" +
                "5) Get Number of Offered/Unoffered/Blocked Students\n" +
                "6) Get Student Details\n" +
                "7) Get Company Details\n" +
                "8) Get Average Package\n" +
                "9) Get Company Process Results\n" +
                "10) Back");
    }
}


class PlacementCell{
    private ArrayList<Student> students;
    private ArrayList<Company> companies;
    private Date student_start;
    private Date student_end;
    private Date company_start;
    private Date company_end;
    public PlacementCell(){
        this.students=new ArrayList<>();
        this.companies=new ArrayList<>();
    }
    public void addCompany(Company company){
        this.companies.add(company);
    }
    public void addStudent(Student student){
        this.students.add(student);
    }

    public void setCGPA(Student student, float old_cgpa,float new_cgpa){
        student.CGPA=new_cgpa;
    }

    public void open_student_registrations(){
        LocalDate start;
        LocalDate end;
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        SimpleDateFormat format_1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.ENGLISH);
        try {
            this.student_start = format_1.parse(s);
            s = sc.nextLine();
            this.student_end = format_1.parse(s);
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    public void open_company_registrations(){
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        SimpleDateFormat format_1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try{
        this.company_start= format_1.parse(s);
        s=sc.nextLine();
        this.company_end=format_1.parse(s);
    }catch (ParseException e){
            e.printStackTrace();
        }
    }
    public void getAvailableCompanies(){
        System.out.println("List of all available companies is as follows: ");
        for(int i=0;i<this.companies.size();i++){
            System.out.println((i+1)+") CompanyName: "+this.companies.get(i).getName());
            System.out.println("   Company role offering: "+this.companies.get(i).getRole());
            System.out.println("   Company Package: "+this.companies.get(i).getPlacement_package()+" LPA");
            System.out.println("   Company CGPA criteria: "+this.companies.get(i).getCgpa_criteria());
        }
    }
    public int get_number_of_students(){
        return students.size();
    }
    public int get_number_of_companies(){
        return companies.size();
    }

    public void get_number_of_offered_unoffered_blocked(){
        int offered=0, unoffered=0, blocked=0;
        for(Student s:students){
            if(s.getStatus().equals("Placed") || s.getStatus().contains("offered")){
                offered++;
            }
            else if(s.getStatus().equals("Unplaced")){
                unoffered++;
            }
            else if(s.getStatus().equals("Blocked")){
                blocked++;
            }
        }
        System.out.println("Number of placed students = "+offered);
        System.out.println("Number of unplaced students = "+unoffered);
        System.out.println("Number of blocked students = "+blocked);
    }

    public ArrayList<Student> get_eligible_students(Company company){
        float cgpa=company.getCgpa_criteria();
        ArrayList<Student> eligible_students=new ArrayList<>();
        String status;
        float ctc=company.getPlacement_package();
        for(Student s:students){
            status=s.getStatus();
            if(!(status.equals("Placed") || status.equals("Blocked")) && s.getCGPA()>=cgpa){
                eligible_students.add(s);
            }
            else if(status.equals("Placed") && s.getCGPA()>=cgpa && ctc>=(s.getPlacement_package()*3)){
                eligible_students.add(s);
            }
        }
        return eligible_students;
    }
    public ArrayList<Company> get_registered_companies(){
        return this.companies;
    }

    public ArrayList<Student> get_registered_students() {
        return this.students;
    }

    public Date getCompany_start() {
        return this.company_start;
    }

    public Date getCompany_end() {
        return this.company_end;
    }

    public Date getStudent_start() {
        return this.student_start;
    }

    public Date getStudent_end() {
        return this.student_end;
    }
}



class Company{
    private ArrayList<Student> registered_students;
    private PlacementCell placementCell;
    private String name;
    private String role;
    private float cgpa_criteria;
    private float placement_package;
    private Date date_time;
    private ArrayList<Student> selected_students;

    public Company(String name, String role, float cgpa_criteria, float placement_package, PlacementCell placementCell){
        this.name=name;
        this.role=role;
        this.cgpa_criteria=cgpa_criteria;
        this.placement_package=placement_package;
        this.placementCell=placementCell;
        this.registered_students=new ArrayList<>();
        this.selected_students=new ArrayList<>();
    }

    public void getDetails(){
        System.out.println("Name: "+this.getName()+"\n"+
            "Role: "+this.getRole()+"\n"+
            "CGPA criteria: "+this.getCgpa_criteria()+"\n" +
                "CTC : "+this.getPlacement_package()+" LPA\n");
    }
    public float getCgpa_criteria() {
        return this.cgpa_criteria;
    }

    public String getRole() {
        return this.role;
    }

    public String getName() {
        return this.name;
    }

    public void setCgpa_criteria(float cgpa_criteria) {
        this.cgpa_criteria = cgpa_criteria;
    }

    public void setPlacement_package(float placement_package) {
        this.placement_package = placement_package;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public float getPlacement_package() {
        return placement_package;
    }
    public void RegisterToInstituteDrive(){
        this.date_time=new Date();
        if (this.date_time.after(placementCell.getCompany_start()) && this.date_time.before(placementCell.getCompany_end())){
            this.placementCell.addCompany(this);
            System.out.println(this.date_time);
            System.out.println("Registered!!!");
        }
        else{
            System.out.println("Cannot register for Institute Drive as registrations are "+(this.date_time.after(placementCell.getCompany_end())?"closed":"not yet open"));
        }
    }
    public void register_student(Student s){
        if(s.getCGPA()>=(this.getCgpa_criteria())){
            this.registered_students.add(s);
            System.out.println("Successfully registered for company "+this.getName());
        }
        else{
            System.out.println("Cannot register to company "+this.getName()+". CGPA criteria not met");
        }
    }
    public void select_students(){
        Random r=new Random();
        int select_ind;
        int vacancies=r.nextInt(this.registered_students.size()) + 1;
        for(int i=1;i<=vacancies;i++){
            while (true){
            select_ind=r.nextInt(this.registered_students.size());
            if(this.registered_students.get(select_ind).getOffered_company().getPlacement_package()<this.getPlacement_package()){
                this.selected_students.add(this.registered_students.get(select_ind));
                this.registered_students.get(select_ind).setStatus("You have been offered by "+this.getName()+ "Please accept the offer.");
                this.registered_students.get(select_ind).setOffered_company(this);
                break;
            }
            }
        }
    }

    public void get_selected_students(){
        this.select_students();         //Selection process will run when selected students'details are asked for
        for(int i=0;i<this.selected_students.size();i++){
            System.out.println((i+1)+") "+this.selected_students.get(i));
        }
        }


}


class Student{
    private Company offered_company;
    private PlacementCell placementCell;
    private String name;
    private int roll_no;
    protected float CGPA;
    private String branch;
    private String status;
    private float placement_package;

    public Student(String name, int roll_no,float CGPA, String branch){
        this.name=name;
        this.roll_no=roll_no;
        this.CGPA=CGPA;
        this.branch=branch;
        this.status="not-applied";
    }

    public Company getOffered_company() {
        return this.offered_company;
    }

    public void setOffered_company(Company offered_company) {
        this.offered_company = offered_company;
    }

    public String getName() {
        return this.name;
    }

    public String getBranch() {
        return this.branch;
    }

    public String getStatus() {
        return this.status;
    }

    public int getRoll_no() {
        return this.roll_no;
    }

    public float getCGPA() {
        return this.CGPA;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public void updateCGPA(float oldCGPA,float newCGPA){
        placementCell.setCGPA(this,oldCGPA,newCGPA);
    }

    public float getPlacement_package() {
        return placement_package;
    }

    public void getStudentDetails(){
        System.out.println("Your details are: ");
        System.out.println("Name: "+this.getName());
        System.out.println("RollNo: "+this.getRoll_no());
        System.out.println("CGPA: "+this.getCGPA());
        System.out.println("Branch: "+this.getBranch());
    }

    @Override
    public String toString() {
        return ("Name: "+this.getName()+"\n"+
                "RollNo: "+this.getRoll_no()+"\n"+
                "CGPA: "+this.getCGPA()+"\n"+
                "Branch: "+this.getBranch());
    }

    public void setPlacement_package(float placement_package) {
        this.placement_package = placement_package;
    }

    public void RegisterForPlacementDrive(){
        Date d=new Date();
        if(d.after(placementCell.getStudent_start()) && d.before(placementCell.getStudent_end())){
            placementCell.addStudent(this);
            this.setStatus("Applied");
            System.out.println(d);
            System.out.println("\n"+this.name+" Registered for the Placement Drive at IIITD!!!!");
            this.getStudentDetails();
        }
        else{
            System.out.println("Cannot register for placement drive! Registrations are "+(d.before(placementCell.getStudent_start())?"not open yet":"closed"));
        }


    }
}