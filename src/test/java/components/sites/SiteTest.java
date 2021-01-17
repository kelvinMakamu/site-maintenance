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

    @Test
    public void getTown_siteInstantiatesWithTown_arusha(){
        Site testSite = setUpNewSite();
        assertEquals("arusha",testSite.getTown());
    }

    @Test
    public void equals_comparesTwoSiteObjects_true(){
        Site firstSite  = setUpNewSite();
        Site secondSite = setUpNewSite();
        assertTrue(firstSite.equals(secondSite));
    }

    @Test
    public void save_insertsSiteObjectIntoDatabase(){
        Site firstSite  = setUpNewSite();
        firstSite.save();
        assertTrue(Site.all().get(0).equals(firstSite));
    }

    @Test
    public void save_assignsIdToInsertedSiteObject(){
        Site firstSite  = setUpNewSite();
        firstSite.save();
        Site foundSite = Site.all().get(0);
        assertEquals(firstSite.getId(),foundSite.getId());
    }

    @Test
    public void all_returnsAllSavedSiteObjects(){
        Site firstSite   = setUpNewSite();
        firstSite.save();
        Site secondSite  = new Site("amagoro","kampala");
        secondSite.save();
        assertTrue(Site.all().get(0).equals(firstSite));
        assertTrue(Site.all().get(1).equals(secondSite));
    }

    @Test
    public void find_querySiteObjectByItsId(){
        Site firstSite   = setUpNewSite();
        firstSite.save();
        Site secondSite  = new Site("amagoro","kampala");
        secondSite.save();
        assertEquals(Site.find(secondSite.getId()),secondSite);
    }

    // HELPER METHOD
    public Site setUpNewSite(){
        return new Site("amaboko","arusha");
    }
}