package com.example.basedul_islam.itsceincedictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Basedul_Islam on 1/14/2017.
 */

public class DictionaryDatabaseHealper extends SQLiteOpenHelper{
    final static String DATABASE_DICTIONARY = "dictionary";
    final static String ITEM_ID_COLUMN = "id";
    final static String WORD_COLUMN = "word";
    final static String DEFINITION_COLUMN = "definiton";
    Context context;
    final static String CREATE_DATABASE_QUERY = "CREATE TABLE "+DATABASE_DICTIONARY+"("+ITEM_ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT, "+WORD_COLUMN+" TEXT, "+DEFINITION_COLUMN+" TEXT)";
    final static String ON_UPGRADE_QUERY = "DROP_TABLE"+DATABASE_DICTIONARY;
    public DictionaryDatabaseHealper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_DICTIONARY, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_DATABASE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(ON_UPGRADE_QUERY);
        onCreate(database);
    }
    public long insertData(WordDefinition wordDefinition)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(WORD_COLUMN, wordDefinition.word);
        values.put(DEFINITION_COLUMN, wordDefinition.definition);

        return database.insert(DATABASE_DICTIONARY, null, values);
    }
    public long updateData(WordDefinition wordDefinition)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(WORD_COLUMN, wordDefinition.word);
        values.put(DEFINITION_COLUMN, wordDefinition.definition);

        return  database.update(DATABASE_DICTIONARY, values, WORD_COLUMN+" =?", new String[]{wordDefinition.word});

    }
    public void deleteData(WordDefinition wordDefinition)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryString = "DELETE FROM "+DATABASE_DICTIONARY+" WHERE "+WORD_COLUMN+" = '"+wordDefinition.word+"'";

        database.execSQL(queryString);

    }
    public ArrayList<WordDefinition>getAllWords()
    {
        ArrayList<WordDefinition>arrayList = new ArrayList<WordDefinition>();
        SQLiteDatabase database = this.getReadableDatabase();

        String selectAllQueryString = "SELECT * FORM "+DATABASE_DICTIONARY;
        Cursor cursor = database.rawQuery(selectAllQueryString, null);
        if(cursor.moveToFirst()) do {
            WordDefinition wordDefinition = new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINITION_COLUMN)));
            arrayList.add(wordDefinition);
        } while (cursor.moveToNext());
        return  arrayList;
    }
    public WordDefinition getWordDefinition(String word)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        WordDefinition wordDefinition = null;
        String selectQueryString = "SELECT * FROM"+DATABASE_DICTIONARY+" WHERE "+WORD_COLUMN+" = '"+word+"'";
        Cursor cursor = database.rawQuery(selectQueryString, null);

        if(cursor.moveToFirst())
            wordDefinition = new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINITION_COLUMN)));
            return  wordDefinition;
    }

    public WordDefinition getWordDefinition(long id)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        WordDefinition wordDefinition = null;
        String selectQueryString = "SELECT * FROM"+DATABASE_DICTIONARY+" WHERE "+ITEM_ID_COLUMN+" = '"+id+"'";
        Cursor cursor = database.rawQuery(selectQueryString, null);

        if(cursor.moveToFirst())
            wordDefinition = new WordDefinition(cursor.getString(cursor.getColumnIndex(WORD_COLUMN)), cursor.getString(cursor.getColumnIndex(DEFINITION_COLUMN)));
        return  wordDefinition;
    }
    public void initializeDatabaseForTheFirstTime(ArrayList<WordDefinition> wordDefinitions)
    {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("BEGIN");

        for (WordDefinition wordDefintion:
            wordDefinitions ) {
            contentValues.put(WORD_COLUMN, wordDefintion.definition);
            contentValues.put(DEFINITION_COLUMN, wordDefintion.definition);
            database.insert(DATABASE_DICTIONARY, null, contentValues);
        }

        database.execSQL("COMMIT");
    }
}
