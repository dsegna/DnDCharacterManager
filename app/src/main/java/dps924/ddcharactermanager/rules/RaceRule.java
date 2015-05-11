package dps924.ddcharactermanager.rules;

import java.util.ArrayList;

public class RaceRule {
    private static final int bonus = 2;
    private String name;
    private String ability1, ability2;
    private String skill1, skill2;
    private ArrayList<FeatRule> raceFeats = new ArrayList<>();

    public RaceRule(String name, String ability1, String skill1, ArrayList<FeatRule> raceFeats) {
        this.name = name;
        this.ability1 = ability1;
        this.skill1 = skill1;
        this.raceFeats = raceFeats;
    }
    public String getName() {
        return name;
    }
    public ArrayList<FeatRule> getRaceFeats() { return raceFeats; }
}
