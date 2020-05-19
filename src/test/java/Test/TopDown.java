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

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class TopDown {
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
    private HashMap<String, Integer> noteMap = new HashMap<>();

    private Nota sampleNota;

    private Nota getSampleNota() {
        Nota nota = null;
        noteMap.put("2", 2);
        for (HashMap.Entry<String, Integer> it : noteMap.entrySet()) {
            if (it.getValue() == 2) {
                nota = new Nota(it, sampleStudent, sampleTema, 10, 2);
            }
        }
        return nota;
    }


    @Test
    public void addStudentTD() {
        srv.add(new Student("123", "asda", 933, "asda@asda.casd", "asda"));
        Student deleted = srv.del("123");
        assertEquals(new Student("123", "asda", 933, "asda@asda.casd", "asda"),
                deleted);
    }

    @Test
    public void addAssignmentTDIntegration() {
        srv.add(new Student("123", "asda", 933, "asda@asda.casd", "asda"));
        serviceTeme.add(sampleTema);
        srv.del("123");
        serviceTeme.del(1);
    }

    @Test
    public void addGradeTDIntegration() {
        srv.add(new Student("123", "asda", 933, "asda@asda.casd", "asda"));
        serviceTeme.add(sampleTema);
        Nota nota = serviceNote.add(getSampleNota(), "Good");
        assertNotSame(nota, getSampleNota().toString().trim());
        srv.del("123");
        serviceTeme.del(1);
    }
    //test end
}
