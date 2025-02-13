/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unoquattro;

/**
 *
 * @author MWright
 */

//type of Card that inherits from ColourCard
public class StandardColourCard extends ColourCard{
    int cardNumber;
    //standard constructor
    public StandardColourCard(int cardCodeNumberArg, String colourArg, int cardNumberArg){
        
        super(cardCodeNumberArg, colourArg);
        cardNumber = cardNumberArg;
    }
    // returns the number of the card
    public int getCardNumber()
    {
        return cardNumber;
    }
    // returns a string that includes details of the card code
    @Override
    public String GetString(){
        
                
        //String returnString = super.GetString()&" "&getCardNumber();
        String returnString; 
        returnString = super.GetString()+" "+Integer.toString(getCardNumber());
        return returnString;
        
    }
    
    @Override
    //returns detials of a string that does not include the card code
    public String GetShortString()
    {
        String returnString = super.GetShortString()+" "+Integer.toString(getCardNumber());
        return returnString;        
    }
    
}
