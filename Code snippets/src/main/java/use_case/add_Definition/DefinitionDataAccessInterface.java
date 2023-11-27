package main.java.use_case.add_Definition;

import main.java.entity.Definition;

import java.util.List;
import java.util.Set;

public interface DefinitionDataAccessInterface {
    void save(int chapterNumber, String term, String definition, String courseId);

    Set<String> getDefinitionTerms(int chapterNumber, String courseId);

    void saveDefinition(String term, String definition, int chapterNumber, String courseId);

    String getDefinition(String term, String courseId);

}
