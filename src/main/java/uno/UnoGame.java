package uno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//import java.util.Collections;

//main entry class for the program
public class UnoGame{

    private ArrayList<Card> cardListStack = new ArrayList<>(); //list of cards in draw pile
    private ArrayList<Card> cardListDiscard = new ArrayList<>(); //list of cards in discard pile
    private ArrayList<Player> playerList = new ArrayList<>(); //list of players
    private final int numberPlayers = 4;        //default number of players (no current functionality to update in game)
    private final int startingCardNumber = 3;   //default number of cards (no current functionality to update in game)
    private int cardCodeNumber = 0;             //code number to identify which card to play



    // Task 0: 
        public void playGame(){
            createCards();//create cards for play
            createPlayers();//create players in teh game
            dealCards();//deal cards to players
            playFirstCard();//turn over top card
            playByTurn();//play cards until a winner is determined
        }

    // Task 1: Writer method createCards() to create 40 card and add them to cardListStack. Then suffle the cards in cardListStack.
        // this code breake this task into  steps.
        //Helper: creates a type of colour card
        private void CreateColourCards(String colour)
        {
           //creating cards of a particular colour with numbers 0 to 9
           for (int i =0; i<10; i++)
           {
               cardCodeNumber = cardCodeNumber +1;          
               StandardColourCard scc= new StandardColourCard (cardCodeNumber, colour, i);
               //adding card to the stack of overall cards
               cardListStack.add(scc);
           }
        }
        //controls the creation each of the four types of colour cards in turn
        private void createCards()
        {
            CreateColourCards("Red");
            CreateColourCards("Blue");
            CreateColourCards("Yellow");
            CreateColourCards("Green"); 
            Collections.shuffle(cardListStack);
        }




    // Task 2: Create players and store in playerList. The first player is human. The human palyer's name needs to be read in from keyboard. 
        //creates each of the live and bot players
        private void createPlayers()
        {   
            boolean isLivePlayer;
            for (int i =1; i<numberPlayers+1; i++)
            {
                //by default first player is only live player in game
                if (i==1)
                {
                    String playerName="";
                    boolean isValidName = false;
                    //checking if name has been entered for player
                    while (isValidName==false)    
                    {   
                        //player enters their name
                        Scanner scannerObject =  new Scanner(System.in);
                        System.out.println("Enter Player Name");
                        playerName = scannerObject.nextLine();

                        //checking valid name has been entered
                        if (playerName.isEmpty()||playerName.startsWith(" "))
                        {
                            System.out.println("Enter a name with one or more non-space characters");
                        }
                        else
                        {
                            isValidName=true;
                        }                
                    }

                    isLivePlayer = true;
                    Player playerLive = new Player(playerName, isLivePlayer);
                    //adding player to list of players
                    playerList.add(playerLive);
                    System.out.println("Player name is: " + playerLive.GetName());      
                }

                //for creation of bot players
                else
                {
                    isLivePlayer = false;
                    String playerName = "Player " + Integer.toString(i);
                    Player playerBot = new Player(playerName, isLivePlayer);
                    //adding player to list of players
                    playerList.add(playerBot);                
                }
            }
        }



    // Task 3: write dealCards() method that deals/moves cards from cardListStack to individual playerList stacks - cards are dealt
        //moves cards from cardListStack to individual playerList stacks - cards are dealt
        private void dealCards(){
            //number of time loop repeats is number of cards dealt to each player
            for (int i =0; i<startingCardNumber; i++)
            {
                //actually 'dealing' cards to each player
                for (int j =0; j<numberPlayers; j++)
                {
                    Card card = cardListStack.get(0);            
                    Player player = playerList.get(j);
                    player.AddCardToHand(card);
                    cardListStack.remove(0);
                }            
            }            
        }

    // Task 4: Write method PrintPlayerCards() that prints cards a player has.    
        //Helper: displays the list of cards from an ArrayList of cards
        private void PrintCards(ArrayList<Card> cardListArg)
        {
            for (int i =0; i<cardListArg.size(); i++)
           {
               Card aCard = cardListArg.get(i);
               System.out.println(aCard.GetString());       
           }  
        }
        //prints out a list of cards that a player has. 
        private void PrintPlayerCards(int playerNumber)            
        { 
                Player player = playerList.get(playerNumber);
                System.out.println("Cards that " + player.GetName() + " has are");                          
                PrintCards(player.cardListPlayer);
                System.out.println(); 
        }


