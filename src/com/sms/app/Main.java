package com.sms.app;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.sms.dao.*;
import com.sms.model.*;

public class Main {

    public static void main(String[] args) {

        StudentDAO dao = new StudentDAOImpl();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("""
1. Add Full-Time Student
2. Add Part-Time Student
3. Remove Student
4. View Student
5. View Students
6. Sort by DOJ
7. Sort by ID
8. Sort by First Name
9. Exit
""");

            int ch = sc.nextInt();

            try {

                switch (ch) {

                    case 1 -> {
                        System.out.print("id fname lname doj(yyyy-mm-dd) salary : ");
                        dao.addStudent(new FullTimeStudent(
                                sc.nextInt(),
                                sc.next(),
                                sc.next(),
                                Date.valueOf(sc.next()),
                                sc.nextDouble()
                        ));
                        System.out.println("Added");
                    }

                    case 2 -> {
                        System.out.print("id fname lname doj(yyyy-mm-dd) rate hours : ");
                        dao.addStudent(new PartTimeStudent(
                                sc.nextInt(),
                                sc.next(),
                                sc.next(),
                                Date.valueOf(sc.next()),
                                sc.nextDouble(),
                                sc.nextInt()
                        ));
                        System.out.println("Added");
                    }

                    case 3 -> {
                        System.out.print("ID : ");
                        dao.removeStudent(sc.nextInt());
                        System.out.println("Removed");
                    }

                    case 4 -> {
                        System.out.print("ID : ");
                        System.out.println(dao.getStudent(sc.nextInt()));
                    }

                    case 5 -> print(dao.getAllStudents("id"));

                    case 6 -> print(dao.getAllStudents("doj"));

                    case 7 -> print(dao.getAllStudents("id"));

                    case 8 -> print(dao.getAllStudents("first_name"));

                    case 9 -> {
                        System.out.println("Bye");
                        System.exit(0);
                    }

                    default -> System.out.println("Invalid choice");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void print(List<Student> list) {

        if (list.isEmpty()) {
            System.out.println("No students found");
            return;
        }

        for (Student s : list) {
            System.out.println(s);
        }
    }
}
