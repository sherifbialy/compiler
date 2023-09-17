package csen1002.main.task6;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Write your info here
 * 
 * @name Sherif Moataz
 * @id 46-1163
 * @labNumber 12
 */

public class CfgFirstFollow {
	ArrayList<String> vArrList;
	ArrayList<String> rulesPerVarArrList;
	ArrayList<String> terms;


	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	public CfgFirstFollow(String cfg) {
		// TODO Auto-generated constructor stub
		String[] parts=cfg.split("#");
		String v=parts[0];
		String terminals=parts[1];
		String rules=parts[2];
		String[] rulesPerVar=rules.split(";");
		String[] Varray=v.split(";");
		vArrList=new ArrayList<>(List.of(Varray));
		terms=new ArrayList<>(List.of(terminals));
		rulesPerVarArrList=new ArrayList<>(List.of(rulesPerVar));



	}

	/**
	 * Calculates the First Set of each variable in the CFG.
	 * 
	 * @return A string representation of the First of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String first() {
		// TODO Auto-generated method stub
//		HashMap<String, ArrayList<String>> table=new HashMap<>();
//		HashMap<String, HashSet<String>> tableDuplicateChecker=new HashMap<>();
		//System.out.println(rulesPerVarArrList.get(0));



		Hashtable<String, ArrayList<String>> table=new Hashtable<>();
		Hashtable<String, HashSet<String>> tableDuplicateChecker=new Hashtable<>();


			boolean change=true;
			//boolean firstPass=true;

			while (change){
				change=false;
				for (String v:
						vArrList) {
					String rules="";
					for (String rule:
							rulesPerVarArrList) {
						if(v.equals(rule.charAt(0)+"")){
							rules=rule;
							break;
						}

					}
					String[] r=rules.split("/");
					String r1=r[1];
			ArrayList<String> rulesArr=new ArrayList<>(List.of(r1.split(",")));
					HashSet set;
					ArrayList first;
					if(tableDuplicateChecker.get(v)==null){
						set=new HashSet<>();
					}
					else{
						set=tableDuplicateChecker.get(v);
						}

				if(table.get(v)==null){
					first=new ArrayList<>();
				}
				else{
					first=table.get(v);
				}

			for (String rule:
				rulesArr ) {





//					if(tableDuplicateChecker.get(v)==null){
//						set=new HashSet<>();
//					}
//				if(table.get(v)==null){
//					first=new ArrayList<>();
//				}
				//System.out.println(rule.charAt(0));
					if ((rule.charAt(0)+"").matches("[a-z]")) {


							if(!set.contains(rule.charAt(0)+"")){
								first.add(rule.charAt(0) + "");
								table.put(v, first);
								set.add(rule.charAt(0)+"");
								//tableDuplicateChecker.put(v,set);
								change=true;


					}
					}

					else if ((rule.charAt(0)+"").matches("[A-Z]")) {
						//System.out.println(table);
//
						ArrayList<String> firstOfOtherVar = table.get(rule.charAt(0) + "");
						if (!(rule.charAt(0) + "").equals(v)) {

							if (!(firstOfOtherVar == null)) {
								if (!set.contains("first of " + rule.charAt(0))) {
//									first.add("first of " + rule.charAt(0));
//									tableDuplicateChecker.put(v,set);
//									change = true;

									for (String t :
											firstOfOtherVar) {
										if (!set.contains(t)) {
											first.add(t);

											//table.put(v, first);
											change = true;
											//tableDuplicateChecker.put(v, set);
											set.add(t);
										}

									}
								}


							}


							//tableDuplicateChecker.put(v,set);

							//change=true;

//
						}
//						System.out.println(!table.entrySet().contains(null));
//						System.out.println(table.entrySet());

						//System.out.println(table);


				}






			}
			table.put(v,first);
			tableDuplicateChecker.put(v,set);
					//System.out.println(table);




					}
				}

			epsilonCheck(table,tableDuplicateChecker);








	String result="";
		for (String v:
	vArrList) {
			ArrayList<String> finalFirst = table.get(v);
		    Collections.sort(finalFirst);
		    result+=v+"/";
			for (String s:
				 finalFirst) {
				result+=s;
			}
			result+=";";
		}
		result = result.substring(0, result.length()-1);




		return result;}


	public void epsilonCheck(Hashtable <String,ArrayList<String>> table2,Hashtable<String,HashSet<String>> tableDuplicateChecker2){
		Hashtable <String,ArrayList<String>> table=table2;
		Hashtable<String,HashSet<String>> tableDuplicateChecker=tableDuplicateChecker2;



     System.out.println(table);
		boolean change1=true;
		while (change1){
			change1=false;
			//System.out.println(vArrList);
			for (String v:
					vArrList) {
				String rules="";
				for (String rule:
						rulesPerVarArrList) {
					if(v.equals(rule.charAt(0)+"")){
						rules=rule;
						break;
					}

				}
				String[] r=rules.split("/");
				String r1=r[1];
				ArrayList<String> rulesArr=new ArrayList<>(List.of(r1.split(",")));
                //System.out.println(rulesArr);
				ArrayList<String> first=table.get(v);

				HashSet set=tableDuplicateChecker.get(v);


//				for (String rule:
//					rulesArr ) {
//					if(!(rule.charAt(0)+"").equals("e")){
//						first.remove("e");
//						set.remove("e");
//					}
//				}



				HashSet<String> firstPerRule=new HashSet<>(table.get(v));
				System.out.println(firstPerRule+"firstPerinit");
				for (String rule:
						rulesArr ) {



					//firstPerRule.remove("e");

					//System.out.println(v);

//							if(tableDuplicateChecker.get(v)==null){
//								set=new HashSet<>();
//							}
//							if(table.get(v)==null){
//								first=new ArrayList<>();
//							}


					int ruleLength = rule.length();

                   System.out.println(v);
					//System.out.println(table.get(rule.charAt(i)));
					for (int i = 0; i < ruleLength; i++) {

						if((rule.charAt(0)+"").matches("[a-z]")){

							break;
						}
						boolean matches = (rule.charAt(i) + "").matches("[A-Z]");


						if((rule.charAt(i)+"").matches("[a-z]")){
							//firstPerRule.remove("e");
							System.out.println(firstPerRule+" terminal");
							System.out.println("terminal");
							System.out.println(v);
							System.out.println(rule.charAt(i));
							System.out.println(table);

							//firstPerRule.add(rule.charAt(i)+"");

								firstPerRule.add(rule.charAt(i)+"");
							    firstPerRule.remove("e");

							for (String f:
									firstPerRule ) {
								if(!set.contains(f)){
									set.add(f);
									first.add(f);

									change1=true;
								}
							}
                            tableDuplicateChecker.put(v,set);
							System.out.println(first+"add");
							table.put(v, first);

							break;
						}

						else if (matches) {
							System.out.println("variable");
							System.out.println(v);
							System.out.println(rule.charAt(i));

							System.out.println(firstPerRule+" variable");


                         ArrayList<String> test=table.get(rule.charAt(i)+"");


							if (test.contains("e")) {

								firstPerRule.addAll(test);
								if(i==ruleLength-1){
									if(!set.contains("e")){
									System.out.println("length");
										firstPerRule.add("e");
										set.add("e");
										tableDuplicateChecker.put(v,set);
									}
									if(i<ruleLength){
										firstPerRule.remove("e");
										System.out.println(firstPerRule);
									}

								}
								//System.out.println(rule.charAt(i));

								//System.out.println(firstPerRule);

								for (String f:
										firstPerRule ) {
									if(!set.contains(f)){
										set.add(f);
										first.add(f);
										change1=true;
									}
								}
								//System.out.println(rule.charAt(i));
								//System.out.println(first);
								tableDuplicateChecker.put(v,set);
								table.put(v,first);




								}
							else{
								firstPerRule.addAll(test);
								firstPerRule.remove("e");
//								table.put(v,first);
								//first.addAll(firstPerRule);



								break;
							}

							}
					}

//						else{
//							break;
//						}

							}
				table.remove(v);

				for (String f:
						firstPerRule ) {
					if(!set.contains(f)){
						set.add(f);
						first.add(f);
					}
				}
				System.out.println(first+"firstFinal");
				table.put(v,first);
				tableDuplicateChecker.put(v,set);


					}

				}}


	/**
	 * Calculates the Follow Set of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String follow() {
		// TODO Auto-generated method stub
		return null;
	}
	}
