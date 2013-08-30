/**
 * Copyright 2013 Gokila Rajaiah.  All rights reserved.  For internal use only.
 */
package com.vegas.interview.PackageMaker;

import java.util.Comparator;
import java.util.List;

/**
 * Comparator that is used to compare values in the sorting order.
 * 
 * @author Gokila
 * 
 */
public class PackageComparator implements Comparator<Package>
{
	
	List<PackageItemType> packageTypesToCompare;
	
	public PackageComparator(List<PackageItemType> packageTypes) 
	{
		this.packageTypesToCompare =  packageTypes;
	}

	@Override
	public int compare(Package left, Package right)
	{
		int comparisonResult = 0;
		
		for(int i =0; i < packageTypesToCompare.size(); i++)
		{
			comparisonResult = this.compareOnPackageType(packageTypesToCompare.get(i), left, right);
			
			if(comparisonResult != 0)
			{
				return comparisonResult;
			}
		}
		
		return comparisonResult;
	}
	
	private int compareOnPackageType(PackageItemType packageType, Package left, Package right)
	{
		if(packageType.equals(PackageItemType.PACKAGE))
		{
			Float leftValue = left.totalPrice;
			Float rightValue = right.totalPrice;
			
			return leftValue.compareTo(rightValue);
		}
		else if(packageType.equals(PackageItemType.HOTEL))
		{
			Float leftValue = left.hotel.getPrice();
			Float rightValue = right.hotel.getPrice();
			
			return leftValue.compareTo(rightValue);
		}
		else if(packageType.equals(PackageItemType.SHOW))
		{
			Float leftValue = left.show.getPrice();
			Float rightValue = right.show.getPrice();
			
			return leftValue.compareTo(rightValue);
		}
		else if(packageType.equals(PackageItemType.TOUR))
		{
			Float leftValue = left.tour.getPrice();
			Float rightValue = right.tour.getPrice();
			
			return leftValue.compareTo(rightValue);
		}
		
		return 0;
	}
}
