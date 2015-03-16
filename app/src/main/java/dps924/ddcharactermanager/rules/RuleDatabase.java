package dps924.ddcharactermanager.rules;

import android.util.Log;

import java.util.HashMap;

import dps924.ddcharactermanager.exceptions.InvalidAbilityException;

public class RuleDatabase {
    private static final String TAG = RuleDatabase.class.getName();

    private HashMap<String, AbilityRule> abilityRules = new HashMap<>();
    private HashMap<String, SkillRule> skillRules = new HashMap<>();

    public HashMap<String, AbilityRule> getAbilityRules() {
        return abilityRules;
    }
    public void addAbilityRule(AbilityRule rule) {
        String key = rule.getKey();
        if(!abilityRules.containsKey(key)) {
            abilityRules.put(key, rule);
        } else {
            Log.e(TAG, "AbilityRule with key: " + key + " already exists");
        }
    }
    public HashMap<String, SkillRule> getSkillRules() {
        return skillRules;
    }
    public void addSkillRule(SkillRule rule) {
        String name = rule.getName();
        if(!skillRules.containsKey(name)) {
            skillRules.put(name, rule);
        } else {
            Log.e(TAG, "SkillRule with name: " + name + "already exists");
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
}
