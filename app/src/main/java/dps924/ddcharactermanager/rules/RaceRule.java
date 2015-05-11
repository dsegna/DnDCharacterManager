package dps924.ddcharactermanager.rules;

public class RaceRule {
    private static final int bonus = 2;
    private String name;
    private String ability1, ability2;
    private String skill1, skill2;

    public RaceRule(String name, String ability1, String skill1) {
        this.name = name;
        this.ability1 = ability1;
        this.skill1 = skill1;
    }
    public String getName() {
        return name;
    }
}
