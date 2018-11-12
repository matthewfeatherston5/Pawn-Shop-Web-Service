//import package com.pawnshop;
package com.pawnshop;
//import com.PawnShop.Item;
//import com.pawnshop.Item;
//import com.pawnshop;
//package pawnshop;
//import pawnshop.Item;
//import com.pawnshop;
//import com.pawnshop.Item;

import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MultivaluedMap;
import java.util.*;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import com.google.gson.Gson;

@Path("ws2")
public class PawnShopServices 
{
	//Array for Object
	//ArrayList<PawnTicket> allTickets = new ArrayList<PawnTicket>();
	ArrayList<Item> allItems = new ArrayList<Item>();
	Gson gson = new Gson();

	//This will end up testing the select * from pawnTicket Db
	@Path("/tickets")
	@GET
	@Produces("text/plain")
	public String getTickets()throws SQLException,ClassNotFoundException
	{
		String connectStr ="jdbc:mysql://localhost:3306/pawnticketdb";

		//DB username
		String username = "root";

		//database password
		String password="password";

		//Driver for Java class to connect to pawnticketdb
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		
		//Creaating connection to DB
		Connection con = DriverManager.getConnection(
			connectStr, username, password);
		

		//Creating  a statement object to be executed on pawnticketdb
		//Statement stmt = con.createStatement();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM pawnticket"); //* FROM pawnticket"); // WHERE ticketId=?"+intId);//.toString());		
		
		ResultSet rs = stmt.executeQuery();		
		
		
		//Showing results from pawnticketdb
		String result2 ="";

		while(rs.next())
		{
			int theId2       = rs.getInt("ticketID");//.toString();//.toString());
			String theId2Str = Integer.toString(theId2);
			String theItemName2 = rs.getString("item");//.toString());
			double theItemPawnAmt = rs.getDouble("pawnValue");
			//system.out.println("the item pawn amount: " + theItemPawnAmt); 
			double theItemLoanAmt = rs.getDouble("itemLoanAmount");
			double theItemAmountPaidOff = rs.getDouble("amountPaidOff");
			String theIteamDatePawned   = rs.getString("datePawned");		
	
	

			//Item itemsOnTicket = new Item(theId2, theItemName2, theItemPawnAmt,theItemLoanAmt);
			Item pulledItemFromDb = new Item(theId2, theItemName2, theItemPawnAmt,theItemLoanAmt,theItemAmountPaidOff,theIteamDatePawned);
			//choosenTicket = new PawnTicket(itemsOnTicket);//theId2);
			//allTickets.add(choosenTicket);
			allItems.add(pulledItemFromDb);
		}

		Item[] itemsArray = allItems.toArray(new Item[allItems.size()]);//(Item) cast
		result2 = gson.toJson(allItems);
		
		
		return result2;//"tickets 1, 2, 3";
	}


