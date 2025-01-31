package coffeemaker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import coffeemaker.CoffeeMaker;
import coffeemaker.domain.Inventory;

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
	
}