    // Task 5: Write method playFirstCard() the moves the top card from the 
        // cardListStack to be top card in the discardStack. This card is shown to players. 
        // also print this card information. You need to call method to print from Card class.
        // moves the top card from the cardListStack to be top card in the discardStack 
        // i.e. the first active card to start the game
        private void playFirstCard()
        {
            Card cardMove = cardListStack.get(0);
            cardListDiscard.add(0, cardMove);
            cardListStack.remove(0);  
            // ShowTopCard();
            System.out.println(cardMove.GetString());  
        }
        //displays the top card of the cardListDiscard
        private void ShowTopCard()
        {
            Card card = cardListDiscard.get(0);        
            System.out.println(card.GetShortString());
        }



    // Task 7.a Write mathod CheckIfCardValid() to verify is a card played by a player matches the top card of cardListDiscard
        //checking if either the colour or number of the selected card matches the top card of discard pile
        private boolean CheckIfCardValid(Card checkCard)
        {

            Card discardCard = cardListDiscard.get(0);
            //casting cards to be StandardColourCards to access properties
            StandardColourCard discardCardStd = (StandardColourCard)discardCard;
            StandardColourCard checkCardStd = (StandardColourCard)checkCard;

            //retrieving number and colour values for comparison from top of discard pile and selected card from player hand
            String discardColour = discardCardStd.getColour();
            int discardNumber = discardCardStd.getCardNumber();
            String checkColour = checkCardStd.getColour();
            int checkNumber = checkCardStd.getCardNumber();

            //checking if retrieved colour or number matches
            if ((discardColour.contentEquals(checkColour))|| discardNumber==checkNumber )
            {
                return true;
            }
            else
            {
                return false;
            }                    
        }
    
    // Task 7.b Write method AddCardToPlayerHand() that deal a card from  cardListStack to player's cards list cardListPlayer
        // if cardListStack is empty, move all cards from cardListDiscard, except the top card (matching card) 
        // to cardListStack and suffle them before dealing
        // adds a card to player hand i.e. when player forced to 'pick up' a card as they are unable to play a card
        private void AddCardToPlayerHand(int playerNumber)
        {
            //checking if there is a card in cardListStack to pick up. If not, moves cards from discard stack to cardListStack
            if (cardListStack.size()==0)       
            {
               RefreshStack();
            }
            //adding a card to player hand        
            Player player = playerList.get(playerNumber);
            ArrayList<Card> playerCards = player.GetCardListPlayer();
            playerCards.add(cardListStack.get(0));
            cardListStack.remove(0);
        }
        //Helper: moves cards from discard pile to main stack 
        private void RefreshStack()
        {
           //saving the top card of the discard pile before updating
           Card lastCard = cardListDiscard.get(0);
           cardListDiscard.remove(0);

           //moving cards from discard pile to cardListStack
           while(cardListDiscard.size()>0)
           {
               Card card = cardListDiscard.get(0);
               cardListStack.add(card);
               cardListDiscard.remove(0);
           }
           //Shuffling cards in cardListStack
           Collections.shuffle(cardListStack);
           //saved top card goes to top of discard pile
           cardListDiscard.add(0,lastCard);
        }
    
    // Task 7.c: Write method humanPlayCard() that manages the selection of a card for human player. 
        // This includes:
            // print the top card of cardListDiscard to be matched so the the human player can know matching card.
            // asking the player to choose cardCode of a card that he/she has and want to play
                // verify if this cardCode is one of cards the player has
                // verify if this card matches top card of cardListDiscard
                // update cardListDiscard and the player card list playerCards if a card is play
            // asking the player to enter 0 (or whatever you want, for example "N") if he/she does not have matching card or does not want to play a card
            // manage to deal a new card the the player
                // update cardListStack and and the player card list playerCards
            // Consider repeatedly asking the player to enter card code until it valid or until he/she enter 0 (or "N" as your choice).
        
