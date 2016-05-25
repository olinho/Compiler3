package TreeComponents;



/**
 * Node can be a leaf , ex. Node(2)
 * or have childrens, ex. Node(add, Node(1), Node(4))
 * 
 * so if we have children then: 
 * hasChildren = true; at least first child is set: child1 != null, child2 != null optionally; expression != "" [add or mul as far]; value = 0;
 * 
 * if we don't have children then:
 * value != 0, hasChildren = false, child1=null, child2=null
 * 
 * @author Olek
 *
 */
public class Node {
	
	private String expression;
	private boolean hasChildren;
	private Node child1;
	private Node child2;
	private int value;	// if unset it's 0
	
	public Node()
	{
		setExpression("");
		hasChildren = false;
		child1 = null;
		child2 = null;
		value = 0;
	}
	
	public Node(int val) 
	{
		this();
		value = val;
	}

	
	public Node(String expr, Node n1, Node n2)
	{
		setExpression(expr);
		hasChildren = true;
		setChild1(n1);
		setChild2(n2);
		value = 0;
	}
	
	public Node(String expr, Node n1)
	{
		this(expr, n1, null);
	}
	
	public Node(String expr, int val1, int val2)
	{
		this(expr, new Node(val1), new Node(val2));
	}
	
	public Node(String expr, int val1)
	{
		this(expr, new Node(val1), null);
	}
	
	/**
	 * check if we have any blank leafs for example Node(add, Node(2), null) 
	 * @return
	 */
	public boolean doesNodeNeedsValue()
	{
		if (!hasChildren)
		{
			if (value == 0)
			{
				return true;
			}
		}
		else	// has Children
		{
			if (child2 == null)
			{
				return true;
			}
		}
		return false; 	// in other way
	}
	
	/**
	 * 
	 * @return string concating all nodes from tree
	 */
	public String nodeToString()
	{
		if (value != 0)
		{
			return "Node("+value+")";
		}
		else 
		{
			// child1 != null
			if (child2 == null)
			{
				return "Node(" + expression.toString() + ", " + child1.nodeToString() + ", null)";
				
			}
			else
			{
				return "Node(" + expression.toString() + ", " + child1.nodeToString() + ", " + child2.nodeToString() + ")";
			}
		}
	}
	
	

	public boolean hasChildren()
	{
		return hasChildren;
	}
	
	public void setValue(int v)
	{
		value = v;
	}
	
	public int getValue()
	{
		return value;
	}

	public Node getChild1() {
		return child1;
	}

	public void setChild1(Node child1) {
		value = 0;
		hasChildren = true;
		this.child1 = child1;
	}

	public Node getChild2() {
		return child2;
	}

	public void setChild2(Node child2) {
		value = 0;
		hasChildren = true;
		this.child2 = child2;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		value = 0;
		this.expression = expression;
	}
	
}
