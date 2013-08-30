/**
 * Copyright 2013 Gokila Rajaiah.  All rights reserved.  For internal use only.
 */
package com.vegas.interview.PackageMaker;

import java.util.ArrayList;
import java.util.List;

import com.vegas.interview.PackageMaker.PackageItemTypes.Hotel;
import com.vegas.interview.PackageMaker.PackageItemTypes.Show;
import com.vegas.interview.PackageMaker.PackageItemTypes.Tour;

/**
 * Class that combines two type of packageItem lists into one single Package
 * list.
 * 
 * @author Gokila Rajaiah
 * 
 */
public class Combinations
{
	List<Package> packageList;

	List<PackageItem> packageItemList;

	List<Package> combinedPackageList;

	float minimumPackagePrice;

	float maximumPackagePrice;

	boolean checkMinimumPrice;

	public Combinations(List<Package> packageList,
			List<PackageItem> packageItemList, float minimumPrice,
			float maximumPrice, boolean checkMinPrice) {
		this.packageList = packageList;
		this.packageItemList = packageItemList;
		this.minimumPackagePrice = minimumPrice;
		this.maximumPackagePrice = maximumPrice;
		this.checkMinimumPrice = checkMinPrice;
	}

	/**
	 * Method that combines the two item type list to one package list
	 * 
	 * @return List<Package>
	 */
	public List<Package> combine()
	{
		this.combinedPackageList = new ArrayList<Package>();

		if (this.packageList.size() == 0)
		{
			for (PackageItem item : this.packageItemList)
			{
				int size = this.packageItemList.size();

				if ((size == 1 && item.price >= minimumPackagePrice && item.price <= maximumPackagePrice)
						|| size > 1)
				{
					Package newPackage = new Package();

					newPackage.setTotalPrice(item.price);

					if (item.type == PackageItemType.HOTEL)
					{
						newPackage.setHotel((Hotel) item);
					}
					else if (item.type == PackageItemType.SHOW)
					{
						newPackage.setShow((Show) item);
					}
					else if (item.type == PackageItemType.TOUR)
					{
						newPackage.setTour((Tour) item);
					}

					this.combinedPackageList.add(newPackage);
				}
			}
		}
		else
		{
			for (Package combinedListItem : this.packageList)
			{
				for (PackageItem item : this.packageItemList)
				{
					float totalPackagePrice = combinedListItem.getTotalPrice()
							+ item.getPrice();

					Package newPackage = new Package();
					combinedListItem.copyFieldsInto(newPackage);

					if ((this.checkMinimumPrice && totalPackagePrice >= minimumPackagePrice)
							|| !this.checkMinimumPrice)
					{
						if (totalPackagePrice <= maximumPackagePrice)
						{
							newPackage.setTotalPrice(totalPackagePrice);

							if (item.type == PackageItemType.HOTEL)
							{
								newPackage.setHotel((Hotel) item);
							}
							else if (item.type == PackageItemType.SHOW)
							{
								newPackage.setShow((Show) item);
							}
							else if (item.type == PackageItemType.TOUR)
							{
								newPackage.setTour((Tour) item);
							}

							this.combinedPackageList.add(newPackage);
						}
					}

				}
			}
		}

		return this.combinedPackageList;
	}

	public List<Package> getPackageList()
	{
		return packageList;
	}

	public List<PackageItem> getPackageItemList()
	{
		return packageItemList;
	}
}
