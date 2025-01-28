package coffeemaker;

import coffeemaker.domain.Recipe;
import coffeemaker.exceptions.RecipeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecipeTest {

    @Test
    public void testGetAmtCoffeeDefault() {
        Recipe r = new Recipe();
        assertEquals(0, r.getAmtCoffee());
    }

    @Test
    public void testSetAmtCoffeePositive() {
        Recipe r = new Recipe();
        r.setAmtCoffee("5");
        assertEquals(5, r.getAmtCoffee());
    }

    @Test
    public void testSetAmtCoffeeNegative() {
        Recipe r = new Recipe();
        assertThrows(RecipeException.class, () -> r.setAmtCoffee("-5"));
    }

    @Test
    public void testSetAmtCoffeeInvalid() {
        Recipe r = new Recipe();
        r.setAmtCoffee("5");
        assertEquals(5, r.getAmtCoffee());
    }

}
