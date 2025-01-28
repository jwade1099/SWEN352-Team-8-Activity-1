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
        assertEquals(0, inventory.getCoffee(), "Default coffee should be 0");
        assertEquals(0, inventory.getMilk(), "Default milk should be 0");
        assertEquals(0, inventory.getSugar(), "Default sugar should be 0");
        assertEquals(0, inventory.getChocolate(), "Default chocolate should be 0");
    }

	@Test
    void testAddIngredients() {
        inventory.addCoffee("4");
        inventory.addMilk("3");
        inventory.addSugar("8");
        inventory.addChocolate("3");
        
        assertEquals(4, inventory.getCoffee(), "Coffee should be 4");
        assertEquals(3, inventory.getMilk(), "Milk should be 3");
        assertEquals(8, inventory.getSugar(), "Sugar should be 8");
        assertEquals(3, inventory.getChocolate(), "Chocolate should be 3");
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
	
}
