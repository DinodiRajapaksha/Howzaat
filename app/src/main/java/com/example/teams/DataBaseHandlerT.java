package com.example.teams;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandlerT extends SQLiteOpenHelper {

    //DataBase Details

    private static final int version = 1;
    private static final String DBName = "MADProjectT";
    private static final String TableName = "Teams";

    // Column Names for Teams table

    private static final String TeamId = "TeamId";
    private static final String TeamName = "TeamName";
    private static final String Category = "Category";
    private static final String BatsmenNo = "BatsmenNo";
    private static final String BowlerNo = "BowlerNo";
    private static final String AllRounderNo = "AllRounderNo";
    private static final String WKNo = "WKNo";

    // column names for Player

    private static final String PlayerId = "PlayerId";

    public DataBaseHandlerT(@Nullable Context context) {
        super(context, DBName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

            // CREATE TABLE STATEMENT

            String CREATE_TABLE = "CREATE TABLE " + TableName + " " +
                "("
                + TeamId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TeamName + " TEXT, "
                + Category + " TEXT, "
                + BatsmenNo + " INTEGER, "
                + BowlerNo + " INTEGER, "
                + AllRounderNo + " INTEGER, "
                + WKNo + " INTEGER " +
                ");" ;


            // EXECUTE CREATE TABLE

            sqLiteDatabase.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // DROP TABLE STATEMENT

        String DROP_TABLE = "DROP TABLE IF EXISTS " + TableName;


        // EXECUTE DROP TABLE

        sqLiteDatabase.execSQL(DROP_TABLE);

        // CREATE NEW TABLE

        onCreate(sqLiteDatabase);

    }

    public void insertTeam(Teams teams) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TeamId,teams.getTeamId());
        contentValues.put(TeamName,teams.getTeamName());
        contentValues.put(Category,teams.getCategory());
        contentValues.put(BatsmenNo,teams.getBatsmenNo());
        contentValues.put(BowlerNo,teams.getBowlerNo());
        contentValues.put(AllRounderNo,teams.getAllRounderNo());
        contentValues.put(WKNo,teams.getWKNo());

        sqLiteDatabase.insert(TableName,null,contentValues);

        sqLiteDatabase.close();




    }

    // select all into a list
    public List<Teams> getAllTeams() {
        List<Teams> Teams = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query="SELECT * FROM " + TableName;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do{
                //create new Team object
                Teams teams = new Teams();
                teams.setTeamId(cursor.getInt(0));
                teams.setTeamName(cursor.getString(1));
                teams.setCategory(cursor.getString(2));
                teams.setBatsmenNo(cursor.getInt(3));
                teams.setBowlerNo(cursor.getInt(4));
                teams.setAllRounderNo(cursor.getInt(5));
                teams.setWKNo(cursor.getInt(6));

                Teams.add(teams);

            }
            while(cursor.moveToNext());
        }

        return Teams;


    }

    // Delete

    public void deleteTeam(int id){

        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.delete(TableName, TeamId + " =?",new String[] {String.valueOf(id)});
        sqLiteDatabase.close();
    }

    public int updateTeams(Teams teams){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TeamName,teams.getTeamName());
        contentValues.put(Category,teams.getCategory());
        contentValues.put(BatsmenNo,teams.getBatsmenNo());
        contentValues.put(BowlerNo,teams.getBowlerNo());
        contentValues.put(AllRounderNo,teams.getAllRounderNo());
        contentValues.put(WKNo,teams.getWKNo());

        int status = sqLiteDatabase.update(TableName,contentValues,TeamId + " = ?", new String[] {String.valueOf(teams.getTeamId())} );

        sqLiteDatabase.close();
        return status;
    }

    public Teams getSingleTeam(int id){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TableName,new String[] {TeamId,TeamName,Category,BatsmenNo,BowlerNo,AllRounderNo,WKNo},TeamId + " =?",new String[] {String.valueOf(id)},null,null,null);

        Teams team;

        if(cursor!=null){

            cursor.moveToFirst();

            team = new Teams(

                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6)

            );

            return team;
        }
        return null;
    }
}
