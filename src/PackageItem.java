/**
 * Copyright 2013 Gokila Rajaiah.  All rights reserved.  For internal use only.
 */
package com.vegas.interview.PackageMaker;

/**
 * Abstract PackageItem class with id, price & type value.
 * 
 * @author Gokila
 * 
 */
public abstract class PackageItem
{

	int	id;
	float price;
	PackageItemType type;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}
	
	public PackageItemType getType()
	{
		return type;
	}

	public void setType(PackageItemType type)
	{
		this.type = type;
	}
}
