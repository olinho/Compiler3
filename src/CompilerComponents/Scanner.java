package CompilerComponents;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jdk.nashorn.internal.runtime.ParserException;


public class Scanner {
	private String exprStr;
	private LinkedList<TokenInfo> tokenInfos;
	private LinkedList<Token> tokens;
	
	public Scanner() {
		tokenInfos = new LinkedList<TokenInfo>();
		tokens = new LinkedList<Token>();
		createTokens();
	}
	
	
	/**
	 * this method read input expression string
	 * Then creates tokens.
	 * @return created List of tokens
	 * @throws Exception 
	 */
	public LinkedList<Token> scan() throws Exception
	{
		readExpression();
		tokenize(exprStr);
		if (!validateExpression()){
//			throw new Exception("Invalid expression");
			System.out.println("Invalid expression");
			System.exit(-1);
		}
		return tokens;
	}
	
//	todo
	public boolean validateExpression()
	{
		String next = "value";
		for (Token tok : tokens)
		{
			if (next.equals("value"))
			{
				if (!tok.isValue())
				{
					return false;
				}
				next = "expression";
			}
			else // now.equals("expression") 
			{
				if (!tok.isExpression())
				{
					return false;
				}
				next = "value";
			}
		}
		return true;
	}
	

	
	/**
	 * in this method we can tokenize the string what means 
	 * to assign a number to regex
	 */
	public void tokenize(String str)
	{
		String s = new String(str);
		s = removeSpaces(s);
		tokens.clear();
		
		boolean match = false;
		
		while (!s.equals(""))
		{
			for (TokenInfo info : tokenInfos)
			{
				Matcher m = info.regex.matcher(s);
				if (m.find())
				{
					match = true;
					
					String tok = m.group().trim();
					System.out.println("Token: " + tok);
					tokens.add(new Token(info.token, tok));
					
					s = m.replaceFirst("");
					break;
				}
			}
		}
		System.out.println();
		if (!match) throw new ParserException("Unexpected character in input: "+s);
	}
	
	public void createTokens()
	{
		add("\\(", 2);
		add("\\)", 3);
		add("[+-]", 4);
		add("[*/]", 5);
		add("\\^", 6);
		add("[0-9]+", 7);
		add("[a-zA-Z][a-zA-Z0-9_]*", 8);	// variable
		add(" ", 9);
	}
	
	
	public class Token 
	{
		public final int token;
		public final String sequence;
		
		public Token(int token, String seq)
		{
			super();
			this.token = token;
			sequence = seq;
		}
		
		public boolean isExpression()
		{
			if (sequence.matches("[+-/*\\^]")){
				return true;
			}
			return false;
		}
		
		public boolean isValue()
		{
			if (sequence.matches("[0-9]+")){
				return true;
			}
			return false;
		}
	}
	
	
	private class TokenInfo
	{
		public final Pattern regex;
		public final int token;
		
		public TokenInfo(Pattern pat, int token){
			super();
			regex = pat;
			this.token = token;
		}
	}
	
	/**
	 * add token 
	 */
	public void add(String regex, int token) {
		tokenInfos.add(
				new TokenInfo(
						Pattern.compile("^("+regex+")"), token));
	}
	
	/**
	 * remove spaces from expression 
	 */
	public String removeSpaces(String str)
	{
		return str.replaceAll(" ", "");
	}
	
	public LinkedList<Token> getTokens() {
		return tokens;
	}
	
	
	public void readExpression() throws IOException
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Get math expression:");
		exprStr = br.readLine();
	}
}
