package dps924.ddcharactermanager.characters;

import java.util.ArrayList;
import java.util.List;

import dps924.ddcharactermanager.DDCharacter;
import dps924.ddcharactermanager.rules.FeatRule;
import dps924.ddcharactermanager.rules.RuleDatabase;

public class CharacterDatabase {
    private static final String TAG = CharacterDatabase.class.getName();

    private List<DDCharacter> characters = new ArrayList();
    private RuleDatabase ruleDB;

    public CharacterDatabase(RuleDatabase ruleDB){
        this.ruleDB = ruleDB;
        createCharacter("Mark", 1, "Dwarf", "Fighter");
        createCharacter("John", 2, "Human", "Paladin");
        createCharacter("Bell", 3, "Elf", "Wizard");
        createCharacter("Gnar", 4, "Gnome", "Artificer");
        createCharacter("Towelie", 5, "Towel", "Mesmer");
    }
    public List<DDCharacter> getCharactersList() {
        return characters;
    }
    public void createCharacter(String n, int l, String r, String c) {
        DDCharacter character = new DDCharacter(n, l, r, c, ruleDB);
        for(FeatRule rule : ruleDB.getFeatRules().values()) {
            character.addFeat(new FeatRule(rule.getName(), rule.getEffect()));
        }
        characters.add(character);
    }
}
