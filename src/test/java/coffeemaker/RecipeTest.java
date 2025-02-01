package coffeemaker;

import coffeemaker.domain.Recipe;
import coffeemaker.exceptions.RecipeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertThrows(RecipeException.class, () -> r.setAmtCoffee("not a number"));
        assertEquals(0, r.getAmtCoffee());
    }

    @Test
    public void testGetAmtMilkDefault() {
        Recipe r = new Recipe();
        assertEquals(0, r.getAmtMilk());
    }

    @Test
    public void testSetAmtMilkPositive() {
        Recipe r = new Recipe();
        r.setAmtMilk("5");
        assertEquals(5, r.getAmtMilk());
    }

    @Test
    public void testSetAmtMilkNegative() {
        Recipe r = new Recipe();
        assertThrows(RecipeException.class, () -> r.setAmtMilk("-5"));
    }

    @Test
    public void testSetAmtMilkInvalid() {
        Recipe r = new Recipe();
        assertThrows(RecipeException.class, () -> r.setAmtMilk("not a number"));
        assertEquals(0, r.getAmtMilk());
    }

    @Test
    public void testGetAmtSugarDefault() {
        Recipe r = new Recipe();
        assertEquals(0, r.getAmtSugar());
    }

    @Test
    public void testSetAmtSugarPositive() {
        Recipe r = new Recipe();
        r.setAmtSugar("5");
        assertEquals(5, r.getAmtSugar());
    }

    @Test
    public void testSetAmtSugarNegative() {
        Recipe r = new Recipe();
        assertThrows(RecipeException.class, () -> r.setAmtSugar("-5"));
    }

    @Test
    public void testSetAmtSugarInvalid() {
        Recipe r = new Recipe();
        assertThrows(RecipeException.class, () -> r.setAmtSugar("not a number"));
    }

    @Test
    public void testChocolateDefault() {
        Recipe r = new Recipe();
        assertEquals(0, r.getAmtChocolate());
    }

    @Test
    public void testSetAmtChocolatePositive() {
        Recipe r = new Recipe();
        r.setAmtChocolate("5");
        assertEquals(5, r.getAmtChocolate());
    }

    @Test
    public void testSetAmtChocolateNegative() {
        Recipe r = new Recipe();
        assertThrows(RecipeException.class, () -> r.setAmtChocolate("-5"));
    }

    @Test
    public void testSetAmtChocolateInvalid() {
        Recipe r = new Recipe();
        assertThrows(RecipeException.class, () -> r.setAmtChocolate("not a number"));
    }

    @Test
    public void testPriceDefault() {
        Recipe r = new Recipe();
        assertEquals(0, r.getPrice());
    }

    @Test
    public void testSetPricePositive() {
        Recipe r = new Recipe();
        r.setPrice("5");
        assertEquals(5, r.getPrice());
    }

    @Test
    public void testSetPriceNegative() {
        Recipe r = new Recipe();
        assertThrows(RecipeException.class, () -> r.setPrice("-5"));
    }

    @Test
    public void testSetPriceInvalid() {
        Recipe r = new Recipe();
        assertThrows(RecipeException.class, () -> r.setPrice("not a number"));
    }

    @Test
    public void testSetNameEmptyString() {
        Recipe r = new Recipe();
        assertThrows(RecipeException.class, () -> r.setName(""));
    }

    @Test
    public void testSetNameBlank() {
        Recipe r = new Recipe();
        assertThrows(RecipeException.class, () -> r.setName(" "));
    }

    @Test
    public void testToString() {
        Recipe r = new Recipe();
        r.setName("Coffee");
        assertEquals("Recipe{Coffee}", r.toString());
    }

    @Test
    public void testToStringBlank() {
        Recipe r = new Recipe();
        assertEquals("Recipe{}", r.toString());
    }


    /*
    hashCode and equals need to correspond together, and they need to be equal
    hahcode needs to e derrived by the equality of the law of equiv
    It must have a non-null String name and non-negative price and ingredient amounts.
    */
    @Test
    public void testHashCode() {
        Recipe r1 = new Recipe();
        r1.setName("Coffee");
        int hashCode = 31 * 1 + "Coffee".hashCode();

        assertEquals(r1.hashCode(), hashCode);
    }

    @Test
    public void testHashEquivalence() {
        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();
        r1.setName("Coffee");
        r2.setName("Coffee");
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    public void testEqualsSameObj() {
        Recipe r1 = new Recipe();
        r1.setName("Coffee");
        assertEquals(r1, r1);
    }

    @Test
    public void testEqualsToNullIsFalse() {
        Recipe r1 = new Recipe();
        r1.setName("Coffee");
        assertNotEquals(r1, null);
    }

    @Test
    public void testEqualsToDifferentObjFalse() {
        Recipe r1 = new Recipe();
        r1.setName("Coffee");
        assertNotEquals(r1, "not a recipe");
    }

    static class SpecialRecipe extends Recipe {
        public SpecialRecipe() {
            super();
        }
    }

    /**
     * This test is testing for some Lava Flow type antipatterns within
     * the class itself. The Recipe equivalence has some haste code that checks
     * for exact class equivalence which could cause issues for inheritance
     */
    @Test
    public void testInheritenceEquivalence() {
        Recipe special = new SpecialRecipe();
        Recipe normal = new Recipe();
        special.setName("Coffee");
        normal.setName("Coffee");
        assertEquals(special, normal);
    }

    /**
     * Testing this method for the equivalence of the Recipe class when using the specialtyrecipe
     * class to ensure that hashcodes are still generated and equal
     */
    @Test
    public void testHashCodeSpecialtyEquivalence() {
        Recipe r1 = new SpecialRecipe();
        Recipe r2 = new Recipe();
        r1.setName("Coffee");
        r2.setName("Coffee");
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    public void testNameEquivalenceFalse() {
        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();
        r1.setName("Coffee");
        r2.setName("Tea");
        assertNotEquals(r1, r2);
    }

    @Test
    public void testGetName() {
        Recipe r = new Recipe();
        r.setName("Coffee");
        assertEquals("Coffee", r.getName());
    }

    @Test
    public void testNullNameDoesntChange() {
        Recipe r = new Recipe();
        r.setName("Coffee");
        assertEquals("Coffee", r.getName());
    }

    @Test
    public void testConstructor() {
        Recipe recipe = new Recipe();
        assertEquals("", recipe.getName());
        assertEquals(0, recipe.getPrice());
        assertEquals(0, recipe.getAmtCoffee());
        assertEquals(0, recipe.getAmtMilk());
        assertEquals(0, recipe.getAmtSugar());
        assertEquals(0, recipe.getAmtChocolate());
    }
}

