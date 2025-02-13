/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unoquattro;
import java.util.*;
/**
 *
 * @author MWright
 */

//player class
public class Player {
    
    private String name;
    ArrayList<Card> cardListPlayer = new ArrayList<>();
    private boolean isLivePlayer;
    
    public Player (String nameArg, boolean isLivePlayerArg)
    {
        name = nameArg;
        isLivePlayer = isLivePlayerArg;
            
    }
    //returns Player name
    public String GetName()
    {
        return name;
    }
    
    //sets player name
    public void SetName(String nameArg)
    {
        name = nameArg;
    }
    
    //returns details if player live or bot
    public boolean GetIsLivePlayer()
    {
        return isLivePlayer;
    }
    
    //returns list of player cards
    public ArrayList<Card> GetCardListPlayer()
    {
        return cardListPlayer;
    }
    
    //sets list of player cards
    public void SetCardListPlayer(ArrayList<Card> cardListPlayerArg)
    {
        cardListPlayer = cardListPlayerArg;
    }
    
    //adds card to player hand
    public void AddCardToHand(Card cardArg)
    {
         cardListPlayer.add(cardArg);
    }
    
}
