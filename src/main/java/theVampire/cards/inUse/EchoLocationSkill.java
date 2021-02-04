package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVampire.DefaultMod;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;
import theVampire.powers.BatPower;

import static theVampire.DefaultMod.makeCardPath;

// public class EchoLocationSkill extends AbstractDynamicCard
public class EchoLocationSkill extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(EchoLocationSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("EchoLocationSkill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DRAW = 0;
    private static final int DISCARD = 2;
    private static final int UPGRADE_PLUS_DISCARD = -1;

    // /STAT DECLARATION/


    @Override
    public void applyPowers(){
        AbstractPower bats = AbstractDungeon.player.getPower(BatPower.POWER_ID);
        if (bats != null) {
            baseMagicNumber = magicNumber = bats.amount/2;
        } else {
            baseMagicNumber = magicNumber = 0;
        }
        super.applyPowers();
    }

    public EchoLocationSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = DRAW;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = DISCARD;

    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DrawCardAction(p, magicNumber, false));

        AbstractDungeon.actionManager.addToBottom(
                new DiscardAction(p, p, defaultSecondMagicNumber, false));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_DISCARD);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
