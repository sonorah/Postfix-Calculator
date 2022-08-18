# Authors 
This assignment was completed by Sonora Halili and Frankie Fan. The work spanned thoughout the week. We were in the same code review group and practiced pair-programming for 90% of the assignment. 

# Process & Structure 

We followed the recommended progress phases: 


* ** Phase 0: Tokenizer**

For the tokenization feature, we decided to use the `Tokenizer class` was provided in class (See Tokenizer.java). We then just called the `readTokens` method in our other classes. 

* **Phase 1: Postfix** 

For the Postfix class, we were assigned the task of computing the result of a postfix expression. Our `doCalculations` method takes in a queue of tokens, parses through them, stores numeric values in a stack and pops them when an operator occurs. It then stores the result back in the stack, and returns it when parsing is complete. We didn't struggle too much with this class. 

* **Phase 2: Calculate**

For this class, we had to implement the Shunting-Yard algorithm. Understanding how it works was a little challenging, so we had to watch a lot of videos that explained it. We then followed the list of steps, but somehow still ran into numerous errors in our while-loop. For this reason we decided to define a series of methods that define the nature of each object in our input queue. These methods include: 

* `isOperand` : checks for numeric values (doubles)
* `isCharacter` : checks for instances of Chars (for operators and parens)
* `isOperator` : checks if char token is an operator
* `isLeftParen` : checks if char token is a left parenthesis
* `isRightParen` : checks if char token is a right parenthesis

We also defined methods that get the precedence of each operator and check for left associativity: 

* `getPrecendence` : returns int precedence of operator
* `hasLeftAssociativity` : checks if operator has left associativity
(We used this one in one of our while-loops to make sure `^` is not misidentified.)

Once we used all of these methods in our implementation of the algorithm, error checking became easier. 

* Finally, we passed the returned queue from Calculate to Postfix, and were able to get the result directly. 


# Classmates & Office Hours: 

We participated in code review on Friday, although we didn't really have any code yet. These are the classmates we consulted: 

* **Emi Neuwalder**: Emi was in the beginning phase of her assignment as well. She had made the Postfix class, and it technically compiled, but our discussion was limited to ideas. We discussed whether the tokenizer code should be in main or whether it should hold its own class. 


* **Yurika Nakagawa**: Yurika was also in the beginning stage. She was just as confused as we were on whether we should use actual stack and queue methods to the (technically) arraydeques that we used, or whether we should use the `addFirst`, `removeFirst`, `addLast`, `removeLast` methods respectively. We consulted prof. Howe, and followed his advice to just use `pop()`,`push()`, `add()`, `remove()` methods. 


* **TA Hours**: 
We were a little lost on Sunday, so we decided to attend 7-9 TA hours. For some reason our Convert method in Calculate wasn't returning tokens in the right order in the output queue. Glory helped us work our way through the logic of our code, and we fixed the final bugs. 


# Resource Exploration 

Besides advice from TAs, professors, and classmates, we used the following resources to help with our assignment: 

* `Tokenizer.java` from 02/14 class. 
* [This example of ShuntingYard](https://www.geeksforgeeks.org/java-program-to-implement-shunting-yard-algorithm/) : lines  90 and 110. 
* [Methods of Stacks](https://docs.oracle.com/javase/7/docs/api/java/util/Stack.html) : used all throughout the assignment
* [Methods of Queues](https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html) : used all throughout the assignment


# Final Reflection 

Our biggest goals for this assignment were: 1) making it work and 2) making sure the documentation, including method & names and usage, were consistent and clear. For this reason we tried to compartmentalize our Calculate class as much as possible. There was a point at which we were unable to troubleshoot although we filled our code with print statements. Using separate methods made everything clear and easy to parse through. This assignment was for sure an encounter with the frustrating aspects of coding, but they were well worth it when everything finally made sense. 