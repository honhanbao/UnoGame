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

//this class has not been utilised at this point
public class SpecialColourCard extends ColourCard implements SpecialAbility{
    String specialAbility;
    public SpecialColourCard(int cardCodeNumberArg, String colourArg, String specialAbilityArg){
        
        super(cardCodeNumberArg, colourArg);
        specialAbility = specialAbilityArg;
    }
    @Override
    public void ActivateAbility()
    {}
    
}
