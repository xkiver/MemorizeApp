package cl.accedo.accedotest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseClass extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MemorizeDB.db";
    
    private String sqlString = "CREATE TABLE 'ranking' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
    												    "'name' TEXT, " +
    												    "'score' TEXT)";
    
    public DataBaseClass(Context ctx){
    	super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sqlString);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS 'ranking'");
		onCreate(db);
	}

}
