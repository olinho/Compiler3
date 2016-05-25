package TreeComponents;

public class TreeManager {
	private Node tree = new Node();
	
	public TreeManager() {
	}
	
	
	/**
	 * method working on private variable tree, adding new value
	 * @param expr
	 */
	public void addValueToTree(int value)
	{
		tree = addValue(tree, value);
	}
	
	/**
	 * method working on private variable tree, adding new expression
	 * @param expr
	 */
	public void addExpressionToTree(String expr)
	{
		tree = addExpression(tree, expr);
	}
	
	
	public Node addExpression(Node node, String expr)
	{
		Node child1 = node.getChild1();
		Node child2 = node.getChild2();
		int value = node.getValue();
		if (child1 == null)
		{
			node.setChild1(new Node(value));
			node.setExpression(expr);
		}
		else
		{
			if (getStringPriority(node.getExpression()) < getStringPriority(expr))
			{
				if (child2.hasChildren())
				{
					node.setChild2(addExpression(node.getChild2(), expr));
				}
				else
				{
					node.setChild2(new Node(expr, child2.getValue()));
				}
			}
			else //getStringPriority(node.getExpression()) >= getStringPriority(expr)
			{
				System.out.println("Checkpoint 1");
				Node newNode = new Node();
				newNode.setChild1(node);
				newNode.setExpression(expr);
				return newNode;
			}
		}
		
		return node;
	}
	
	public Node addValue(Node node, int val)
	{
		Node child1 = node.getChild1();
		Node child2 = node.getChild2();
		String expression = node.getExpression();
		
		if (child1 == null)
		{
			node.setValue(val); 	// return Node(val)
		}
		else	// child1 != null    for example Node(add, Node(2), null)
		{
			if (child1.doesNodeNeedsValue())
			{
				node.setChild1(addValue(node.getChild1(), val));
			}
			else
			{
				if (child2 == null)
				{
					node.setChild2(new Node(val));
				}
				else
				{
					node.setChild2(addValue(node.getChild2(), val));
				}
			}
		}
		return node;
	}
	
	public String treeToString()
	{
		return tree.nodeToString();
	}
	
	
	public int getStringPriority(String str)
	{
		if (str.matches("[+-]"))
			return 2;
		else if (str.matches("[*/]"))
			return 3;
		else if (str.matches("[\\^]"))
			return 4;
		else // if (str.matches("[()]")
			return 5;
	}
	
	public Node getTree()
	{
		return tree;
	}
}
