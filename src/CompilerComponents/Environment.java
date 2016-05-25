package CompilerComponents;

import java.util.LinkedList;
import java.util.Stack;

public class Environment {

	private Stack<Integer> stack = new Stack<Integer>();
	LinkedList<String> availableExpr = new LinkedList<String>();
	
	LinkedList<String> cmds = new LinkedList<String>(); 
	
	public Environment(LinkedList<String> cmds) {
		this.cmds = cmds;
		createExpression();
	}

	public void executeMathOperations() throws Exception 
	{
		int it = 1;
		for (String cmd : cmds)
		{
			System.out.println("Stack content: " + stack);
			if (!isCorrectExpr(cmd))
				throw new Exception("Exception: unavailable expression ");
			
			if (isPutExpr(cmd)){
				addToStack(getValueFromPutExpr(cmd));
			}
			else if (cmd.equals("end"))
			{
				
				endExprService();
				break;
			}
			else
			{
				executeExpression(cmd);
			}
			it++;
			
		}
		
		if (cmds.size() == it){
			System.out.println("Correct math operation");
		}
		else
			System.out.println("Something is wrong");
	}
	
	
	
	private void createExpression() {
		availableExpr.add("add");
		availableExpr.add("mul");
		availableExpr.add("sub");
		availableExpr.add("div");
		availableExpr.add("end");
		availableExpr.add("put");
		availableExpr.add("pow");
	}
	
	
	/**
	 * return the value from put expression
	 */
	public int getValueFromPutExpr(String putExpr)
	{
		return Integer.parseInt(putExpr.split(" ")[1]);
	}
	
	/**
	 * action after end expression read
	 */
	public void endExprService()
	{
		if (stack.size() == 1)
		{
			System.out.println("Result = " + getFromStack());
		}
		else if (stack.size() == 0) 
			throw new StackOverflowError("Stack is empty.");
		else
			throw new StackOverflowError("Too many elements in stack.");
	}
	
	
	public void executeExpression(String expr)
	{
		
		int [] pair = getTwoElementsFromStack(); // pair[0] - last element, pair[1] - penultimate element
		int result;
		switch (expr) {
		case "add":
			result = pair[1] + pair[0];
			addToStack(result);
			break;
		case "sub":
			result = pair[1] - pair[0];
			addToStack(result);
			break;
		case "mul":
			result = pair[1] * pair[0];
			addToStack(result);
			break;
		case "div":
			result = pair[1] / pair[0];
			addToStack(result);
			break;
		case "pow":
			result = 1;
			for(int i=1; i <= pair[0]; i++){
				result = pair[1] * result;
			}
			addToStack(result);
			break;
		default:
			break;
		}
		
	}
	
	public int[] getTwoElementsFromStack()
	{
		int [] pair = new int[2];
		if (stack.size() < 2)
		{
			throw new StackOverflowError("Not enough elements");
		}
		else
		{
			pair[0] = getFromStack();
			pair[1] = getFromStack();
		}
		
		
		return pair;
	}
	
	public boolean isCorrectExpr(String expr)
	{
		if (isPutExpr(expr))
			return true;
		return availableExpr.contains(expr);
	}
	
	public boolean isPutExpr(String s)
	{
		if (s.matches("put [0-9][0-9]*"))
		{
			return true;
		}
		return false;
	}

	public void addToStack(int val){
		stack.push(val);
	}
	public int getFromStack(){
		return stack.pop();
	}
}
