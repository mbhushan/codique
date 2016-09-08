package test.manib.dhaba;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.manib.dhaba.Item;
import com.manib.dhaba.Meal;
import com.manib.dhaba.VegBurger;

public class MealTest {

	private Meal meal;
	
	@Test
	public void testMealItem() {
		meal = new Meal();
		Item item = new VegBurger();
		meal.addItem(item);
		
		List<Item> items = meal.getItems();
		Item it = items.get(0);
		float actual  = it.price();
		Assert.assertEquals(25.50f, actual, 0.01f);
	}
}
