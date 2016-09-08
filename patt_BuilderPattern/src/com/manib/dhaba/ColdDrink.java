package com.manib.dhaba;

public abstract class ColdDrink implements Item {

	@Override
	public abstract float price();

	@Override
	public Packing packing() {
		return new Bottle();
	}

}
