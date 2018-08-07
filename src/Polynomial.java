import java.lang.*;
import java.util.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Polynomial {
	private String poly;
	private Map<Integer, Integer> map;
	
	public String getPoly() {
		return poly;
	}


	public void setPoly(String poly) {
		this.poly = poly;
	}


	public Map<Integer, Integer> getMap() {
		return map;
	}


	public void setMap(Map<Integer, Integer> map) {
		this.map = map;
	}

	public Polynomial(){
		
	}
	
	public Polynomial(String poly_param){
		this.poly= poly_param;
		this.map= generateMap(poly_param);
		//System.out.println(this.map);
	}
	
	public Polynomial(String poly_param, Map<Integer, Integer> map_param){
		this.poly= poly_param;
		this.map= map_param;
		//System.out.println(poly);
	}
	
	Map<Integer, Integer> calculateMap(String poly){
		Map<Integer, Integer> map=new HashMap<>();
		return map;
	}
	
	String calculatePoly(Map<Integer, Integer> map){
		String result="";
	
		return result;
	}
	
	static Map<Integer,Integer> generateMap(String str)
    {
        
        Map<Integer,Integer> my_map=new HashMap<>();
        if(str==null) return my_map;
        //String str="2x^3+4x^2+3";
        String my_str=null;
        String str1=null;
         for(int i=0;i<str.length();i++)
         {
             my_str=str.replace("-", "+-");
         }
         //System.out.println(str);
         StringTokenizer st = new StringTokenizer(my_str,"+"); 
         int i=0,pow=0,co_eff=0;
         String t=null;
         while (st.hasMoreTokens()) {  
             str1=st.nextToken();
             
            //System.out.println("term "+i+++" "+str1);
            if(str1.contains("x^"))
            {
                
                StringTokenizer temp = new StringTokenizer(str1,"x^");
                if(str1.startsWith("x")) co_eff=1;
                else if(str1.startsWith("-x")) co_eff=-1;
                else co_eff=Integer.parseInt(temp.nextToken());
                if(str1.endsWith("x")) pow=1;
                else pow=Integer.parseInt(temp.nextToken());
                my_map.put(pow, co_eff);
            }
            else if(str1.contains("x"))
            {
                StringTokenizer temp = new StringTokenizer(str1,"x");
                if(str1.startsWith("x")) co_eff=1;
                else if(str1.startsWith("-x")) co_eff=-1;
                else co_eff=Integer.parseInt(temp.nextToken());
                if(str1.endsWith("x")) pow=1;
                else pow=Integer.parseInt(temp.nextToken());
                my_map.put(pow, co_eff);
            }
            else //constants
            {
                pow=0;
                co_eff=Integer.parseInt(str1);
                my_map.put(pow, co_eff);
            }
           
         }
       return my_map;
    }
	
	static String generateString(Map<Integer, Integer> my_map) {
		   String str="";
		   Set<Integer> keys=my_map.keySet();
		   List<Integer> sortedList = new ArrayList<>(keys);
		   Collections.sort(sortedList);
		   Collections.reverse(sortedList);
		   for (int k :sortedList)
		   {
			   	   if( my_map.get(k)==0) continue;	
			       if(my_map.get(k)>0) str+="+";
			       if(k==0)
			       { 
			    	   str+=my_map.get(k);
			       }
			       else if(k==1)
			       {   
			    	   if(my_map.get(k)==1)
			    		   str+="x";
			    	   else if(my_map.get(k)==-1)  str+="-x";
			    	   else  str+=my_map.get(k)+"x";
			    	}
			       else
			       {   
			    	   if(my_map.get(k)==1)
			    		 str+="x^"+k;
			    	    
			    	   else if(my_map.get(k)==-1)
			    		   str+="-x^"+k;
			    	   else
			    		   str+=my_map.get(k)+"x^"+k;
			    		   
			        }
			       
			       
			       
		   }
		   if(str=="") str="0";
		   if(str.startsWith("+")) str=str.substring(1, str.length());
		   //System.out.println("Polynomail String 1:"+str);
		   return str;
	   }
	
	Polynomial multiply(Polynomial b){
		Polynomial c;
		if(this.poly==null){
			c=new Polynomial(null, null);
		}
		if(b.poly==null){
			c=new Polynomial(null, null);
		}
		Map<Integer, Integer> map_a= this.getMap();
		Map<Integer, Integer> map_b= b.getMap();
		Map<Integer, Integer> map_c= new HashMap<>();
		for(Map.Entry<Integer, Integer> entry1: map_a.entrySet()){
			int power1=entry1.getKey();
			int coeff1=entry1.getValue();
			for(Map.Entry<Integer, Integer> entry2: map_b.entrySet()){
				int power2= entry2.getKey();
				int coeff2=entry2.getValue();
				int power3= power1+power2;
				int coeff3= coeff1*coeff2;
				if(!map_c.containsKey(power3)){
					map_c.put(power3,  coeff3);
				}
				else{
					int existingCoeff= map_c.get(power3);
					int newCoeff= coeff3+ existingCoeff;
					map_c.put(power3, newCoeff);
				}
			}
		}	
		String poly= generateString(map_c);
		c=new Polynomial(poly, map_c);
		return c;
	}
	
	 public Polynomial add(Polynomial a) {
		 	if(this.poly == null) return a;
		 	else if(a.poly == null) return this;
	        Map<Integer, Integer> map_a = this.map;
	        Map<Integer, Integer> map_b = a.map;
	        Map<Integer, Integer> map_result = new HashMap<>();
	        int coeff;

	       for(Map.Entry< Integer,Integer> pow:map_a.entrySet()) {
	            if(map_b.containsKey(pow.getKey())) {
	                coeff = pow.getValue() + map_b.get(pow.getKey());
	                map_result.put(pow.getKey(), coeff);
	            }
	            else
	                map_result.put(pow.getKey(), pow.getValue());
	        }
	        for(Map.Entry< Integer,Integer> pow:map_b.entrySet()) {
	            if(!map_result.containsKey(pow.getKey()))
	                map_result.put(pow.getKey(), pow.getValue());
	        }

	        //System.out.println(map_result);
	       String poly = generateString(map_result);

	       Polynomial result = new Polynomial(poly, map_result);
	       //Polynomial result = new Polynomial();
	        return result;
	    }
	 
	 public Polynomial sub(Polynomial a) {
		 	if(this.poly == null) return a;
		 	else if(a.poly == null) return this;
	        Map<Integer, Integer> map_a = this.map;
	        Map<Integer, Integer> map_b = a.map;
	        Map<Integer, Integer> map_result = new HashMap<>();
	        int coeff;

	       for(Map.Entry< Integer,Integer> pow:map_a.entrySet()) {
	            if(map_b.containsKey(pow.getKey())) {
	                coeff = pow.getValue() - map_b.get(pow.getKey());
	                map_result.put(pow.getKey(), coeff);
	            }
	            else
	                map_result.put(pow.getKey(), pow.getValue());
	        }
	        for(Map.Entry< Integer,Integer> pow:map_b.entrySet()) {
	            if(!map_result.containsKey(pow.getKey()))
	                map_result.put(pow.getKey(), -1*pow.getValue());
	        }
	       String poly = generateString(map_result);

	       Polynomial result = new Polynomial(poly, map_result);
	        return result;
	    }
	
	public static void main(String []args){
        String str="-x-1";
        System.out.println("Polynomial 1: "+str);
        Polynomial a=new Polynomial(str);
        str="x+1";
        System.out.println("Polynomial 2: "+str);
        Polynomial b= new Polynomial(str);
        Polynomial c= a.add(b);
        System.out.println("Addition result: "+ c.getPoly());
        Polynomial d= a.sub(b);
        System.out.println("Subraction result: "+ d.getPoly());
        Polynomial e= a.multiply(b);
        System.out.println("Multiplication result: "+ e.getPoly());
       }
}
