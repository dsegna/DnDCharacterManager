package dps924.ddcharactermanager.rules;

public class AbilityRule {

    private String name;
    private String key;
    private String calculation;

    public AbilityRule(String name, String key, String calculation) {
        this.name = name;
        this.key = key;
        this.calculation = calculation;
    }
    public String getName() {
        return name;
    }
    public String getKey() {
        return key;
    }
    public int calculateModifier(int abilityScore) {
        //TODO: Change hardcoded calculation to use dynamic calculation string
        return abilityScore - 10 / 2;
    }
}
