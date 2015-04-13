package dps924.ddcharactermanager.rules;

public class DeityRule {
    private String name,
                   alignment;

    public DeityRule(String name, AlignmentRule alignment) {
        this.name = name;
        this.alignment = alignment.getName();
    }
    public String getName() { return name; }
    public String getAlignment() { return alignment; }
}
