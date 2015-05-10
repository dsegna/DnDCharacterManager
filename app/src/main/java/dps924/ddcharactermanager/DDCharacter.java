package dps924.ddcharactermanager;

import java.util.ArrayList;
import java.util.HashMap;

import dps924.ddcharactermanager.exceptions.InvalidAbilityException;
import dps924.ddcharactermanager.rules.AbilityRule;
import dps924.ddcharactermanager.rules.AlignmentRule;
import dps924.ddcharactermanager.rules.DeityRule;
import dps924.ddcharactermanager.rules.FeatRule;
import dps924.ddcharactermanager.rules.ItemRule;
import dps924.ddcharactermanager.rules.RaceRule;
import dps924.ddcharactermanager.rules.RuleDatabase;

public class DDCharacter {

    // Character Profile Values
    private String name, race, charClass, paragon ="", epic ="", desc ="";
    private int level, exp = 0;

    private RuleDatabase ruleDB;
    private AlignmentRule alignment;
    private DeityRule deity;
    private RaceRule raceRule;
    private HashMap<String, Integer> abilityScores = new HashMap<>();
    private ArrayList<String> trainedSkills = new ArrayList<>();
    private ArrayList<FeatRule> feats = new ArrayList<>();
    private ArrayList<ItemRule> items = new ArrayList<>();

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
    /* Skills */
    public boolean getIsTrained(String name) {
        if(trainedSkills.indexOf(name) != -1) {
            return true;
        } else {
            return false;
        }
    }
    //TODO: Calculate Skill Values
    public int getSkillValue(String name) {
        int value = 0;
        if(getIsTrained(name)) {

        }
        return value;
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
    public ArrayList<FeatRule> getFeats() { return feats; }
    public void addFeat(FeatRule feat) {
        feats.add(feat);
    }
    public ArrayList<ItemRule> getItems() { return items; }
    public void addItem(ItemRule item) {
        items.add(item);
    }
    public void removeItem(ItemRule item) {
        items.remove(item);
    }
    public void removeItem(int index) {
        items.remove(index);
    }
}
