package control;

import model.Company;
import model.Customer;
import view.Menu;
import java.util.ArrayList;
import java.util.function.Predicate;

public class StudentsManagement extends Menu<String> {

    private ArrayList<Customer> arrListInput;
    private Company company;
    private CheckValid input;

    public StudentsManagement() {
      
        super(
                "===== Students Management Menu =====",
                new String[]{
                    " Display all Students",
                    " Add new Students",
                    " Search Students",
                    " Delete a Students by ID",
                    " Update Semester",
                    " Update courseName",
                    " Exit"
                }
        );
        input = new CheckValid();
        company = new Company();
        arrListInput = new ArrayList<>();;
    }

    private void display(String message) {
        System.out.println(message);
    }

    @Override
    public void execute() {
        String choice;
        do {
            dispplay();
            Predicate<String> p = s -> s.matches("[12345678]");
            choice = input.checkInputAndLoop(input.getInput("Enter your choice: "), p).trim();
            switch (choice) {
                case "1":
                    displayAllCustomer();
                    break;
                case "2":
                    addNewCus();
                    break;
                case "3":
                    searchCusomer();
                    break;
                case "4":
                    deleteCustomerByID();
                    break;
                case "5":
                    uppdateSemester();
                    break;
                case "6":
                    uppdateCourseName();
                    break;
                case "7":
                    display("Exit....");
                    break;
                default:
                    display("Invalid choice.");
            }
        } while (!choice.equals("7"));
    }

    private void searchCusomer() {
        String[] mSearch = {"Search Students by ID ","Search Students by Name","Search Students by Semester","Search Students by CourseName"};                  
        Menu menu = new Menu("===== Students Menu =====", mSearch) {
    @Override
    public void execute() {
        Customer found;
        String choice;
        Predicate<String> p = s -> s.matches("[1-4]");
        choice = input.checkInputAndLoop(input.getInput("Enter your choice:"), p).trim();
        switch (choice) {
            case "1":
                String idSearch;
                do {
                    idSearch = (input.checkID(input.getInput("Enter ID Customer(KH..): ")));
                    if (idSearch == null) {
                        display("Error ID, ID have to has KH in the head of ID and next only numbers");
                    } else {
                        String idSearchbuffer = idSearch;
                        Customer foundCustomerById = company.searchCustomerReturnObject(s -> s.getStudentsID().equals(idSearchbuffer));
                        if (foundCustomerById == null) {
                            display("Not Students");
                            break;
                        } else {
                            display(foundCustomerById.toString());
                            break;
                        }
                    }
                } while (idSearch == null);

                break;
            case "2":
                String nameSearch;
                do {
                    nameSearch = input.checkName(input.getInput("Enter Name To Seach: "));
                    if (nameSearch == null) {
                        display("Error name, name have to not space, empty character, special characters and numbers");
                    } else {
                        String nameSearchBufer = nameSearch;
                        ArrayList<Customer> listFound = company.searchCustomerReturnArray(s -> s.getName().contains(nameSearchBufer));
                        if (listFound.isEmpty()) {
                            display("Not Found Name");
                            break;
                        } else {
                            for (Customer customer : listFound) {
                                display(customer.toString());
                                break;
                            }
                        }
                    }
                } while (nameSearch == null);
                break;
            case "3":
                String semesterSearch;
                do {
                    semesterSearch = input.checkSemester(input.getInput("Enter Phone To Seach"));
                    if (semesterSearch == null) {
                        display("Error semester, semester have to not space, empty character, special characters and numbers");
                    } else {
                        String semesterSearchBuffer = semesterSearch;
                        Customer foundCustomerBySemester = company.searchCustomerReturnObject(s -> s.getSemester().equals(semesterSearchBuffer));
                        if (foundCustomerBySemester == null) {
                            display("Not Found Semester");
                            break;
                        } else {
                            display(foundCustomerBySemester.toString());
                            break;
                        }
                    }
                } while (semesterSearch == null);
                break;

            case "4":
                String courseNameSearch;
                do {
                    courseNameSearch = input.checkName(input.getInput("Enter courseName: "));
                    if (courseNameSearch == null) {
                        display("Error courseName, courseName have to not space, empty character, special characters and numbers");
                    } else {
                        String courseNameSearchBuffer = courseNameSearch;

                        ArrayList<Customer> foundCustomerByCourseName = company.searchCustomerReturnArray(s -> s.getCourseName().equals(input.checkName(courseNameSearchBuffer)));
                        if (foundCustomerByCourseName.isEmpty()) {
                            display("Not Found CourseName");
                            break;
                        } else {
                            found = foundCustomerByCourseName.get(0);
                            display(found.toString());
                            break;
                        }
                    }
                } while (courseNameSearch == null);
                break;
        }
    }
            
        };
        menu.run();
    }

    private void displayAllCustomer() {
        company.sortArrByID();
        ArrayList<Customer> arrList = company.getArrList();
        if (arrList.isEmpty()) {
            display("No customer!");
        }
        for (Customer cus : arrList) {
            display(cus.toString());
        }
    }

