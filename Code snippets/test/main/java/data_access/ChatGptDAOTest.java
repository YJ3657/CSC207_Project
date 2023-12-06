package main.java.data_access;

import main.java.app.Constants;
import main.java.entity.Definition;
import main.java.entity.DefinitionFactory;
import main.java.entity.Question;
import main.java.entity.QuestionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//NOTE: VERY EXPENSIVE!!!!!
class ChatGptDAOTest {

    final DefinitionFactory definitionFactory = new DefinitionFactory();;
    final QuestionFactory questionFactory = new QuestionFactory();

    ChatGptDAO chatGPTDAO;

    String prompt = "";
    @BeforeEach
    void init(){
        chatGPTDAO = new ChatGptDAO();
        Definition limitDefinition = definitionFactory.create(Constants.CHAPTERNO_PLACEHOLDER,
                Constants.LIMIT_TERM,
                Constants.LIMIT_DEF);
        Definition continuityDefinition = definitionFactory.create(Constants.CHAPTERNO_PLACEHOLDER + 1,
                Constants.CONTINUITY_TERM,
                Constants.CONTIUNUITY_DEF);
        Question limitQuestion = questionFactory.create(Constants.CHAPTERNO_PLACEHOLDER,
                Constants.LIMIT_QUES,
                Constants.LIMIT_DEF);
        Question continuityQuestion = questionFactory.create(Constants.CHAPTERNO_PLACEHOLDER + 1,
                Constants.CONTINUITY_QUES,
                Constants.CONTIUNUITY_DEF);

        prompt = limitDefinition.getWord() + ":" + limitDefinition.getDefinition() + " "
                + continuityDefinition.getWord() + ":" + continuityDefinition.getDefinition() + " " +
                limitQuestion.getQuestion() + ":" + limitQuestion.getAnswer() + " "
                + continuityQuestion.getQuestion() + ":" + continuityQuestion.getAnswer() + " ";
    }

    @Test
    void checkForResponse(){
        prompt = "Say something";

        try{
            String result = chatGPTDAO.execute(prompt);
            //if successfully obtains result
            assert result != null;  // don't want empty string
            assert result != "";
        } catch(RuntimeException e){
            fail("Problem accessing the API or getting a non-error response :(");
        }

    }

    @Test
    void realisticQuizQuestion() {
        prompt = "Create ONLY ONE summary question under 30 words, along with an answer, based on the provided questions: " + prompt;
        try{
            String result = chatGPTDAO.execute(prompt);
            //if successfully obtains result
            int instanceOfQuestion = countOccurrences(result, "?");
            assert instanceOfQuestion == 1;
            int instanceOfAnswer = countOccurrences(result, "Answer");
            assert instanceOfAnswer <= 1;  // if Chat generates more than 1 response, this will be more than 1
            assert result.indexOf("?") < 150;  // question less than 150 characters, reasonably lengthed


        } catch(RuntimeException e){
            fail("Problem accessing the API or getting a non-error response :(");
        }
    }

    /**
     * Helper method to count how many times substring occurs in main string
     * @param mainString -
     * @param substring -
     * @return number of times found in main string
     */
    private int countOccurrences(String mainString, String substring) {
        int count = 0;
        int startIndex = 0;

        while (startIndex != -1) {
            startIndex = mainString.indexOf(substring, startIndex);

            if (startIndex != -1) {
                count++;
                startIndex += substring.length();
            }
        }

        return count;
    }


}