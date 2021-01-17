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

    @Test
    public void update_updateSiteDetails(){
        Site firstSite       = setUpNewSite();
        String initialName   = firstSite.getName();
        String initialTown   = firstSite.getTown();
        firstSite.save();
        int updateStatus     = firstSite.update("beberu","nakuru");
        Site updatedSite     = Site.find(firstSite.getId());
        String updatedName   = updatedSite.getName();
        String updatedTown   = updatedSite.getTown();
        assertEquals(firstSite.getId(),updatedSite.getId());
        switch(updateStatus){
            case 1000:
            assertNotEquals(initialName,updatedName);
            assertNotEquals(initialTown,updatedTown);
            break;

            case 1002:
            assertNotEquals(initialName,updatedName);
            assertEquals(initialTown,updatedTown);
            break;

            case 1004:
            assertEquals(initialName,updatedName);
            assertNotEquals(initialTown,updatedTown);
            break;

            case 1001:
            assertEquals(initialName,updatedName);
            assertEquals(initialTown,updatedTown);
            break;
        }
    }

    @Test
    public void delete_deleteSiteRecords_0(){
        Site firstSite   = setUpNewSite();
        firstSite.save();
        firstSite.delete();
        assertEquals(0, Site.all().size());
    }

    @Test
    public void deleteAll_deleteAllSiteRecords_0(){
        Site firstSite   = setUpNewSite();
        firstSite.save();
        Site secondSite  = new Site("amagoro","kampala");
        secondSite.save();
        Site.deleteAll();
        assertEquals(0,Site.all().size());
    }

    @Test
    public void assignEngineer_assignAnEngineer(){
        Site firstSite           = setUpNewSite();
        firstSite.save();
        Engineer firstEngineer   = setUpNewEngineer();
        firstEngineer.save();
        firstSite.assignEngineer(firstEngineer.getId());
        //Return Engineer By ID
        Engineer foundEngineer   = Engineer.find(firstEngineer.getId());
        //assertTrue getAssignedEngineer contains foundEngineer
        assertTrue(firstSite.getAssignedEngineer().contains(foundEngineer));
    }

    @Test
    public void getAssignedEngineer_countEngineerAssignedToASite(){
        Site firstSite           = setUpNewSite();
        firstSite.save();
        Engineer firstEngineer   = setUpNewEngineer();
        firstEngineer.save();
        firstSite.assignEngineer(firstEngineer.getId());
        assertEquals(1, firstSite.getAssignedEngineer().size());
    }

    // HELPER METHOD
    public Site setUpNewSite(){
        return new Site("amaboko","arusha");
    }

    public Engineer setUpNewEngineer(){
        return new Engineer("kelvin","makamu");
    }

}