package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVampire.DefaultMod;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;
import theVampire.powers.BloodPower;

import static theVampire.DefaultMod.makeCardPath;

// public class BloodBathAttack extends AbstractDynamicCard
public class BloodBathAttack extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(BloodBathAttack.class.getSimpleName());
    public static final String IMG = makeCardPath("BloodBathAttack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 3;

    private static final int ATTACKS = 2;
    private static final int UPGRADE_PLUS_ATTACKS = 1;

    // /STAT DECLARATION/

    @Override
    public void applyPowers(){
        AbstractPower BloodStacks = AbstractDungeon.player.getPower(BloodPower.POWER_ID);
        if (BloodStacks != null) {
            baseDamage = BloodStacks.amount;
        }
        super.applyPowers();

    }

    @Override
    public void calculateCardDamage(AbstractMonster mo){
        AbstractPower BloodStacks = AbstractDungeon.player.getPower(BloodPower.POWER_ID);
        if (BloodStacks != null) {
            baseDamage = BloodStacks.amount;
        }
        super.calculateCardDamage(mo);
    }

    public BloodBathAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = 0;
        baseMagicNumber = magicNumber = ATTACKS;
        this.isMultiDamage = true;
        this.exhaust = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn,
                            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        AbstractDungeon.actionManager.addToBottom(
                new HealAction(p, p, damage));

        AbstractDungeon.actionManager.addToBottom(
                new RemoveSpecificPowerAction(p, p, BloodPower.POWER_ID));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_ATTACKS);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
