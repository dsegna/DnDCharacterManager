package dps924.ddcharactermanager.rules;

import android.util.Log;

import java.util.HashMap;
import java.util.logging.Level;

import dps924.ddcharactermanager.exceptions.InvalidAbilityException;

public class RuleDatabase {
    private static final String TAG = RuleDatabase.class.getName();

    private LevelRule levelRule;
    private HashMap<String, AbilityRule> abilityRules = new HashMap<>();
    private HashMap<String, SkillRule> skillRules = new HashMap<>();
    private HashMap<String, RaceRule> raceRules = new HashMap<> ();

    public LevelRule getLevelRule() {
        return levelRule;
    }
    /* Ability Rules */
    public HashMap<String, AbilityRule> getAbilityRules() {
        return abilityRules;
    }
    public void addAbilityRule(AbilityRule rule) {
        Log.d(TAG, "Adding ability rule " + rule.getName());
        String key = rule.getKey();
        if(!abilityRules.containsKey(key)) {
            abilityRules.put(key, rule);
        } else {
            Log.w(TAG, "AbilityRule with key: " + key + " already exists");
        }
    }
    public int getAbilityModifier(String key, int value) {
        AbilityRule rule = abilityRules.get(key);
        if(rule != null) {
            return rule.calculateModifier(value);
        }
        else {
            throw new InvalidAbilityException(key);
        }
    }
    /* Skill Rules */
    public HashMap<String, SkillRule> getSkillRules() {
        return skillRules;
    }
    public void addSkillRule(SkillRule rule) {
        Log.d(TAG, "Adding skill rule " + rule.getName());
        String name = rule.getName();
        if(!skillRules.containsKey(name)) {
            skillRules.put(name, rule);
        } else {
            Log.w(TAG, "SkillRule with name: " + name + "already exists");
        }
    }
    /* Race Rules */
    public HashMap<String, RaceRule> getRaceRules() { return raceRules; }
    public void addRaceRule(RaceRule rule) {
        String name = rule.getName();
        if(!raceRules.containsKey(name)) {
            raceRules.put(name, rule);
        } else {
            Log.w(TAG, "RaceRule with name: " + name + "already exists");
        }
    }
}
