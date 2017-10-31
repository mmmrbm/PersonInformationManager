package com.yukon.madhuranga.personinformationmanager.base.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yukon.madhuranga.personinformationmanager.util.ConstantHolder;

/**
 * Entity to manage database definitions for the application.
 * DDL queries will be executed against a SQLite database.
 */
public class DatabaseStructureDefinitionManager extends SQLiteOpenHelper {
    /**
     * DDL query for creation of table for @see Student model
     */
    private static final String STUDENT_TABLE_CREATE =
            "CREATE TABLE " + ConstantHolder.STUDENT_TABLE_NAME + " ("
                    + ConstantHolder.COL_RECORD_ID + " INTEGER PRIMARY KEY, "
                    + ConstantHolder.COL_PERSON_NAME + " TEXT NOT NULL, "
                    + ConstantHolder.COL_AGE + " INTEGER NOT NULL, "
                    + ConstantHolder.COL_STUDENT_AVG_GRADE + " REAL NOT NULL, "
                    + ConstantHolder.COL_STUDENT_ASSOC_REG_NO + " TEXT NOT NULL "
                    + ");";

    /**
     * DDL query for creation of table for @see Teacher model
     */
    private static final String TEACHER_TABLE_CREATE =
            "CREATE TABLE " + ConstantHolder.TEACHER_TABLE_NAME + " ("
                    + ConstantHolder.COL_RECORD_ID + " INTEGER PRIMARY KEY, "
                    + ConstantHolder.COL_PERSON_NAME + " TEXT NOT NULL, "
                    + ConstantHolder.COL_AGE + " INTEGER NOT NULL, "
                    + ConstantHolder.COL_TEACHER_HIGHEST_EDU_QUAL + " TEXT NOT NULL "
                    + ");";

    /**
     * Initializes @see DatabaseDefinitionManager.
     *
     * @param context The @see Context required to create the database.
     */
    public DatabaseStructureDefinitionManager(Context context) {
        super(context, ConstantHolder.DATABASE_NAME, null, ConstantHolder.DATABASE_VERSION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(STUDENT_TABLE_CREATE);
        database.execSQL(TEACHER_TABLE_CREATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + ConstantHolder.STUDENT_TABLE_NAME);
        database.execSQL("DROP TABLE IF EXISTS " + ConstantHolder.TEACHER_TABLE_NAME);
        onCreate(database);
    }
}
