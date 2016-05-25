package CompilerComponents;

import java.util.LinkedList;

import CompilerComponents.Scanner.Token;
import TreeComponents.Node;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner();
		LinkedList<Token> tokens = scanner.scan();
		for (Token tok : tokens)
		{
			System.out.print("["+tok.sequence+"]"+"\t");
		}
		
		Parser parser = new Parser(tokens);
		
		Node tree = parser.parse();
		
		System.out.println(tree.nodeToString());
		
		TreeReader treeReader = new TreeReader(tree);
		
		LinkedList<String> cmds = treeReader.changeTreeToCommands();
		
		for (String str : cmds)
		{
			System.out.println(str);
		}
		
		Environment env = new Environment(cmds);
		env.executeMathOperations();
	}

}
