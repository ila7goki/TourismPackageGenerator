/**
 * Copyright 2013 Gokila Rajaiah.  All rights reserved.  For internal use only.
 */
package com.vegas.interview.PackageMaker;

import com.vegas.interview.PackageMaker.PackageItemTypes.Hotel;
import com.vegas.interview.PackageMaker.PackageItemTypes.Show;
import com.vegas.interview.PackageMaker.PackageItemTypes.Tour;

/**
 * Represents the Package object with totalPrice, & different types of package
 * items.
 * 
 * @author Gokila
 * 
 */
public class Package
{
	float totalPrice;
	Hotel hotel;
	Show show;
	Tour tour;
	
	public void copyFieldsInto(Package other)
	{
		other.totalPrice = this.totalPrice;
		other.hotel = this.hotel;
		other.show = this.show;
		other.tour = this.tour;
	}
	
	public float getTotalPrice()
	{
		return totalPrice;
	}
	
	public void setTotalPrice(float totalPrice)
	{
		this.totalPrice = totalPrice;
	}
	
	public Hotel getHotel()
	{
		return hotel;
	}
	
	public void setHotel(Hotel hotel)
	{
		this.hotel = hotel;
	}
	
	public Show getShow()
	{
		return show;
	}
	public void setShow(Show show)
	{
		this.show = show;
	}
	
	public Tour getTour()
	{
		return tour;
	}
	
	public void setTour(Tour tour)
	{
		this.tour = tour;
	}
}
