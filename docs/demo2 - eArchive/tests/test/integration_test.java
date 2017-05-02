package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.NumberFormat;

public class integration_test{
	public static void main(String[] args){
		if(Integer.parseInt(args[0])==0){
			Account t=new Account(Integer.parseInt(args[1]), args[2], args[3], args[4]);
			System.out.println(t.getId());
			System.out.println(t.getEmail());
			System.out.println(t.getUsername());
			System.out.println(t.getPassword());
		}
		if(Integer.parseInt(args[0])==1){
			double his[]=new double[4];
			his[0]=11;
			his[1]=22;
			his[2]=20;
			his[3]=10;
			Company x=new Company(args[1], args[2], args[3],his , Integer.parseInt(args[4]));
			System.out.println(x.getName());
			System.out.println(x.getPrice());
			System.out.println(x.getPriceText());
			System.out.println(x.getPriceHistory()[3]);
			System.out.println(x.returnWeek());
			System.out.println(x.getPriceAt(0));
			System.out.println(x.getTicker());
			System.out.println(x.getDescription());
			System.out.println(x.getRealName());
			x.goToNextWeek();
			System.out.println(x.getPrice());
			System.out.println(x.returnWeek());
			x.setPrice(Double.parseDouble(args[5]));
			System.out.println(x.getPrice());
		}
		if(Integer.parseInt(args[0])==2){
			double his[]=new double[4];
			his[0]=11;
			his[1]=22;
			his[2]=20;
			his[3]=10;
			Company x=new Company("bankofa", "boa", "Costco",his , 1);
			Map<Company, Integer> stocks=new HashMap<Company, Integer>();
			Portfolio y = new Portfolio(Integer.parseInt(args[1]),stocks, Double.parseDouble(args[2]));
			System.out.println(y.getId());
			y.updateStock(x,1);
			System.out.println(y.getAmountOfStock(x));
			y.updateStock(x,Integer.parseInt(args[3]));
			System.out.println(y.getAmountOfStock(x));
			System.out.println(y.getBalance());
			y.addTurnBalance();
			System.out.println(y.getBalanceText());
			y.updateBalance(Double.parseDouble(args[4]),Integer.parseInt(args[5]),true);
			System.out.println(y.getBalanceText());
			y.updateBalance(Double.parseDouble(args[4]),Integer.parseInt(args[5]),false);
			System.out.println(y.getBalanceText());
			System.out.println(y.getBalanceAt(0));
		}
		if(Integer.parseInt(args[0])==3){
			Achievement ach=new Achievement(args[1], args[2]);
			System.out.println(ach.getName());
			System.out.println(ach.getProgress());		
		}	
	}
}
