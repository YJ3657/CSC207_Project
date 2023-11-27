package main.java.use_case.add_Definition;

import main.java.entity.Definition;

import java.util.List;

public interface DefinitionDataAccessInterface {
    void save(int chapterNumber, String term, String definition, String userId, String courseId);

    List<Definition> getDefinitions(int chapterNumber, String courseId);

    void saveDefinition(String term, String definition, int chapterNumber, String courseId);

}
