import java.util.Queue;
import java.util.ArrayDeque;
import java.util.EmptyStackException;

/** 
 *  Calculates result of postfix expression.
 *  @author Sonora Halili & Frankie Fan
 *  @version February 2022
*/

public class Postfix { 
  
  /** 
   *  Short main method for testing purposes. 
   *  @param String[] args 
   *  @return  void
  */
  public static void main(String[] args) {
    Tokenizer TON = new Tokenizer();
    Queue <Object> tokens = TON.readTokens(args[0]);
    System.out.println(doCalculations(tokens));
  }
  
  /** 
   *  Calculates result of postfix expression. 
   *  @param Queue <Object> tokens to compute 
   *  @return  ArrayDeque <Double> stack with result
  */
  public static ArrayDeque<Double> doCalculations (Queue <Object> tokens) {
    //stores tokens; should only contain result at the end
    ArrayDeque<Double> stack = new ArrayDeque <Double> () ;  

    //browses through tokens
    while (tokens.size() > 0) {
      try {
      //object from queue  
      Object ob = tokens.remove();

      //to store objects after parsing
      double d;
      char c;
      double x;
      double y;
      double result;

      //checks if token is a number
      if (ob instanceof Double) {
        //assigns token to d
        d = (Double)ob;
        //adds to stack
        stack.push(d);
        continue;
      }

      //checks if token is a Char
      if (ob instanceof Character) {
        //assigns token to c
        c = (Character)ob;
        
        //checks if token is ^ 
        if (c == '^'){
          //throws exception if stack is empty
          if (stack.size() == 0) {
            throw new EmptyStackException();
          } else {
          //assigns first double to x
          x = stack.pop();
          //assigns second double to y
          y = stack.pop();
          //computes result (right associativity taken care of)
          result = Math.pow(y,x);
          //stores in stack
          stack.push(result);
          continue;
          }
        }

        //checks if token is * 
        else if (c == '*'){
          //throws exception if stack is empty
          if (stack.size() == 0) {
            throw new EmptyStackException();
          } else {
          //assigns first double to x
          x = stack.pop();
          //assigns second double to y
          y = stack.pop();
          //computes result
          result = (x*y);
          //stores in stack
          stack.push(result);
          continue;
          }
        }


        //checks if token is /
        else if (c == '/'){
          //throws exception if stack is empty
          if (stack.size() == 0) {
          throw new EmptyStackException();
          } else{
          //assigns first double to x
          x = stack.pop();
          //assigns second double to y
          y = stack.pop();
          //computes result
          result = y/x;
          //stores in stack
          stack.push(result);
          continue;
          }
        }


        //checks if token is + 
        else if (c == '+'){
          //throws exception if stack is empty
          if (stack.size() == 0) {
            throw new EmptyStackException();
          } else{
          //assigns first double to x
          x = stack.pop();
          //assigns second double to y
          y = stack.pop();
          //computes result
          result = x+y;
          //stores in stack
          stack.push(result);
          continue;
          }
        }

        //checks if token is -  
        else if (c == '-'){
          //throws exception if stack is empty
          if (stack.size() == 0) {
            throw new EmptyStackException();
          } else{
          x = stack.pop();
          //assigns second double to y
          y = stack.pop();
          //computes result
          result = y-x;
          //stores in stack
          stack.push(result);
          continue;
          }
        }
        continue;
      }

      } catch (RuntimeException e) {
        //prints out error message
        System.out.println("Stack may have run out of operands. Try again.");
        System.out.println(e.getMessage());
        continue;
      }   
    }

    //return stack that should contain the final result
    return stack;
  } 
}