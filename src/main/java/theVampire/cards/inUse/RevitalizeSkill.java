package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVampire.DefaultMod;
import theVampire.actions.SpendBloodAction;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;

import static theVampire.DefaultMod.makeCardPath;

public class RevitalizeSkill extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(RevitalizeSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("RevitalizeSkill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int BLOODCOST = 5;
    private static final int HEAL = 5;
    private static final int UPGRADE_PLUS_HEAL = 3;

    // /STAT DECLARATION/


    public RevitalizeSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = HEAL;
        this.exhaust = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new SpendBloodAction(p, BLOODCOST));
        AbstractDungeon.actionManager.addToBottom(
                new HealAction(p, p, magicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_HEAL);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
