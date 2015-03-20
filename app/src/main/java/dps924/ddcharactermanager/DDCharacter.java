package dps924.ddcharactermanager;

import java.util.ArrayList;
import java.util.HashMap;

import dps924.ddcharactermanager.exceptions.InvalidAbilityException;
import dps924.ddcharactermanager.rules.AbilityRule;
import dps924.ddcharactermanager.rules.RaceRule;
import dps924.ddcharactermanager.rules.RuleDatabase;

public class DDCharacter {

    // Character Profile Values
    private String name, race, charClass, paragon ="", epic ="", desc ="";
    private int level, exp = 0;
    private RaceRule raceRule;

    private RuleDatabase ruleDB;
    private HashMap<String, Integer> abilityScores = new HashMap<>();
    private ArrayList<String> trainedSkills = new ArrayList<>();

    public DDCharacter(String name, int level, String race, String charClass, RuleDatabase ruleDB) {
        this.name = name;
        this.level = level;
        this.race = race;
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
    // Getters
    public String getName() {
        return name;
    }
    public String getRace() {
        return race;
    }
    public String getCharClass() {
        return charClass;
    }
    public String getParagon() {
        return paragon;
    }
    public String getEpic() {
        return epic;
    }
    public String getDesc() {
        return desc;
    }
    public int getLevel() {
        return level;
    }
    public int getExp() {
        return exp;
    }
}
