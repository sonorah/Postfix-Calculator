import java.lang.Character;
import java.util.Scanner;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.*;
import java.util.ArrayList;

/** 
 *  Converts infix notation into postfix.
 *  @author Sonora Halili & Frankie Fan
 *  @version February 2022
*/
class Calculate {
  
  /** 
   *  Checks if object is a Double. 
   *  @param ob  the Object to check
   *  @return  boolean
  */
  private static boolean isOperand(Object ob) {
    return(ob instanceof Double);
  }
  
  /** 
   *  Checks if object is a Character. 
   *  @param ob  the Object to check
   *  @return  boolean
  */
  private static boolean isCharacter(Object ob) {
    return(ob instanceof Character);
  }
  
  /** 
   *  Checks if object is an Operator. 
   *  @param ob  the Object to check
   *  @return  boolean
  */
  private static boolean isOperator(Object ob) { 
    //arraylist to store operators
    ArrayList<Character> al = new ArrayList<Character> (Arrays.asList('+','-','*', '/', '^'));
    char c = ' '; 
    //checks if object i instance of character
    if (isCharacter(ob)) {
      //stores obj as character
      c = (Character)ob;
    }
    //returns true if c is operator
    return (al.contains(c));
  }
  
  /** 
   *  Checks if object is a Left Parenthesis. 
   *  @param ob  the Object to check
   *  @return  boolean
  */
  private static boolean isLeftParen(Object ob) {
    char c = ' '; 
    //checks if object is instance of character
    if (isCharacter(ob)) {
      //stores
      c = (Character)ob;
    }
    //returns true if c is left paren
    return ((c=='('));
  }

  /** 
   *  Checks if object is a Right Parenthesis. 
   *  @param ob  the Object to check
   *  @return  boolean
  */
  private static boolean isRightParen(Object ob) {
    char c = ' '; 
    //checks if object is instance of character
    if (isCharacter(ob)) {
      //stores
      c = (Character)ob;
    }
    //returns true if c is right paren
    return (c==')');
  }
      

  /** 
   *  Checks for precedence between operators. 
   *  @param char operator
   *  @return  int precedence
  */
  static int getPrecedence(char ch) {
    //+ and - have the same precedence
    if (ch == '+' || ch == '-')
      return 1;
    // * and / have the same precendence
    else if (ch == '*' || ch == '/')
      return 2;
    // ^ has the highest precedence
    else if (ch == '^')
      return 3;
    //anything else (i.e parens) have lowest precedence
    else
      return -1;
  }
    
  /** 
   *  Checks if operator has left associativity. 
   *  @param char operator
   *  @return  boolean
  */  
  static boolean hasLeftAssociativity(char ch) {
    //checks if operator is +,-,/,*
    if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
      return true;
    } else {                    //case where operator is '^'
      return false;
    }
  }


  /** 
   *  Converts queue of infix tokens into postfix ones. 
   *  @param Queue <Object> tokens to convert 
   *  @return  ArrayDeque <Object> output queue
  */
  public static ArrayDeque<Object> Convert (Queue <Object> tokens) {
    
    /** ArrayDeque used as stack to store operations */
    ArrayDeque<Object> stack = new ArrayDeque <Object> () ;
    /** ArrayDeque used as queue to store postfix output */
    ArrayDeque<Object> output = new ArrayDeque <Object> ();
    
    /** Double to store operands under*/
    double d;
    /** Char to store operators & parens under*/
    char c;

    /** While loop to browse through tokens */
		while (tokens.size() > 0) {
      try {
      Object ob = tokens.remove();
      
      //check if token is a double (operand)
      if (isOperand(ob)) {
        //store under d
        d = (Double)ob;
        //add to output queue
        output.add(d);
      }

      //check if token is an instance of character
      else if (isCharacter(ob)) {
        //store under c
        c = (Character)ob;

        //check if token is LeftParen
        if (isLeftParen(c)){
          //push left paren to operator stack
          stack.push(c);
        }
    
        //check if token is RightParen
        else if (isRightParen(c)){
          
          //check if there is an operator on top of stack
          while (!stack.isEmpty() && !isLeftParen((Character)stack.peek())) {
					  //pop said operator and add it to output queue
            output.add(stack.pop());
          }

          //pop the top of the stack
          stack.pop();
          continue;
        }
        
        //check if token is an operator
        else if (isOperator(c)){
          //check if whatever is at the top of the stack has bigger precedence than c & left associativity
          while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence((Character)stack.peek()) && hasLeftAssociativity(c)) {
            //pop said operator from stack onto queue
            output.add(stack.pop());
          }
          
          //push into operator stack
          stack.push(c);
          continue;
        }
        continue;
      }

      //catches potential exceptions & prints out message
      } catch (RuntimeException e) {
        System.out.print(e.getMessage());
        continue;
      }   
    }

    //check if there are leftover operators in stack
    while (!stack.isEmpty()) {
      
      c = (Character)stack.pop();
      //checks if there has been a mismatch of parens
      if (c == '(' || c == ')'){
        System.out.print("Warning: mismatch of parenthesis may affect result!");
      } else{
      //adds leftovers from stack into output queue
      output.add(c);
      }
    }

    //returns queue of elements ordered in postfix notation
		return output;
	}


	/** 
   *  Shows result of expression. 
   *  @param String[] args 
   *  @return  void
  */
  public static void main(String[] args) {   
    
    //checks if arguments are passed
    if (args.length == 0) {
      // If no arguments passed, print instructions
      System.err.println("Usage:  java Calculate <expr>");
    } else {
      // Otherwise, read in input
      Scanner input = new Scanner(new StringReader(args[0])); 
      while (input.hasNext()) {
        //construct tokenizer and postfix objects
        Tokenizer ton = new Tokenizer();
        Postfix pfx = new Postfix();
        
        //Queue to store result of Tokenizer work
        Queue <Object> tokens = ton.readTokens(input.next());
        //Queue to store result of Calculate work (converted to postfix notation)
        Queue <Object> pfxNotation = Convert(tokens);
        //prints out result by calling doCalculations method on Postfix object
        System.out.println(pfx.doCalculations(pfxNotation));
      }
    }
  }
}