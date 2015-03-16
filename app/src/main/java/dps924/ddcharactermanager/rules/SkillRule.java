package dps924.ddcharactermanager.rules;

import dps924.ddcharactermanager.DDCharacter;

public class SkillRule {

    private String name;
    private String abilityModKey;
    private int trainedMod;

    public SkillRule(String name, String abilityModKey, int trainedMod) {
        this.name = name;
        this.abilityModKey = abilityModKey;
        this.trainedMod = trainedMod;
    }
    public String getName() {
        return name;
    }
    public String getAbilityModKey() { return abilityModKey; }
    public int getSkillValue(DDCharacter character) {
        int abilityMod = character.getAbilityModifier(abilityModKey);
        if(trainedMod != 0) {
            if(character.getIsTrained(name)) {
                return trainedMod + abilityMod;
            } else {
                return abilityMod;
            }
        } else {
            return abilityMod;
        }
    }
}

