/**
 * Copyright 2013 Gokila Rajaiah.  All rights reserved.  For internal use only.
 */
package com.vegas.interview.PackageMaker;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.vegas.interview.PackageMaker.PackageItemTypes.Hotel;
import com.vegas.interview.PackageMaker.PackageItemTypes.Show;
import com.vegas.interview.PackageMaker.PackageItemTypes.Tour;

/**
 * Class to organize the different package lists from input file and creating
 * the sort order of output package file.
 * 
 * @author Gokila Rajaiah
 * 
 */
public class PackageOrganizer
{
	List<Show> showList = new ArrayList<Show>();

	List<Tour> tourList = new ArrayList<Tour>();

	List<Hotel> hotelList = new ArrayList<Hotel>();

	List<Package> packageList = new ArrayList<Package>();

	List<PackageItemType> packageOrderList = new ArrayList<PackageItemType>();

	static String inputFileName;

	static String packageOrderString;

	static String validationError;

	static float minimumPrice;

	static float maximumPrice;

	/**
	 * Creates & returns a new PackageOrganizer object after validating the
	 * parameter inputs.
	 * 
	 * @param fileName
	 * @param packageOrder
	 * @param minimumPackagePrice
	 * @param maximumPackagePrice
	 * @return PackageOrganizer
	 */
	public static PackageOrganizer organizer(String fileName,
			String packageOrder, float minimumPackagePrice,
			float maximumPackagePrice)
	{
		inputFileName = fileName;
		packageOrderString = packageOrder;
		minimumPrice = minimumPackagePrice;
		maximumPrice = maximumPackagePrice;

		if (validateInputs())
		{
			return new PackageOrganizer();
		}
		else
		{
			System.out.println(validationError);

			return null;
		}
	}

	public PackageOrganizer() {

		this.createPackageItemList();
		this.createPackageOrder();
	}

	/**
	 * Method validating the fileName, min & max price and package order string
	 * 
	 * @return boolean
	 */
	private static boolean validateInputs()
	{
		if (!inputFileName.isEmpty())
		{
			if (!packageOrderString.isEmpty())
			{
				if (minimumPrice >= 0 && maximumPrice >= 0)
				{
					return true;
				}
				else
				{
					validationError = validationError
							+ "Maximum or Minimum price is less than 0 \n";
					return false;
				}
			}
			else
			{
				validationError = validationError
						+ "Package Order String is empty \n";
				return false;
			}
		}
		else
		{
			validationError = validationError + "File name is empty \n";
			return false;
		}
	}

	/**
	 * Method to read the input file and create different PackageItemLists (ex:
	 * show list, hotel list, tour list)
	 */
	private void createPackageItemList()
	{
		BufferedReader br = null;
		FileInputStream fileInputStream = null;
		DataInputStream dataInputStream = null;

		try
		{
			if (!inputFileName.isEmpty())
			{
				fileInputStream = new FileInputStream(inputFileName);

				dataInputStream = new DataInputStream(fileInputStream);

				br = new BufferedReader(new InputStreamReader(dataInputStream));

				String line;

				while ((line = br.readLine()) != null)
				{
					String[] lineItems = line.split("\t");

					if (lineItems.length == 3)
					{
						int id = 0;
						float price = 0;

						try
						{
							id = Integer.parseInt(lineItems[1]);
							price = Float.parseFloat(lineItems[2]);
						}
						catch (NumberFormatException e)
						{
							System.out
									.println("Id or Price is not an Integer or Float value respectively"
											+ e.getMessage());
						}

						switch (lineItems[0])
						{
							case "HOTEL":
								Hotel hotel = new Hotel();

								hotel.setId(id);
								hotel.setPrice(price);
								hotel.setType(PackageItemType.HOTEL);
								hotelList.add(hotel);
								break;

							case "SHOW":
								Show show = new Show();
								show.setId(id);
								show.setPrice(price);
								show.setType(PackageItemType.SHOW);
								showList.add(show);
								break;

							case "TOUR":
								Tour tour = new Tour();
								tour.setId(id);
								tour.setPrice(price);
								tour.setType(PackageItemType.TOUR);
								tourList.add(tour);
								break;

							default:
								System.out
										.println("Package Type is not defined");
								break;
						}
					}
					else
					{
						System.out
								.println("Number of values in input file is not equal to 3");
					}

				}
			}

		}
		catch (FileNotFoundException e)
		{
			System.out.println("Input File not found: " + e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("Exception in reading input: " + e.getMessage());
		}
		finally
		{
			try
			{
				if (br != null)
				{
					br.close();
				}

				if (fileInputStream != null)
				{
					fileInputStream.close();
				}

				if (dataInputStream != null)
				{
					dataInputStream.close();
				}
			}
			catch (IOException e)
			{
				System.out.println("Error in Closing the Reader: "
						+ e.getMessage());
			}
		}
	}

	/**
	 * Method creating the list of package order using input string. Package
	 * order is used in sorting and also in combining the different package item
	 * types into a package.
	 * 
	 * @return ArrayList<PackageType>
	 */
	private List<PackageItemType> createPackageOrder()
	{
		char[] packageOrderCharArray = packageOrderString.toCharArray();

		for (char c : packageOrderCharArray)
		{
			switch (c)
			{
				case 'p':
					packageOrderList.add(PackageItemType.PACKAGE);
					break;
				case 'h':
					packageOrderList.add(PackageItemType.HOTEL);
					break;
				case 't':
					packageOrderList.add(PackageItemType.TOUR);
					break;
				case 's':
					packageOrderList.add(PackageItemType.SHOW);
					break;
			}
		}

		return this.packageOrderList;
	}

	public List<Show> getShowList()
	{
		return showList;
	}

	public List<Tour> getTourList()
	{
		return tourList;
	}

	public List<Hotel> getHotelList()
	{
		return hotelList;
	}

	public List<Package> getPackageList()
	{
		return packageList;
	}

	public List<PackageItemType> getPackageOrderList()
	{
		return packageOrderList;
	}

	public String getInputFileName()
	{
		return inputFileName;
	}

	public String getPackageOrderString()
	{
		return packageOrderString;
	}
}
