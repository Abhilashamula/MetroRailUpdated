package com.arun.service;
import java.util.*;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arun.MetroEntity.Metrobank;
import com.arun.MetroEntity.Metroentity;
import com.arun.repo.metrorepo;
import com.arun.metroservice.*;
@Service
public class servicemetro {
	@Autowired
	private metrorepo repo;

	@Autowired
	private metrobankservice bankserive;

	public double calFair(Metroentity m)
	{    double amt;   
	int fw=-1,to=-1;
	List<String> str=Arrays.asList("raidurg","hitech city","durgam cheruvu","madhapur","peddamma gudi","jubliee hills check post","roadnumber 5 jubliee hills","yusufguda","madhuranagar","ameerpet","begumpet","invesco prakashnagar","rasoolpura","paradise","parade groung","secundrabad","mettuguda","tarnaka","habsiguda","ngri","stadium","uppal","nagole");


	for(int i=0;i<str.size();i++)
	{   String s=str.get(i);
	fw++;
	if(s.equals(m.getFromstation()))
	{
		break;
	}
	}
	for(int i=0;i<str.size();i++)
	{    String s=str.get(i);
	to++;
	if(s.equals(m.getTostation()))
	{
		break;
	}
	
	}
	int x=fw-to;
	if(x<0)
	{x=-1*x;}
	
	if(x<=2)
	{amt=10.0;}
	else if(x<=4)
	{amt=15;}
	else if(x<=6)
	{amt=20;}
	else if(x<=8)
	{amt=30;}
	else
		if(x<=10)
		{amt=35;}
		else
			if(x<=13)
			{amt=40;}
			else
				if(x<=16)
				{amt=45;}
				else
					if(x<=20)
					{amt=50;}
					else
						if(x<=23)
						{amt=60;}
						else
						{amt=0.0;}

	return amt;
	}

	public Metroentity getAccount(Metroentity m)
	{  Optional<Metroentity> m1=repo.findById(m.getId());

	return m1.get();	
	}

	public Metroentity bookTicket(Metroentity m,String c)
	{    m.setCode(c);
	Metrobank m1=bankserive.getAccount(m.getId());
	m1.setAmount(m1.getAmount()-m.getAmount());
	bankserive.saveAccount(m1);
	return repo.save(m);

	}
	public String getCode()
	{
		double d=Math.random();
		d=d*1000000000;
		int num=(int)d;
		String s=Integer.toBinaryString(num);
		String code="|| ||";
		for(int i=0;i<s.length();i++)
		{
		    char ch=(s.charAt(i)=='1')?('|'):('-');	
		    code=code+ch;
		    if(i==s.length()-1)
		    {code=code+"|| ||";}
		}
		return code;
	}
	public void deleteTicketDetails(Metroentity m)
	{
		repo.deleteById(m.getId());
	}

	public Metroentity saveTicketDetails(Metroentity m)
	{    
		return repo.save(m);	
	}
	public boolean boardedOrNot(Metroentity m)
	{
		return repo.existsById(m.getId());	
	}
	public Metroentity getTicket(int id)
	{
		return repo.findById(id).get();
	}

}
