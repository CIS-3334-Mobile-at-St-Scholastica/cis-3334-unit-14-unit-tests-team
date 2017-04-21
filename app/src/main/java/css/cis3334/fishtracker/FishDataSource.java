package css.cis3334.fishtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgibons on 4/20/2017.
 *
 * Provide methods for CRUD functions on the SQLite databse for the fish object
 */

public class FishDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    /*
    * This method creates a new database helper that is a new MySQLHelper object with the parameter context.
    *
    * @param context This parameter is a handle to the system. Helps obtain access to databases,
    * preferences, and helps resolve resources.
     */
    public FishDataSource(Context context) {
        dbHelper = MySQLiteHelper.getInstance(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Fish createFish( String species, String weightInOz, String dateCaught) {           //Added String rating as a parameter
        ContentValues values = new ContentValues();                         // Create a new ContentValue Object
        values.put(MySQLiteHelper.COLUMN_SPECIES, species);                 // Insert a species into the COLUMN_SPECIES field using MYSQLiteHelper
        values.put(MySQLiteHelper.COLUMN_WEIGHT, weightInOz);               // Insert weightInOz into the COLUMN_WEIGHT field using MYSQLiteHelper
        values.put(MySQLiteHelper.COLUMN_DATECAUGHT, dateCaught);

        long insertId = database.insert(MySQLiteHelper.TABLE_FISH, null, values);         //  Instert the fish into the database using the parameters above
        Fish newFish = new Fish(insertId, species, weightInOz, dateCaught);
        return newFish;
    }

    public Fish createFish( String species, String weightInOz, String dateCaught, String locationLatitude, String locationLongitude) {           //Added String rating as a parameter
        ContentValues values = new ContentValues();                         // Create a new ContentValue Object
        values.put(MySQLiteHelper.COLUMN_SPECIES, species);                 // Insert a species into the COLUMN_SPECIES field using MYSQLiteHelper
        values.put(MySQLiteHelper.COLUMN_WEIGHT, weightInOz);               // Insert weightInOz into the COLUMN_WEIGHT field using MYSQLiteHelper
        values.put(MySQLiteHelper.COLUMN_DATECAUGHT, dateCaught);
        values.put(MySQLiteHelper.COLUMN_LOC_LAT, locationLatitude);
        values.put(MySQLiteHelper.COLUMN_LOC_LON, locationLongitude);

        long insertId = database.insert(MySQLiteHelper.TABLE_FISH, null, values);         //  Instert the fish into the database using the parameters above
        Fish newFish = new Fish(insertId, species, weightInOz, dateCaught, locationLatitude, locationLongitude);
        return newFish;
    }

    public void deleteFish(Fish fish) {
        long id = fish.getId();
        System.out.println("Fish deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_FISH, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Fish> getAllFish() {
        List<Fish> fishList = new ArrayList<Fish>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_FISH,       //Modified to return all database fields
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Fish fish = cursorToFish(cursor);
            fishList.add(fish);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        //cursor.close();
        return fishList;
    }

    /**
     *  Converts the current row in the database cursor into a fish object
     * @param cursor points to the current row in the databsae cursor
     * @return a fish object created from that row
     */
    private Fish cursorToFish(Cursor cursor) {
        Fish fish = new Fish();
        fish.setId(cursor.getLong(cursor.getColumnIndex(MySQLiteHelper.COLUMN_ID)));
        fish.setSpecies(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_SPECIES)));
        fish.setWeightInOz(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_WEIGHT)));
        fish.setDateCaught(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_DATECAUGHT)));
        fish.setLocationCaughtLatitude(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_LOC_LAT)));
        fish.setLocationCaughtLongitude(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_LOC_LON)));
        return fish;
    }

}
