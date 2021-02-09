package theVampire.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theVampire.DefaultMod;
import theVampire.util.TextureLoader;

import static theVampire.DefaultMod.makePowerPath;


public class StarvingDesperationPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("StarvingDesperationPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int growingDamage = 5;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public StarvingDesperationPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
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


    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m){
        if (card.type == AbstractCard.CardType.ATTACK){
            this.flash();
            AbstractDungeon.actionManager.addToTop(
                    new DamageAction(m, new DamageInfo(owner, card.damage)));
        }

    }

    @Override
    public void onInitialApplication() {
        AbstractPower blood = AbstractDungeon.player.getPower(BloodPower.POWER_ID);
        if (blood != null && blood.amount > 0){
            AbstractDungeon.actionManager.addToBottom(
                    new RemoveSpecificPowerAction(owner, owner, BloodPower.POWER_ID));
        }
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power == AbstractDungeon.player.getPower(BloodPower.POWER_ID)){
            AbstractDungeon.actionManager.addToBottom(
                    new ReducePowerAction(owner, owner, power, power.amount));
        }
    }


    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(
                new LoseHPAction(owner, owner, growingDamage));
        growingDamage++;
        updateDescription();


    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + growingDamage + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new StarvingDesperationPower(owner, source, amount);
    }
}
