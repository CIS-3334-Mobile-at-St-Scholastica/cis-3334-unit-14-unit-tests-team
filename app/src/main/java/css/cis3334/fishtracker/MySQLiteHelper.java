package css.cis3334.fishtracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

/**
 * This class serves as a helper class to open and create a database. Constant variables are created for table columns,
 * a constructor is defined to create a table, an execute database SQL is executed in onCreate, and code is provided
 * to assist with upgrading the database.
 *
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_FISH = "fish";                 // a constant for the TABLE_FISH table name
    /**
     * These variable serves as a constant for the column names.
     **/
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SPECIES = "species";
    public static final String COLUMN_WEIGHT = "weightInOz";
    public static final String COLUMN_DATECAUGHT = "dateCaught";
    public static final String COLUMN_LOC_LAT = "locationLatitude";
    public static final String COLUMN_LOC_LON = "locationLongitude";

    private static final String DATABASE_NAME = "fishing.db";     // constant for the database name
    //This variable serves as a constant for the DATABASE version.
    //Incremented the version of the database so Android deletes the old one and creates a new one with our new field
    private static final int DATABASE_VERSION = 101;

    /*
    * This statement defines a database creation sql statement.
    */
    //Added COLUMN_RATING to DATABASE_CREATE
    private static final String DATABASE_CREATE = "create table "
            + TABLE_FISH + "( "
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_SPECIES + " TEXT not null,"
                + COLUMN_WEIGHT + " TEXT,"
                + COLUMN_DATECAUGHT + " TEXT,"
                + COLUMN_LOC_LAT + " TEXT,"
                + COLUMN_LOC_LON + " TEXT"
            + ");";

    private static MySQLiteHelper sInstance;
    public static synchronized MySQLiteHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new MySQLiteHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    /*
    * This method makes a method call to the SQLiteOpenHelper (super). Passes in the context
    * (parameter), database name (set as a constant), null (no value needed), and database version
    * (also defined as a constant). This is used to help open and reference the database.
    *
    * @param context This parameter is a handle to the calling activity.
    */
    private MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
    * This method contains a method call that will execute an SQL Statement defined as a String
    * parameter. Upon creating the application, we run our SQL command.
    *
    * @param database This is the database we are referencing to get the SQL statements.
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    /*
    * This method provides code to assist with a database upgrade. A log file is written stating
    * what the old and newer version is, and drops the table defined in the constant TABLE_COMMENTS.
    * Then, the database is recreated with the updated version.
    *
    * @param SQLiteDatabase db This is the database being referenced that is to be upgraded
    * @param int oldVersion This is the old version of the database
    * @param int newVersion This is the new version of the database
    *
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("MySQLiteHelper", "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FISH);
        onCreate(db);
    }



}
