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

    // HELPER METHOD
    public Engineer setUpNewEngineer(){
        return new Engineer("Kelvin","Makamu");
    }
}