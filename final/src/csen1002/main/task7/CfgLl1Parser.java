package csen1002.main.task7;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Sherif Moataz
 * @id 46-1163
 * @labNumber 12
 */

public class CfgLl1Parser {

	HashMap<String,HashMap<String,String>> LL1table;

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param input A formatted string representation of the CFG, the First sets of
	 *            each right-hand side, and the Follow sets of each variable. The
	 *            string representation follows the one in the task description
	 */
	public CfgLl1Parser(String input) {
		// TODO Auto-generated constructor stub
		LL1table=new HashMap<String,HashMap<String,String>>();
		String[] parts=input.split("#");
		String vars=parts[0];
		String terminals=parts[1];
		String rules=parts[2];
		String firsts=parts[3];
		String follows=parts[4];
		String[] rulesPerVar=rules.split(";");
		String[] vArray=vars.split(";");
		String[] tArray=terminals.split(";");
		String[] firstsPerVar=firsts.split(";");
		String[] followsPerVar=follows.split(";");
		System.out.println(Arrays.toString(followsPerVar));
		ArrayList<String> terminalsArrayList=new ArrayList<>(List.of(tArray));
		terminalsArrayList.add("$");
        //table generation
		for (String v:
			 vArray) {
			HashMap<String,String> termHashMap=new HashMap<>();
			for (String t:
				 terminalsArrayList) {
				termHashMap.put(t,"");

			}
			LL1table.put(v,termHashMap);
		}

		//table filling
		//first
		for (int i = 0; i < rulesPerVar.length; i++) {
			String[] ruleSplit=rulesPerVar[i].split("/");
			String currentVar=ruleSplit[0];
			String[] rulesOnly=ruleSplit[1].split(",");
			String[] firstSplit=firstsPerVar[i].split("/");
			String[] firstOnly=firstSplit[1].split(",");
			HashMap<String,String> innerTable =LL1table.get(currentVar);
         //looping over rules and their corresponding first sets
			for (int j = 0; j < rulesOnly.length; j++) {


				String currentRule=rulesOnly[j];
				String correspondingFirst=firstOnly[j];
				//looping over corresponding first set and placing rule within inner table
				for (int k = 0; k < correspondingFirst.length(); k++) {
//					System.out.println(correspondingFirst+" out");
//					System.out.println(currentRule+" outRule");
					if(!(correspondingFirst.charAt(k)+"").contains("e")&&!(currentRule.contains("e"))){
//						System.out.println(correspondingFirst+" in");
//						System.out.println(currentRule+" inRule");
						innerTable.put(correspondingFirst.charAt(k)+"",currentRule);
					}

				}

			}
			LL1table.put(currentVar,innerTable);
			//System.out.println(LL1table);

		}
		//table filling
		//follow
        //looping over vars and getting their first sets
		for (int i = 0; i < firstsPerVar.length; i++) {
			String[] followsSplit=followsPerVar[i].split("/");
			//System.out.println(Arrays.toString(followsSplit));
			String followsOnly=followsSplit[1];
			//System.out.println(followsOnly);
			String[] firstSplit=firstsPerVar[i].split("/");
			String[] firstOnly=firstSplit[1].split(",");
			String currentVar=firstSplit[0];
			HashMap<String,String> innerTable =LL1table.get(currentVar);

			//looping over first sets and checking for epsilon
			for (int j = 0; j < firstOnly.length; j++) {


				String first=firstOnly[j];
				//System.out.println(first+" first");
//					System.out.println(currentRule+" outRule");
				if(first.contains("e")){
					//if epsilon exists, place e in all elements in follow set of var
					for (int k = 0; k < followsOnly.length(); k++) {
						//System.out.println(followsOnly.charAt(k)+" follow");
						innerTable.put(followsOnly.charAt(k)+"","e");
					}
				}
				}

			LL1table.put(currentVar,innerTable);
			}
				//String correspondingFirst=firstOnly[i];


		System.out.println(LL1table);

	}

	/**
	 * @param input The string to be parsed by the LL(1) CFG.
	 * 
	 * @return A string encoding a left-most derivation.
	 */
	public String parse(String input) {
		// TODO Auto-generated method stub

		//PDA construction
		String inputCopy=input;
		Stack<String> pdaStack=new Stack<>();
        pdaStack.push("$");
		pdaStack.push("S");
		ArrayList<String> result1=new ArrayList<>();
		//String previousDerivation="S";
		result1.add("S");
        while (!pdaStack.isEmpty()){
			String currentTop=pdaStack.peek();
			String currentChar="";
			if(inputCopy.length()>0){
			currentChar=inputCopy.charAt(0)+"";}
			else {
				if(!(pdaStack.peek().equals("$")&&!pdaStack.isEmpty())){
					String finalderivation=LL1table.get(pdaStack.pop()).get("$");
					if (finalderivation == "") {
						result1.add("ERROR");
						break;
					}
					else{
						result1.add(result1.get(result1.size()-1).substring(0,(result1.get(result1.size()-1)).length()-1));
						break;
					}

				}
				else {

					break;
				}


			}
			System.out.println(currentChar);
			System.out.println(pdaStack);

			//boolean matches=false;
			if(currentTop.matches("[A-Z]")){
				String derivation=LL1table.get(currentTop).get(currentChar);

				if(derivation!=""){
//					previousDerivation=previousDerivation.substring(0,previousDerivation.length()-1);
					String previousDerivation=result1.get(result1.size()-1);
					System.out.println(previousDerivation);
					//System.out.println(currentTop);
					String newDerivation="";
					boolean firstOccurrenceFound=false;
					for (int i = 0; i < previousDerivation.length(); i++) {

						if((previousDerivation.charAt(i)+"").equals(currentTop)){
							if(firstOccurrenceFound==false){
								firstOccurrenceFound=true;
								if(!derivation.equals("e")){
									newDerivation+=derivation;

								}
								else {
									newDerivation+="";
								}

							}
							else if(firstOccurrenceFound==true){
								newDerivation+=previousDerivation.charAt(i)+"";
							}

						}
							else {
								newDerivation+=previousDerivation.charAt(i)+"";
							}



					}
					//System.out.println(newDerivation);

					result1.add(newDerivation);
//
//					result1.add(previousDerivation+derivation);
//					previousDerivation=previousDerivation+derivation;

					pdaStack.pop();
					if(!derivation.equals("e")){
						for (int i = derivation.length()-1; i >= 0 ; i--) {
							pdaStack.push(derivation.charAt(i)+"");

						}
					}

					
			}
				else{
					result1.add("ERROR");
					break;
				}
			}
			else{
				if(currentTop.matches(currentChar)){
					inputCopy=inputCopy.substring(1);
					pdaStack.pop();
				}
				else{

					result1.add("ERROR");
					break;
				}
			}
		}
		String result="";
		if(inputCopy.length()>0){
			//result1.add("ERROR");
			for (String res:
				 result1) {
				result+=res+";";
			}
			return result.substring(0,result.length()-1);
		}
		else{
			for (String res:
					result1) {
				result+=res+";";
			}
			return result.substring(0,result.length()-1);
		}
		}
			

	}


