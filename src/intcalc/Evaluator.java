package intcalc;

import java.util.Hashtable;

import edu.hendrix.grambler.ParseException;
import edu.hendrix.grambler.Tree;

public class Evaluator {
	private newParser grammar;
	private Hashtable<String, Integer> variables;
	
	public Evaluator() {
		grammar = new newParser();
		variables = new Hashtable<String, Integer>();
	}
	
	public String eval(String input) throws ParseException{
		Tree t;
		try {
			t = grammar.parse(input);
			return evalTree(t);
		} catch (ParseException e) {
			e.printStackTrace();
			return e.toString();
		}
	}

	public String evalTree(Tree t) {
		if (t.isNamed("lines")) {
			if (t.getNumChildren() == 1) {return evalTree(t.getLastChild());}
			else {return evalTree(t.getNamedChild("lines")) + "\n" + evalTree(t.getLastChild());}
		}
		
		else if (t.isNamed("line")) {
			if (t.getNumNamed("assn") == 1) {return evalTree(t.getNamedChild("assn"));}
			else {return "";}
		}
		
		else if (t.isNamed("num")) {
			if (t.getNumChildren() == 1) {return evalTree(t.getChild(0));}
			else {return t.toString();}
		}
		
		else if (t.isNamed("var")) {return String.valueOf(variables.get(t.toString()));}
		
		else if (t.isNamed("paren")) {
			if (t.getNumChildren() == 1) {return evalTree(t.getChild(0));}
			else if (t.hasNamed("expr")) {return evalTree(t.getNamedChild("expr"));}
		}
		
		else if (t.isNamed("expr2")) {
			if (t.getNumChildren() == 1) {return evalTree(t.getChild(0));}
			else if (t.hasNamed("op2")) {
				if (t.getNamedChild("op2").toString().equals("*")) {return String.valueOf(Integer.parseInt(evalTree(t.getNamedChild("expr2"))) * Integer.parseInt(evalTree(t.getNamedChild("paren"))));}
				else if (t.getNamedChild("op2").toString().equals("/")) {return String.valueOf(Integer.parseInt(evalTree(t.getNamedChild("expr2"))) / Integer.parseInt(evalTree(t.getNamedChild("paren"))));}
			}
		}
		
		else if (t.isNamed("expr")) {
			if (t.getNumChildren() == 1) {return evalTree(t.getChild(0));}
			else if (t.hasNamed("op")) {
				if (t.getNamedChild("op").toString().equals("+")) {return String.valueOf(Integer.parseInt(evalTree(t.getNamedChild("expr"))) + Integer.parseInt(evalTree(t.getNamedChild("expr2"))));}
				if (t.getNamedChild("op").toString().equals("-")) {return String.valueOf(Integer.parseInt(evalTree(t.getNamedChild("expr"))) - Integer.parseInt(evalTree(t.getNamedChild("expr2"))));}
			}
		}
		
		else if (t.isNamed("assn")) {
			if (t.getNumChildren() == 1) {return evalTree(t.getChild(0));}
			else {
				if (variables.containsKey(t.getNamedChild("var").toString())) {variables.remove(t.getNamedChild("var"));}
				variables.put(t.getNamedChild("var").toString(), Integer.parseInt(evalTree(t.getNamedChild("expr"))));
				return String.valueOf(variables.get(t.getNamedChild("var").toString()));
			}
		}
		
		else {throw new IllegalArgumentException("what is \"" + t.getName() + "\"");}
		
		return "Not sure what went wrong";
	}
}
