package csen1002.main.task2;


import java.util.*;

/**
 * Write your info here
 * 
 * @name Sherif Moataz
 * @id 46-1163
 * @labNumber 12
 */

public class NfaToDfa {

	DFA result;

	/**
	 * Constructs a DFA corresponding to an NFA
	 *
	 * @param input A formatted string representation of the NFA for which an
	 *              equivalent DFA is to be constructed. The string representation
	 *              follows the one in the task description
	 */
	public NfaToDfa(String input) {
		// TODO Auto-generated constructor stub
		String[] splitInput = input.split("#");
		String alphabet=splitInput[1];

		String transitions=splitInput[2];
		String initialState=splitInput[3];
		String finalState=splitInput[4];
		String toGetTable = splitInput[0] + "#" + splitInput[2];
		HashMap epTable = epsilonClosureTable(toGetTable);

		result=DFAConstructor(alphabet,transitions,epTable,initialState,finalState);





	}

	/**
	 * @return Returns a formatted string representation of the DFA. The string
	 * representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub

		ArrayList States=result.States;
		ArrayList Transitions=result.Transitions;
		ArrayList finalStates=result.finalState;
		String alphabet=result.Alphabet;
		String initialState=result.initialState;
		String finalString="";

		for (int i = 0; i <States.size() ; i++) {
			if(i==States.size()-1){
				finalString+=States.get(i)+"#";
			}
			else{
				finalString+=States.get(i)+";";
			}
		}
		finalString+=alphabet+"#";
		for (int j = 0; j <Transitions.size() ; j++) {
			if(j==Transitions.size()-1){
				finalString+=Transitions.get(j)+"#";
			}
			else{
				finalString+=Transitions.get(j)+";";
			}
		}
		finalString+=initialState+"#";
		for (int k = 0; k <finalStates.size() ; k++) {
			if(k==finalStates.size()-1){
				finalString+=finalStates.get(k);
			}
			else{
				finalString+=finalStates.get(k)+";";
			}
		}

		return finalString;
	}
    public DFA DFAConstructor(String alphabet, String transitions, HashMap<Integer,HashSet<String>> epTable, String initialState, String finalState){

		ArrayList<String> States=new ArrayList<>();
		String[] nfaTransitionsSplit=transitions.split(";");
		ArrayList<String> dfaTransitions=new ArrayList<>();
		String[] alphabetSplit=alphabet.split(";");
		HashSet InitialState1= epTable.get(Integer.parseInt(initialState));
		String[] stringArray = (String[]) InitialState1.toArray(new String[InitialState1.size()]);
		String InitialState = String.join(",", stringArray);
		String InitalStateout=stateSort(InitialState);
		String[] acceptStates=finalState.split(";");
		Queue stateQueue=new ArrayDeque<>();

		String[] stateSplit=InitialState.split(",");

		States.add(stateSort(InitialState));
		stateQueue.add(stateSplit);
		while(!stateQueue.isEmpty()){
			String[] thisState=(String[]) stateQueue.remove();
			String thisStateString="";
			for (int i = 0; i < thisState.length; i++) {
				if(i==thisState.length-1){
					thisStateString+=thisState[i];
				}
				else{
					thisStateString+=thisState[i]+",";
				}

			}
			for (String literal: alphabetSplit) {
				boolean epsilonforthis=true;
				String stateToBeAdded="";
				ArrayList<String> stateToBeAddedArr=new ArrayList<>();
				for (String State: thisState) {
                  System.out.println(Arrays.toString(thisState));
					//System.out.println(State);

					for (String transition:nfaTransitionsSplit) {
						String[] transitionSplit=transition.split(",");
						if (transitionSplit[0].equals(State)){
							if(transitionSplit[1].equals(literal)) {
								epsilonforthis=false;
								HashSet x= epTable.get(Integer.parseInt(transitionSplit[2]));
								String[] xArr = (String[]) x.toArray(new String[x.size()]);

								String x1="";
								for(String xS:xArr){

									x1+=xS+",";
									//System.out.println(x1);
								}





								//System.out.println(x);
								if(!stateToBeAddedArr.contains(x1)){
								stateToBeAddedArr.add(x1);
							}




								//System.out.println(stateToBeAdded);

								}



							}

				}}
				for (String s:stateToBeAddedArr
					 ) {
					stateToBeAdded+=s;

				}
				System.out.println(literal);
				System.out.println(thisStateString);
				System.out.println(stateToBeAdded);
				//new
//				String[] duplicateclean=stateToBeAdded.split(",");
//				Object[] statetobeadded= Arrays.stream(duplicateclean).distinct().toArray();
//				stateToBeAdded="";
//				for(Object xS:statetobeadded){
//					stateToBeAdded+=xS+",";
//				}
				//new end

//				if(stateToBeAdded.length()>2&&!stateToBeAdded.contains(",")){
//					String replacement="";
//					for (int i = 0; i < stateToBeAdded.length(); i++) {
//						if(i==stateToBeAdded.length()-1){
//							replacement+=stateToBeAdded.charAt(i);
//						}
//						else{
//							replacement+=stateToBeAdded.charAt(i)+",";
//						}
//					}
//					stateToBeAdded=replacement;
//				}
//				    if(!stateToBeAdded.contains(",")){
//						char[] temp=stateToBeAdded.toCharArray();
//						String temp1="";
//						for (char c:temp
//							 ) {
//							if(!temp1.contains(c+"")){
//							temp1+=c+",";}
//						}
//						stateToBeAdded=temp1;
//					}
					//System.out.println(stateToBeAdded);
					if(epsilonforthis){
						if(!States.contains("-1")){
							States.add("-1");
							for (String literalDead:alphabetSplit) {
								String Transition="-1"+","+literalDead+","+"-1";
								dfaTransitions.add(Transition);
							}
						}
						String Transition=stateSort(thisStateString)+","+literal+","+"-1";
						dfaTransitions.add(Transition);
					}
					else{
						 stateToBeAdded=stateSort(stateToBeAdded);
//						 String bigState="";
//						 boolean bigStatebool=false;
//						 Iterator StateIterator=States.iterator();
//						 while (StateIterator.hasNext()){
//							bigState=(String) StateIterator.next();
//							 if(bigState.contains(stateToBeAdded)){
//								 bigStatebool=true;
//								 break;
//							 }
//						 }
//						 if(bigStatebool){
//						String Transition=stateSort(thisStateString)+","+literal+","+bigState;
//						 dfaTransitions.add(Transition);}
						// System.out.println(stateToBeAdded);
						if(!States.contains(stateToBeAdded)){

							States.add(stateToBeAdded);
							String[] stateToBeAddedSplit=stateToBeAdded.split("/");
							stateQueue.add(stateToBeAddedSplit);
							String Transition=stateSort(thisStateString)+","+literal+","+stateToBeAdded;
							dfaTransitions.add(Transition);
						}
						else{
							//System.out.println(stateToBeAdded);
							String Transition=stateSort(thisStateString)+","+literal+","+stateToBeAdded;
									//States.get(States.indexOf(stateToBeAdded));
							dfaTransitions.add(Transition);

						}
					}

			}






			//States.add();

		}

		ArrayList<String> finalStates=new ArrayList<>();
		Iterator StateChecker=States.iterator();

		while (StateChecker.hasNext()){
			String thisState= (String) StateChecker.next();
			for (String acceptState: acceptStates) {
				if(thisState.contains(acceptState)&&!finalStates.contains(thisState)){
					finalStates.add(thisState);
				}
			}





		}
		//System.out.println(sortedStates.stream().iterator());


//		for(String substate:stateSplit){
//
//			alteredState+=substate+
//		}

		Comparator<? super String> MyComparator=new MyComparator() {
			@Override
			public int compare(String o1, String o2) {
				return super.compare(o1, o2);
			}
		};
		Collections.sort(dfaTransitions,MyComparator);
		Comparator<? super String> MyComparator1 = new MyComparator1() {
			@Override
			public int compare(String o1, String o2) {
				return super.compare(o1, o2);
			}
		};
		Collections.sort(finalStates,MyComparator1);
		Collections.sort(States,MyComparator1);
		DFA finalDFA=new DFA(States,alphabet,dfaTransitions,InitalStateout,finalStates);
    return finalDFA;

	}
	public static String stateSort(String state){
		ArrayList<Integer> sortedStates=new ArrayList<Integer>();
//		state.replaceAll(",","/");
 	    String[] thisStateArr=state.split(",");
		for (String t:thisStateArr
			 ) {
			t.replaceAll(" ","");
			sortedStates.add(Integer.parseInt(t));

		}

		Collections.sort(sortedStates);
	   Object[] finalArr=sortedStates.toArray();
		String finalString="";
		for (int i=0;i<finalArr.length;i++){
			if(i==finalArr.length-1){
				finalString+=String.valueOf(finalArr[i]);
			}
			else{
				finalString+=String.valueOf(finalArr[i]) +"/";
			}

		}
		//
		// System.out.println(finalString);
		return finalString;

	}


	public static HashMap<Integer, HashSet<String>> epsilonClosureTable(String input) {
		HashMap<Integer, HashSet<String>> epsilonClosure = new HashMap<Integer, HashSet<String>>();
		String[] parts = input.split("#");
		String[] states = parts[0].split(";");
		String[] transitions = parts[1].split(";");


		for (String state : states) {
			int s = Integer.parseInt(state);
			epsilonClosure.put(s, new HashSet<String>());
			epsilonClosure.get(s).add(state);
		}


		boolean closureChanged = true;
		while (closureChanged) {
			closureChanged = false;
			for (String transition : transitions) {
				String[] t = transition.split(",");
				if (t[1].equals("e")) {
					int source = Integer.parseInt(t[0]);
					int destination = Integer.parseInt(t[2]);
					if (epsilonClosure.get(source).addAll(epsilonClosure.get(destination))) {
						closureChanged = true;
					}
				}
			}
		}


		HashMap<Integer, HashSet<String>> result = new HashMap<Integer, HashSet<String>>();
		for (String state : states) {
			int s = Integer.parseInt(state);
			HashSet<String> closureSet = new HashSet<String>();
			closureSet.add(state);
			closureSet.addAll(epsilonClosure.get(s));
			List<String> sortedClosure = new ArrayList<String>(closureSet);
			Collections.sort(sortedClosure);
			HashSet<String> distinctClosure = new HashSet<String>(sortedClosure);
			StringBuilder sb = new StringBuilder();
			for (String closure : distinctClosure) {
				sb.append(closure);
				if (distinctClosure.size() == 1 && closure.equals(state)) {
					sb.append(",");
				}
			}
			result.put(s, distinctClosure);
		}

		for (Integer state : result.keySet()) {
			HashSet<String> closure = result.get(state);
			System.out.print(state + ": ");
			for (String s : closure) {
				System.out.print(s);
				if (closure.size() == 1 && s.equals(String.valueOf(state))) {
					System.out.print(",");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
		return result;
	}








//	public static ArrayList<String[]> getEpTable(String input) {
//		String[] splitInput = input.split("#");
//		String states = splitInput[0];
//		String transitions = splitInput[1];
//		String[] stateSplit=states.split(";");
//		ArrayList<String[]> table = new ArrayList<>();
//		boolean ChangePerRow=false;
//		boolean noChangePerRow = true;
//		//boolean noChangeFalse=false;
//		//int stateLength=states.length();
//		//int stateCounter=0;
//		String[] transitionsSplit = transitions.split(";");
//		//
//		// System.out.println(transitionsSplit[2].toString());
//		for (int i = 0; i < stateSplit.length; i++) {
//			String[] row = new String[2];
//			//to be tested
//			row[0] = i + "";
//			row[1] = i + "";
//			table.add(row);
//
//		}
//
//		boolean[] rowChange=new boolean[table.size()];
//		//System.out.println(table.get(0)[1]);
//		for (int k = 0; k < stateSplit.length; k++) {
//
//
//			for (int j = 0; j < transitionsSplit.length; j ++) {
//				String[] transitionsSplit2 =transitionsSplit[j].split(",");
//
//					if (transitionsSplit2[0].equals(k + "")){
//						if(transitionsSplit2[1].equals("e")) {
//
//							String[] update = table.get(k);
//
//							update[1] += "," + transitionsSplit2[2];
//
//							table.set(k, update);
//
//						} }
//				}
//
//
//				}
//		int test=50;
//
//		while(true) {
//
//			String candidate = null;
//			for (String[] thisRow : table) {
//				String statesInEClosure = thisRow[1];
//				//System.out.print(statesInEClosure);
//				String[] statesInEClosureArr = statesInEClosure.split(",");
//
//
//				//System.out.println(statesInEClosureArr.toString());
//
//				for (int b = 1; b < statesInEClosureArr.length; b++) {
//
//
//					candidate = table.get(Integer.parseInt(statesInEClosureArr[b]))[1];
//
//					//String[] candidateArr= rowOfAdded[1].split(",");
////					String[] candidateArr = rowOfAdded[1].split(",");
////					candidate = "";
////					for (int i = 1; i < candidateArr.length; i++) {
////						if (i == candidateArr.length - 1) {
////							candidate += candidateArr[i];
////							//System.out.println(candidateArr[i]);
////						} else {
////							candidate += candidateArr[i] + ",";
////						}
//
//					//}
//
//
//					//System.out.println(candidate);
//					if (thisRow[1].contains(candidate)) {
//						noChangePerRow=false;
//					} else {
//						String replacementString = statesInEClosure + "," + candidate;
//						//statesInEClosure +=","+ candidate;
//						String candidate2 = "";
//						Object[] duplicateChecker = Arrays.stream(replacementString.split(",")).distinct().toArray();
//						for (int f = 0; f < duplicateChecker.length; f++) {
//							if (f == duplicateChecker.length - 1) {
//								candidate2 += (String) duplicateChecker[f];
//							} else {
//								candidate2 += (String) duplicateChecker[f] + ",";
//							}
//
//						}
//
//						String[] replacement = new String[2];
//						replacement[0] = thisRow[0];
//						replacement[1] = candidate2;
//						table.set(Integer.parseInt(thisRow[0]), replacement);
//						noChangePerRow=true;
//
//					}
//
//				}
//
//
//			}
//			if (noChangePerRow){
//				break;
//
//			}
//
//			if(candidate.length()==1){
//				for (String[] thisRow : table) {
//					thisRow[1]+=",";
//				}
//
//
//			}
//
//		}
//            Iterator printIterator=table.iterator();
//		    while (printIterator.hasNext()){
//				String[] he=(String[]) printIterator.next();
//				System.out.println(he[1]);
//			}
//
//				return table;
//	}
	class DFA{
		public ArrayList<String> States;
		public String Alphabet;
		public ArrayList<String> Transitions;
		public String initialState;
		public ArrayList<String> finalState;

		public DFA(ArrayList<String> States, String Alphabet, ArrayList<String> Transitions, String initialState, ArrayList<String> finalState){
			this.States=States;
			this.finalState=finalState;
			this.initialState=initialState;
			this.Alphabet=Alphabet;
			this.Transitions=Transitions;}
	}
	abstract class MyComparator implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			List<String> list1 = Arrays.asList(s1.split(","));
			List<String> list2 = Arrays.asList(s2.split(","));

			String[] states1 = list1.get(0).split("/");
			String[] states2 = list2.get(0).split("/");

			for (int i = 0; i < states1.length && i < states2.length; i++) {
				int cmp = Integer.compare(Integer.parseInt(states1[i]), Integer.parseInt(states2[i]));
				if (cmp != 0) {
					return cmp;
				}
			}
			if (states1.length != states2.length) {
				return Integer.compare(states1.length, states2.length);
			}

			return list1.get(1).compareTo(list2.get(1));
		}

	}
		abstract class MyComparator1 implements Comparator<String> {
			@Override

			public int compare(String o1, String o2) {
				String[] s1Split=o1.split("/");
				String[] s2Split=o2.split("/");


				for (int i = 0; i < Math.min(s1Split.length,s2Split.length); i++) {
					if(Integer.parseInt(s1Split[i])==-1){
						return -1;
					}
					if(Integer.parseInt(s1Split[i])>Integer.parseInt(s2Split[i])){
						return 1;
					} else if (Integer.parseInt(s1Split[i])<Integer.parseInt(s2Split[i])) {
						return -1;

					}

				}
				if(s1Split.length>s2Split.length){
					return 1;
				} else if (s1Split.length<s2Split.length) {
					return -1;
				}
				else{
				return 0;}

			}}

	public static void main(String[] args){
		//0;1;2;3;4;5;6;7;8;9;10;11#k;n;w#0,w,1;1,n,2;2,e,0;2,e,4;3,e,0;3,e,4;4,e,11;5,e,6;6,e,7;6,e,9;7,k,8;8,e,7;8,e,9;9,e,11;10,e,3;10,e,5#10#11"
		//NfaToDfa test=new NfaToDfa("0;1;2;3;4;5;6;7;8;9;10;11#k;n;w#0,w,1;1,n,2;2,e,0;2,e,4;3,e,0;3,e,4;4,e,11;5,e,6;6,e,7;6,e,9;7,k,8;8,e,7;8,e,9;9,e,11;10,e,3;10,e,5#10#11");
//        //ArrayList arrayList=NfaToDfa.DFAConstructor("k;n;w","0,w,1;1,n,2;2,e,0;2,e,4;3,e,0;3,e,4;4,e,11;5,e,6;6,e,7;6,e,9;7,k,8;8,e,7;8,e,9;9,e,11;10,e,3;10,e,5", getEpTable("0;1;2;3;4;5;6;7;8;9;10;11#0,w,1;1,n,2;2,e,0;2,e,4;3,e,0;3,e,4;4,e,11;5,e,6;6,e,7;6,e,9;7,k,8;8,e,7;8,e,9;9,e,11;10,e,3;10,e,5"), "10", "11");
//		Iterator testIterator=arrayList.iterator();
//		while (testIterator.hasNext()){
//			System.out.println(testIterator.next());
//		}
		//EpsilonClosureTable.generateEpsilonClosureTable("0;1;2;3;4;5;6;7;8;9;10;11#0,w,1;1,n,2;2,e,0;2,e,4;3,e,0;3,e,4;4,e,11;5,e,6;6,e,7;6,e,9;7,k,8;8,e,7;8,e,9;9,e,11;10,e,3;10,e,5");
	}
}
