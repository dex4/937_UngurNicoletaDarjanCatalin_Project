package Test;

import Domain.Student;
import Domain.Teme;
import Repository.StudentRepo;
import Repository.TemeRepo;
import Service.ServiceStudent;
import Service.ServiceTeme;
import Validator.StudentValidator;
import Validator.ValidationException;
import Validator.Validator;
import Validator.TemeValidator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    private Validator<Student> studentValidator = new StudentValidator();
    private Validator<Teme> temeValidator = new TemeValidator();

    private StudentRepo rep = new StudentRepo(studentValidator, "studenti.xml");
    private ServiceStudent srv = new ServiceStudent(rep);
    private Student sampleStudent = new Student("1", "John Doe", 932, "john@doe.com", "Johnatan");

    private TemeRepo temeRepo = new TemeRepo(temeValidator, "teme.xml");
    private ServiceTeme serviceTeme = new ServiceTeme(temeRepo);
    private Teme sampleTema = new Teme(1, "very important", 3, 5);

    @Test
    public void addTeme() {
        assertEquals(serviceTeme.add(sampleTema).toString().trim(),
                new Teme(1, "very important", 3, 5).toString().trim());
    }

    @Test
    public void addTemeInvalid() {
        assertNotSame(serviceTeme.add(sampleTema).toString().trim(),
                new Teme(2, "not so important", 3, 5).toString().trim());
    }

    @Test
    public void addStudent() {

        assertEquals(new Student("123", "asda", 933, "asda@asda.casd", "asda"),
                srv.add(new Student("123", "asda", 933, "asda@asda.casd", "asda")));
    }

    @Test
    public void addStudentInvalid() {
        assertNotSame(new Student("123", "asda", 933, "asda@asda.casd", "asda"),
                srv.add(new Student("124", "asda", 933, "asda@asda.casd", "asda")));
    }

    @Test(expected = ValidationException.class)
    public void addStudentWrongId() {
        Student student = sampleStudent;
        student.setID(null);
        assertEquals(student,
                srv.add(student));

    }

    //Solves for id == "" and length of id < 1
    @Test(expected = ValidationException.class)
    public void addStudentEmptyId() {
        Student student = sampleStudent;
        student.setID("");
        srv.add(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudentIdWithNotDigit() {
        Student student = sampleStudent;
        student.setID("91a");
        srv.add(student);
    }


    @Test(expected = ValidationException.class)
    public void addStudentWithWrongGroup1() {
        Student student = new Student("1", "John Doe", 110, "john@doe.com", "Johnatan");
        srv.add(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudentWithWrongGroup2() {
        Student student = new Student("1", "John Doe", 101, "john@doe.com", "Johnatan");
        srv.add(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudentWithWrongGroup3() {
        Student student = new Student("1", "John Doe", 141, "john@doe.com", "Johnatan");
        assertEquals(srv.add(student), student);
    }

    @Test(expected = ValidationException.class)
    public void addStudentWithWrongGroup4() {
        Student student = new Student("1", "John Doe", 938, "john@doe.com", "Johnatan");
        assertEquals(srv.add(student), student);
    }

    @Test
    public void addStudentWithOkGroup1() {
        Student student = new Student("1", "John Doe", 112, "john@doe.com", "Johnatan");
        assertEquals(srv.add(student), student);
    }

    @Test
    public void addStudentWithOkGroup2() {
        Student student = new Student("1", "John Doe", 936, "john@doe.com", "Johnatan");
        assertEquals(srv.add(student), student);
    }

    @Test
    public void addStudentWithOkGroup3() {
        Student student = new Student("1", "John Doe", 111, "john@doe.com", "Johnatan");
        assertEquals(srv.add(student), student);
    }

    @Test
    public void addStudentWithOkGroup4() {
        Student student = new Student("1", "John Doe", 937, "john@doe.com", "Johnatan");
        assertEquals(srv.add(student), student);
    }

    @Test(expected = ValidationException.class)
    public void addStudentWithWrongEmail1() {
        Student student = sampleStudent;
        student.setMail("stuent.com");
        srv.add(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudentWithWrongEmail2() {
        Student student = sampleStudent;
        student.setMail("stndent@com");
        srv.add(student);
    }

    @Test(expected = ValidationException.class)
    public void addStudentWithWrongEmail3() {
        Student student = sampleStudent;
        student.setMail("studentcom");
        srv.add(student);
    }

    @Test()
    public void addStudentWithOkEmail() {
        Student student = sampleStudent;
        student.setMail("student@scs.com");
        assertEquals(srv.add(student), student);
    }
}
