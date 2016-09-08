package com.manib.dhaba;

import java.util.ArrayList;
import java.util.List;

public class Meal {

	private List<Item> itemList ;
	
	public Meal() {
		itemList = new ArrayList<Item>();
	}
	
	public void addItem(Item item) {
		itemList.add(item);
	}
	
	public float getCost() {
		float cost = 0.0f;
		
		for (Item i: itemList) {
			cost += i.price();
		}
		
		return cost;
	}
	
	public void showItems() {
		StringBuffer sb = new StringBuffer();
		
		for (Item i: itemList) {
			sb.append(i.name() + ", " + i.packing().pack() + ", "+ i.price() + ";\n");
		}
		System.out.println(sb.toString());
	}
}
