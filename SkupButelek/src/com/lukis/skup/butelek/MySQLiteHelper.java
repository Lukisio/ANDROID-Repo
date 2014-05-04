package com.lukis.skup.butelek;



import java.util.LinkedList;
import java.util.List;
 
import com.lukis.skup.butelek.Beer;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class MySQLiteHelper extends SQLiteOpenHelper {
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BeerDB";
 
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create beer table
        String CREATE_BEER_TABLE = "CREATE TABLE beers ( " +
                "lp INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "code TEXT, "+
                "name TEXT, "+
                "producer TEXT, "+
                "returnable TEXT, "+
                "date TEXT )";
 
        // create beers table
        db.execSQL(CREATE_BEER_TABLE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older beers table if existed
        db.execSQL("DROP TABLE IF EXISTS beers");
 
        // create fresh beers table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------
 
    /**
     * CRUD operations (create "add", read "get", update, delete) beer + get all beers + delete all beers
     */
 
    // Beers table name
    private static final String TABLE_BEERS = "bottles";
 
    // Beers Table Columns names
    private static final String KEY_LP = "lp";
    private static final String KEY_CODE = "code";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRODUCER = "producer";
    private static final String KEY_RETURNABLE = "returnable";
    private static final String KEY_DATE = "date";
 
    private static final String[] COLUMNS = {KEY_LP,KEY_CODE,KEY_NAME,KEY_PRODUCER,KEY_RETURNABLE, KEY_DATE};
 
    public void addBeer(Beer beer){
        Log.d("addBeer", beer.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_CODE, beer.getCode()); // get title 
        values.put(KEY_NAME, beer.getName()); // get author
        values.put(KEY_PRODUCER, beer.getProducer()); // get producer
        values.put(KEY_RETURNABLE, beer.getReturnable()); // get returnable
        values.put(KEY_DATE, beer.getDate()); // get date
 
        // 3. insert
        db.insert(TABLE_BEERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
 
        // 4. close
        db.close(); 
    }
 
    public Beer getBeer(int id){
 
        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
 
        // 2. build query
        Cursor cursor = 
                db.query(TABLE_BEERS, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
 
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
 
        // 4. build beer object
        Beer beer = new Beer();
        beer.setLp(Integer.parseInt(cursor.getString(0)));
        beer.setCode(cursor.getString(1));
        beer.setName(cursor.getString(2));
        beer.setProducer(cursor.getString(3));
        beer.setReturnable(cursor.getString(4));
        beer.setDate(cursor.getString(5));
 
        Log.d("getBeer("+id+")", beer.toString());
 
        // 5. return beer
        return beer;
    }
 
    // Get All Beers
    public List<Beer> getAllBeers() {
        List<Beer> beers = new LinkedList<Beer>();
 
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_BEERS;
 
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
 
        // 3. go over each row, build beer and add it to list
        Beer beer = null;
        if (cursor.moveToFirst()) {
            do {
                beer = new Beer();
                beer.setLp(Integer.parseInt(cursor.getString(0)));
                beer.setCode(cursor.getString(1));
                beer.setName(cursor.getString(2));
                beer.setProducer(cursor.getString(3));
                beer.setReturnable(cursor.getString(4));
                beer.setDate(cursor.getString(5));
 
                // Add beer to beers
                beers.add(beer);
            } while (cursor.moveToNext());
        }
 
        Log.d("getAllBeers()", beers.toString());
 
        // return beers
        return beers;
    }
 
     // Updating single beer
    public int updateBeer(Beer beer) {
 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("code", beer.getCode()); // get code 
        values.put("name", beer.getName()); // get name
        values.put("producer", beer.getProducer()); // get producer
        values.put("returnable", beer.getReturnable()); // get returnable
        values.put("date", beer.getDate()); // get date
 
        // 3. updating row
        int i = db.update(TABLE_BEERS, //table
                values, // column/value
                KEY_LP+" = ?", // selections
                new String[] { String.valueOf(beer.getLp()) }); //selection args
 
        // 4. close
        db.close();
 
        return i;
 
    }
 
    // Deleting single beer
    public void deleteBeer(Beer beer) {
 
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_BEERS,
                KEY_LP+" = ?",
                new String[] { String.valueOf(beer.getLp()) });
 
        // 3. close
        db.close();
 
        Log.d("deleteBeer", beer.toString());
 
    }
}