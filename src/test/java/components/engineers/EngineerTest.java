package components.engineers;

import components.data.DatabaseRule;
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
    public void getFirstName_engineerInstantiatesWithFirstName_Kelvin(){
        Engineer testEngineer = setUpNewEngineer();
        assertEquals("Kelvin",testEngineer.getFirstName());
    }

    @Test
    public void getLastName_engineerInstantiatesWithLastName_Makamu(){
        Engineer testEngineer = setUpNewEngineer();
        assertEquals("Makamu",testEngineer.getLastName());
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
        Engineer secondEngineer  = setUpNewEngineer();
        secondEngineer.save();
        assertTrue(Engineer.all().get(0).equals(firstEngineer));
        assertTrue(Engineer.all().get(1).equals(secondEngineer));
    }
    // HELPER METHOD
    public Engineer setUpNewEngineer(){
        return new Engineer("Kelvin","Makamu");
    }
}