	//This will test just pulling up one pawn ticket from the Db
	@Path("tickets/{id}")
	@GET 
	@Produces("text/plain")
	public String getTicketById(@PathParam("id")String theId) throws SQLException,ClassNotFoundException
	{
		int intId = 0;
		String theIng = "";
		
		//Converting url string into an int
		try
		{
			intId = Integer.parseInt(theId);
		}
		catch(NumberFormatException ne)
		{
			intId = 1;
		}

		//Creating item from ID
		//Item choosenItem = new Item(intId);
		//allItems.add(choosenItem);
		

		//Getting Ticket from DB
		//Protocol String "<protocol>://<domainName>:<portNumber>/<databaseName>
		String connectStr ="jdbc:mysql://localhost:3306/pawnticketdb";

		//DB username
		String username = "root";

		//database password
		String password="password";

		//Driver for Java class to connect to pawnticketdb
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		
		//Creaating connection to DB
		Connection con = DriverManager.getConnection(
			connectStr, username, password);
		

		//Creating  a statement object to be executed on pawnticketdb
		//Statement stmt = con.createStatement();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM pawnticket WHERE ticketId=?"); //* FROM pawnticket"); // WHERE ticketId=?"+intId);//.toString());		
//line 100
		//Do a bd query
		//ResultSet rs = stmt.executeQuery("SELECT item, ticketID FROM pawnticket WHERE ticketId="+intId);
		stmt.setString(1,theId);//ticketId);
		ResultSet rs = stmt.executeQuery();		
		
		
		//Showing results from pawnticketdb
		String result ="";
		//PawnTicket choosenTicket;
		//Item pulledItemFromDB;

		while(rs.next())
		{
			//From regular statement 
			//String theId       = rs.getString("ticketID");
			//String theItemName = rs.getString("item");
			
			//From Prepared Statement 
			int theId2       = rs.getInt("ticketID");//.toString();//.toString());
			String theId2Str = Integer.toString(theId2);
			String theItemName2 = rs.getString("item");//.toString());
			double theItemPawnAmt = rs.getDouble("pawnValue");
			//system.out.println("the item pawn amount: " + theItemPawnAmt); 
			double theItemLoanAmt = rs.getDouble("itemLoanAmount");
			double theItemAmountPaidOff = rs.getDouble("amountPaidOff");
			String theIteamDatePawned   = rs.getString("datePawned");		
	
	

			//Item itemsOnTicket = new Item(theId2, theItemName2, theItemPawnAmt,theItemLoanAmt);
			Item pulledItemFromDb = new Item(theId2, theItemName2, theItemPawnAmt,theItemLoanAmt,theItemAmountPaidOff,theIteamDatePawned);
			//choosenTicket = new PawnTicket(itemsOnTicket);//theId2);
			//allTickets.add(choosenTicket);
			allItems.add(pulledItemFromDb);
			//result += allTickets.toString(); //"ID: " + theId2Str + "Item: " + theItemName2;
		}
		//PawnTicket [] allJsonTickets;
			//allJsonTickets = (PawnTicket) allTickets.toArray();//choosenTicket.getNumberOfItemsOnTicket());//choosenTicket[allTickets.size()]);// PawnTicket[allTickets.size()]);
		
		//Changing items array list to array and to json
		//Item[] allItemsArray = new Item[allItems.size()];
			//allItemsArray = allItems.toArray();
		//allItemsArray = (Item)allItems.toArray();

		Item[] itemsArray = allItems.toArray(new Item[allItems.size()]);//(Item) cast
		result = gson.toJson(allItems);		


		//result = gson.toJson(allJsonTickets);
//line 150
		//if(intId ==2)
		//{
			//theIng = "Ticet 2";
		//}
		//else
		//{
			//theIng = "Some other ticket";
		//}
		//return theIng;
		return result;
	}

	//This will pull up tickets with a certain item they have in common
	@Path("/tickets/item")
	@GET
	@Produces("text/plain")
	public String getItem(@QueryParam("name") String theItem)
	{
		String theIng="";
		//Get tickets with the item being searched from DB
		if(theItem.equals("bike"))
		{
			theIng = "Ticket Ids with Bikes 12, 17,19";
		}
		else 
		{
			theIng = "Some other item";
		}
		return theIng;
	}


	@Path("/tickets/item")
	@POST
	@Produces("text/plain")
	@Consumes("application/x-www-form-urlencoded")

