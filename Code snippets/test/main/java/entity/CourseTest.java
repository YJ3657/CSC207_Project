package main.java.entity;

import main.java.app.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;

class CourseTest {

    private final CourseFactory courseFactory = new CourseFactory();

    private Student student;

    private Course course;

    @BeforeEach
    void init() {
        course = courseFactory.create(Constants.TEST_COURSE_ID);
        Constants.CURRENT_USER = Constants.TEST_USERNAME;
        StudentFactory studentFactory = new StudentFactory();
        student = studentFactory.create(Constants.CURRENT_USER, LocalDateTime.now().toString());
        course.addStudent(student);
        QuestionFactory questionFactory = new QuestionFactory();
        Question limitQuestion = questionFactory.create(Constants.CHAPTERNO_PLACEHOLDER,
                Constants.LIMIT_QUES,
                Constants.LIMIT_DEF);
        Question continuityQuestion = questionFactory.create(Constants.CHAPTERNO_PLACEHOLDER + 1,
                Constants.CONTINUITY_QUES,
                Constants.CONTIUNUITY_DEF);
        DefinitionFactory definitionFactory = new DefinitionFactory();
        Definition limitDefinition = definitionFactory.create(Constants.CHAPTERNO_PLACEHOLDER,
                Constants.LIMIT_TERM,
                Constants.LIMIT_DEF);
        Definition continuityDefinition = definitionFactory.create(Constants.CHAPTERNO_PLACEHOLDER + 1,
                Constants.CONTINUITY_TERM,
                Constants.CONTIUNUITY_DEF);


        course.setDefinitions(new ArrayList<>(Arrays.asList(limitDefinition, continuityDefinition)));
        course.setQuestions(new ArrayList<>(Arrays.asList(limitQuestion, continuityQuestion)));
    }

    @Test
    void getQuestions() {
        List<Question> questions = course.getQuestions();
        assert questions.get(0).getQuestion().equals(Constants.LIMIT_QUES);
        assert questions.get(0).getChapterno() == Constants.CHAPTERNO_PLACEHOLDER;
        assert questions.get(0).getAnswer().equals(Constants.LIMIT_DEF);

        assert questions.get(1).getQuestion().equals(Constants.CONTINUITY_QUES);
        assert questions.get(1).getChapterno() == Constants.CHAPTERNO_PLACEHOLDER + 1;
        assert questions.get(1).getAnswer().equals(Constants.CONTIUNUITY_DEF);

    }

    @Test
    void setQuestion() {
        QuestionFactory questionFactory = new QuestionFactory();
        Question derivativesQuestion = questionFactory.create(Constants.CHAPTERNO_PLACEHOLDER + 2,
                Constants.DERIVATIVES_QUES,
                Constants.DERIVATIVES_DEF);
        course.setQuestion(derivativesQuestion);
        List<Question> questions = course.getQuestions();
        assert questions.get(questions.size() - 1).equals(derivativesQuestion);
    }

    @Test
    void getQuestionQuestions() {
        List<String> questions = new ArrayList<>(Arrays.asList(Constants.LIMIT_QUES, Constants.CONTINUITY_QUES));
        Assertions.assertEquals(course.getQuestionQuestions(), questions);
    }

    @Test
    void getDefinitions() {
        List<Definition> definitions = course.getDefinitions();
        assert definitions.get(0).getDefinition().equals(Constants.LIMIT_DEF);
        assert definitions.get(0).getChapterno() == Constants.CHAPTERNO_PLACEHOLDER;
        assert definitions.get(0).getWord().equals(Constants.LIMIT_TERM);

        assert definitions.get(1).getDefinition().equals(Constants.CONTIUNUITY_DEF);
        assert definitions.get(1).getChapterno() == Constants.CHAPTERNO_PLACEHOLDER + 1;
        assert definitions.get(1).getWord().equals(Constants.CONTINUITY_TERM);
    }

    @Test
    void getDefinitionTerms() {
        List<String> expectedTerms = new ArrayList<>(Arrays.asList(Constants.LIMIT_TERM, Constants.CONTINUITY_TERM));
        assert course.getDefinitionTerms().equals(expectedTerms);
    }

    @Test
    void setDefinition() {
        DefinitionFactory definitionFactory = new DefinitionFactory();
        Definition derivativesDefinition = definitionFactory.create(Constants.CHAPTERNO_PLACEHOLDER + 2,
                Constants.DERIVATIVES_TERM, Constants.DERIVATIVES_DEF);
        course.setDefinition(derivativesDefinition);
        List<Definition> definitions = course.getDefinitions();
        assert definitions.get(definitions.size() - 1).equals(derivativesDefinition);
    }


    @Test
    void getSetContents() {
        Map<Integer, String> contents = new LinkedHashMap<>();
        contents.put(Constants.CHAPTERNO_PLACEHOLDER, Constants.LIMIT_DEF);
        course.setContents(contents);
        assert course.getContents().equals(contents);
    }

    @Test
    void getId() {
        assert course.getId().equals(Constants.TEST_COURSE_ID);
    }

    @Test
    void getStudents() {
        List<Student> students = course.getStudents();
        Student student = students.get(0);
        assert student.equals(this.student);
    }
}