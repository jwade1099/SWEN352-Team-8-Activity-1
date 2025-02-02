package coffeemaker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import coffeemaker.CoffeeMaker;
import coffeemaker.domain.Inventory;
import coffeemaker.domain.Recipe;
import coffeemaker.exceptions.InventoryException;

public class InventoryTest {
 
	private Inventory inventory;

	@BeforeEach
	public void setUp() {
		inventory = new Inventory();
	}

	@Test
	 void testEmptyInventory() {
        assertNotNull(inventory, "Inventory should be created");
        assertEquals(15, inventory.getCoffee(), "Default coffee starts at 15");
        assertEquals(15, inventory.getMilk(), "Default milk starts at 15");
        assertEquals(15, inventory.getSugar(), "Default sugar starts at 15");
        assertEquals(15, inventory.getChocolate(), "Default chocolate starts at 15");
    }

	@Test
    void testAddIngredients() {
        inventory.addCoffee("4");
        inventory.addMilk("3");
        inventory.addSugar("8");
        inventory.addChocolate("3");
        
        assertEquals(19, inventory.getCoffee(), "Coffee should be 19");
        assertEquals(18, inventory.getMilk(), "Milk should be 18");
        assertEquals(23, inventory.getSugar(), "Sugar should be 23");
        assertEquals(18, inventory.getChocolate(), "Chocolate should be 18");
    }

    @AfterEach
    public void tearDown() {
		inventory = null;
	}


    @Test
    void testSetIngredients() {
        inventory.setCoffee(5);
        inventory.setMilk(7);
        inventory.setSugar(9);
        inventory.setChocolate(11);

        assertEquals(5, inventory.getCoffee(), "Coffee should be 5");
        assertEquals(7, inventory.getMilk(), "Milk should be 7");
        assertEquals(9, inventory.getSugar(), "Sugar should be 9");
        assertEquals(11, inventory.getChocolate(), "Chocolate should be 11 yummy");
    }

    @Test
    void testToString() {
        assertEquals("Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n", inventory.toString(), "toString should be correct"); 
    }


    @Test
    void testBadInput() {
        assertThrows(InventoryException.class, () -> inventory.addCoffee("a"));
        assertThrows(InventoryException.class, () -> inventory.addMilk("b"));
        assertThrows(InventoryException.class, () -> inventory.addSugar("c"));
        assertThrows(InventoryException.class, () -> inventory.addChocolate("d"));
    }
    
    @Test
    void testUseIngredient() {
        inventory.setCoffee(5);
        inventory.setMilk(7);
        inventory.setSugar(9);
        inventory.setChocolate(11);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("4");
        recipe.setAmtCoffee("3");
        recipe.setAmtMilk("2");
        recipe.setAmtSugar("1");

        assertTrue(inventory.useIngredients(recipe), "Enough ingredients");
    }

    @Test
    void testNotEnoughIngredienst() {
        inventory.setCoffee(1);
        inventory.setMilk(1);
        inventory.setSugar(1);
        inventory.setChocolate(11);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("23");
        recipe.setAmtCoffee("3");
        recipe.setAmtMilk("2");
        recipe.setAmtSugar("1");

        assertFalse(inventory.useIngredients(recipe), "Not enough ingredients");
    }


    @Test
    void testNegativeNumber() {
        assertThrows(InventoryException.class, () -> inventory.addCoffee("-1"));
        assertThrows(InventoryException.class, () -> inventory.addMilk("-1"));
        assertThrows(InventoryException.class, () -> inventory.addSugar("-1"));
        assertThrows(InventoryException.class, () -> inventory.addChocolate("-1"));
    }


    @Test
    void testSugarProblem() {
        inventory.setCoffee(5);
        inventory.setMilk(7);
        inventory.setSugar(1);
        inventory.setChocolate(11);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("4");
        recipe.setAmtCoffee("3");
        recipe.setAmtMilk("2");
        recipe.setAmtSugar("5");

        assertFalse(inventory.useIngredients(recipe), "Not Enough Sugar");
        
    }


    @Test
    void testBadSet() {
        inventory.setCoffee(-1);
        inventory.setSugar(-1);
        inventory.setMilk(-1);
        inventory.setChocolate(-1);
        assertEquals(15, inventory.getCoffee(), "Coffee should be 15");
        assertEquals(15, inventory.getMilk(), "Milk should be 15");
        assertEquals(15, inventory.getSugar(), "Sugar should be 15");
        assertEquals(15, inventory.getCoffee(), "coffee is 15");
    }
    
    
    
	
}
