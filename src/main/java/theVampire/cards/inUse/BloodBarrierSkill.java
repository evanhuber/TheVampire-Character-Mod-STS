package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVampire.DefaultMod;
import theVampire.actions.SpendBloodAction;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;

import static theVampire.DefaultMod.makeCardPath;

// public class BloodBarrierSkill extends AbstractDynamicCard
public class BloodBarrierSkill extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(BloodBarrierSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("BloodBarrierSkill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int BLOODCOST = 5;
    private static final int UPGRADE_BLOOD_COST = -2;

    private static final int BLOCK = 15;
    private static final int UPGRADE_PLUS_BLOCK = 5;

    // /STAT DECLARATION/


    public BloodBarrierSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = BLOODCOST;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new SpendBloodAction(p, magicNumber));
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, block));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeMagicNumber(UPGRADE_BLOOD_COST);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
