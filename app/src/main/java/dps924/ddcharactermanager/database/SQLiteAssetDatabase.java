package dps924.ddcharactermanager.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class SQLiteAssetDatabase extends SQLiteAssetHelper{

    public SQLiteAssetDatabase(Context context, String name, int version) {
        super(context, name, null, version);
        setForcedUpgrade();
    }
}
