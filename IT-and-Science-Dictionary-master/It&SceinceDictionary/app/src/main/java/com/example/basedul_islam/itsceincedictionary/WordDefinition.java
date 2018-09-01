package com.example.basedul_islam.itsceincedictionary;

import java.util.ArrayList;

/**
 * Created by Basedul_Islam on 1/14/2017.
 */

public class WordDefinition {
    String word, definition;
    public WordDefinition(String word, ArrayList<String>allDefinition)
    {
        StringBuilder stringBuilder = new StringBuilder();
        this.word = word;
        for(String string:allDefinition)
        {
            stringBuilder.append(string);
        }
        this.definition = stringBuilder.toString();
    }
    public WordDefinition(String word, String definition)
    {
        this.word = word;
        this.definition = definition;
    }
}
