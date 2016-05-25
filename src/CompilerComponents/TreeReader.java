package CompilerComponents;

import java.util.LinkedList;

import TreeComponents.Node;

public class TreeReader {
	
	private Node tree;

	public TreeReader(Node tree) {
		this.tree = tree;
	}
	
	public String recursiveRead()
	{
		return recursiveRead(tree);
	}
	
	public String recursiveRead(Node node)
	{
		if (node.hasChildren())
		{
			return  recursiveRead(node.getChild1()) + " | " + recursiveRead(node.getChild2()) + " | " + node.getExpression();
		}
		else
			return String.valueOf(node.getValue());
	}
	
	public LinkedList<String> changeTreeToCommands()
	{
		String treeAsString = recursiveRead();
		LinkedList<String> cmds = new LinkedList<String>();
		int it = 0;
		
		int amountOfElements = treeAsString.split(" \\| ").length;
		String expr = new String();
		while (it < amountOfElements)
		{
			expr = treeAsString.split(" \\| ")[it];
			if (expr.matches("[0-9]+"))
			{
				cmds.add("put "+expr);
			}
			else
			{
				if (expr.equals("+"))
					cmds.add("add");
				else if (expr.equals("-"))
					cmds.add("sub");
				else if (expr.equals("*"))
					cmds.add("mul");
				else if (expr.equals("/"))
					cmds.add("div");
				else if (expr.equals("^"))
					cmds.add("pow");
			}
			
			it++;
		}
		cmds.add("end");
		return cmds;
	}
	
	
	
}
