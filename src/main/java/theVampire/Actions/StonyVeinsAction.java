//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package theVampire.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;


public class StonyVeinsAction extends AbstractGameAction {
    private int energyOnUse = -1;
    private AbstractPlayer p;
    private boolean freeToPlayOnce = false;
    private int bloodCost;

    public StonyVeinsAction(AbstractPlayer p, boolean freeToPlayOnce, int energyOnUse, int bloodCost) {
        this.energyOnUse = energyOnUse;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.bloodCost = bloodCost;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;

        if (energyOnUse != -1 )
        {
            effect = energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect > 0)
        {
            for (int i = 0; i < effect; i++)
            {
                this.addToBot(new SpendBloodAction(p, bloodCost));
                this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, 4), 4));
            }
        }

        if (!this.freeToPlayOnce) {
            this.p.energy.use(EnergyPanel.totalCount);
        }

        this.isDone = true;
    }
}
