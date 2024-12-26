import java.util.Scanner;
import java.util.Stack;
public class wizard {

	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		String code = sc.nextLine();
		
		Boolean valid = validate(code);
		System.out.println(valid);
	}
	private static boolean validate(String code) {
		int count = 0;
		Stack<Character> stack = new Stack<>();
		for(int i =0; i<code.length();i++) {
			char c = code.charAt(i);
			if (c == '<' ) count++;
			if (c== '>' ) count++;   //to count den allazei swsta alla douleuei
			if (c == '[' || c == '{' || c == '(' || c == '<') {
				if (c=='<') {
					while(i+1<code.length() && code.charAt(i+1) != '>') {
						if (code.charAt(i+1) == '#' || code.charAt(i+1) == '@' || code.charAt(i+1) == '*' ) i++;
						else {
							
							return false;
						}
					}
					if((i+1<code.length() && code.charAt(i+1) == '>')) i++;
				}else {
					stack.push(c);
				}
				
			}else if(c == ']' || c == '}' || c == ')' || c == '>') {
				if(stack.isEmpty()) return false;
				
				char last = stack.pop();
				if (last == '[' && c != ']') return false;
				if (last == '{' && c != '}') return false;
				if (last == '(' && c != ')') return false;
				if (last == '<' && c != '>') return false;
			}else if (c == '@' || c == '*' || c== '#') continue;
		}
		if(count>1) return false;
		//System.out.println(count);
		return stack.isEmpty();
	}

}