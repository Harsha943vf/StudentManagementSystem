package com.sms.dao;

import java.util.List;
import com.sms.model.Student;

public interface StudentDAO {

    void addStudent(Student s) throws Exception;

    void removeStudent(int id) throws Exception;

    Student getStudent(int id) throws Exception;

    List<Student> getAllStudents(String orderBy) throws Exception;
}
