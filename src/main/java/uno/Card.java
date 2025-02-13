/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

//abstract parent class for all cards
public abstract class Card {
    
    //unique identifier for each card
    private int cardCodeNumber;
    
    //constructor 
    public Card(int cardCodeNumberArg){
       cardCodeNumber = cardCodeNumberArg; 
    }
    
    //returns card code   
    public int getCardCodeNumber(){
        return cardCodeNumber;
    }
    
    // returns details of properties in string format
    public abstract String GetString();  
    //returns details of properties in shortend string format
    public abstract String GetShortString();
    
}
