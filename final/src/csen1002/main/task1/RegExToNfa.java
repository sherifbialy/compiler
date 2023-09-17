package csen1002.main.task1;

import java.util.*;

/**
 * Write your info here
 *
 * @name Sherif Moataz
 * @id 46-1163
 * @labNumber 12
 */

public class RegExToNfa {
	Stack s=new Stack<NFA>();
	NFA f;
	int counter=0;

	//ArrayList<String> Alphabet;

	/**
	 * Constructs an NFA corresponding to a regular expression based on Thompson's
	 * construction
	 *
	 * @param input The alphabet and the regular expression in postfix notation for
	 *              which the NFA is to be constructed
	 */
	public RegExToNfa(String input) {
		// TODO Auto-generated constructor stub
		String[]Input=input.split("#");
		String alphabet=Input[0]+"#";


		for(int j=0;j<Input[1].length();j++){
			String currentElement = String.valueOf(Input[1].charAt(j));
			if(currentElement.equals("*")){
				NFA popped=(NFA)s.pop();
				ArrayList States=popped.States;
				State oldInitial=popped.initialState;
				State oldFinal=popped.finalState;
				State newInitial= new State();
				State newFinal=new State();
				States.add(newFinal);
				States.add(newInitial);
				ArrayList<ArrayList<String>> Transitions=popped.Transitions;
				ArrayList<String> Transition1=new ArrayList<>();
				Transition1.add(String.valueOf(newInitial.number));
				Transition1.add("e");
				Transition1.add(String.valueOf(newFinal.number));
				Transitions.add(Transition1);

				ArrayList<String> Transition2=new ArrayList<>();
				Transition2.add(String.valueOf(newInitial.number));
				Transition2.add("e");
				Transition2.add(String.valueOf(oldInitial.number));
				Transitions.add(Transition2);
				ArrayList<String> Transition3=new ArrayList<>();
				Transition3.add(String.valueOf(oldFinal.number));
				Transition3.add("e");
				Transition3.add(String.valueOf(newFinal.number));
				Transitions.add(Transition3);
				ArrayList<String> Transition4=new ArrayList<>();
				Transition4.add(String.valueOf(oldFinal.number));
				Transition4.add("e");
				Transition4.add(String.valueOf(oldInitial.number));
				Transitions.add(Transition4);
				NFA toBePushed=new NFA(States,alphabet,Transitions,newInitial,newFinal);
				s.push(toBePushed);








			}
			else if(currentElement.equals("|")){
				NFA popped1=(NFA)s.pop();
				NFA popped2=(NFA)s.pop();
				ArrayList States1=popped1.States;
				ArrayList States2=popped2.States;
				State oldInitial1=popped1.initialState;
				State oldFinal1=popped1.finalState;
				State oldInitial2=popped2.initialState;
				State oldFinal2=popped2.finalState;
				ArrayList<State> States=new ArrayList<State>();
				Iterator<State> stateIterator1=States1.iterator();
				while(stateIterator1.hasNext()){
					States.add(stateIterator1.next());
				}
				Iterator<State> stateIterator2=States2.iterator();
				while(stateIterator2.hasNext()){
					States.add(stateIterator2.next());
				}
				State newInitial= new State();
				State newFinal=new State();
				States.add(newFinal);
				States.add(newInitial);
				ArrayList<ArrayList<String>> Transitions1=popped1.Transitions;
				ArrayList<ArrayList<String>> Transitions2=popped2.Transitions;
				ArrayList<ArrayList<String>> Transitions=new ArrayList<>();

				Iterator<ArrayList<String>> transition1Iterator=Transitions1.iterator();
				Iterator<ArrayList<String>> transition2Iterator=Transitions2.iterator();
				while(transition1Iterator.hasNext()){
					ArrayList current=transition1Iterator.next();
					//System.out.print(current);
					Transitions.add(current);

				}
				while(transition2Iterator.hasNext()){
					ArrayList current=transition2Iterator.next();
					//System.out.print(current);
					Transitions.add(current);

				}
				ArrayList<String> Transition1=new ArrayList<>();
				Transition1.add(String.valueOf(newInitial.number));
				Transition1.add("e");
				Transition1.add(String.valueOf(oldInitial1.number));
				Transitions.add(Transition1);
				ArrayList<String> Transition2=new ArrayList<>();
				Transition2.add(String.valueOf(newInitial.number));
				Transition2.add("e");
				Transition2.add(String.valueOf(oldInitial2.number));
				Transitions.add(Transition2);
				ArrayList<String> Transition3=new ArrayList<>();
				Transition3.add(String.valueOf(oldFinal1.number));
				Transition3.add("e");
				Transition3.add(String.valueOf(newFinal.number));
				Transitions.add(Transition3);
				ArrayList<String> Transition4=new ArrayList<>();
				Transition4.add(String.valueOf(oldFinal2.number));
				Transition4.add("e");
				Transition4.add(String.valueOf(newFinal.number));
				Transitions.add(Transition4);
				//System.out.print(Transitions);
//			StringBuilder newAlphabet=new StringBuilder();
//			for (int i = 0; i < popped1.Alphabet.length() || i < popped2.Alphabet.length(); i++) {
//
//				// First choose the ith character of the
//				// first string if it exists
//				if (i < popped1.Alphabet.length())
//					if(!newAlphabet.toString().contains(popped1.Alphabet.charAt(i)+""));
//					newAlphabet.append(popped1.Alphabet.charAt(i));
//
//				// Then choose the ith character of the
//				// second string if it exists
//				if (i < popped2.Alphabet.length())
//					if(!newAlphabet.toString().contains(popped1.Alphabet.charAt(i)+""));
//					newAlphabet.append(popped2.Alphabet.charAt(i));
//			}
				NFA toBePushed=new NFA(States,alphabet,Transitions,newInitial,newFinal);
				s.push(toBePushed);


			}
			else if(currentElement.equals(".")){
				NFA secondOperand=(NFA)s.pop();
				NFA firstOperand=(NFA)s.pop();
				ArrayList<State> States2ndOp=secondOperand.States;
				ArrayList<State> States1stOp=firstOperand.States;
				State Initial2nd=secondOperand.initialState;
				State Final2nd=secondOperand.finalState;
				State Initial1st=firstOperand.initialState;
				State Final1st=firstOperand.finalState;
				ArrayList<State> States=new ArrayList<>();
				ArrayList<ArrayList<String>> Transitions2nd=secondOperand.Transitions;
				ArrayList<ArrayList<String>> Transitions1st=firstOperand.Transitions;
				ArrayList<ArrayList<String>> Transitions=new ArrayList<>();
				States2ndOp.remove(States2ndOp.indexOf(Initial2nd));
				Iterator<State> state2ndOpIterator1=States2ndOp.iterator();
				ArrayList<String> Transition=new ArrayList<>();
				Transition.add(Final1st.number+"");



				while(state2ndOpIterator1.hasNext()){
					//State hasNext=
					;
					States.add(state2ndOpIterator1.next());
				}
				Iterator<State> stateIterator2=States1stOp.iterator();
				while(stateIterator2.hasNext()){

					States.add(stateIterator2.next());
				}



				Iterator<ArrayList<String>> transition2ndtIterator=Transitions2nd.iterator();
				Iterator<ArrayList<String>> transition1stIterator=Transitions1st.iterator();

				while(transition1stIterator.hasNext()){

					ArrayList current=transition1stIterator.next();
					//System.out.print(current);
					Transitions.add(current);

				}
//				while(transition2ndtIterator.hasNext()){
//					ArrayList<String> current=transition2ndtIterator.next();
//					System.out.println(current);
//
//				}
				while(transition2ndtIterator.hasNext()){
					ArrayList current=transition2ndtIterator.next();
					if(current.contains(Initial2nd.number+"")){

						current.set(current.indexOf(""+Initial2nd.number),""+Final1st.number);
						//Transitions.add(transition2ndtIterator.next());
					}

					System.out.print(current);
					Transitions.add(current);

				}
				System.out.println(Transitions);

				NFA toBePushed=new NFA(States,alphabet,Transitions,Initial1st,Final2nd);
				s.push(toBePushed);


			}



			else{
				ArrayList<State> States=new ArrayList<State>();
				State initialState= new State();
				State finalState= new State();
				States.add(initialState);
				States.add(finalState);
//				Iterator<String> alphaIterator=Alphabet.iterator();
//				String alphabetString="";
//				while (alphaIterator.hasNext()){
//					alphabetString+=alphaIterator.next();
//
//
//			}
				ArrayList<ArrayList<String>> Transitions=new ArrayList<>();
				ArrayList<String> Transition=new ArrayList<>();
				Transition.add(String.valueOf(initialState.number));
				Transition.add(currentElement+"");
				Transition.add(String.valueOf(finalState.number));
				Transitions.add(Transition);
				NFA NFA=new NFA(States,alphabet,Transitions,initialState,finalState);

				s.push(NFA);


			}




		}
		f=(NFA)s.pop();
	}

