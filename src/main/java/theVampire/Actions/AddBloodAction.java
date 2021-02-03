package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVampire.powers.BloodPower;
import theVampire.powers.StarvingDesperationPower;

public class AddBloodAction extends AbstractGameAction {
    //Private Variables
    private int magicNumber;
    private AbstractPlayer p;


    public AddBloodAction(final AbstractPlayer p, final int magicNumber) {
        this.p = p;
        this.magicNumber = magicNumber;
        actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        AbstractPower power = AbstractDungeon.player.getPower(StarvingDesperationPower.POWER_ID);
        if (power != null && power.amount == 0) {
            AbstractDungeon.actionManager.addToTop(
                    new ApplyPowerAction(p, p, new BloodPower(p, p, magicNumber), magicNumber));
        } else if (power == null) {
            AbstractDungeon.actionManager.addToTop(
                    new ApplyPowerAction(p, p, new BloodPower(p, p, magicNumber), magicNumber));
        } else { power.flash(); }
        isDone = true;
    }
}
