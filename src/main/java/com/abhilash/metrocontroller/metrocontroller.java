package com.abhilash.metrocontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abhilash.MetroEntity.Metrobank;
import com.abhilash.MetroEntity.Metroentity;
import com.abhilash.metroservice.metrobankservice;
import com.abhilash.service.servicemetro;

@Controller
public class metrocontroller {
	@Autowired
	private servicemetro service;
	
	@Autowired
	private metrobankservice bankservice;

	Metroentity m10= new Metroentity();


	@RequestMapping("/")
	public String goHome( )
	{
		return "home";	
	}
	
	@RequestMapping("/bookticket")
	public String bookTicket(ModelMap mod)
	{ System.out.println("insise book ticket...");
	Metroentity e= new Metroentity();
	mod.put("e", e);
	return "fromto";
	}
	
	@RequestMapping("/pay")
	public String payMoney(@ModelAttribute Metroentity m,ModelMap mod,ModelMap mod2)
	{  System.out.println("inside pay money..");
	Metroentity m1= new Metroentity();
	m10.setFromstation(m.getFromstation());
	m10.setTostation(m.getTostation());
	m10.setId(m.getId());
	service.saveTicketDetails(m10);
	double amt=service.calFair(m);
	m10.setAmount(amt);

	mod.put("m1", m1);
	mod.put("res","pay us the amount rupees: "+amt );
	return "pay";
	}
	@RequestMapping("/getticket")
	public String getTicket(@ModelAttribute Metroentity m,@RequestParam double amount ,ModelMap mod)
	{   System.out.println("inside get ticket..");
	m.setFromstation(m10.getFromstation());
	m.setTostation(m10.getTostation());
	m.setId(m10.getId());

	System.out.println(m.getFromstation()+" "+m.getTostation());

	Metroentity m2= service.saveTicketDetails(m);
	boolean b=bankservice.payMoney(m2,amount);
	if(b && amount==m10.getAmount())
	{      m2.setAmount(amount);
	m2=service.saveTicketDetails(m2);
	String c=service.getCode();
    service.bookTicket(m2, c);
    Metroentity m16=service.getTicket(m2.getId());
	mod.put("res", "your ticket has been successfully booked and your ticket code is.."+m16.getCode());
	m10=new Metroentity();
	return "success";
	}
	else
		if(amount!=m10.getAmount())
		{
			service.deleteTicketDetails(m2);
			mod.put("res", "booking failed, lesser than required amount entered...");
			return "success";
		}
		else
		{         service.deleteTicketDetails(m2);
		mod.put("res", "booking failed insufficient balance please recharge...");
		return "success";
		}


	}
	@RequestMapping("/onboard")
	public String travel(ModelMap mod)
	{   Metroentity m= new Metroentity();
	mod.put("m", m);
	return "travel";	
	}
	@RequestMapping("/startjourney")
	public String journey(@ModelAttribute Metroentity m,ModelMap mod)
	{    Metroentity m1=service.getAccount(m);
	
	if(m.getCode().equals(m1.getCode()))
	{      m1.setBoaredornot(true);

	m1= service.saveTicketDetails(m1);
	mod.put("res", "your journey from "+m1.getFromstation()+" to "+m1.getTostation()+" has begun we wish you a safe journey..");
	return "success";
	}
	else
	{
		mod.put("res", "in correct code entered try again..");
		return "success";
	}
	}



	@RequestMapping("/deboard")
	public String deboard(ModelMap mod)
	{  
		Metroentity m1= new Metroentity();
		mod.put("m1", m1);
		return "deboard";
	}
	@RequestMapping("/deboardjourney")
	public String endjourney(@ModelAttribute Metroentity m, ModelMap mod)
	{   
		Metroentity m1=service.getAccount(m);

		if(m1.getTostation().equals(m.getTostation()) && m1.getCode().equals(m.getCode()) &&m1.isBoaredornot() )
		{ 

			service.deleteTicketDetails(m1);

			mod.put("res", "thanks for having a ride with us..");
			return "success";
		}
		else
			if(m1.isBoaredornot()==false)
			{
				mod.put("res", "you have not boarded any train yet!!!");
				return "success";
			}
			else
				if(m1.getCode()!=m.getCode())
				{
					mod.put("res", "you have entered the wrong code kindly enter the code again...");
					return "success";
				}
				else
				{
					mod.put("res", "you have deboarded on the wrong station..");
					return "success";
				}
	}



	@RequestMapping("/register")
	public String register(ModelMap mod)
	{   Metrobank m= new Metrobank();
	mod.put("m", m);
	return "register";
	}
	@RequestMapping("/newaccount")
	public String newAccount(@ModelAttribute Metrobank m, ModelMap mod )
	{    bankservice.saveAccount(m);
	mod.put("res", "your account has been registered successfully...");
	return "success";
	}
	@RequestMapping("/recharge")
	public String recharge(ModelMap mod)
	{
		Metrobank m= new Metrobank();
		mod.put("m", m);
		return "recharge";
	}
	@RequestMapping("/rechargeacc")
	public String rechargeAcc(@ModelAttribute Metrobank m, ModelMap mod)
	{    
		bankservice.recharge(m);
		mod.put("res", "recharged successfully...");
		return "success";
	}

	@RequestMapping("/viewbal")
	public String balForm(ModelMap mod)
	{
		Metrobank m= new Metrobank();
		mod.put("m", m);
		return "bal";
	}

	@RequestMapping("/balance")
	public String balance(@ModelAttribute Metrobank m, ModelMap mod)
	{
		Metrobank m1=bankservice.getAccount(m.getId());
		mod.put("res", "The currently available balance in your account is: "+m1.getAmount());
		return "success";
	}
	
	@Scheduled(cron="0 0 * * *")
	public void delTickets()
	{
	   service.deleted();	
	}

}
