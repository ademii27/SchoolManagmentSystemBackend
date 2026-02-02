package kz.aitu.SchoolManagmentSystem.repository;

import kz.aitu.SchoolManagmentSystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Student> rowMapper = new RowMapper<Student>() {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
        }
    };

    public List<Student> findAll() {
        return jdbcTemplate.query("SELECT * FROM students", rowMapper);
    }

    public Student findById(int id) {
        List<Student> list = jdbcTemplate.query(
                "SELECT * FROM students WHERE id = ?",
                new Object[]{id}, rowMapper
        );
        return list.isEmpty() ? null : list.get(0);
    }
    public void addStudent(Student student) {
        jdbcTemplate.update(
                "INSERT INTO students (id, name, age) VALUES (?, ?, ?)",
                student.getId(), student.getName(), student.getAge()
        );
    }

    // PUT — обновить студента
    public void updateStudent(int id, Student student) {
        jdbcTemplate.update(
                "UPDATE students SET name = ?, age = ? WHERE id = ?",
                student.getName(), student.getAge(), id
        );
    }

    // DELETE — удалить студента
    public void deleteStudent(int id) {
        jdbcTemplate.update(
                "DELETE FROM students WHERE id = ?",
                id
        );
    }
}
