package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVampire.DefaultMod;
import theVampire.actions.SpendBloodAction;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;

import static theVampire.DefaultMod.makeCardPath;

// public class BloodSacrificeSkill extends AbstractDynamicCard
public class BloodSacrificeSkill extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(BloodSacrificeSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("BloodSacrificeSkill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int UPGRADED_COST = 0;

    private static final int DRAW = 3;

    private static final int BLOODCOST = 10;
    private static final int UPGRADE_PLUS_BLOODCOST = -2;

    // /STAT DECLARATION/


    public BloodSacrificeSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BLOODCOST;
    }


    // Spend 10(8) blood, draw 2 cards.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new SpendBloodAction(p, magicNumber));
        AbstractDungeon.actionManager.addToBottom(
                new DrawCardAction(p, DRAW, false));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_BLOODCOST);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
