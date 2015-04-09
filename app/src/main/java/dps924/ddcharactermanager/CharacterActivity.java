package dps924.ddcharactermanager;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import dps924.ddcharactermanager.database.RuleDatabaseFactory;
import dps924.ddcharactermanager.database.SQLiteAssetDatabase;
import dps924.ddcharactermanager.rules.AbilityRule;
import dps924.ddcharactermanager.rules.RaceRule;
import dps924.ddcharactermanager.rules.RuleDatabase;
import dps924.ddcharactermanager.rules.SkillRule;

public class CharacterActivity extends ActionBarActivity
        implements ProfileFragment.OnFragmentInteractionListener,
                   SkillsFragment.OnFragmentInteractionListener {
    private static final String TAG = CharacterActivity.class.getName();
    ViewPager viewPager;
    //DEBUGGING
    RuleDatabase ruleDatabase;
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
        //Change title bar based on the page being viewed
        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){}
            public void onPageSelected(int position) {
                getSupportActionBar().setTitle(viewPager.getAdapter().getPageTitle(position));
            }
        });
        //Set title bar to the first view title
        getSupportActionBar().setTitle(viewPager.getAdapter().getPageTitle(0));
        //TODO: Support for multiple possible databases
        SQLiteAssetDatabase sqlDb = new SQLiteAssetDatabase(this, "TestDB", 1);
        ruleDatabase = new RuleDatabaseFactory(sqlDb).getRuleDatabase();
        //TODO: Creating testing DB and character. Should be dynamic
        /*
        ruleDatabase.addAbilityRule(new AbilityRule("Strength", "STR", ""));
        ruleDatabase.addAbilityRule(new AbilityRule("Constitution", "CON", ""));
        ruleDatabase.addAbilityRule(new AbilityRule("Dexterity", "DEX", ""));
        ruleDatabase.addAbilityRule(new AbilityRule("Intelligence", "INT", ""));
        ruleDatabase.addAbilityRule(new AbilityRule("Wisdom", "WIS", ""));
        ruleDatabase.addAbilityRule(new AbilityRule("Charisma", "CHA", ""));
    */
        ruleDatabase.addSkillRule(new SkillRule("Acrobatics", "DEX", 5));
        ruleDatabase.addSkillRule(new SkillRule("Arcana", "INT", 5));
        ruleDatabase.addSkillRule(new SkillRule("Athletics", "STR", 5));
        ruleDatabase.addSkillRule(new SkillRule("Bluff", "CHA", 5));
        ruleDatabase.addSkillRule(new SkillRule("Diplomacy", "CHA", 5));
        ruleDatabase.addSkillRule(new SkillRule("Dungeoneering", "WIS", 5));
        ruleDatabase.addSkillRule(new SkillRule("Endurance", "CON", 5));
        ruleDatabase.addSkillRule(new SkillRule("Heal", "WIS", 5));
        ruleDatabase.addSkillRule(new SkillRule("History", "INT", 5));
        ruleDatabase.addSkillRule(new SkillRule("Insight", "WIS", 5));
        ruleDatabase.addSkillRule(new SkillRule("Intimidate", "CHA", 5));
        ruleDatabase.addSkillRule(new SkillRule("Nature", "WIS", 5));
        ruleDatabase.addSkillRule(new SkillRule("Perception", "WIS", 5));
        ruleDatabase.addSkillRule(new SkillRule("Religion", "INT", 5));
        ruleDatabase.addSkillRule(new SkillRule("Stealth", "DEX", 5));
        ruleDatabase.addSkillRule(new SkillRule("Streetwise", "CHA", 5));
        ruleDatabase.addSkillRule(new SkillRule("Thievery", "DEX", 5));

        ruleDatabase.addRaceRule(new RaceRule("Human"));
        ruleDatabase.addRaceRule(new RaceRule("Dwarf"));
        ruleDatabase.addRaceRule(new RaceRule("Halfling"));

        ddCharacter = new DDCharacter("TestName", 3, "TestRace", "TestClass", ruleDatabase);

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
