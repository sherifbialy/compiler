package csen1002.main.task5;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Sherif Moataz
 * @id 46-1163
 * @labNumber 12
 */

public class CfgLeftRecElim {
	HashMap<String, ArrayList<String>> table;
	String V;

	ArrayList<String> newV;
	ArrayList<String> Varraylist;
	String T;

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	public CfgLeftRecElim(String cfg) {
		// TODO Auto-generated constructor stub
		String[] parts=cfg.split("#");
		V=parts[0];
		String[] Varray=V.split(";");
		//System.out.println(Varray.toString());
		Varraylist=new ArrayList<>();

		for (String s:
				Varray
			 ) {
			Varraylist.add(s);

		}
		T=parts[1];
		String[] rulesPerVar=parts[2].split(";");
		table=new HashMap<String, ArrayList<String>>();
		for (String s:
				rulesPerVar ) {
			String[] sourceSplit=s.split("/");
			String source=sourceSplit[0];
			String[] splitDestinations=sourceSplit[1].split(",");
			ArrayList<String> tobeAdded=new ArrayList<>();
			tobeAdded.addAll(List.of(splitDestinations));
			table.put(source,tobeAdded);



		}
		for (String i : table.keySet()) {
			System.out.println("key: " + i + " value: " + table.get(i));
		}

	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result=V;
		if (!newV.isEmpty()){
		result+=";";
		for (int i = 0; i < newV.size() ; i++) {
			if(i== newV.size()-1){
				result+=newV.get(i)+"#";
			}
			else {
				result+=newV.get(i)+";";
			}

		}}
		else {
			result+="#";
		}
		result+=(T+"#");
		for (String v:
				Varraylist ) {
			ArrayList<String> list = table.get(v);
			//list.remove("eremoved");
			//System.out.println(list);
			//Collections.sort(list);
			result += v + "/";
			for (int i = 0; i < list.size(); i++) {
				String y = list.get(i);
				if (i == list.size() - 1) {
					result += y+";";

				}
				else{
					result+=y+",";
				}
			}
		}
		//System.out.println(newV);
		for (String newV:newV
			 ) {
			ArrayList<String> list1 = table.get(newV);

			result +=  newV + "/";
			for (int i = 0; i < list1.size(); i++) {
				String y = list1.get(i);
				if (i == list1.size() - 1) {
					//System.out.println(1);
					result += y+";";

				}
				else{
					result+=y+",";
				}

		}

		}
		result=result.substring(0,result.length()-1);
		return result;
	}

	/**
	 * Eliminates Left Recursion from the grammar
	 */
	public void eliminateLeftRecursion() {
		// TODO Auto-generated method stub
		newV=new ArrayList<>();
		for (String var:
				Varraylist
			 ) {
			if(Varraylist.indexOf(var)==0){
				ArrayList<String> current=table.get(var);
				ArrayList<String> varDash=new ArrayList<>();
				ArrayList<String> varNoDash=new ArrayList<>();
				boolean leftRecursive=false;
				for (String currentString:
						current) {
					if (currentString.substring(0,1).equals(var)){
						leftRecursive=true;
					}
				}
				if(leftRecursive){
					for (String currentString:
							current) {
						if (currentString.substring(0,1).equals(var)){
							String toBeAdded = currentString.substring(1)+var + "'";

							varDash.add(toBeAdded);


							//current.remove(currentString);
						}
						else{
							String toBeAltered=currentString+var+"'";
							varNoDash.add(toBeAltered);
						}
					}
					table.put(var,varNoDash);
					newV.add(var+"'");
					if(!varDash.contains("e")){
						varDash.add("e");
					}
					table.put(var+"'",varDash);
			}}
			else{

			replaceProductions(Varraylist.indexOf(var));

            ArrayList<String> current1=table.get(var);
			ArrayList<String> varDash1=new ArrayList<>();
			ArrayList<String> varNoDash1=new ArrayList<>();
			boolean leftRecursive1=false;
			for (String currentString:
				current1) {
			if (currentString.substring(0,1).equals(var)){
				leftRecursive1=true;
			}
		}
		if(leftRecursive1){
			for (String currentString:
					current1) {
				if (currentString.substring(0,1).equals(var)){
					String toBeAdded = currentString.substring(1)+var + "'";

					varDash1.add(toBeAdded);


					//current.remove(currentString);
				}
				else{
					String toBeAltered=currentString+var+"'";
					varNoDash1.add(toBeAltered);
				}
			}
			table.put(var,varNoDash1);
			newV.add(var+"'");
			if(!varDash1.contains("e")){
				varDash1.add("e");
			}
			table.put(var+"'",varDash1);
		}}



}
		for (String h : table.keySet()) {
		System.out.println("key: " + h + " value: " + table.get(h));
		}}
public void replaceProductions(int index){

		//for (int i = 1; i <= index; i++) {

            //System.out.println(Varraylist);

	        ArrayList<String> dontAdd=new ArrayList<>();
				ArrayList<String> current=table.get(Varraylist.get(index));
				int oldSize=table.get(Varraylist.get(index)).size();
				int newSize=0;
				while (newSize!=oldSize){
					ArrayList<String> prodReplaced=new ArrayList<>();
					current=table.get(Varraylist.get(index));
					System.out.println(oldSize+","+newSize);
					oldSize=newSize;
	   for (String s:
			current ) {


			for (int j = 0; j <= index-1; j++) {
				//System.out.println(index+","+j);

				ArrayList<String> currentReplace=table.get(Varraylist.get(j));

				//System.out.println(s);
				//System.out.println(Varraylist.get(j));


					if(s.substring(0,1).equals(Varraylist.get(j))){

						dontAdd.add(s);
						for (String t:
							 currentReplace) {

							String sNew=t+s.substring(1);
							prodReplaced.add(sNew);

						}
//
					}
					else{
//
						prodReplaced.add(s);
//						}
					}


			}
		   //prodReplaced.removeIf(n->dontAdd.contains(n));

		   prodReplaced.removeIf(n->dontAdd.contains(n));
		   LinkedHashSet<String> Link=new LinkedHashSet<>(prodReplaced);
		   ArrayList<String> prodReplaced2=new ArrayList<>(Link);
		   System.out.println(Varraylist.get(index)+","+prodReplaced2);
		   table.put(Varraylist.get(index),prodReplaced2);
		   newSize=table.get(Varraylist.get(index)).size();



				}}





		}


}
