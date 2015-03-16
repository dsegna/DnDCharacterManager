package dps924.ddcharactermanager.rules;

public class AbilityRule {

    private String name;
    private String key;

    public AbilityRule(String name, String key, String calculation) {
        this.name = name;
        this.key = key;
        //TODO: Parse the calculation string
    }
    public String getName() {
        return name;
    }
    public String getKey() {
        return key;
    }
    public int calculateModifier(int value) {
        //TODO: Change hardcoded calculation to use dynamic calculation string
        return value - 10 / 2;
    }
}
