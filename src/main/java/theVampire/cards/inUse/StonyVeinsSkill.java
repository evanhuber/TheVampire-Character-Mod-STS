package theVampire.cards.inUse;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVampire.DefaultMod;
import theVampire.actions.StonyVeinsAction;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;

import static theVampire.DefaultMod.makeCardPath;

public class StonyVeinsSkill extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(StonyVeinsSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("StonyVeinsSkill.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = -1;
    private static final int UPGRADED_COST = -1;

    private static final int BLOODCOST = 8;
    private static final int UPGRADE_PLUS_BLOODCOST = -2;

    // /STAT DECLARATION/


    public StonyVeinsSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = BLOODCOST;
        this.exhaust = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
               new StonyVeinsAction(p, this.freeToPlayOnce, this.energyOnUse, magicNumber));
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
