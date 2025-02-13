/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;


//inherits from card is parent of all colour cards
public class ColourCard extends Card {
    
    //colour class
    private String colour;
    
    //constructor that make use of super
    public ColourCard (int cardCodeNumberArg, String colourArg)
    {
        super(cardCodeNumberArg);
        colour = colourArg;
    }
    
    //returns colour
    public String getColour()
    {
        return colour;
    }
    
    //returns details of properties that include card code
    @Override
    public String GetString()
    {
        String returnValue = "Card Code:"+getCardCodeNumber()+" - "+ getColour();
        return returnValue;
    }
    
    //returns details of properties that do not include card code
    @Override
    public String GetShortString()
    {
        String returnValue = getColour();
        return returnValue;
    }         
}
