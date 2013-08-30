/**
 * Copyright 2013 Gokila Rajaiah.  All rights reserved.  For internal use only.
 */
package com.vegas.interview.PackageMaker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Class that displays the PackageList output in the desired format.
 * 
 * @author Gokila Rajaiah
 * 
 */
public class Display
{
	List<Package>	listToDisplay;

	/**
	 * Creates a new Display object with the packageListToDisplay.
	 * 
	 * @param packageListToDisplay
	 */
	public Display(List<Package> packageListToDisplay) {

		this.setListToDisplay(packageListToDisplay);

	}

	/**
	 * Outputs the list in a file with the fileName in the same working
	 * directory.
	 * 
	 * @param fileName
	 */
	public void displayAsFile(String fileName)
	{
		try
		{
			if (!fileName.isEmpty())
			{
				File file = new File(fileName);

				if (!file.exists())
				{
					file.createNewFile();
				}

				FileWriter fileWriter = null;

				fileWriter = new FileWriter(file.getAbsoluteFile());

				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				for (Package item : listToDisplay)
				{
					DecimalFormat df = new DecimalFormat("0.00##");

					String show = item.show != null ? "SHOW\t"
							+ item.show.getId() + "\t"
							+ df.format(item.show.getPrice()) + "\t" : "";
					String hotel = item.hotel != null ? "HOTEL\t"
							+ item.hotel.getId() + "\t"
							+ df.format(item.hotel.getPrice()) + "\t" : "";
					String tour = item.tour != null ? "TOUR\t"
							+ item.tour.getId() + "\t"
							+ df.format(item.tour.getPrice()) + "\t" : "";

					String content = "PACKAGE\t" + df.format(item.totalPrice)
							+ "\t" + hotel + show + tour + "\n";

					bufferedWriter.write(content);

				}

				bufferedWriter.close();
			}
			else
			{
				System.out.println("Filename is empty");
			}

		}
		catch (IOException e)
		{
			System.out.print("Error in displaying the file:" + e.getMessage());
		}
	}

	public void setListToDisplay(List<Package> listToDisplay)
	{
		this.listToDisplay = listToDisplay;
	}
}
