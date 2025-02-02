package coffeemaker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import coffeemaker.domain.Recipe;
import coffeemaker.domain.RecipeBook;

public class RecipeBookTest {

	/**
	 * Component under Test.
	 */
	private RecipeBook book;

    @BeforeEach
	public void setUp() {
		book = new RecipeBook();
	}

	@AfterEach
	public void tearDown() {
		book = null;
	}

	@Test
	@DisplayName("Add four recipes to the RecipeBook")
	public void addFourRecipes(){
		for(int i = 0; i < 4; i++){
			Recipe recipe = new Recipe();
			recipe.setName("test" + i);
			assertTrue(book.addRecipe(recipe));
		}
		Recipe[] recipeArray = book.getRecipes();
		assertEquals(recipeArray[0].getName(), "test0");
		assertEquals(recipeArray[3].getName(), "test3");
	}

	@Test
	@DisplayName("Add five recipes to the RecipeBook")
	public void addFiveRecipes(){
		for(int i = 0; i < 4; i++){
			Recipe recipe = new Recipe();
			recipe.setName("test" + i);
			assertTrue(book.addRecipe(recipe));
		}
		// fifth addition should fail
		assertFalse(book.addRecipe(new Recipe()));
	}

	@Test
	@DisplayName("Add five recipes to the RecipeBook with one duplicate")
	public void addFiveWithDuplicate(){
		assertTrue(book.addRecipe(new Recipe()));
		assertFalse(book.addRecipe(new Recipe()));
		for(int i = 0; i < 3; i++){
			Recipe recipe = new Recipe();
			recipe.setName("test" + i);
			assertTrue(book.addRecipe(recipe));
		}
	}

	@Test
	@DisplayName("Delete before adding recipes")
	public void deleteEmptyRecipe(){
		assertNull(book.deleteRecipe(0));
		assertNull(book.deleteRecipe(3));
	}

	@Test
	@DisplayName("Delete recipes in a full RecipeBook")
	public void deleteRecipes(){
		// populate RecipeBook
		for(int i = 0; i < 4; i++){
			Recipe recipe = new Recipe();
			recipe.setName("test" + i);
			book.addRecipe(recipe);
		}
		// delete recipes
		assertEquals(book.deleteRecipe(0), "test0");
		assertNull(book.deleteRecipe(0));
		assertEquals(book.deleteRecipe(3), "test3");
		assertNull(book.deleteRecipe(3));
	}

	@Test
	@DisplayName("Delete and add a recipe")
	public void deleteAndAdd(){
		// populate RecipeBook
		for(int i = 0; i < 4; i++){
			Recipe recipe = new Recipe();
			recipe.setName("test" + i);
			book.addRecipe(recipe);
		}
		// delete recipe
		assertEquals(book.deleteRecipe(1), "test1");
		Recipe newRecipe = new Recipe();
		newRecipe.setName("newRecipe");
		assertTrue(book.addRecipe(newRecipe));
	}

	@Test
	@DisplayName("Delete a recipe out of bounds")
	public void deleteOutOfBound(){
		assertNull(book.deleteRecipe(4));
		assertNull(book.deleteRecipe(-1));
	}

	@Test
	@DisplayName("Delete a recipe twice")
	public void deleteTwice(){
		Recipe recipe = new Recipe();
		recipe.setName("test");
		book.addRecipe(recipe);
		assertEquals(book.deleteRecipe(0), "test");
		assertNull(book.deleteRecipe(0));
	}

	@Test
	@DisplayName("Replace an empty recipe")
	public void replaceEmptyRecipe(){
		assertNull(book.replaceRecipe(0, new Recipe()));
	}

	@Test
	@DisplayName("Replace recipes in a full RecipeBook")
	public void replaceFullRecipeBook(){
		// populate RecipeBook
		for(int i = 0; i < 4; i++){
			Recipe recipe = new Recipe();
			recipe.setName("test" + i);
			book.addRecipe(recipe);
		}
		// create replacement recipes
		Recipe replacementRecipe1 = new Recipe();
		replacementRecipe1.setName("replace1");
		Recipe replacementRecipe2 = new Recipe();
		replacementRecipe2.setName("replace2");
		// replace recipes
		assertEquals(book.replaceRecipe(0, replacementRecipe1), "test0");
		assertEquals(book.replaceRecipe(3, replacementRecipe2), "test3");
		// test if recipes have been properly replaced
		Recipe[] recipeArray = book.getRecipes();
		assertTrue(replacementRecipe1.equals(recipeArray[0]));
		assertTrue(replacementRecipe2.equals(recipeArray[3]));
	}

	@Test
	@DisplayName("Replace a recipe out of bounds")
	public void replaceOutOfBound(){
		book.replaceRecipe(4, new Recipe());
		book.replaceRecipe(-1, new Recipe());
	}

	@Test
	@DisplayName("Replace a recipe twice")
	public void replaceTwice(){
		// set up recipes
		Recipe firstRecipe = new Recipe();
		Recipe replacementRecipe = new Recipe();
		firstRecipe.setName("first");
		replacementRecipe.setName("replacement");
		// test replacement
		book.addRecipe(firstRecipe);
		assertEquals(book.replaceRecipe(0, replacementRecipe), "first");
		assertEquals(book.replaceRecipe(0, new Recipe()), "replacement");
	}
}
