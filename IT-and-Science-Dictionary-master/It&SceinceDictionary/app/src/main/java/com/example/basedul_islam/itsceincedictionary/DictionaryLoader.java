package com.example.basedul_islam.itsceincedictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Basedul_Islam on 1/14/2017.
 */

public class DictionaryLoader {
    public static  void loadData(BufferedReader bufferedReader, DictionaryDatabaseHealper dictionaryDatabaseHealper)
    {
        ArrayList<WordDefinition>allWords = new ArrayList<WordDefinition>();
        try {
            BufferedReader fileReader = bufferedReader;
            try{
                int c = 17;
                c = fileReader.read();
                while(c!=(-1))
                {
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((char)c!='\n'&&c!=-1)
                    {
                        try
                        {
                            stringBuilder.append((char)c);
                        }
                        catch (Exception e)
                        {
                            System.out.println(stringBuilder.length());
                        }
                        c = fileReader.read();
                        if(c==-1)
                        {
                            return;
                        }
                    }
                    String wordString = stringBuilder.toString();

                    ArrayList<String>definiton = new ArrayList<String>();
                    while (c=='\n'||c=='\t')
                    {
                        c = fileReader.read();
                        if(c=='\n'||c=='\t'||c=='\r')
                        {
                            StringBuilder stringBuilder2 = new StringBuilder();
                            while (c!='\n')
                            {
                                stringBuilder2.append((char)c);
                                c = fileReader.read();
                            }
                            String definitionString = stringBuilder2.toString();
                            definiton.add(definitionString);
                        }else
                        {
                            break;
                        }

                    }
                    wordString = wordString.trim();
                    allWords.add(new WordDefinition(wordString, definiton));


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dictionaryDatabaseHealper.initializeDatabaseForTheFirstTime(allWords);
                fileReader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