    private void addNewCus() {
    ArrayList<Customer> arrList = company.getArrList();
    String customerId;
    Predicate<Customer> p;
    do {
        customerId = input.getInput("Enter Customer ID (4 digits - KHxx): ");
        final String cusID = customerId;
        p = s -> s.getStudentsID().equals(cusID);
        if (input.checkID(customerId) == null) {
            display("Error ID (4 digits - KHxx)");
        } else if (input.checkSame(arrList, p)) {
            display("The ID existed");
        }
    } while (input.checkID(customerId) == null || input.checkSame(arrList, p));
    
    String name;
    do {
        name = input.getInput("Enter Name:");
        if (input.checkName(name) == null) {
            display("Error name, name have to not space, empty character, special characters and numbers");
        }
    } while (input.checkName(name) == null);
    
    String semester;
    do {
        semester = input.getInput("Enter Semester: ");
        final String semesterbufffer = semester;
        if (input.checkSemester(semester) == null) {
            display("Error semester, semester have to not space, empty character, special characters and numbers");
        }
        p = s -> s.getSemester().equals(semesterbufffer);
        if (input.checkSame(arrList, p)) {
            display("The Semester existed");
        }
    } while (input.checkSemester(semester) == null || input.checkSame(arrList, p));
    
    String courseName;
    do {
        courseName = input.getInput("Enter CourseName: ");
        final String courseNamebufffer = courseName;
        if (input.checkName(courseName) == null) {
            display("Error CourseName, CourseName have to not space, empty character, special characters and numbers");
        }
        p = s -> s.getCourseName().equals(courseNamebufffer);
        if (input.checkSame(arrList, p)) {
            display("The CourseName existed");
        }
    } while (input.checkName(courseName) == null || input.checkSame(arrList, p));
    
    // Sau khi nhập thông tin hợp lệ, bạn có thể tạo một đối tượng khách hàng mới và thêm vào danh sách.
    Customer newCustomer = new Customer(customerId, name, semester, courseName);
    arrList.add(newCustomer);
    
    // Optional: In ra thông báo để xác nhận rằng khách hàng đã được thêm vào danh sách.
    display("Customer added successfully!");
}

    private void deleteCustomerByID() {
        String idSearch;
        Customer cus;
        do {
            idSearch = (input.checkID(input.getInput("Enter ID Customer(KH..): ")));
            String idSearchBufer = idSearch;
            cus = company.searchCustomerReturnObject(s -> s.getStudentsID().equals(idSearchBufer));
            if (idSearch == null) {
                display("Error ID, ID have to has KH in the head of ID and next only numbers");
            } else if (cus == null) {
                display("Not Found Customer, Enter agian");
            }
        } while (idSearch == null || cus == null);

        display("Customer will be deleted");
        display("Do you want to delete (Y-N)?");
        display(cus.toString());
        String choice;
        String pattern = "[YNyn]";
        Predicate<String> p1 = s -> s.matches(pattern);
        do {
            choice = input.getInput("Choice: ");
            if (!p1.test(choice)) {
                display("Wrong choice! Please enter agian");
            } else if (choice.matches("[Yy]")) {
                company.deleteByID(idSearch);
                display("delete Success");
            } else {
                display("OK!");
            }
        } while (!p1.test(choice));
    }

    private void uppdateSemester() {

        ArrayList<Customer> arrList = company.getArrList();
        String idSearch;
        Customer cus;
        do {
            idSearch = (input.checkID(input.getInput("Enter ID Customer(KH..): ")));
            if (idSearch == null) {
                display("Error ID, ID have to has KH in the head of ID and next only numbers");
            }
            String idSearchBuffer = idSearch;
            cus = company.searchCustomerReturnObject(s -> s.getStudentsID().equals(idSearchBuffer));

            if (cus == null) {
                display("Not Found Customer, Enter agian");
            } else {
                display(cus.toString());
            }
        } while (idSearch == null || cus == null);

        String semesterSearch;
        Predicate<Customer> p;
        do {
            semesterSearch = input.checkName(input.getInput("Enter New Semester: "));
            String semesterSearchBuffer = semesterSearch;
            p = s -> s.getSemester().equals(semesterSearchBuffer);
            if (semesterSearch == null) {
                display("Error Semester, Semester have to not space, empty character, special characters and numbers");
            } else if (input.checkSame(arrList, p)) {
                display("The semester existed");
            }
        } while (semesterSearch == null || input.checkSame(arrList, p));

        String pattern = "[YNyn]";
        Predicate<String> p1 = s -> s.matches(pattern);
        display("Do you want to update?(Y-N)");
        String choice = input.checkInputAndLoop(input.getInput("Choice: "), p1);
        if (choice.matches("[Yy]")) {
            String newSemester = semesterSearch;
            company.uppdateSemester(idSearch, newSemester);
            display("Update succeed");
        } else {
            display("OK!");
        }
    }

    private void uppdateCourseName() {
        ArrayList<Customer> arrList = company.getArrList();
        String idSearch;
        Customer cus;
        do {
            idSearch = (input.checkID(input.getInput("Enter ID Customer(KH..): ")));
            if (idSearch == null) {
                display("Error ID, ID have to has KH in the head of ID and next only numbers");
            }
            String idSearchBuffer = idSearch;
            cus = company.searchCustomerReturnObject(s -> s.getStudentsID().equals(idSearchBuffer));

            if (cus == null) {
                display("Not Found Customer, Enter agian");
            } else {
                display(cus.toString());
            }
        } while (idSearch == null || cus == null);
        String newCourseName;
        do {
            newCourseName = input.checkName(input.getInput("Enter New CourseName "));
            if (newCourseName == null) {
                display("Error coursename, coursename have to not space, empty character, special characters and numbers");
            }
        } while (newCourseName == null);
        display("Do you want to update?(Y-N)");
        String pattern = "[YNyn]";
        Predicate<String> p = s -> s.matches(pattern);
        String choice = input.checkInputAndLoop(input.getInput("Choice: "), p);
        if (choice.matches("[Yy]")) {
            company.updateCourseName(idSearch, newCourseName);
            display("Update succeed");
        } else {
            display("OK!");
        }
    }
}
