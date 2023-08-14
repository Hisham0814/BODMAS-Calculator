package calculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Calculate {
	Calculator calc = new Calculator();
	String infix = calc.display1.getText();
	Stack<String> operators = new Stack<String>();
	
	//================================================ PRECEDENCE OF OPERATORS =================================================
	
	public int priority(String symbol) {
		int p = 0;
		switch(symbol) {
			case "!": p=4; break;
			case "^": p=3; break;
			case "*":case "/": p=2; break;
			case "+":case "-": p=1; break;
			//case '(':case')': p=4;
		}
		return p;
	}
	
	//================================================ CALCULATATION OF FACTORIAL ==================================================
	
	public int factorial(int n) {
		if(n==0)
			return 1;
		else 
			return n*factorial(n-1);
	}
	
	// ================================================ CALCULATION OF PERCENTAGE ==================================================
	
	public String percentage(String infix) {
		String msg = "ERROR: INVALID EXPRESSION";
		String symbols = "!+-*/()^%";
		int len = infix.length();
		int i;
		ArrayList num = new ArrayList();
		StringBuilder digits = new StringBuilder();
		
		for(i=0;i<len;i++) {
			String element = infix.substring(i, i+1);
			String nextElement = new String();
			if(i<infix.length()-1)
				nextElement = infix.substring(i+1, i+2);
			// if it is a digit
			if(element.matches("[0-9]+") || element.equals(".")) {
				digits.append(element);
				//if there is an operator after current digit
				if(symbols.contains(nextElement)) {
					String number = digits.toString();
					num.add(number);
					digits.delete(0, digits.length());
				}
			}
			//if it is an operator
			else if(symbols.contains(element)) {
				num.add(element);
			}
		}
		if(num.size()!=4) {
			return msg;
		}
		num.remove(3);
		num.add(3,"*");
		num.add(4,"100");
		ArrayList post = conversion(num);
		String ans = evaluate(post);
		return ans;
	}
	
	//=============================================== CONVERSION FROM STRING TO LIST=================================================
	
	public ArrayList stringtolist(String infix) {
		
		//infix2 = infix2.replaceAll("[^a-zA-Z0-9]", " ");
		//String msg = "ERROR: INVALID EXPRESSION";
		String symbols = "!+-*/()^";
		String symbols2 = "!+-*/^";
		String postfix ;
		
		ArrayList<String> num = new ArrayList<String>();
		ArrayList<String> msg = new ArrayList<String>();
		int i,j,flag=0,k = 0;
		
		System.out.println("==Adding elements==");
		
		if(infix.substring(0, 1).equals("-") || infix.substring(0,1).equals("+"))
			infix = "0"+infix;
		if(infix.substring(0, 1).equals("/") || infix.substring(0,1).equals("*"))
			return msg;
		if(infix.substring(0, 1).equals("^") || infix.substring(0, 1).equals("!"))
			return msg;
		if(infix.substring(0, 1).equals(")") || infix.substring(0, 1).equals("%") )
			return msg;
		for(i=0;i<infix.length()-1;i++) {
			//if 2 operators are together return ERROR
			if(symbols2.contains(infix.substring(i,i+1)) && symbols2.contains(infix.substring(i+1,i+2)) && !infix.substring(i,i+1).equals("!") )
				return msg;
		}
		
		StringBuilder digits = new StringBuilder();
		
		for(i=0;i<infix.length();i++) {
			String element = infix.substring(i,i+1);
			if(element.matches("[^a-zA-Z0-9]") && !element.equals(".") && !symbols.contains(element)) // if it is not a valid operator
				return msg;
			if(element.matches("[a-zA-Z]+")) //if it is a letter
				return msg;
			if(i<infix.length()-1)
				if(element.equals(".") && infix.substring(i+1, i+2).equals(".")) // if there is ..
					return msg;
			//if(symbols2.contains(element) && symbols2.contains(infix.substring(i+1,i+2))) //if there are two operators together
				//return msg;
			//if( element.equals("%") && infix.substring(i+1,i+2).matches("[0-9]+") ) 
				//return msg;
			//if( element.equals("%") && symbols2.contains(infix.substring(i-1,i)) )
				//return msg;
			
			String str = infix.substring(i);
			// if element is %
			if(element.equals("%")) {
				num.add(element);
				continue;
			}
			
			//if element is not the last number
			if( str.contains("+") || str.contains("-") || str.contains("*") || str.contains("/") || str.contains("(") || str.contains(")") || str.contains("^") ) {
				String sym = infix.substring(i, i+1);
				String sym2 = new String();
				if(i<infix.length()-1)
					sym2 = infix.substring(i+1, i+2);
				
				//if element is a digit
				if ( !symbols.contains(sym) && !sym.equals("%") ) {
					digits.append(sym);
					if(symbols.contains(sym2)) {
						String number = digits.toString();
						num.add(number);
						System.out.println("number: "+number);
						digits.delete(0, digits.length());
					}
				}
				//if element is a symbol
				else if( symbols.contains(sym) ){
					//if 2 symbols are together return ERROR
					if( symbols2.contains(sym2)  && !sym.equals(")") && !sym.equals("(") && !sym.equals("!")) 
					//if( symbols2.contains(sym2) && !symbols.contains(")"))
						return msg;
					else if( !sym.equals(")") && sym2.equals("!") ) {
						return msg;
					}
					//else if( sym.equals(")") && sym2.equals("."))
						//return msg;
					else if( symbols2.contains(sym) && !sym.equals("!") && sym2.equals(")"))
						return msg;
					else if(sym.equals("!") && sym2.equals("!"))
						return msg;
					else if( sym.equals("(") && sym2.equals(")") )
						return msg;
					else if(sym.equals(")")) {
						num.add(sym);
					}
					else if(sym.equals("!") && sym2.equals(")"))
						num.add(sym);
					// if number is enclosed in ( )
					//else if(sym.equals("(") && ( sym2.equals("+") || sym2.equals("-") ) ) {
						//num.add(sym);
						//num.add(num.indexOf(sym)+1,"0");
					//}
					//if there is a * or / after ( , return ERROR
					else if(sym.equals("(") && ( sym2.equals("*") || sym2.equals("/") ||  sym2.equals("^") ) ) 
						return msg;
					//else if(sym.equals("%")  ) {
						//num.add(sym);
					//}
					else
						num.add(sym);
					System.out.println("operator: "+sym);
				}
			}
			//if element is the last number in infix expression
			else if(!str.contains("+") && !str.contains("-") && !str.contains("*") && !str.contains("/") && !str.contains("(") && !str.contains(")") && !str.contains("^")){
				if(i<infix.length()-1) {
					String sym = infix.substring(i,i+1);
					digits.append(sym);
					if(infix.substring(i+1).equals("!") || infix.substring(i+1).equals("%")) {
						String number = digits.toString();
						num.add(number);
						digits.delete(0, digits.length());
					}
				}
				//if element is the last character of the infix string
				else if(i==infix.length()-1) {
					if(!infix.substring(infix.length()-1, infix.length()).equals("!") && !infix.substring(infix.length()-1, infix.length()).equals("%")) {
						String sym = infix.substring(infix.length()-1, infix.length());
						digits.append(sym);
						String number = digits.toString();
						num.add(number);
						System.out.println("number: "+number);
						digits.delete(0, digits.length());
					}
					// if last character is !
					else if(infix.substring(infix.length()-1, infix.length()).equals("!")){
						String sym = infix.substring(infix.length()-1, infix.length());
						num.add(sym);
						System.out.println("number: "+sym);
					}
					else if(infix.substring(infix.length()-1, infix.length()).equals("%")) {
						String sym = infix.substring(infix.length()-1, infix.length());
						num.add(sym);
						System.out.println("number: "+sym);
					}
				}
			}
		}
		
		System.out.println("adding symbols: "+num);
		
		// check if number of left and right brackets are equal
		int rtbracketcount = 0;
		int ltbracketcount = 0;
		for(i=0;i<num.size();i++) {
			if(num.get(i).equals("("))
				ltbracketcount++;
			else if(num.get(i).equals(")"))
				rtbracketcount++;
		}
		// if unequal number of left and right brackets return ERROR
		if(rtbracketcount!=ltbracketcount)
			return msg;
		
		for(i=0;i<num.size()-1;i++) {
			if( num.get(i).equals(")") && num.get(i+1).substring(0, 1).equals(".") )
				num.add(i+1,"*");
		}
		
		// a(b) => a*(b)
		for(i=0;i<num.size()-1;i++) {
			String n = num.get(i);
			if( ( n.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+") || n.substring(n.length()-1).equals(".") || n.substring(0,1).equals(".")) && num.get(i+1).equals("(") ) {
				num.add(i+1,"*");
			}
		}
		// (a)b => a*b
		for(i=0;i<num.size()-1;i++) {
			if( num.get(i+1).matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+") && num.get(i).equals(")") ) {
				num.add(i+1,"*");
			}
		}
		//if number is enclosed in ( )
		for(i=0;i<num.size()-1;i++) {
			if( num.get(i).equals("(") && ( num.get(i+1).equals("+") || num.get(i+1).equals("-")) ) {
				num.add(i+1,"0");
			}
		}
		
		for(i=0;i<num.size()-1;i++) {
			if( num.get(i).equals(")") && num.get(i+1).equals("("))
					num.add(i+1,"*");
		}
		for(i=1;i<num.size()-1;i++) {
			if(num.get(i).equals(".")) {
				num.remove(i);
				num.add(i,"0");
			}
		}
		
		System.out.println("adding symbols: "+num);
		//find factorial of number and update the num list
	/*	for(i=1;i<num.size();i++) {
			if(num.get(i).equals("!") && !num.get(i-1).equals(")")) {
				String elem = num.get(i-1);
				int element = Integer.parseInt(elem);
				String fact =String.valueOf(factorial(element));
				num.remove(i);
				num.remove(i-1);
				num.add(i-1, fact);
			}
		}	*/
				
		//numbers = (String[]) num.toArray(numbers);
		return num;
		
	}	
	
	
		// ================================================ CONVERSION TO POSTFIX ====================================================
	
	public ArrayList conversion(ArrayList num) {
		
		System.out.println("==Converting infix to postfix==");
		ArrayList<String> post = new ArrayList<String>();
		ArrayList<String> msg = new ArrayList<String>();
		String symbol = null;
		//String msg = "ERROR: INVALID EXPRESSION";
		String symbols = "!+-*/()^";
		String symbols2 = "!+-*/^";
		String postfix ;
		int i;
		for(i=0;i<num.size();i++) {
			symbol = (String) num.get(i);

			if (!symbols.contains(symbol)) {
				post.add(symbol);
				System.out.println("postfix exp: "+post);
			}
			else if (symbol.equals("("))   
				operators.push(symbol); 
			
			else if (symbol.equals(")")) {  
				if(!operators.contains("("))
					return msg;
				while (!operators.peek().equals("(")) {  
					post.add(operators.pop());
					if(operators.empty())
						return msg;
				}  
				operators.pop();    //remove '('  
			}  

			else if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/") || symbol.equals("^") || symbol.equals("!")) {
				while ( !operators.isEmpty() && !( operators.peek().equals("(") ) &&  (priority(operators.peek()) >= priority(symbol))  )   
					post.add(operators.pop());  
				operators.push(symbol);
				System.out.print("stack elements: "+operators+" ");
				System.out.println("stack size: "+operators.size());
				
			}
		} //for loop
		
		while (!operators.isEmpty())  
			post.add(operators.pop());
		
		postfix = post.toString();
		String str = "hello";
		System.out.println("postfix exp: "+postfix);
		//String ans = evaluate(post);
		//return postfix;  
		return post;

	} // conversion()
	
	
	//========================================== EVALUATION OF POSTFIX EXPRESSION =================================================
	
	public String evaluate(ArrayList post) {
		
		String msg = "ERROR: DIVISION BY ZERO";
		String msg2 = "ERROR"; 
		Stack<Float> numbers = new Stack<Float>();
		int n = post.size();
		for(int i=0;i<n;i++){
			
			// current element is an operator
			if (post.get(i).equals("+") || post.get(i).equals("-") || post.get(i).equals("*") || post.get(i).equals("/") || post.get(i).equals("^") ) {
				// pop top 2 operands.
				float op1 = numbers.pop();
				float op2 = numbers.pop();
	              
				// evaluate in reverse order i.e. op2 operator op1.
				String sym = (String) post.get(i);
				switch(sym){
				
	            	case "+": numbers.push((float) (op2 + op1));
	            			  break;
	                      
	            	case "-": numbers.push(op2 - op1);
	                      	  break;
	                      
	            	case "*": numbers.push(op2 * op1);
	                          break;
	                
	            	case "/": if(op1!=0) {
	            			  	numbers.push(op2 / op1);
	            			  }
	            			  else {
	            				  System.out.println("result:"+msg);
	            				  return msg;
	            				  //calc.displayAns.setText(msg);
	            			  }
	            			  
	                          break;
	                          
	            	case "^": numbers.push((float) Math.pow(op2,op1));
	                    
				}
	        }
			
			// current element is !, calculate factorial
			else if(post.get(i).equals("!")) {
				float op = numbers.pop();
				int op2 = (int)op;
				if(op<0 || op2-op!=0)
					return msg2;
				int op1 = (int)op;
				int fact = factorial(op1);
				numbers.push((float)fact);
			}
	        // Current Char is Operand simple push into stack
	        else{
	        	// convert to integer
	        	//int operand = (int) post.get(i);
	        	//int operand = Integer.parseInt((String) post.get(i));
	        	float operand = Float.parseFloat((String) post.get(i));
	        	numbers.push((float) operand);
	        }
	    }
	      
	    // Stack at End will contain result.
		if(!numbers.isEmpty()) {
			System.out.println("result: "+numbers.peek());
			System.out.println("");
			Float res = numbers.peek();
			Integer res2 = res.intValue();
			System.out.println("int value: "+res2);
			String ans;
			// if result is an int value, return int  else, return float
			if(res-res2 == 0) {
				ans = Integer.toString(res2);
			}
			else
				ans = Float.toString(res);
			return ans;
	    }
		else
			//return calc.display1.getText()+msg;
			return msg2;
	}
	
	public String result(String infix) {
		
		if(infix.substring(infix.length()-1).equals("%"))
			return percentage(infix);
		
		String msg = "ERROR: INVALID EXPRESSION";
		ArrayList list = stringtolist(infix);
		if(list.isEmpty())
			return msg;
		ArrayList postfix = conversion(list);
		if(postfix.isEmpty())
			return msg;
		String ans = evaluate(postfix);
		return ans;
	}
}

