package CompilerComponents;

import java.util.LinkedList;

import CompilerComponents.Scanner.Token;
import TreeComponents.Node;
import TreeComponents.TreeManager;

public class Parser {
	private LinkedList<Token> tokens;
	private TreeManager treeManager;

	public Parser(LinkedList<Token> tokens) {
		treeManager = new TreeManager();
		this.tokens = new LinkedList<Scanner.Token>(tokens);
	}
	
	public void createTree()
	{
		for (Token tok : tokens)
		{
			if (tok.isExpression())
			{
				treeManager.addExpressionToTree(tok.sequence);
			}
			else
			{
				treeManager.addValueToTree(Integer.parseInt(tok.sequence));
			}
			System.out.println(treeManager.treeToString());
		}
	}
	
	
	public Node parse()
	{
		createTree();
		return treeManager.getTree();
	}

	
}
