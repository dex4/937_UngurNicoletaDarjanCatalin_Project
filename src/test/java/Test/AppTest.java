package Test;

import Domain.Nota;
import Domain.Student;
import Domain.Teme;
import Repository.NoteRepo;
import Repository.StudentRepo;
import Repository.TemeRepo;
import Service.ServiceNote;
import Service.ServiceStudent;
import Service.ServiceTeme;
import Validator.StudentValidator;
import Validator.TemeValidator;
import Validator.NotaValidator;
import Validator.Validator;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void addStudent() {
        Validator<Student> studentValidator = new StudentValidator();

        StudentRepo rep = new StudentRepo(studentValidator, "studenti.xml");
        ServiceStudent srv = new ServiceStudent(rep);
        assertEquals(new Student("123", "asda", 933, "asda@asda.casd", "asda"),
                srv.add(new Student("123", "asda", 933, "asda@asda.casd", "asda")));
    }

    @Test
    public void addStudentInvalid() {
        Validator<Student> studentValidator = new StudentValidator();

        StudentRepo rep = new StudentRepo(studentValidator, "studenti.xml");
        ServiceStudent srv = new ServiceStudent(rep);
        assertNotSame(new Student("123", "asda", 933, "asda@asda.casd", "asda"),
                srv.add(new Student("124", "asda", 933, "asda@asda.casd", "asda")));
    }
}
