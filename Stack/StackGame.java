import java.util.*;  
/**
 * The StackGame class is a game that simulates the King of Stacks ruleset.
 * Two players will add disks to one of three stacks each round. 
 * Every 3rd, 5th, and 8th round, the top entry of the respective stack will be popped off. 
 * The player with the most entries across all three stacks at the end of the desired round count
 * will win the game. 
 * 
 * @author Frank Mina
 * @version 1.0
 */
public class StackGame
{ 

    public static void main(String[] args){

        //create the 3 stacks for the game
        StackInterface<String> stack1 = new VectorStack();
        StackInterface<String> stack2 = new VectorStack();
        StackInterface<String> stack3 = new VectorStack();

        //create scanner, random number generator, and player names 
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        String[] players = {"player1", "player2"};

        int p1score = 0;
        int p2score = 0; 
        
        //ask user for round count 
        System.out.println("How many turns would you like to play ?");
        int n = sc.nextInt();

        //if rounds < 30, ask for a new number >= 30 
        if( n < 30){
            System.out.println("The game cannot be played with a round count of less than 30, please try again");
            n = sc.nextInt();                     
        }

        //the game loop 
        for (int i = 1; i <= n; i++){
            System.out.println("Round: " + i); 

            //if statements for the pop conditions on ever 3rd, 5th, and 8th round. 
            if(i % 3 == 0){
                System.out.println("It is round " + i + ", the top of stack 1 will be popped.");

                //to prevent an empty stack exception, 
                //we will check to see if the stack is empty on the start of the turn
                //if the stack is in fact empty, we push string "null" to the front
                //the null string will be popped, and no player will lose score. 
                if(stack1.isEmpty()){
                    stack1.push("null"); 
                }

                //remove score from the players if their disk was removes from the stack
                if(stack1.peek().equals("player1")){
                    p1score--;
                }
                if(stack1.peek().equals("player2")){
                    p2score--;
                }
                stack1.pop(); 
            }
            if(i % 5 == 0){
                System.out.println("It is round " + i + ", the top of stack 2 will be popped.");

                if(stack2.isEmpty()){
                    stack2.push("null"); 
                }

                if(stack2.peek().equals("player1")){
                    p1score--;
                }
                if(stack2.peek().equals("player2")){
                    p2score--;
                }
                stack2.pop(); 
            }
            if(i % 8 == 0){
                System.out.println("It is round " + i + ", the top of stack 3 will be popped.");

                if(stack3.isEmpty()){
                    stack3.push("null"); 
                }
                if(stack3.peek().equals("player1")){
                    p1score--;
                }
                if(stack3.peek().equals("player2")){
                    p2score--;
                }
                stack3.pop(); 
            }

            //loop the player turns within each round, add their name to the stack for scoring
            for(int j = 0; j < 2; j++){
                int stackNum = rand.nextInt(3); 
                if(stackNum == 0){
                    System.out.println("Pushing a disk for " + players[j] + " to the top of stack 1");
                    stack1.push(players[j]); 
                }
                if(stackNum == 1){
                    System.out.println("Pushing a disk for " + players[j] + " to the top of stack 2");
                    stack2.push(players[j]);
                }
                if(stackNum == 2){
                    System.out.println("Pushing a disk for " + players[j] + " to the top of stack 3");
                    stack3.push(players[j]);
                }
            }
            //each player adds a disk during their turn regardless of the pop
            //increment score at the end of the round. 
            p1score++;
            p2score++; 
            
            System.out.println();
        }

        //win conditions
        if(p1score > p2score){
            System.out.println("Player 1 has won the game!");
            System.out.println("Their score was: " + p1score);
            System.out.println("Player 2's score was: " + p2score); 
        }
        if(p2score > p1score){
            System.out.println("Player 2 has won the game!");
            System.out.println("Their score was: " + p2score);
            System.out.println("Player 1's score was: " + p1score); 
        }
        if(p1score == p2score){
            System.out.println("The game has ended in a tie.");
            System.out.println("Both players had a score of: " + p1score);
        }

    }
}
