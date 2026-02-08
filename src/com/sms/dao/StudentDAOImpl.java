package com.sms.dao;

import java.sql.*;
import java.util.*;

import com.sms.exception.StudentNotFoundException;
import com.sms.model.*;
import com.sms.util.DBUtil;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public void addStudent(Student s) throws Exception {

        String sql = "INSERT INTO students VALUES(?,?,?,?,?,?,?,?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, s.getId());
            ps.setString(2, s.getFirstName());
            ps.setString(3, s.getLastName());
            ps.setDate(4, s.getDoj());
            ps.setString(5, s.getType());

            if (s instanceof FullTimeStudent) {

                ps.setDouble(6, ((FullTimeStudent) s).getSalary());
                ps.setNull(7, Types.DOUBLE);
                ps.setNull(8, Types.INTEGER);

            } else {

                PartTimeStudent p = (PartTimeStudent) s;
                ps.setNull(6, Types.DOUBLE);
                ps.setDouble(7, p.getHourlyRate());
                ps.setInt(8, p.getHours());
            }

            ps.executeUpdate();
        }
    }

    @Override
    public void removeStudent(int id) throws Exception {

        String sql = "DELETE FROM students WHERE id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int count = ps.executeUpdate();

            if (count == 0)
                throw new StudentNotFoundException("Student not found");
        }
    }

    @Override
    public Student getStudent(int id) throws Exception {

        String sql = "SELECT * FROM students WHERE id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next())
                throw new StudentNotFoundException("Student not found");

            return map(rs);
        }
    }

    @Override
    public List<Student> getAllStudents(String orderBy) throws Exception {

        List<Student> list = new ArrayList<>();

        String sql = "SELECT * FROM students ORDER BY " + orderBy;

        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(map(rs));
            }
        }

        return list;
    }

    private Student map(ResultSet rs) throws Exception {

        String type = rs.getString("type");

        if ("FULL".equals(type)) {

            return new FullTimeStudent(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("doj"),
                    rs.getDouble("salary")
            );
        }

        return new PartTimeStudent(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getDate("doj"),
                rs.getDouble("hourly_rate"),
                rs.getInt("hours")
        );
    }
}
