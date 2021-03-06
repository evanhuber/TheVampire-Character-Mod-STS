package theVampire.powers;

import basemod.abstracts.cardbuilder.actionbuilder.HealActionBuilder;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theVampire.DefaultMod;
import theVampire.util.TextureLoader;

import static theVampire.DefaultMod.makePowerPath;

//Lose one blood per turn, heal 1 hp.

public class BloodPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("BloodPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public BloodPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

       
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }
    
    
    public void stackPower(int stackAmount){
        super.stackPower(stackAmount);
    }

    // At the Start of the turn, remove one blood, heal 1hp.
    @Override
    public void atStartOfTurn() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, BloodPower.POWER_ID, 1));
        AbstractDungeon.actionManager.addToBottom(new HealAction(owner, owner, 1));
    }





    @Override
    public void updateDescription() {

        this.description = (DESCRIPTIONS[0]);
    }

    @Override
    public AbstractPower makeCopy() {
        return new BloodPower(owner, source, amount);
    }
}
