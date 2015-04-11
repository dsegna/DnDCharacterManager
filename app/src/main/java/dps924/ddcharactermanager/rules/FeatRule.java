package dps924.ddcharactermanager.rules;

public class FeatRule {
    private String name;
    private String effect;

    public FeatRule(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }
    public String getName() { return name; }
    public String getEffect() { return effect; }
}
