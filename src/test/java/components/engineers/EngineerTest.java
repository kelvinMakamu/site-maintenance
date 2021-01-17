package components.engineers;

import components.data.DatabaseRule;
import components.sites.Site;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EngineerTest {

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void Engineer_instantiatesCorrectly_true(){
        Engineer testEngineer = setUpNewEngineer();
        assertTrue(testEngineer instanceof Engineer);
    }

    @Test
    public void getFirstName_engineerInstantiatesWithFirstName_kelvin(){
        Engineer testEngineer = setUpNewEngineer();
        assertEquals("kelvin",testEngineer.getFirstName());
    }

    @Test
    public void getLastName_engineerInstantiatesWithLastName_makamu(){
        Engineer testEngineer = setUpNewEngineer();
        assertEquals("makamu",testEngineer.getLastName());
    }

    @Test
    public void equals_comparesTwoEngineerObjects_true(){
        Engineer firstEngineer  = setUpNewEngineer();
        Engineer secondEngineer = setUpNewEngineer();
        assertTrue(firstEngineer.equals(secondEngineer));
    }

    @Test
    public void save_insertsEngineerObjectIntoDatabase(){
        Engineer testEngineer = setUpNewEngineer();
        testEngineer.save();
        assertTrue(Engineer.all().get(0).equals(testEngineer));
    }

    @Test
    public void save_assignsIdToInsertedEngineerObject(){
        Engineer testEngineer  = setUpNewEngineer();
        testEngineer.save();
        Engineer foundEngineer = Engineer.all().get(0);
        assertEquals(testEngineer.getId(),foundEngineer.getId());
    }

    @Test
    public void all_returnsAllSavedEngineerObjects(){
        Engineer firstEngineer   = setUpNewEngineer();
        firstEngineer.save();
        Engineer secondEngineer  = new Engineer("gerald","white");
        secondEngineer.save();
        assertTrue(Engineer.all().get(0).equals(firstEngineer));
        assertTrue(Engineer.all().get(1).equals(secondEngineer));
    }

    @Test
    public void find_queryEngineerObjectByItsId(){
        Engineer firstEngineer   = setUpNewEngineer();
        firstEngineer.save();
        Engineer secondEngineer  = new Engineer("gerald","white");
        secondEngineer.save();
        assertEquals(Engineer.find(secondEngineer.getId()),secondEngineer);
    }

    @Test
    public void update_updateEngineerDetails(){
        Engineer firstEngineer   = setUpNewEngineer();
        String initialFirstName  = firstEngineer.getFirstName();
        String initialLastName   = firstEngineer.getLastName();
        firstEngineer.save();
        int updateStatus         = firstEngineer.update("melvin","jones");
        Engineer updatedEngineer = Engineer.find(firstEngineer.getId());
        String updatedFirstName  = updatedEngineer.getFirstName();
        String updatedLastName   = updatedEngineer.getLastName();
        assertEquals(firstEngineer.getId(),updatedEngineer.getId());
        switch(updateStatus){
            case 1000:
            assertNotEquals(initialFirstName,updatedFirstName);
            assertNotEquals(initialLastName,updatedLastName);
            break;

            case 1002:
            assertNotEquals(initialFirstName,updatedFirstName);
            assertEquals(initialLastName,updatedLastName);
            break;

            case 1004:
            assertEquals(initialFirstName,updatedFirstName);
            assertNotEquals(initialLastName,updatedLastName);
            break;

            case 1001:
            assertEquals(initialFirstName,updatedFirstName);
            assertEquals(initialLastName,updatedLastName);
            break;
        }
    }

    @Test
    public void delete_deleteEngineerRecords_0(){
        Engineer engineer = setUpNewEngineer();
        engineer.delete();
        assertEquals(0, Engineer.all().size());
    }

    @Test
    public void deleteAll_deleteAllEngineerRecords_0(){
        Engineer firstEngineer   = setUpNewEngineer();
        firstEngineer.save();
        Engineer secondEngineer  = new Engineer("gerald","white");
        secondEngineer.save();
        Engineer.deleteAll();
        assertEquals(0,Engineer.all().size());
    }

    @Test
    public void assignSite_assignAnEngineerASite(){
        Engineer firstEngineer   = setUpNewEngineer();
        firstEngineer.save();
        Site firstSite           = setUpNewSite();
        //firstSite.save();
        // firstEngineer.assignSite(firstSite.getId());
        //Return Site By ID
        //assertTrue getAssignedSites contains foundSite
    }

    // HELPER METHOD
    public Engineer setUpNewEngineer(){
        return new Engineer("kelvin","makamu");
    }

    public Site setUpNewSite(){
        return new Site("amaboko","arusha");
    }
}