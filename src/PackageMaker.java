/**
 * Copyright 2013 Gokila Rajaiah.  All rights reserved.  For internal use only.
 */
package com.vegas.interview.PackageMaker;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class, can also be called as client class that uses PackageOrganizer,
 * PackageMakerFactory & Display to create various packages with the given
 * package item types.
 * 
 * @author Gokila
 * 
 */
public class PackageMaker
{
	public static void main(String[] args)
	{
		List<Package> packageList = new ArrayList<Package>();

		String inputFileName;

		String packageOrder;

		String outputFileName;

		float minimumPrice;

		float maximumPrice;

		inputFileName = args[0];
		outputFileName = args[4];
		minimumPrice = Float.parseFloat(args[1]);
		maximumPrice = Float.parseFloat(args[2]);
		packageOrder = args[3];

		PackageOrganizer organizer = PackageOrganizer.organizer(inputFileName,
				packageOrder, minimumPrice, maximumPrice);
		
		if (organizer != null)
		{
			PackageMakerFactory factory = new PackageMakerFactory(organizer);

			packageList = factory.generatePackages();

			factory.sortPackagesByPackageOrder(packageList,
					organizer.packageOrderList);

			Display display = new Display(packageList);

			display.displayAsFile(outputFileName);
		}
	}
}
