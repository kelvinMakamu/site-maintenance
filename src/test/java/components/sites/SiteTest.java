package components.sites;

import components.data.DatabaseRule;
import components.engineers.Engineer;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SiteTest {

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void Site_instantiatesCorrectly_true(){
        Site testSite = setUpNewSite();
        assertTrue(testSite instanceof Site);
    }

    @Test
    public void getName_siteInstantiatesWithName_amaboko(){
        Site testSite = setUpNewSite();
        assertEquals("amaboko",testSite.getName());
    }
    // HELPER METHOD
    public Site setUpNewSite(){
        return new Site("amaboko","arusha");
    }
}