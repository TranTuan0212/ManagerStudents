package model;

import control.CheckValid;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Predicate;


public class Company {

    private ArrayList<Customer> arrCus;
    private CheckValid con;

    public Company() {
        arrCus = new ArrayList<>();
        con = new CheckValid();
    }

    public ArrayList<Customer> getArrList() {
        return arrCus;
    }

    public String getInput(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        return sc.nextLine();
    }

    public void writeFile(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Customer customer : arrCus) {
                bufferedWriter.write(customer.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
//nhận vào một đối tượng Predicate<Customer> làm tham số, và trả về một danh sách ArrayList<Customer> chứa các khách hàng thỏa mãn điều kiện được định nghĩa bởi Predicate.
//c là một tham số của kiểu Predicate<Customer>. Biến này được sử dụng để đại diện cho một đối tượng Predicate dùng để kiểm tra các điều kiện trên đối tượng khách hàng (Customer).
    public Customer searchCustomerReturnObject(Predicate<Customer> c) {
        for (Customer cus : arrCus) {
            if (c.test(cus)) {
                return cus;
            }
        }
        return null;
    }

    public ArrayList<Customer> searchCustomerReturnArray(Predicate<Customer> p) {
        ArrayList<Customer> arr = new ArrayList<>();
        for (Customer cus : arrCus) {
            if (p.test(cus)) {
                arr.add(cus);
            }
        }
        return arr;
    }

    public void updateName(String idSearch, String newName) {
        for (Customer customer : arrCus) {
            if (customer.getStudentsID().equals(idSearch)) {
                customer.setName(newName);
                break;
            }
        }
    }
    public void updateCourseName(String idSearch, String newCourseName) {
        for (Customer customer : arrCus) {
            if (customer.getStudentsID().equals(idSearch)) {
                customer.setCourseName(newCourseName);
                break;
            }
        }
    }

    public void uppdateSemester(String idSearch, String newSemester) {
        for (Customer customer : arrCus) {
            if (customer.getStudentsID().equals(idSearch)) {
                customer.setSemester(newSemester);
                break;
            }
        }
    }

    public void deleteByID(String idSearch) {
        for (Customer customer : arrCus) {
            if (customer.getStudentsID().equals(idSearch)) {
                arrCus.remove(customer);
                break;
            }
        }
    }

    public void sortArrByID() {
        // anonymous inner 
        Collections.sort(arrCus, new Comparator<Customer>() {

            @Override
            public int compare(Customer o1, Customer o2) {
                String a = o1.getStudentsID().substring(2); // Lấy phần sau chuỗi "KH" của o1
                String b = o2.getStudentsID().substring(2); // Lấy phần sau chuỗi "KH" của o2
                return a.compareTo(b);
            }

        });
     
    }
}
