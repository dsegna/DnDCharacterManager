package dps924.ddcharactermanager;

import java.util.ArrayList;
import java.util.HashMap;

import dps924.ddcharactermanager.exceptions.InvalidAbilityException;
import dps924.ddcharactermanager.rules.AbilityRule;
import dps924.ddcharactermanager.rules.RuleDatabase;

public class DDCharacter {

    public String charName;
    public int charLevel;
    public String charRace;
    public String charClass;

    private RuleDatabase ruleDB;
    private HashMap<String, Integer> abilityScores = new HashMap<>();
    private ArrayList<String> trainedSkills = new ArrayList<>();

    public DDCharacter(String charName, int charLevel, String charRace, String charClass, RuleDatabase ruleDB) {
        this.charName = charName;
        this.charLevel = charLevel;
        this.charRace = charRace;
        this.charClass = charClass;
        this.ruleDB = ruleDB;
        //TODO: Hardcoding default ability scores
        for(AbilityRule rule : ruleDB.getAbilityRules().values()) {
            abilityScores.put(rule.getKey(), 10);
        }
    }

    public int getAbilityModifier(String key) throws InvalidAbilityException {
        Integer value = abilityScores.get(key);
        if(value != null) {
            return ruleDB.getAbilityModifier(key, value);
        } else {
            throw new InvalidAbilityException(key);
        }
    }

    public RuleDatabase getRuleDB() {
        return ruleDB;
    }

    public boolean getIsTrained(String name) {
        if(trainedSkills.indexOf(name) != -1) {
            return true;
        } else {
            return false;
        }
    }
}
