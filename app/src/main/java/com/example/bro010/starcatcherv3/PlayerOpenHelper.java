package com.example.bro010.starcatcherv3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by bro016 on 05/03/2018.
 */

public class PlayerOpenHelper  extends SQLiteOpenHelper {


        public static final String DATABASENAME = "Player.db";
        public static final String TABLE_USERS = "allPlayers";
        public static final int DATABASEVERSION = 1;

        public static final String COLUMN_USER_NUM = "userNum";
        public static final String COLUMN_USER_NAME = "userName";

        public static final String COLUMN_USER_SCORE = "userScore";

        private static final String CREATE_TABLE_ALL_USERS = "CREATE TABLE IF NOT EXISTS " +
                TABLE_USERS + "(" + COLUMN_USER_NUM + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " VARCHAR," + COLUMN_USER_SCORE + " VARCHAR "
                + ");";


        String[] allColumns = {COLUMN_USER_NUM, COLUMN_USER_NAME, COLUMN_USER_SCORE};

        SQLiteDatabase database;

        public PlayerOpenHelper(Context context) {
            super(context, DATABASENAME, null, DATABASEVERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_ALL_USERS);
            Log.i("data", "Table customer created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);
        }

        public void open() {
            database = this.getWritableDatabase();
            Log.i("data", "Database connection open");
        }

        //quearies ......
        public Player createUser(Player u) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, u.getPlayerName());
            values.put(COLUMN_USER_SCORE, u.getScore());

            long insertId = database.insert(PlayerOpenHelper.TABLE_USERS, null, values);
            Log.i("data", "Customer " + insertId + "insert to database");
            u.setPlayerid(insertId);
            return u;


        }

        public ArrayList<Player> getAllUsers() {

            ArrayList<Player> lst = new ArrayList<Player>();
            Cursor cursor = database.query(TABLE_USERS, allColumns, null, null, null, null, null);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    long id = cursor.getLong(cursor.getColumnIndex(COLUMN_USER_NUM));
                    String fname = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));
                    String score = cursor.getString(cursor.getColumnIndex(COLUMN_USER_SCORE));



                    Player u = new Player( fname, score,id);
                    lst.add(u);
                }
            }
            Log.i("data", "כל הלקוחות");
            return lst;
        }

        public ArrayList<Player> getAllUsersByFIlter(String selection, String OrderBy) {
            Cursor cursor = database.query(PlayerOpenHelper.TABLE_USERS, allColumns, selection, null, null, null, OrderBy);
            ArrayList<Player> lst = convertTableToList(cursor);
            return lst;
        }


        private ArrayList<Player> convertTableToList(Cursor cursor) {
            ArrayList<Player> lst = new ArrayList<Player>();

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    long id = cursor.getLong(cursor.getColumnIndex(COLUMN_USER_NUM));
                    String fname = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));
                    String score = cursor.getString(cursor.getColumnIndex(COLUMN_USER_SCORE));



                    Player u = new Player( fname, score,id);
                    lst.add(u);

                }

            }
            return lst;
        }
        public long deleteAll()

        {


            return database.delete(PlayerOpenHelper.TABLE_USERS, null, null);

        }
        public long deleteCustomerByRow(long rowId)
        {
            return database.delete(PlayerOpenHelper.TABLE_USERS, PlayerOpenHelper.COLUMN_USER_NUM + "=" + rowId, null);
        }

        public long updateByRow(Player u)
        {

            ContentValues values=new ContentValues();
            values.put(COLUMN_USER_NAME, u.getPlayerName());
            values.put(COLUMN_USER_SCORE, u.getScore());

            // Toast.makeText(null,u.toString(),Toast.LENGTH_LONG).show();
            return database.update(TABLE_USERS, values, COLUMN_USER_NUM +"=" + u.getPlayerid(), null);



        }
        public Player getUserById(long rowId)
        {
            Cursor cursor=database.query(PlayerOpenHelper.TABLE_USERS, allColumns, PlayerOpenHelper.COLUMN_USER_NUM + "=" +rowId, null, null, null, null);

            cursor.moveToFirst();
            if(cursor.getCount()>0)
            {
                long id = cursor.getLong(cursor.getColumnIndex(COLUMN_USER_NUM));
                String fname = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));
                String score = cursor.getString(cursor.getColumnIndex(COLUMN_USER_SCORE));



                Player u = new Player( fname, score,id);
                return u;
            }

            return null;

        }
    }


