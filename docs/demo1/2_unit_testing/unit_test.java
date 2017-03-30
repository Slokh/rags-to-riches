package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.NumberFormat;

public class unit_test{
	public static void main(String[] args){
		int c=Integer.parseInt(args[0]);
		String s = " ";
		int i =0;
		double his[]=new double[4];
		his[0]=11;
		his[1]=22;
		his[2]=20;
		his[3]=10;
		Company x=new Company("bankofa", "boa", "Costco",his , 1);
		Map<Company, Integer> stocks=new HashMap<Company, Integer>();
		switch(c){
			case 0:
				Account t = new Account(Integer.parseInt(args[1]),s,s,s);
				System.out.println(t.getId());
				break;
			case 1:
				Account tt=new Account(i, args[1],s,s);
				System.out.println(tt.getEmail());
				break;
			case 2:
				Account ttt=new Account(i,s, args[1],s);
				System.out.println(ttt.getUsername());
				break;
			case 3:
				Account tttt=new Account(i,s,s, args[1]);
				System.out.println(tttt.getPassword());
				break;
			case 4:
				Company q=new Company(args[1], s, s,his , i);
				System.out.println(q.getName());
				break;
			case 5:
				his[0]=Double.parseDouble(args[1]);
				Company xx=new Company(s, s, s,his , i);
				System.out.println(xx.getPrice());
				break;
			case 6:
				his[0]=Double.parseDouble(args[1]);
				Company xxx=new Company(s, s, s,his , i);
				System.out.println(xxx.getPriceText());
				break;
			case 7:
				his[0]=Double.parseDouble(args[1]);
				Company xxxx=new Company(s, s, s,his , i);
				System.out.println(xxxx.getPriceHistory()[0]);
				break;
			case 8:
				Company xxxxx=new Company(s, s, s,his , Integer.parseInt(args[1]));
				System.out.println(xxxxx.returnWeek());
				break;
			case 9:
				Company xxxxxx=new Company(s, s, s,his ,i);
				System.out.println(xxxxxx.getPriceAt(Integer.parseInt(args[1])));
				break;
			case 10:
				Company xxxxxxx=new Company(s, args[1], s,his , i);
				System.out.println(xxxxxxx.getTicker());
				break;
			case 11:
				Company xxxxxxxx=new Company(s, s, s,his , i);
				System.out.println(xxxxxxxx.getDescription());
				break;
			case 12:
				Company xxxxxxxxx=new Company(s, s, args[1],his , i);
				System.out.println(xxxxxxxxx.getRealName());
				break;
			case 14:
				Company ww=new Company(s, s, s,his , i);
				System.out.println(ww.returnWeek());
				System.out.println(ww.getPrice());
				ww.goToNextWeek();
				System.out.println(ww.returnWeek());
				System.out.println(ww.getPrice());
				break;
			case 15:
				Company www=new Company(s, s, s,his , i);
				System.out.println(www.getPrice());
				www.setPrice(Double.parseDouble(args[1]));
				System.out.println(www.getPrice());
				break;
			case 16:
				Portfolio y = new Portfolio(Integer.parseInt(args[1]),stocks, 0.0);
				y.updateStock(x,0);
				System.out.println(y.getId());
				break;
			case 17:
				Portfolio yy = new Portfolio(i,stocks, 0.0);
				yy.updateStock(x,Integer.parseInt(args[1]));
				System.out.println(yy.getAmountOfStock(x));
				break;
			case 19:
				Portfolio yyyy = new Portfolio(i,stocks, 0.0);
				yyyy.updateStock(x,0);
				System.out.println(yyyy.getAmountOfStock(x));
				yyyy.updateStock(x,Integer.parseInt(args[1]));
				System.out.println(yyyy.getAmountOfStock(x));
				break;	
			case 20:
				Portfolio yyyyy = new Portfolio(i,stocks, 3.0);
				yyyyy.updateStock(x,0);
				System.out.println(yyyyy.getBalance());
				break;
			case 21:
				Portfolio yyyyyy = new Portfolio(i,stocks, 3.0);
				yyyyyy.updateStock(x,0);
				yyyyyy.addTurnBalance();
				System.out.println(yyyyyy.getBalanceAt(0));
				break;
			case 22:
				Portfolio u = new Portfolio(i,stocks, 3.0);
				u.updateStock(x,0);
				System.out.println(u.getBalanceText());
				break;
			case 23:
				Portfolio uu = new Portfolio(i,stocks, 0.0);
				uu.updateStock(x,0);
				System.out.println(uu.getBalance());
				uu.updateBalance(Double.parseDouble(args[1]),Integer.parseInt(args[2]),true);
				System.out.println(uu.getBalance());
				break;
			case 24:
				Portfolio uuu = new Portfolio(i,stocks, 0.0);
				uuu.updateStock(x,0);
				System.out.println(uuu.getBalance());
				uuu.updateBalance(Double.parseDouble(args[1]),Integer.parseInt(args[2]),false);
				System.out.println(uuu.getBalance());
				break;
			default:
		}	
	}
}
