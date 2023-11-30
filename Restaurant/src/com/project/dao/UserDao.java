package com.project.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.project.entity.Item;
import com.project.entity.OrderedItem;
import com.project.entity.User;
import com.project.sessionfactory.SessionFactoryConnection;

public class UserDao {
	private SessionFactory sesfact;
	private Session ses;
	double totalAmount=0;
	
	private Transaction tx;
	public UserDao()
	{
		sesfact = SessionFactoryConnection.con();
	}
	
	public void OrderItem(String itemname, int plates, User u)
	{
		
		ses=sesfact.openSession();
		tx=ses.beginTransaction();
		Query q=ses.createQuery("select i from Item i where i.itemname=:name");
		q.setParameter("name", itemname);
		List<Item> ilst=q.list();
		if(!ilst.isEmpty())
		{
			for(Item i:ilst)
			{
				
				OrderedItem oi=new OrderedItem();
				oi.setDate(LocalDate.now());
				oi.setItemnames(itemname);
				oi.setTotalprice(plates*i.getPrice());
				oi.setQuantity(plates);
				oi.setUserobj(u);
				ses.save(oi);
				totalAmount=totalAmount+plates*i.getPrice();
			}
			
		}
		else
		{
			System.out.println("No Item With That Namez");
		}
		tx.commit();
	
		
	}
	
	public void generateBill()
	{
		System.out.println("total Bill is "+totalAmount);
		System.exit(0);
	}

	public void displayItemsMenu() {
		ses=sesfact.openSession();
		tx=ses.beginTransaction();
		Query q1=ses.createQuery("from Item");
		List<Item> itm=q1.list();
		for(Item i:itm)
		{
			System.out.println("Item Name is   :"+i.getItemname());
			System.out.println("Item Price is  :"+i.getPrice());
			System.out.println("*****************************************");
		}
		
	}

	public void orderedHistory(User u) {
		ses=sesfact.openSession();
		tx=ses.beginTransaction();
		Query q2=ses.createQuery("select o from OrderedItem o where o.userobj=:idd");
		q2.setParameter("idd", u);
		List<OrderedItem> olist=q2.list();
		if(!olist.isEmpty())
		{
			for(OrderedItem oi:olist)
			{
				System.out.println("Item name is "+oi.getItemnames());
				System.out.println("Item name is "+oi.getQuantity());
				System.out.println("Price        "+oi.getTotalprice());
				System.out.println("Date is      "+oi.getDate());
				System.out.println("************************************************");
			}
		}
		else
		{
			System.out.println("No Such User with That ID");
		}	
		
	}

}
