package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVampire.powers.BloodPower;



public class SpendBloodAction extends AbstractGameAction {
    //Private Variables 
    private int bloodCost;
    private AbstractPlayer p;


    public SpendBloodAction(final AbstractPlayer p, final int bloodCost) {
        this.p = p;
        this.bloodCost = bloodCost;
        actionType = ActionType.SPECIAL;

    }


    @Override
    public void update(){

        AbstractPower power = AbstractDungeon.player.getPower(BloodPower.POWER_ID);
        if (power != null && bloodCost > power.amount) {
            int costLeft = bloodCost - power.amount;
            AbstractDungeon.actionManager.addToTop(
                    new ReducePowerAction(p, p, BloodPower.POWER_ID, power.amount));
            AbstractDungeon.actionManager.addToTop(
                    new LoseHPAction(p, p, costLeft));
        }
        else if (power != null && bloodCost <= power.amount) {
            AbstractDungeon.actionManager.addToTop(
                    new ReducePowerAction(p, p, BloodPower.POWER_ID, bloodCost));
        }
        else {
            AbstractDungeon.actionManager.addToTop(
                    new LoseHPAction(p, p, bloodCost));
        }
        isDone = true;
    }
}
