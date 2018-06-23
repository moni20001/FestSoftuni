package com.example.moni.helloworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class DBHelper  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "grades.db";
    public static final String CONTACTS_TABLE_NAME = "subject_grades";
    public static final String GRADES_COLUMN_ID = "id";
    public static final String GRADES_COLUMN_NAME = "name";
    public static final String GRADES_COLUMN_GRADES = "grades";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table subject_grades " +
                        "(id integer primary key, name text,grades_first text,grades_second text,final_grade text)"
        );
        db.execSQL("Insert into subject_grades(id,name,grades_first,grades_second,final_grade) VALUES (1,\"БЕЛ-ЗИП\",  \"\"  ,\"\",    \"\")," +
                "(2,\"БЕЛ-ЗП\",\"\",    \"\"         ,\"\"),"+
                "(3,\"Английски език\",   \"\"        ,\"\"      ,\"\"),"+
                "(4,\"Математика-СИП\",           \"\",        \"\",        \"\"),"+
                "(5,\"Математика-ЗП\",     \"\",       \"\",      \"\"),"+
                "(6,\"История и цивилизация\",       \"\"      ,\"\"      ,\"\"),"+
                "(7,\"Философия\",         \"\"      ,\"\"           ,\"\"),"+
                "(8,\"ФВС\",       \"\",            \"\",            \"\"),"+
                "(9,\"Предприемачество\",      \"\",         \"\",       \"\"),"+
                "(10,\"ПроцесориПаметиРС\",        \"\",       \"\",     \"\"),"+
                "(11,\"ДънниПлаткиРС\",        \"\",         \"\",            \"\"),"+
                "(12,\"Мрежи\",                  \"\",       \"\",           \"\"),"+
                "(13,\"Мрежи-ЗИПП /УчПр\",        \"\",       \"\",             \"\"),"+
                "(14,\"Мрежи-ЗПП /УчПр\",           \"\",      \"\",            \"\"),"+
                "(15,\"Приложен софтуер\",           \"\",       \"\",             \"\"),"+
                "(16,\"Периферни устройства\",       \"\",       \"\",            \"\"),"+
                "(17,\"АсемблиранеРС /УчПр\",         \"\",        \"\",           \"\"),"+
                "(18,\"ПроцесПаметиДънни /УчПр\",\"\",\"\",\"\"),"+
                "(19,\"Електронни схеми\",\"\",\"\",\"\")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS subject_grades");
        onCreate(db);
    }



    public void updateGradeFirst(String subj, String grades){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",subj);
        contentValues.put("grades_first",grades);
        db.update("subject_grades", contentValues, "name = ? ", new String[] { subj } );

    }

    public String getFinalAverage(){
        String result = "";
        HashMap<String,String> finalGrades = getFinalGrades();
        int count = 0;
        double average = 0;
        for (String grade:finalGrades.keySet()) {
            if(!finalGrades.get(grade).isEmpty() && finalGrades.get(grade)!= null && !finalGrades.get(grade).equals("")){
                count++;
                average += Double.parseDouble(finalGrades.get(grade));
            }
        }
        result += "Годишна:\n";
        result += "Предмети: "+count+"\n"+"Средно аритметично: "+average/count;
        return  result;
    }

    public String getFirstAverage(){
        StringBuilder builder = new StringBuilder();

        LinkedHashMap<String,String> firstGrades = getSubjectFirst();
        for (String grade:firstGrades.keySet()) {
            if(!firstGrades.get(grade).isEmpty() && firstGrades.get(grade)!= null && !firstGrades.get(grade).equals("")){
                String[] grades = firstGrades.get(grade).split("[ ,]");
                double res = 0;
                int counter = 0;
                for (String gradeTemp :grades) {
                    if(!gradeTemp.isEmpty() && gradeTemp!=null) {
                        int gradeInt = Integer.parseInt(gradeTemp);
                        counter++;
                        res += gradeInt;
                    }
                }
                String r = String.format("%.2f",res/counter);
                builder.append(grade+": "+r).append("\n");
            }
        }
        return  builder.toString();
    }

    public String getSecondAverage(){
        StringBuilder builder = new StringBuilder();

        LinkedHashMap<String,String> secondGrades = getSubjectSecond();
        for (String grade:secondGrades.keySet()) {
            if(!secondGrades.get(grade).isEmpty() && secondGrades.get(grade)!= null && !secondGrades.get(grade).equals("")){
                String[] grades = secondGrades.get(grade).split("[ ,]");
                double res = 0;
                int counter = 0;
                for (String gradeTemp :grades) {
                    if(!gradeTemp.isEmpty() && gradeTemp!=null){
                        int gradeInt = Integer.parseInt(gradeTemp);
                        counter++;
                        res+=gradeInt;
                    }

                }
                String r = String.format("%.2f",res/counter);
                builder.append(grade+": "+r).append("\n");
            }
        }
        return  builder.toString();
    }

    public void updateGradeSecond(String subj, String grades){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",subj);
        contentValues.put("grades_second",grades);
        db.update("subject_grades", contentValues, "name = ? ", new String[] { subj } );

    }

    public void updateFinalGrade(String subj, String grades){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",subj);
        contentValues.put("final_grade",grades);
        db.update("subject_grades", contentValues, "name = ? ", new String[] { subj } );

    }

    public LinkedHashMap<String,String> getSubjectFirst(){
        SQLiteDatabase db = this.getReadableDatabase();
        LinkedHashMap<String,String> subjects = new LinkedHashMap<>();
        Cursor cursor = db.rawQuery("Select * from subject_grades",null);
        if (cursor.moveToFirst()) {
            do {
                String nameSubject =  cursor.getString(cursor.getColumnIndex("name"));
                String gradesSubject = cursor.getString(cursor.getColumnIndex("grades_first"));
              subjects.put(nameSubject,gradesSubject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return subjects;
    }
    public LinkedHashMap<String,String> getFinalGrades(){
        SQLiteDatabase db = this.getReadableDatabase();
        LinkedHashMap<String,String> subjects = new LinkedHashMap<>();
        Cursor cursor = db.rawQuery("Select * from subject_grades",null);
        if (cursor.moveToFirst()) {
            do {
                String nameSubject =  cursor.getString(cursor.getColumnIndex("name"));
                String gradesSubject = cursor.getString(cursor.getColumnIndex("final_grade"));
                subjects.put(nameSubject,gradesSubject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return subjects;
    }

    public LinkedHashMap<String,String> getSubjectSecond(){
        SQLiteDatabase db = this.getReadableDatabase();
        LinkedHashMap<String,String> subjects = new LinkedHashMap<>();
        Cursor cursor = db.rawQuery("Select * from subject_grades",null);
        if (cursor.moveToFirst()) {
            do {
                String nameSubject =  cursor.getString(cursor.getColumnIndex("name"));
                String gradesSubject = cursor.getString(cursor.getColumnIndex("grades_second"));
                subjects.put(nameSubject,gradesSubject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return subjects;
    }

    public String getGrades(String subj){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("subject_grades",
                new String[]{"grades"},
                "name = ?",
                new String[]{subj},
                null,
                null,
                null);

       // Cursor cursor = db.rawQuery("Select * from subject_grades where \"name = ?\"", new String[]{subj});

        String grades ="";
        if(cursor.moveToFirst()){
            grades = cursor.getString(cursor.getColumnIndex("grades"));
        }
        cursor.close();
        return grades;
    }

}
