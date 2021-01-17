package components.engineers;

import components.data.DatabaseRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EngineerTest {

    @Rule
    DatabaseRule databaseRule = new DatabaseRule();

    @Test
    public void Engineer_instantiatesCorrectly_true(){
        Engineer testEngineer = setUpNewEngineer();
        assertTrue(testEngineer instanceof Engineer);
    }

    // HELPER METHOD
    public Engineer setUpNewEngineer(){
        return new Engineer("Kelvin","Makamu");
    }
}