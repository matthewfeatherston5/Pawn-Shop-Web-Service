//package com.pawnshop;
//package com.pawnshop;
//package pawnshop;
package com.pawnshop;
/**
*Item class will be used to create an Item object to add to the pawn ticket*/

//package pawnshop;


public class Item
{
	//Instance Variables 
	private int itemID;
	private String itemName;
	private double itemPawnedValue; //The Amt left to pay on this item 
	private double itemLoanAmt;
	private double itemAmtPaid;
	private String itemDueDate;
	//private int NONE = 0;

	//Consturctor 
	public Item(int anItemID,String aItemName, double aItemPawnedValue, double aItemLoanAmt,double anAmtPaid, String aDueDate)
	{
		itemID          = anItemID;
		itemName        = aItemName;
		itemPawnedValue = aItemPawnedValue;
		itemLoanAmt     = aItemLoanAmt;
		itemAmtPaid     = anAmtPaid;
		itemDueDate     = aDueDate;
	}

	public Item(int anID)
	{
		itemID        = anID;
		itemPawnedValue = 0;
		itemPawnedValue = 0;
		itemLoanAmt     = 0;
		itemAmtPaid     = 0;
	} 

	//public Item(int anID, 
	

	//mutators and accessors 
	/**
	*getItemName will return a string value that contains the name of this item
	*@return itemName - is a String value that contains the name of this item*/
	public String getItemName()
	{
		return this.itemName;
	}

	/**
	*getItemPawnedValue will return the pawned(loan) amount for this item
	*@return itemPawnedValue - is a double type that contains the pawned value for this item*/
	public double getItemPawnedValue()
	{
		return this.itemPawnedValue;
	}

	/**
	*getLoanAmt will return the amount of money that was loaned out for this item
	*@return itemLoanAmt - is a double type that contains the amount that was loaned on this item*/
	public double getLoanAmt()
	{
		return this.itemLoanAmt;
	}

	/**
	*getItemAmtPaid will return a double type that contains the amount that the guest has left to pay for 
	*for this item
	*@return itemAmtPaid - is a double that contains the amount rest to pay for this item*/
	public double getItemAmtPaid()
	{
		return this.itemAmtPaid;
	}

	/**
	*getItemDueDate will return a String type that contains the date at which all money must be paid for in the 
	*format of mm/dd/yy
	*@return - itemDueDate - String that contains the date all monies must be paid for this item*/
	public String getItemDueDate()
	{
		return this.itemDueDate;
	}

	/**
	*getItemID will return an integer type that is an ID for the ticket that this Item is on
	*@return itemID - is an integer type that contians the ID to the ticket that this item is on*/
	public int getItemID()
	{
		return itemID;
	}

	/**
	*setItemName will set the this items name to the parameter used in this method
	*@param aItemName - is a String type that contains the name to this item*/
	public void setItemName(String aItemName)
	{
		this.itemName = aItemName;
	}

	/**
	*setItemPawnedValue will set the pawn(loan) amount for this item 
	*@param aItemPanedValue - is a double used to set the pawn(loan) amount for this item*/
	public void setItemPawnedValue(double aItemPawnedValue)
	{
		this.itemPawnedValue = aItemPawnedValue;
	}

	/**
	*setAmtLeftToPay will set the amount that is still left to be paid for this item
	*@param aItemAmtPaid - is a double value that is used to set the amount left to be paid for this item*/
	public void setAmtLeftToPay(double aItemAmtPaid)
	{
		this.itemAmtPaid = aItemAmtPaid;
	}

	/**
	*setItemID will set this item's ID to the integer value provided in the parameter 
	*@param anID - is an integer type that contains the ID to the ticket this item is on*/
	public void setItemID(int anID)
	{
		this.itemID = anID;
	}

	/**
	*setItemDueDate will set this item's due date in the format of mm/dd/yy
	*@param aDueDate - is a String type that contains the due date for this item*/
	public void setItemDueDate(String aDueDate)
	{
		this.itemDueDate = aDueDate;
	}
	
	/**
	*toString will be used to save all attributes of this item as a string and return the 
	*string
	*@return results - is a String type that conatins all infromation about this item*/
	public String toString()
	{
		double pawnValue = this.getItemPawnedValue();
		double loanAmt   = this.getLoanAmt();
		double amtPaid   = this.getItemAmtPaid();
		String pawnValueStr = Double.toString(pawnValue);
		String loanAmtStr   = Double.toString(loanAmt);
		String amtPaidStr      = Double.toString(amtPaid);
		String results = "item name:"+this.getItemName()+" "+"pawn value: "+pawnValueStr
					+"Loan Amt:" + loanAmtStr + "Amount Paid :" + amtPaidStr;
		return results;
	}

}
