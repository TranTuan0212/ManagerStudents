package control;


import model.Customer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Predicate;

public class CheckValid {

    public CheckValid() {
    }

    private void display(String message) {
        System.out.println(message);
    }

    public String getInput(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        return sc.nextLine();
    }

    public String checkInputAndLoop(String input, Predicate<String> p) {
        while (input.isEmpty() || !p.test(input)) {
            input = getInput("Wrong choice! Please input agian:");
        }
        return input;
    }

    public String checkName(String name) {
        // Kiểm tra xem tên có chỉ chứa chữ cái, chỉ có một dấu cách và không có dấu cách ở đầu hay không
        String regex = "^[a-zA-Z][a-zA-Z ]*[a-zA-Z]$";
        if (name.matches(regex) && name.length() >= 2 && name.charAt(0) != ' ') {
            return name;
        }
        return null;
    }

    public String checkID(String input) {
        if (input.length() != 4 || !input.startsWith("KH")) {
            return null;
        }

        char secondChar = input.charAt(2);
        char thirdChar = input.charAt(3);

        if (Character.isDigit(secondChar) && Character.isDigit(thirdChar)) {
            return input;
        } else {
            return null;
        }
    }
public String checkSemester(String input) {
    String[] validSemesters = {"Fall", "Spring", "Summer", "Winter"};
    for (String semester : validSemesters) {
        if (input.equalsIgnoreCase(semester)) {
            return semester; // Trả về kỳ học hợp lệ
        }
    }
    return null; // Trả về null nếu không hợp lệ
}



public boolean checkSame( ArrayList<Customer> list, Predicate<Customer> p ) {
        for (Customer cus : list) {
            if (p.test(cus)) {
                return true;
            }
        }
        return false;
    }

}