	public String createItem(MultivaluedMap<String,String> fromFields) throws SQLException, ClassNotFoundException
	{
		//ensuring I'm in the createItem method
		System.out.println("In Create Item Method");
		
		//Getting information from the add item fields 
		String newItemID         = fromFields.getFirst("Id");
		String newItemName       = fromFields.getFirst("itemName");
		String newItemPawnedAmount = fromFields.getFirst("itemPawnedValue");
		String newItemLoanAmount   = fromFields.getFirst("itemLoanAmount");
		String newItemAmtPaid     = fromFields.getFirst("amountPaidOff");
		String newDueDate         = fromFields.getFirst("newDate");
		//String result             = "";
		int sizeOfMap             = fromFields.size();
		String allResults = "all vars:"+newItemID + newItemName+newItemPawnedAmount +newItemLoanAmount+newItemAmtPaid+ newDueDate;
		System.out.println("Seeing if all variables show:" + allResults);
		System.out.println("Seeing the size of my map:"+sizeOfMap);
		System.out.println("Seeing if I can get a String to print out newItemID should be 1: "+newItemID);
//line 200
		//parsed amounts from field 
		double itemPawnedAmountDouble = Double.valueOf(newItemPawnedAmount);
		System.out.println("Seeing if item pawned amount parsed:"+itemPawnedAmountDouble);
		Double itemLoanAmountDouble   = Double.parseDouble(newItemLoanAmount);
		Double itemAmtPaidDouble      = Double.parseDouble(newItemAmtPaid);
		int    newItemIDInt           = Integer.parseInt(newItemID);
		System.out.println("seeing if i'm passed the parsed");
		//Connecting to the DB
		

		//Getting Ticket from DB
		//Protocol String "<protocol>://<domainName>:<portNumber>/<databaseName>
		String connectStr ="jdbc:mysql://localhost:3306/pawnticketdb";

		//DB username
		String username = "root";

		//database password
		String password="password";

		



		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		
		//Creaating connection to DB
		Connection con = DriverManager.getConnection(
			connectStr, username, password);
		
		//Past the DB stuff
		System.out.println("Your past the DB connenction...1/2 done!!;)");
		//Inserting Item into DB
		String sql = "INSERT INTO pawnticket(ticketID,item,pawnValue,itemLoanAmount,amountPaidOff,datePawned) VALUES (?,?,?,?,?,?)";
		System.out.println("Seeing if newDueDate is being set: "+newDueDate);
	
		//PreparedStatement createStmt = con.prepareStatement(INSERT INTO pawnticket(ticketID, item, pawnValue, itemLoanAmount,amountPaidOff,datePawned)VALUES (newItemID,newItemName,itemPawnedAmountDouble, itemLoanAmountDouble, itemAmtPaidDouble,newDueDate);
		PreparedStatement createStmt = con.prepareStatement(sql);//INSERT INTO pawnticket(ticketID,item,pawnValue,itemLoanAmount,amountPaidOff,datePawned) VALUES (sql);//(?,?,?,?,?,?));
		System.out.println("after PreparedStatement createStmt");
		createStmt.setInt(1,newItemIDInt);
		createStmt.setString(2,newItemName);
		createStmt.setDouble(3,itemPawnedAmountDouble);
		createStmt.setDouble(4,itemLoanAmountDouble);
		createStmt.setDouble(5,itemAmtPaidDouble);
		System.out.println("after itemAmtPaidDouble is set");
		createStmt.setString(6,newDueDate);
		System.out.println("after newDueDate is set");
		//executeUpadeate will return 1 for good 
		int res = createStmt.executeUpdate();

		//For debugging and test purposes
		System.out.println("Result is: " + res);

		//Checking results
		if(res==1)
		{
			System.out.println("Start of the IF statement");
			String sql2 = "Select * from pawnticket WHERE ticketID=? AND item=? AND pawnValue=? AND itemLoanAmount=? AND amountPaidOff=? AND datePawned=?";
			PreparedStatement retrieveStmt = con.prepareStatement(sql2);
			retrieveStmt.setInt(1, newItemIDInt);
			retrieveStmt.setString(2, newItemName);
			retrieveStmt.setDouble(3, itemPawnedAmountDouble);
			retrieveStmt.setDouble(4, itemLoanAmountDouble);
			retrieveStmt.setDouble(5, itemAmtPaidDouble);
			retrieveStmt.setString(6, newDueDate);
			ResultSet rs = retrieveStmt.executeQuery();
			System.out.println("After retrieveStmt");

			String result ="";
			int count = 0;
			int MAX = 100;
			Item [] ingArray = new Item[MAX];
		
			while(rs.next()){
				
				int theItemID = rs.getInt("ticketID");
				String theItemName = rs.getString("item");
				double theItemPawnValue = rs.getDouble("pawnValue");
				double theItemLoanAmount = rs.getDouble("itemLoanAmount");
				double theItemAmountPaid = rs.getDouble("amountPaidOff");
				String theItemDatePawned  = rs.getString("datePawned");
				
				Item ing = new Item(theItemID,theItemName,theItemPawnValue,theItemLoanAmount,
							theItemAmountPaid, theItemDatePawned);
				System.out.println(ing);
				ingArray[count] = ing;
				count++;
			}
			//triming array down to current size
			ingArray = Arrays.copyOf(ingArray,count);
			
			//Creating GsonObj 
			Gson theGsonObj = new Gson();
			result = theGsonObj.toJson(ingArray);//String result2 = theGsonObj.toJson(ingArray);
			System.out.println("the json: \n"+result);
			return result;

		}
		else//the ingredient never got inserted into the DB
		{
			Gson theGsonObj = new Gson();
			Item[] blankIngArray = new Item[1];
			blankIngArray[0] = new Item(0, "none",0.0,0.0,0.0,"none");
			String blankResult = theGsonObj.toJson(blankIngArray);
			return blankResult;
		}	
					
		//For debugging 
		}



