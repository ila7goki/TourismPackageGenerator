/**
 * Copyright 2013 Gokila Rajaiah.  All rights reserved.  For internal use only.
 */
package com.vegas.interview.PackageMaker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that used the PackageOrganizer's packageItem lists, minimum price,
 * maximum price & packageOrder. Instantiate this class by sending a
 * PackageOrganizer.
 * 
 * @author Gokila Rajaiah
 * 
 */
public class PackageMakerFactory
{
	PackageOrganizer	organizer;


	/**
	 * Initializes the PackageMakerFactory's organizer to access the organized
	 * PackageItemTypelists and packageOrder List.
	 * 
	 * @param organizer
	 */
	public PackageMakerFactory(PackageOrganizer organizer) {
		this.organizer = organizer;
	}

	/**
	 * Method that creates different packages with package price > minimumPrice
	 * and < maximumPrice.
	 * 
	 * @return List<Package>
	 */
	public List<Package> generatePackages()
	{
		List<PackageItemType> testType = new ArrayList<PackageItemType>();

		for (int i = 0; i < this.organizer.packageOrderList.size(); i++)
		{
			boolean checkMinPrice;

			if (i == this.organizer.packageOrderList.size() - 1)
			{
				checkMinPrice = true;
			}
			else
			{
				checkMinPrice = false;
			}

			PackageItemType itemType = this.organizer.packageOrderList.get(i);

			testType.add(itemType);

			List<PackageItem> pItemList;

			Combinations packageCombinations = null;

			if (itemType != PackageItemType.PACKAGE)
			{
				if (itemType == PackageItemType.HOTEL)
				{
					pItemList = new ArrayList<PackageItem>(
							this.organizer.hotelList);

					packageCombinations = new Combinations(
							this.organizer.packageList, pItemList,
							PackageOrganizer.minimumPrice,
							PackageOrganizer.maximumPrice, checkMinPrice);
				}
				else if (itemType == PackageItemType.SHOW)
				{
					pItemList = new ArrayList<PackageItem>(
							this.organizer.showList);

					packageCombinations = new Combinations(
							this.organizer.packageList, pItemList,
							PackageOrganizer.minimumPrice,
							PackageOrganizer.maximumPrice, checkMinPrice);
				}
				else if (itemType == PackageItemType.TOUR)
				{
					pItemList = new ArrayList<PackageItem>(
							this.organizer.tourList);

					packageCombinations = new Combinations(
							this.organizer.packageList, pItemList,
							PackageOrganizer.minimumPrice,
							PackageOrganizer.maximumPrice, checkMinPrice);
				}

				this.organizer.packageList = packageCombinations.combine();
			}
		}

		return this.organizer.packageList;
	}

	/**
	 * Method that sorts the packageListToSort by packageSortOrder. Passed
	 * packageListToSort will be the in the sorted order.
	 * 
	 * @param packageListToSort
	 * @param packageSortOrder
	 */
	public void sortPackagesByPackageOrder(List<Package> packageListToSort,
			List<PackageItemType> packageSortOrder)
	{
		Collections.sort(packageListToSort,
				new PackageComparator(
				packageSortOrder));
	}
}
