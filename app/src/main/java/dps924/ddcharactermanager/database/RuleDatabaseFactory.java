package dps924.ddcharactermanager.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import dps924.ddcharactermanager.rules.AbilityRule;
import dps924.ddcharactermanager.rules.AlignmentRule;
import dps924.ddcharactermanager.rules.ClassRule;
import dps924.ddcharactermanager.rules.DeityRule;
import dps924.ddcharactermanager.rules.FeatRule;
import dps924.ddcharactermanager.rules.RaceRule;
import dps924.ddcharactermanager.rules.RuleDatabase;
import dps924.ddcharactermanager.rules.SkillRule;

//TODO: Make table names and columns dynamic. Possibly through a descriptive JSON file
public class RuleDatabaseFactory {
    private static final String ALIGNMENT_TABLE = "Alignment",
                                DEITY_TABLE = "DEITY",
                                ABILITY_TABLE = "Ability",
                                SKILL_TABLE = "Skill",
                                RACE_TABLE = "Race", RACEFEATS_TABLE = "RaceFeats",
                                CLASS_TABLE = "Class" ;

    private SQLiteDatabase database;
    private RuleDatabase ruleDatabase;
    public RuleDatabaseFactory(SQLiteAssetDatabase db) {
        database = db.getReadableDatabase();
        ruleDatabase = new RuleDatabase();
        getAlignmentRules();
        getDeityRules();
        getAbilityRules();
        getSkillRules();
        getClassRules();
        getRaceRules();
    }
    public RuleDatabase getRuleDatabase() {
        return ruleDatabase;
    }
    private void getAlignmentRules() {
        Cursor cursor = database.rawQuery("select * from " + ALIGNMENT_TABLE, null);
        final int NAMECOLUMN = cursor.getColumnIndex("Name");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            AlignmentRule alignmentRule = new AlignmentRule(
                    cursor.getString(NAMECOLUMN)
            );
            ruleDatabase.addAlignmentRule(alignmentRule);
            cursor.moveToNext();
        }
    }
    private void getDeityRules() {
        Cursor cursor = database.rawQuery("select * from " + DEITY_TABLE, null);
        final int NAMECOLUMN = cursor.getColumnIndex("Name");
        final int ALIGNMENTCOLUMN = cursor.getColumnIndex("Alignment");
        final HashMap<String, AlignmentRule> alignmentRules = ruleDatabase.getAlignmentRules();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            DeityRule deityRule = new DeityRule(
                    cursor.getString(NAMECOLUMN),
                    alignmentRules.get(cursor.getString(ALIGNMENTCOLUMN))
            );
            ruleDatabase.addDeityRule(deityRule);
            cursor.moveToNext();
        }

    }
    private void getAbilityRules() {
        Cursor cursor = database.rawQuery("select * from " + ABILITY_TABLE, null);
        final int KEYCOLUMN = cursor.getColumnIndex("Key");
        final int NAMECOLUMN = cursor.getColumnIndex("Name");
        final int CALCULATIONCOLUMN = cursor.getColumnIndex("Calculation");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            AbilityRule abilityRule = new AbilityRule(
                    cursor.getString(NAMECOLUMN),
                    cursor.getString(KEYCOLUMN),
                    cursor.getString(CALCULATIONCOLUMN)
            );
            ruleDatabase.addAbilityRule(abilityRule);
            cursor.moveToNext();
        }
    }
    private void getSkillRules() {
        Cursor cursor = database.rawQuery("select * from " + SKILL_TABLE, null);
        final int NAMECOLUMN = cursor.getColumnIndex("Name");
        final int MODIFIERCOLUMN = cursor.getColumnIndex("Modifier");
        final int TRAINEDCOLUMN = cursor.getColumnIndex("Trained_Modifier");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int trainedModifier = 0;
            if(!cursor.isNull(TRAINEDCOLUMN)) {
                trainedModifier = cursor.getInt(TRAINEDCOLUMN);
            }
            SkillRule skillRule = new SkillRule(
                cursor.getString(NAMECOLUMN),
                cursor.getString(MODIFIERCOLUMN),
                trainedModifier
            );
            ruleDatabase.addSkillRule(skillRule);
            cursor.moveToNext();
        }
    }
    private void getRaceRules() {
        Cursor cursor = database.rawQuery("select * from " + RACE_TABLE, null);
        final int NAMECOLUMN = cursor.getColumnIndex("Name");
        final int ABILITY1COLUMN = cursor.getColumnIndex("Ability1");
        final int SKILL1COLUMN = cursor.getColumnIndex("Skill1");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String name = cursor.getString(NAMECOLUMN);
            Cursor featsCursor = database.rawQuery("select * from " + RACEFEATS_TABLE + " where Race = " + '"' + name + '"', null);
            final int FEATNAMECOLUMN = featsCursor.getColumnIndex("Name");
            final int FEATDESCRIPTION = featsCursor.getColumnIndex("Description");
            ArrayList<FeatRule> raceFeats = new ArrayList<>();
            featsCursor.moveToFirst();
            while(!featsCursor.isAfterLast()) {
                raceFeats.add(new FeatRule(
                        featsCursor.getString(FEATNAMECOLUMN),
                        featsCursor.getString(FEATDESCRIPTION)
                ));
                featsCursor.moveToNext();
            }
            RaceRule raceRule = new RaceRule(
                name,
                cursor.getString(ABILITY1COLUMN),
                cursor.getString(SKILL1COLUMN),
                raceFeats
            );
            ruleDatabase.addRaceRule(raceRule);
            cursor.moveToNext();
        }
    }
    private void getClassRules() {
        Cursor cursor = database.rawQuery("select * from " + CLASS_TABLE, null);
        final int NAMECOLUMN = cursor.getColumnIndex("Name");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {

            ClassRule classRule = new ClassRule(
                    cursor.getString(NAMECOLUMN)
            );
            ruleDatabase.addClassRule(classRule);
            cursor.moveToNext();
        }
    }
}
