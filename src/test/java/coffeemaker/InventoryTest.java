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

    @AfterEach
    public void tearDown() {
		inventory = null;
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

    @Test
    void testUseTwoRecipes() {
        inventory.setCoffee(30);
        inventory.setMilk(30);
        inventory.setSugar(30);
        inventory.setChocolate(30);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("4");
        recipe.setAmtCoffee("3");
        recipe.setAmtMilk("2");
        recipe.setAmtSugar("1");

        assertTrue(inventory.useIngredients(recipe), "Enough ingredients");


        Recipe recipe2 = new Recipe();
        recipe2.setAmtChocolate("7");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("2");
        recipe2.setAmtSugar("5");

        assertTrue(inventory.useIngredients(recipe2), "Enough ingredients");
    }

    @Test
    void testRecipeTwice() {
        inventory.setCoffee(30);
        inventory.setMilk(30);
        inventory.setSugar(30);
        inventory.setChocolate(30);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("5");
        recipe.setAmtCoffee("5");
        recipe.setAmtMilk("5");
        recipe.setAmtSugar("5");

        assertTrue(inventory.useIngredients(recipe), "Enough ingredients");
        assertTrue(inventory.useIngredients(recipe), "Enough ingredients again");  
    }

    @Test
    void testIngredientsUsed() {
        inventory.setCoffee(30);
        inventory.setMilk(30);
        inventory.setSugar(30);
        inventory.setChocolate(30);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("5");
        recipe.setAmtCoffee("5");
        recipe.setAmtMilk("5");
        recipe.setAmtSugar("5");

        assertTrue(inventory.useIngredients(recipe), "Enough ingredients");
        
        assertEquals(25, inventory.getCoffee(), "Coffee should be 25");
        assertEquals(25, inventory.getMilk(), "Milk should be 25");
        assertEquals(25, inventory.getSugar(), "Sugar should be 25");
        assertEquals(25, inventory.getChocolate(), "Chocolate should be 25");
    }

    @Test
    void testIngredientsUsedTwice() {
        inventory.setCoffee(30);
        inventory.setMilk(30);
        inventory.setSugar(30);
        inventory.setChocolate(30);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("5");
        recipe.setAmtCoffee("5");
        recipe.setAmtMilk("5");
        recipe.setAmtSugar("5");

        assertTrue(inventory.useIngredients(recipe), "Enough ingredients");
        
        assertEquals(25, inventory.getCoffee(), "Coffee should be 25");
        assertEquals(25, inventory.getMilk(), "Milk should be 25");
        assertEquals(25, inventory.getSugar(), "Sugar should be 25");
        assertEquals(25, inventory.getChocolate(), "Chocolate should be 25");

        assertTrue(inventory.useIngredients(recipe), "Enough ingredients");
        
        assertEquals(20, inventory.getCoffee(), "Coffee should be 20");
        assertEquals(20, inventory.getMilk(), "Milk should be 20");
        assertEquals(20, inventory.getSugar(), "Sugar should be 20");
        assertEquals(20, inventory.getChocolate(), "Chocolate should be 20");
    }

    @Test
    void testUseTwoRecipesTwice() {
        inventory.setCoffee(30);
        inventory.setMilk(30);
        inventory.setSugar(30);
        inventory.setChocolate(30);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("4");
        recipe.setAmtCoffee("3");
        recipe.setAmtMilk("2");
        recipe.setAmtSugar("1");

        Recipe recipe2 = new Recipe();
        recipe2.setAmtChocolate("7");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("2");
        recipe2.setAmtSugar("5");

        assertTrue(inventory.useIngredients(recipe), "Enough ingredients");
        assertTrue(inventory.useIngredients(recipe2), "Enough ingredients");
        assertTrue(inventory.useIngredients(recipe), "Enough ingredients");
        assertTrue(inventory.useIngredients(recipe2), "Enough ingredients");

    }

    @Test
    void testIngredientsToSting() {
        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("10");
        recipe.setAmtCoffee("10");
        recipe.setAmtMilk("10");
        recipe.setAmtSugar("10");

        inventory.useIngredients(recipe);


        assertEquals("Coffee: 5\nMilk: 5\nSugar: 5\nChocolate: 5\n", inventory.toString(), "toString should be correct"); 

    }

    @Test
    void testIngredientsToStingZeroValues() {
        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("10");
        recipe.setAmtCoffee("10");
        recipe.setAmtMilk("10");
        recipe.setAmtSugar("10");

        Recipe recipe2 = new Recipe();

        recipe2.setAmtChocolate("5");
        recipe2.setAmtCoffee("5");
        recipe2.setAmtMilk("5");
        recipe2.setAmtSugar("5");


        inventory.useIngredients(recipe);
        inventory.useIngredients(recipe2);


        assertEquals("Coffee: 0\nMilk: 0\nSugar: 0\nChocolate: 0\n", inventory.toString(), "toString should be correct"); 

    }

    @Test
    void testUseTwoRecipesZero() {
        inventory.setCoffee(30);
        inventory.setMilk(30);
        inventory.setSugar(30);
        inventory.setChocolate(30);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("15");
        recipe.setAmtCoffee("15");
        recipe.setAmtMilk("15");
        recipe.setAmtSugar("15");

        assertTrue(inventory.useIngredients(recipe), "Enough ingredients");
        assertTrue(inventory.useIngredients(recipe), "Enough ingredients");

        assertEquals(0, inventory.getCoffee(), "Coffee should be 0");
        assertEquals(0, inventory.getMilk(), "Milk should be 0");
        assertEquals(0, inventory.getSugar(), "Sugar should be 0");
        assertEquals(0, inventory.getChocolate(), "Chocolate should be 0");
    }

    @Test
    void testUseTwoRecipesLeftovers() {
        inventory.setCoffee(30);
        inventory.setMilk(30);
        inventory.setSugar(30);
        inventory.setChocolate(30);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("15");
        recipe.setAmtCoffee("15");
        recipe.setAmtMilk("15");
        recipe.setAmtSugar("15");

        assertTrue(inventory.useIngredients(recipe), "Enough ingredients");

        assertEquals(15, inventory.getCoffee(), "Coffee should be 15");
        assertEquals(15, inventory.getMilk(), "Milk should be 15");
        assertEquals(15, inventory.getSugar(), "Sugar should be 15");
        assertEquals(15, inventory.getChocolate(), "Chocolate should be 15");
    }
    @Test
    void testUseTwoRecipesOneGoodOneBad() {
        inventory.setCoffee(30);
        inventory.setMilk(30);
        inventory.setSugar(30);
        inventory.setChocolate(30);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("15");
        recipe.setAmtCoffee("15");
        recipe.setAmtMilk("15");
        recipe.setAmtSugar("15");

        Recipe recipe2 = new Recipe();

        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("20");
        recipe2.setAmtMilk("20");
        recipe2.setAmtSugar("20");


        assertTrue(inventory.useIngredients(recipe), "Enough ingredients");
        assertFalse(inventory.useIngredients(recipe2), "Not Enough Ingredients");

        assertEquals(15, inventory.getChocolate());
        assertEquals(15, inventory.getCoffee());
        assertEquals(15, inventory.getMilk());
        assertEquals(15, inventory.getSugar());

    }
    @Test
    void testInventoryBadRecipe() {
        inventory.setCoffee(10);
        inventory.setMilk(10);
        inventory.setSugar(10);
        inventory.setChocolate(10);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("15");
        recipe.setAmtCoffee("15");
        recipe.setAmtMilk("15");
        recipe.setAmtSugar("15");

        assertFalse(inventory.useIngredients(recipe), "Not Enough Ingredients");

        assertEquals(10, inventory.getCoffee(), "Coffee should be 10");
        assertEquals(10, inventory.getMilk(), "Milk should be 10");
        assertEquals(10, inventory.getSugar(), "Sugar should be 10");
        assertEquals(10, inventory.getChocolate(), "Chocolate should be 10");
    }

    @Test
    void testUseThreeRecipes() {
        inventory.setCoffee(45);
        inventory.setMilk(45);
        inventory.setSugar(45);
        inventory.setChocolate(45);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("15");
        recipe.setAmtCoffee("15");
        recipe.setAmtMilk("15");
        recipe.setAmtSugar("15");

        Recipe recipe2 = new Recipe();

        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("20");
        recipe2.setAmtMilk("20");
        recipe2.setAmtSugar("20");

        Recipe recipe3 = new Recipe();

        recipe3.setAmtChocolate("10");
        recipe3.setAmtCoffee("10");
        recipe3.setAmtMilk("10");
        recipe3.setAmtSugar("10");

        assertTrue(inventory.useIngredients(recipe), "Enough ingredients");
        assertTrue(inventory.useIngredients(recipe2), "Enough ingredients");
        assertTrue(inventory.useIngredients(recipe3), "Enough ingredients");
       

        assertEquals(0, inventory.getChocolate());
        assertEquals(0, inventory.getCoffee());
        assertEquals(0, inventory.getMilk());
        assertEquals(0, inventory.getSugar());
    }

    @Test
    void testUseFourRecipes() {
        inventory.setCoffee(65);
        inventory.setMilk(65);
        inventory.setSugar(65);
        inventory.setChocolate(65);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("15");
        recipe.setAmtCoffee("15");
        recipe.setAmtMilk("15");
        recipe.setAmtSugar("15");

        Recipe recipe2 = new Recipe();

        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("20");
        recipe2.setAmtMilk("20");
        recipe2.setAmtSugar("20");

        Recipe recipe3 = new Recipe();

        recipe3.setAmtChocolate("5");
        recipe3.setAmtCoffee("5");
        recipe3.setAmtMilk("5");
        recipe3.setAmtSugar("5");

        Recipe recipe4 = new Recipe();

        recipe4.setAmtChocolate("25");
        recipe4.setAmtCoffee("25");
        recipe4.setAmtMilk("25");
        recipe4.setAmtSugar("25");

        assertTrue(inventory.useIngredients(recipe), "Enough ingredients");
        assertTrue(inventory.useIngredients(recipe2), "Enough ingredients");
        assertTrue(inventory.useIngredients(recipe3), "Enough ingredients");
        assertTrue(inventory.useIngredients(recipe4), "Enough ingredients");
       

        assertEquals(0, inventory.getChocolate());
        assertEquals(0, inventory.getCoffee());
        assertEquals(0, inventory.getMilk());
        assertEquals(0, inventory.getSugar());
    }
    
    @Test
    void testTwoBadRecipes() {
        inventory.setCoffee(10);
        inventory.setMilk(10);
        inventory.setSugar(10);
        inventory.setChocolate(10);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("15");
        recipe.setAmtCoffee("15");
        recipe.setAmtMilk("15");
        recipe.setAmtSugar("15");

        Recipe recipe2 = new Recipe();

        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("20");
        recipe2.setAmtMilk("20");
        recipe2.setAmtSugar("20");


        assertFalse(inventory.useIngredients(recipe), "Not Enough Ingredients");
        assertFalse(inventory.useIngredients(recipe2), "Not Enough Ingredients");

        assertEquals(10, inventory.getCoffee(), "Coffee should be 10");
        assertEquals(10, inventory.getMilk(), "Milk should be 10");
        assertEquals(10, inventory.getSugar(), "Sugar should be 10");
        assertEquals(10, inventory.getChocolate(), "Chocolate should be 10");
    }

    @Test
    void testThreeBadRecipes() {
        inventory.setCoffee(10);
        inventory.setMilk(10);
        inventory.setSugar(10);
        inventory.setChocolate(10);

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("15");
        recipe.setAmtCoffee("15");
        recipe.setAmtMilk("15");
        recipe.setAmtSugar("15");

        Recipe recipe2 = new Recipe();

        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("20");
        recipe2.setAmtMilk("20");
        recipe2.setAmtSugar("20");

        Recipe recipe3 = new Recipe();

        recipe3.setAmtChocolate("30");
        recipe3.setAmtCoffee("30");
        recipe3.setAmtMilk("30");
        recipe3.setAmtSugar("30");


        assertFalse(inventory.useIngredients(recipe), "Not Enough Ingredients");
        assertFalse(inventory.useIngredients(recipe2), "Not Enough Ingredients");
        assertFalse(inventory.useIngredients(recipe3), "Not Enough Ingredients");

        assertEquals(10, inventory.getCoffee(), "Coffee should be 10");
        assertEquals(10, inventory.getMilk(), "Milk should be 10");
        assertEquals(10, inventory.getSugar(), "Sugar should be 10");
        assertEquals(10, inventory.getChocolate(), "Chocolate should be 10");
    }

    @Test
    void testEmptyRecipe() {
        inventory.setCoffee(10);
        inventory.setMilk(10);
        inventory.setSugar(10);
        inventory.setChocolate(10);

        Recipe recipe = new Recipe();

        assertTrue(inventory.useIngredients(recipe), "empty recipe");
    }

    @Test
    void testEmptyAndGoodRecipe() {
        inventory.setCoffee(10);
        inventory.setMilk(10);
        inventory.setSugar(10);
        inventory.setChocolate(10);

        Recipe recipe = new Recipe();

        assertTrue(inventory.useIngredients(recipe), "empty recipe");

        recipe.setAmtChocolate("5");
        recipe.setAmtCoffee("5");
        recipe.setAmtMilk("5");
        recipe.setAmtSugar("5");

        assertTrue(inventory.useIngredients(recipe), "recipe used");
        
    }

    @Test
    void testEmptyBadAndGoodRecipe() {
        inventory.setCoffee(10);
        inventory.setMilk(10);
        inventory.setSugar(10);
        inventory.setChocolate(10);

        Recipe recipe = new Recipe();

        assertTrue(inventory.useIngredients(recipe), "empty recipe");

        recipe.setAmtChocolate("10");
        recipe.setAmtCoffee("10");
        recipe.setAmtMilk("10");
        recipe.setAmtSugar("10");

        assertTrue(inventory.useIngredients(recipe), "recipe used");

        Recipe recipe2 = new Recipe();

        recipe2.setAmtChocolate("10");
        recipe2.setAmtCoffee("10");
        recipe2.setAmtMilk("10");
        recipe2.setAmtSugar("10");

        assertFalse(inventory.useIngredients(recipe2));
  
    }
    
    @Test
    void testAmountsNotChanged() {
        inventory.setCoffee(10);
        inventory.setMilk(10);
        inventory.setSugar(10);
        inventory.setChocolate(10);

        Recipe recipe = new Recipe();

        assertTrue(inventory.useIngredients(recipe), "empty recipe");

        assertEquals(10, inventory.getCoffee(), "Coffee should be 10");
        assertEquals(10, inventory.getMilk(), "Milk should be 10");
        assertEquals(10, inventory.getSugar(), "Sugar should be 10");
        assertEquals(10, inventory.getChocolate(), "Chocolate should be 10");
        
    }
    

    @Test
    void testAddAndUse() {
        inventory.addChocolate("5");
        inventory.addCoffee("5");
        inventory.addSugar("5");
        inventory.addMilk("5");

        Recipe recipe = new Recipe();

        recipe.setAmtChocolate("10");
        recipe.setAmtCoffee("10");
        recipe.setAmtMilk("10");
        recipe.setAmtSugar("10");

        assertTrue(inventory.useIngredients(recipe));

        assertEquals(10, inventory.getChocolate());
        assertEquals(10, inventory.getCoffee());
        assertEquals(10, inventory.getMilk());
        assertEquals(10, inventory.getSugar());

    }
	
}