	@Path("tickets/{id}")
	@GET 
	@Produces("text/plain")
	public String updateTicketById(MultivaluedMap<String,String> fromFields,@PathParam("id")String theId) throws SQLException,ClassNotFoundException
	{
		int intId = 0;
		String theIng = "";
		
		//Converting url string into an int
		try
		{
			intId = Integer.parseInt(theId);
		}
		catch(NumberFormatException ne)
		{
			intId = 1;
		}

		//Creating item from ID
		//Item choosenItem = new Item(intId);
		//allItems.add(choosenItem);
		

		//Getting Ticket from DB
		//Protocol String "<protocol>://<domainName>:<portNumber>/<databaseName>
		String connectStr ="jdbc:mysql://localhost:3306/pawnticketdb";

		//DB username
		String username = "root";

		//database password
		String password="password";

		//Driver for Java class to connect to pawnticketdb
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		
		//Creaating connection to DB
		Connection con = DriverManager.getConnection(
			connectStr, username, password);
		


		//Getting Info from Fields 
		//Getting information from the add item fields 
		String newItemID         = fromFields.getFirst("Id");
		String newItemName       = fromFields.getFirst("itemName");
		String newItemPawnedAmount = fromFields.getFirst("itemPawnedValue");
		String newItemLoanAmount   = fromFields.getFirst("itemLoanAmount");
		String newItemAmtPaid     = fromFields.getFirst("amountPaidOff");
		String newDueDate         = fromFields.getFirst("newDate");


		//Parsing some data
		Double itemLoanAmountDouble   = Double.parseDouble(newItemLoanAmount);
		Double itemAmtPaidDouble      = Double.parseDouble(newItemAmtPaid);
		int    newItemIDInt           = Integer.parseInt(newItemID);
		Double itemItemPawnAmountDouble   = Double.parseDouble(newItemPawnedAmount);
		
		//SQL String 
		String sqlStr = "UPDATE pawnticket SET  ticketID = newItemIDInt, itemName=newItemName, pawnVaule = newItemPawnAmountDouble,itemLoanAmount=itemLoanAmountDouble,amountPaidOff=itemAmtPaidDouble,datePawned=newDueDate WHERE ticketId=?";

		
	         PreparedStatement createStmt = con.prepareStatement(sqlStr);
		int result = createStmt.executeUpdate();
		String resultStr = "Your result was : " + result;
		//itemLoanAmount = newItemLoanAmount, amountPaidOff = newItemAmtPaid, datePawned = newDueDate WHERE ticketId=?"); 		
//line 100
		//Do a bd query
		//ResultSet rs = stmt.executeQuery("SELECT item, ticketID FROM pawnticket WHERE ticketId="+intId);
		//stmt.setString(1,theId);//ticketId);
		//ResultSet rs = stmt.executeQuery();

		//Return
		return resultStr;
	}
		
}