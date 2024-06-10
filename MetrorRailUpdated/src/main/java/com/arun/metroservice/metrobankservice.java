package com.arun.metroservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arun.MetroEntity.Metrobank;
import com.arun.MetroEntity.Metroentity;
import com.arun.repo.metrobankrepo;

@Service
public class metrobankservice {
@Autowired
private metrobankrepo bankrepo;


public boolean payMoney(Metroentity m, double amount)
{        
	    
	Optional<Metrobank> m1=bankrepo.findById(m.getId());

     Metrobank m2=m1.get();
     
     if(m2.getAmount()>=amount )
     {
      
     return true;
     }
     else
     {
    	 return false;
     }
}
public Metrobank recharge(Metrobank m)
{  
	Optional<Metrobank> m1= bankrepo.findById(m.getId());
	Metrobank m2=m1.get();
	//System.out.println(m.getAmount());
	m2.setAmount(m.getAmount()+m2.getAmount());
	  Metrobank m3=bankrepo.save(m2);
   return m3;
}
public Metrobank getAccount(int id)
{
   Optional<Metrobank> m1=bankrepo.findById(id);
    return m1.get();
}
public void delete(int id)
{
   bankrepo.deleteById(id);	
}

public Metrobank saveAccount(Metrobank m)
{
  return bankrepo.save(m);	
}

}
