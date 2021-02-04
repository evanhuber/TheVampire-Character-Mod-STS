package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVampire.DefaultMod;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;
import theVampire.powers.AllTheTimeInTheWorldPower;

import static theVampire.DefaultMod.makeCardPath;

// public class AllTheTimeInTheWorldSkill extends AbstractDynamicCard
public class AllTheTimeInTheWorldSkill extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(AllTheTimeInTheWorldSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("AllTheTimeInTheWorldSkill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;

    private static final int STACKS = 2;
    private static final int UPGRADE_PLUS_STACKS = 1;

    // /STAT DECLARATION/


    public AllTheTimeInTheWorldSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = STACKS;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new AllTheTimeInTheWorldPower(p, p, magicNumber)));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_STACKS);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
