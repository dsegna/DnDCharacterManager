package dps924.ddcharactermanager;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import dps924.ddcharactermanager.rules.AbilityRule;
import dps924.ddcharactermanager.rules.RuleDatabase;
import dps924.ddcharactermanager.rules.SkillRule;


public class CharacterActivity extends ActionBarActivity
        implements ProfileFragment.OnFragmentInteractionListener,
                   SkillsFragment.OnFragmentInteractionListener {

    ViewPager viewPager;
    //DEBUGGING
    RuleDatabase testDB;
    DDCharacter ddCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        //Setup the ViewPager
        viewPager = (ViewPager) findViewById(R.id.characterPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        //Bind tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.characterTabs);
        tabs.setViewPager(viewPager);
        //TODO: Creating testing DB and character. Should be dynamic
        testDB = new RuleDatabase();
        testDB.addAbilityRule(new AbilityRule("Strength", "STR", ""));
        testDB.addAbilityRule(new AbilityRule("Constitution", "CON", ""));
        testDB.addAbilityRule(new AbilityRule("Dexterity", "DEX", ""));
        testDB.addAbilityRule(new AbilityRule("Intelligence", "INT", ""));
        testDB.addAbilityRule(new AbilityRule("Wisdom", "WIS", ""));
        testDB.addAbilityRule(new AbilityRule("Charisma", "CHA", ""));

        testDB.addSkillRule(new SkillRule("Acrobatics", "DEX", 5));
        testDB.addSkillRule(new SkillRule("Arcana", "INT", 5));
        testDB.addSkillRule(new SkillRule("Athletics", "STR", 5));
        testDB.addSkillRule(new SkillRule("Bluff", "CHA", 5));
        testDB.addSkillRule(new SkillRule("Diplomacy", "CHA", 5));
        testDB.addSkillRule(new SkillRule("Dungeoneering", "WIS", 5));
        testDB.addSkillRule(new SkillRule("Endurance", "CON", 5));
        testDB.addSkillRule(new SkillRule("Heal", "WIS", 5));
        testDB.addSkillRule(new SkillRule("History", "INT", 5));
        testDB.addSkillRule(new SkillRule("Insight", "WIS", 5));
        testDB.addSkillRule(new SkillRule("Intimidate", "CHA", 5));
        testDB.addSkillRule(new SkillRule("Nature", "WIS", 5));
        testDB.addSkillRule(new SkillRule("Perception", "WIS", 5));
        testDB.addSkillRule(new SkillRule("Religion", "INT", 5));
        testDB.addSkillRule(new SkillRule("Stealth", "DEX", 5));
        testDB.addSkillRule(new SkillRule("Streetwise", "CHA", 5));
        testDB.addSkillRule(new SkillRule("Thievery", "DEX", 5));

        ddCharacter = new DDCharacter("Test", 1, "TestRace", "TestClass", testDB);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_character, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public DDCharacter getCharacter() {
        return ddCharacter;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
