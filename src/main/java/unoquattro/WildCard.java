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

//this class not utilised as this point
public class WildCard extends Card implements SpecialAbility 
{
    String specialAbility;
    
    public WildCard (int cardCodeNumberArg, String specialAbilityArg)
    {
        super(cardCodeNumberArg);
        specialAbility = specialAbilityArg;
    }
    
    
    @Override
    public String GetShortString()            
    {
        String returnValue = "";
        return returnValue;
    }
    
    @Override
    public String GetString()            
    {
        String returnValue = "";
        return returnValue;        
    }
    @Override
    public void ActivateAbility()            
    {

    }
    
}