	/**
	 * @return Returns a formatted string representation of the NFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub

		if(!f.equals(null)){
			return f.toString();}
		else
			return null;
	}

	class NFA{
		public ArrayList<State> States;
		public String Alphabet;
		public ArrayList<ArrayList<String>> Transitions;
		public State initialState;
		public State finalState;

		public NFA(ArrayList<State> States,String Alphabet,ArrayList<ArrayList<String>> Transitions,State initialState,State finalState){
			this.States=States;
			this.finalState=finalState;
			this.initialState=initialState;
			this.Alphabet=Alphabet;
			this.Transitions=Transitions;}

		public String toString(){
			String result="";
			Comparator<? super State> MyComparator=new MyComparator() {
				@Override
				public int compare(State o1, State o2) {
					return super.compare(o1, o2);
				}
			};
			Collections.sort(this.States,MyComparator);
			//Iterator<State> iterator =States.iterator();

			for(int i=0;i<this.States.size();i++){
				if(i==this.States.size()-1)
					result+=this.States.get(i).number+"#";
				else{
					result+=this.States.get(i).number+";";
				}


			}
			result+=this.Alphabet+"";

			Comparator<? super ArrayList<String>> MyComparator1=new MyComparator1() {
				@Override
				public int compare(ArrayList<String> a1, ArrayList<String> a2) {
					return super.compare(a1, a2);
				}
			};
			Collections.sort(this.Transitions,MyComparator1);
			for(int j=0;j<this.Transitions.size();j++){
				if(j==this.Transitions.size()-1){
					for(int k=0;k<this.Transitions.get(j).size();k++) {

						if (k == this.Transitions.get(j).size() - 1) {
							result += this.Transitions.get(j).get(k).toString() + "";
						}
						else{
							result+=this.Transitions.get(j).get(k).toString()+",";
						}
					}
					result+="#";
				}
				//result+=this.Transitions.get(j).get(k).toString().toString()+"#";
				else{
					for(int k=0;k<this.Transitions.get(j).size();k++) {

						if (k == this.Transitions.get(j).size() - 1) {
							result += this.Transitions.get(j).get(k).toString() + "";
						}
						else{
							result+=this.Transitions.get(j).get(k).toString()+",";
						}
					}
					result+=";";
				}

				//result+=this.Transitions.get(j).toString().toString()+";";
			}






			result+=this.initialState.number+"#";
			result+=this.finalState.number+"";
			return result;}}
	class State{
		public int number;

		public State(){
			this.number=counter;
			counter++;
		}
	}
	abstract class MyComparator implements Comparator<State> {
		@Override
		public int compare(State o1, State o2) {
			if (o2.number > o1.number) {
				return -1;
			} else if (o2.number < o1.number) {
				return 1;
			}
			return 0;
		}}
	abstract class MyComparator1 implements Comparator<ArrayList<String>> {
		@Override
		public int compare(ArrayList<String> a1, ArrayList<String> a2) {
			if (Integer.parseInt(a1.get(0)) > Integer.parseInt(a2.get(0))) {
				return 1;
			} else if (Integer.parseInt(a1.get(0))   < Integer.parseInt(a2.get(0))) {
				return -1;
			}
			else if(a1.get(1).compareTo(a2.get(1))==1){
				return 1;
			} else if (a1.get(1).compareTo(a2.get(1))==-1) {
				return -1;
			}
			else if(Integer.parseInt(a1.get(2)) > Integer.parseInt(a2.get(2))){
				return 1;
			} else if (Integer.parseInt(a1.get(2)) < Integer.parseInt(a2.get(2))) {
				return -1;
			}
			return 0;
		}}


	public static void main(String[] args) {
		RegExToNfa test=new RegExToNfa("a;o;z#za|*o.");
		//
		System.out.println(test);
	}


}