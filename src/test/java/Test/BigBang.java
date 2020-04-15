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
import Validator.Validator;

import Validator.TemeValidator;
import Validator.StudentValidator;
import Validator.NotaValidator;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


public class BigBang {

    private Validator<Student> studentValidator = new StudentValidator();
    private Validator<Teme> temeValidator = new TemeValidator();
    private Validator<Nota> noteValidator = new NotaValidator();

    private StudentRepo rep = new StudentRepo(studentValidator, "studenti.xml");
    private ServiceStudent srv = new ServiceStudent(rep);
    private Student sampleStudent = new Student("1", "John Doe", 932, "john@doe.com", "Johnatan");

    private TemeRepo temeRepo = new TemeRepo(temeValidator, "teme.xml");
    private ServiceTeme serviceTeme = new ServiceTeme(temeRepo);
    private Teme sampleTema = new Teme(1, "very important", 3, 5);

    private NoteRepo noteRepo = new NoteRepo(noteValidator);
    private ServiceNote serviceNote = new ServiceNote(noteRepo);
    //private Nota sampleNota = new Nota("1",sampleStudent, sampleTema,10, 2);


    @Test
    public void addStudentBBTest() {
        assertEquals(new Student("123", "asda", 933, "asda@asda.casd", "asda"),
                srv.add(new Student("123", "asda", 933, "asda@asda.casd", "asda")));
    }

    @Test
    public void addTemeBBTest() {
        assertEquals(serviceTeme.add(sampleTema).toString().trim(),
                new Teme(1, "very important", 3, 5).toString().trim());
    }
    @Test
    public void bigBangIntegration() {
        addStudentBBTest();
        addTemeBBTest();

    }

}
