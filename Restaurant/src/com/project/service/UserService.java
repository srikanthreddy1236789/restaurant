package com.project.service;

import java.util.Scanner;

import com.project.dao.UserDao;
import com.project.entity.User;

public class UserService {
	private Scanner sc;
	private UserDao user;
	public UserService()
	{
		sc=new Scanner(System.in);
		user=new UserDao();
		
	}
	public void menudisplay(User u)
	{
		/*System.out.println("How many Items Do you Want");
		int noofitems=sc.nextInt();
		for(int i=0;i<noofitems;i++)
		{
			
		System.out.println("Enter the itemname");
		String itemname=sc.next();
		user.selectItem(u,itemname);
		}*/
		displayItems();
		boolean ch=true;
		while(ch)
		{
			System.out.println("Enter 1 for order item using name");
			System.out.println("Enter 2 for Generate Bill");
			System.out.println("Enter 3 for History");
			int choice =sc.nextInt();
			switch(choice)
			{
			case 1:orderUsingName(u);
			      break;
			case 2:user.generateBill();
			       break;
			case 3:orderHistory(u);
			       break;
				
				
			}
			
		}
		
		
	}
	public void orderUsingName(User u)
	{
		System.out.println("Enter the itemname");
		String itemname=sc.next();
		System.out.println("How Many Plates");
		int plates=sc.nextInt();
		user.OrderItem(itemname,plates,u);
	}
	
	public void displayItems()
	{
		user.displayItemsMenu();
		
	}
	public void orderHistory(User u)
	{
		
		user.orderedHistory(u);
	}
	

}
