package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVampire.DefaultMod;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;
import theVampire.powers.HemorrhagePower;
import theVampire.powers.WolfPower;

import static theVampire.DefaultMod.makeCardPath;

// public class BeastsHungerAttack extends AbstractDynamicCard
public class BeastsHungerAttack extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(BeastsHungerAttack.class.getSimpleName());
    public static final String IMG = makeCardPath("BeastsHungerAttack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 3;

    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DMG = 1;


    // /STAT DECLARATION/

    //Calculate how many instances of damage are dealt
    @Override
    public void applyPowers(){
        AbstractPower wolves = AbstractDungeon.player.getPower(WolfPower.POWER_ID);
        if (wolves != null) {
            baseMagicNumber = magicNumber = wolves.amount;
        } else {
            baseMagicNumber = magicNumber = 0;
        }
        super.applyPowers();
    }
    @Override
    public void calculateCardDamage(AbstractMonster mo){
        AbstractPower wolves = AbstractDungeon.player.getPower(WolfPower.POWER_ID);
        if (wolves != null) {
            baseMagicNumber = magicNumber = wolves.amount;
        } else {
            baseMagicNumber = magicNumber = 0;
        }
        super.calculateCardDamage(mo);
    }



    public BeastsHungerAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = 0;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(m, p, new HemorrhagePower(m, p, 1), 1));
        }
        AbstractDungeon.actionManager.addToBottom(
                new RemoveSpecificPowerAction(p, p, WolfPower.POWER_ID));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
