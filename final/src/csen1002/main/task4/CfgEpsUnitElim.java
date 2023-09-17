package csen1002.main.task4;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Sherif Moataz
 * @id 46-1163
 * @labNumber 12
 */

public class CfgEpsUnitElim {

	static HashMap<String, HashSet<String>> table;
	String V;
	String T;
	

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	public CfgEpsUnitElim(String cfg) {
		String[] parts=cfg.split("#");
		V=parts[0];
		T=parts[1];
		String[] rulesPerVar=parts[2].split(";");
		table=new HashMap<String,HashSet<String>>();
		for (String s:
			rulesPerVar ) {
			String[] sourceSplit=s.split("/");
			String source=sourceSplit[0];
			String[] splitDestinations=sourceSplit[1].split(",");
			HashSet<String> tobeAdded=new HashSet<>();
			tobeAdded.addAll(List.of(splitDestinations));
			table.put(source,tobeAdded);



		}
		for (String i : table.keySet()) {
			System.out.println("key: " + i + " value: " + table.get(i));
		}

		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		 String result=V+"#"+T+"#";
		String[] vCompare=V.split(";");
		for (String v:
			vCompare ) {
			ArrayList<String> list=new ArrayList<>(table.get(v));
			list.remove("eremoved");
			//System.out.println(list);
			Collections.sort(list);
			result+=v+"/";
			for (int i = 0; i < list.size(); i++) {
				String y=list.get(i);
				if(i== list.size()-1){
					result+=y;
				}
				else{
					result+=y+",";
				}

			}

			result+=";";
		}
		result=result.substring(0,result.length()-1);
        
		return result;
	}

	/**
	 * Eliminates Epsilon Rules from the grammar
	 */
	public void eliminateEpsilonRules() {
		boolean hasEpsilons=true;
		while(hasEpsilons){
			for (String i : table.keySet()) {
				//System.out.println(i);
				HashSet<String> current=table.get(i);
				if(current.contains("e")){
					current.remove("e");
					current.add("eremoved");
					table.put(i,current);
					String toLookUp=i;
					for (String j: table.keySet()
						 ) {
						HashSet<String> Destination=table.get(j);
						HashSet<String> newToBeAdded=new HashSet<String>();
						if(Destination.contains(toLookUp)&&!Destination.contains("eremoved")){

								Destination.add("e");
								//
							// System.out.println("foo");

							for (String x:Destination
							) {
								//System.out.println(x);
								if(x.contains(toLookUp)) {


									List<String> toBe = removeCharCombinations(x,toLookUp);
									toBe.remove("");




									newToBeAdded.addAll(toBe);

								}

							}}

							else{


							List<String> toBe=new ArrayList<>();
						for (String x:Destination
							 ) {
                                    //System.out.println(x);
							        if(x.contains(toLookUp)) {
										toBe.addAll(removeCharCombinations(x,toLookUp));
										toBe.remove("");


									}
									newToBeAdded.addAll(toBe);

								}}
							//System.out.println(newToBeAdded+" "+toLookUp);
							Destination.addAll(newToBeAdded);
							table.put(j,Destination);

							}

						}}
					hasEpsilons=false;
					for (String f: table.keySet()
						 ) {
						HashSet<String> checker=table.get(f);
						if(checker.contains("e")){
							hasEpsilons=true;
						}
					}
		}
		for (String i : table.keySet()) {
			System.out.println("key: " + i + " value: " + table.get(i));
		}
		}



	public static List<String> removeCharCombinations(String input, String c) {
		List<String> combinations = new ArrayList<>();
		int length = input.length();


		List<Integer> indices = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			if (input.charAt(i) == c.charAt(0)) {
				indices.add(i);
			}
		}


		for (int i = 0; i < (1 << indices.size()); i++) {
			StringBuilder sb = new StringBuilder();
			int j = 0;
			for (int k = 0; k < length; k++) {
				if (j < indices.size() && indices.get(j) == k) {
					if ((i & (1 << j)) == 0) {
						sb.append(c);
					}
					j++;
				} else {
					sb.append(input.charAt(k));
				}
			}
			combinations.add(sb.toString());
		}

		Set<String> set = new HashSet<>(combinations);
		set.remove(input);
		combinations.clear();
		combinations.addAll(set);

		return combinations;
	}


		// TODO Auto-generated method stub

	/**
	 * Eliminates Unit Rules from the grammar
	 */
	public void eliminateUnitRules() {
		boolean hasVars=true;
		while(hasVars){
			for (String i : table.keySet()) {
				//System.out.println(i);
				HashSet<String> current=table.get(i);
				HashSet<String> newCurrent=table.get(i);
				ArrayList<String> currentList=new ArrayList<>(current);

				HashSet<String> tobeAdded=new HashSet<>();
					for(String j:currentList) {
						if (j.matches("[A-Z]")) {
							//System.out.println(j);
                            if(table.get(j)!=null){
							tobeAdded.addAll(table.get(j));}

						}


					}
					//currentList.removeIf(n -> (n.matches("[A-Z]")));
				ArrayList<String> alreadyRemoved=new ArrayList<>();
				for (String c: currentList
					 ) {
					if(c.matches("[A-Z]")){
						alreadyRemoved.add(c);
					}

				}
				currentList.removeIf(n -> (n.matches("[A-Z]")));
				tobeAdded.removeIf(n->alreadyRemoved.contains(n));

					current.clear();
					newCurrent.addAll(currentList);
					//current.toArray();

				    newCurrent.addAll(tobeAdded);

					table.put(i,newCurrent);

				}
			hasVars=false;
			for (String f: table.keySet()
			) {
				HashSet<String> checker=table.get(f);
				for(String j:checker) {
					if (j.matches("[A-Z]")) {
						hasVars=true;




					}
			}}

		}






		}
		// TODO Auto-generated method stub



}
