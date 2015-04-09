package dps924.ddcharactermanager.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dps924.ddcharactermanager.rules.AbilityRule;
import dps924.ddcharactermanager.rules.RuleDatabase;
import dps924.ddcharactermanager.rules.SkillRule;

public class RuleDatabaseFactory {
    private static final String ABILITY_TABLE = "Ability",
                                SKILL_TABLE = "Skill";

    private SQLiteDatabase database;
    private RuleDatabase ruleDatabase;
    public RuleDatabaseFactory(SQLiteAssetDatabase db) {
        database = db.getReadableDatabase();
        ruleDatabase = new RuleDatabase();
        getAbilityRules();
        getSkillRules();
    }
    public RuleDatabase getRuleDatabase() {
        return ruleDatabase;
    }
    private void getAbilityRules() {
        Cursor cursor = database.rawQuery("select * from " + ABILITY_TABLE, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            AbilityRule abilityRule = new AbilityRule(
                    cursor.getString(1),
                    cursor.getString(0),
                    cursor.getString(2)
            );
            ruleDatabase.addAbilityRule(abilityRule);
            cursor.moveToNext();
        }
    }
    private void getSkillRules() {
        Cursor cursor = database.rawQuery("select * from " + SKILL_TABLE, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int trainedModifier = 0;
            if(!cursor.isNull(2)) {
                trainedModifier = cursor.getInt(2);
            }
            SkillRule skillRule = new SkillRule(
                cursor.getString(0),
                cursor.getString(1),
                trainedModifier
            );
            ruleDatabase.addSkillRule(skillRule);
            cursor.moveToNext();
        }
    }
}
