package dps924.ddcharactermanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.astuetz.PagerSlidingTabStrip;

import dps924.ddcharactermanager.characters.CharacterDatabase;
import dps924.ddcharactermanager.database.RuleDatabaseFactory;
import dps924.ddcharactermanager.database.SQLiteAssetDatabase;
import dps924.ddcharactermanager.rules.FeatRule;
import dps924.ddcharactermanager.rules.RaceRule;
import dps924.ddcharactermanager.rules.RuleDatabase;

public class CharacterActivity extends ActionBarActivity
        implements ProfileFragment.OnFragmentInteractionListener,
                   SkillsFragment.OnFragmentInteractionListener,
                   FeatsFragment.OnFragmentInteractionListener,
                   CharactersFragment.OnFragmentInteractionListener,
                   ItemsFragment.OnFragmentInteractionListener{
    private static final String TAG = CharacterActivity.class.getName();
    ViewPager viewPager;
    RuleDatabase ruleDatabase;
    CharacterDatabase charDB;
    DDCharacter ddCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_select);
        //Setup the ViewPager
        viewPager = (ViewPager) findViewById(R.id.characterSelect);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new CharactersFragment();
            }

            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Characters";
            }
        });

        //Bind tabs to the ViewPager
       /*
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
        */
        //TODO: Support for multiple possible databases
        SQLiteAssetDatabase sqlDb = new SQLiteAssetDatabase(this, "4eDB", 1);
        ruleDatabase = new RuleDatabaseFactory(sqlDb).getRuleDatabase();
        //TODO: Creating testing DB and character. Should be dynamic
        /*
        ruleDatabase.addRaceRule(new RaceRule("Human"));
        ruleDatabase.addRaceRule(new RaceRule("Dwarf"));
        ruleDatabase.addRaceRule(new RaceRule("Halfling"));
        */
        FeatRule feat1 = new FeatRule("Improved Stealth", "+5 feat bonus to Stealth checks");
        FeatRule feat2 = new FeatRule("Improved Streetwise", "+5 feat bonus to Streetwise checks");
        FeatRule feat3 = new FeatRule("Improved Thievery", "+5 feat bonus to Thievery checks");
        ruleDatabase.addFeatRule(feat1);
        ruleDatabase.addFeatRule(feat2);
        ruleDatabase.addFeatRule(feat3);
        charDB = new CharacterDatabase(ruleDatabase);
        /*
        ddCharacter = new DDCharacter("TestName", 3, "TestRace", "TestClass", ruleDatabase);
        ddCharacter.addFeat(feat1);
        ddCharacter.addFeat(feat2);
        ddCharacter.addFeat(feat3);
        */
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
    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    public void selectCharacter(DDCharacter c){
        //Changes content view, basically swaps to another fragment set.
        //Loses back function because of this.
        //Should replace with activity launch.
        ddCharacter = c;
        setContentView(R.layout.activity_character);
        viewPager = (ViewPager) findViewById(R.id.characterPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        //Bind tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.characterTabs);
        tabs.setViewPager(viewPager);
    }
    public CharacterDatabase getCharacterDatabase(){
        return charDB;
    }
}