        //mangages the selection of a card for a live player
        private void humanPlayCard()
        {
            boolean validPlay = false;
            //loops until a valid selection of a card is made
            while (validPlay==false)
            {
                //displays the cards they play has
                PrintPlayerCards(0);

                //displays the card on top of the discard pile
                System.out.println("The top card is:");
                ShowTopCard();
                Scanner scannerObject =  new Scanner(System.in);
                System.out.println("Enter the Card Code of the card you would like to play");
                System.out.println("If you don't have a valid card enter 0");
                String codeNumberString = scannerObject.nextLine();
                // checking that values entered are in correct number format
                try
                {
                    Integer codeNumberPlayer = Integer.valueOf(codeNumberString);  

                    //adds card to player hand (to be used if they don't have a valid card to play)
                    if (codeNumberPlayer==0)
                    {
                        AddCardToPlayerHand(0);
                        System.out.println("A card has been added to your hand.");
                        PrintPlayerCards(0);
                        return;
                    }

                    Player player = playerList.get(0);
                    ArrayList<Card> playerCards = player.GetCardListPlayer();

                    //looping through the cards the player has in their hand
                    for (int i=0; i<playerCards.size(); i++)
                    {
                        Card playerCard = playerCards.get(i);

                        //checking if the selected card matches one in the player hand
                        if (playerCard.getCardCodeNumber()==codeNumberPlayer)
                        {                
                            //running a check first to see if card is able to be legitimately played
                            if (CheckIfCardValid(playerCard)== true)
                            {
                                //moving card from player hand to top of discard pile
                                cardListDiscard.add(0,playerCard);
                                playerCards.remove(i);
                                System.out.println(player.GetName());
                                ShowTopCard();
                                return;  //exit from the method as legitimate card has been played              
                            }
                        }
                    } 
                    System.out.println("That is not a valid selection - try again \n");
                }
                //catching for circumstances where non integer value entered for card number.
                catch (Exception e)
                {
                   System.out.println("Number input was expected. Try again");
                }
            }    
        }
    // Task 7.d: Write method boltPlayCard() 
        //mangages the selection of a card for a bolt player
        private void boltPlayCard(int playerNumber){
            boolean hasFinishedTurn = false;                       
            Player player = playerList.get(playerNumber);
            ArrayList<Card> playerCards = player.GetCardListPlayer();
            //for each card that a player has
            for (int j=0; j<playerCards.size(); j++)
            {
                 Card playerCard = playerCards.get(j);
                 //checking if the selected card can be played
                 if (CheckIfCardValid(playerCard)== true)
                 {
                     //moves card from player list of cards to top of discard list of cards
                     cardListDiscard.add(0,playerCard);
                     playerCards.remove(j);
                     System.out.println(player.GetName()+ " has played the following card:");
                     ShowTopCard();
                     //breaks out of the loop if a valid card has been played (avoids multiple cards being played)
                     hasFinishedTurn = true; 
                     break;
                 }
            } 
            //checks if a card has been played. Adds a card to hand if not
            if (hasFinishedTurn==false)
            {
                System.out.println (player.GetName() +  " has picked up a card");
                AddCardToPlayerHand(playerNumber);                            
            }  
        }



    // Task 7 e:  write method playByTurn that manage to let players take turns to play.
        // The human player (playe 1) starts the games: call humanPlayCard
        // then bots player 2, 3, 4: call boltPlayCard
        // Repeatedly print what each player has done and honw many card the player has, especially what cards the human player has after his/her turn   
        // if one player wins, print an anouncement and end the game 

        //manages individual player turns
        private void playByTurn()
        {
            boolean winner = false;   
            //loops until player has won
            while (winner == false)
            {
               //for each player in the game
                for (int i=0; i<playerList.size(); i++)
                {
                   //assumption that player 0 is the live player in the game
                   if (i == 0)
                   {
                       //live player plays their card
                       humanPlayCard();
                   }
                   //management of bot players
                   else
                   {
                      boltPlayCard(i);            
                   } 

                    // Display result after a playing
                    Player player = playerList.get(i); 
                    ArrayList<Card> playerCards = player.GetCardListPlayer();
                   //checking to see if the current player may have 0 cards
                    if (playerCards.size()==0)
                    {
                        winner= true;
                        System.out.println(player.GetName()+ " is the winner");
                        //exiting the loop (and the game) if there is a winner
                        return;
                    }
                    //Displaying how many cards a player has
                    System.out.println(player.GetName()+ " has the following number of cards: " + playerCards.size()+ "\n");               
                }
            }

        }




} 