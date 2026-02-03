//contoller/StudentController

package kz.aitu.SchoolManagmentSystem.controller;

import kz.aitu.SchoolManagmentSystem.model.Student;
import kz.aitu.SchoolManagmentSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/studentsl")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        int generatedId = studentRepository.addStudent(student);
        student.setId(generatedId);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
        public ResponseEntity<Student> updateStudent ( @PathVariable int id, @RequestBody Student student){
            studentRepository.updateStudent(id, student);
            return ResponseEntity.ok(student);
        }

        @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent ( @PathVariable int id){
            studentRepository.deleteStudent(id);
            return ResponseEntity.ok("Student with id " + id + " deleted");
        }
    // GET /studentsl/filter?age=18
    @GetMapping("/filter")
    public List<Student> filterByAge(@RequestParam int age) {
        return studentRepository.findByAge(age);
    }
    @GetMapping("/averageAge")
    public Double averageAge() {
        return studentRepository.getAverageAge();
    }


}



