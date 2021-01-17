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
        Engineer engineer = new Engineer("Kelvin","Makamu");
        assertTrue(engineer instanceof Engineer);
    }

}