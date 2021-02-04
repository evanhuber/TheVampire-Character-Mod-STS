package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVampire.DefaultMod;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;

import static theVampire.DefaultMod.makeCardPath;

// public class ExposeWeaknessSkill extends AbstractDynamicCard
public class ExposeWeaknessSkill extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(ExposeWeaknessSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("ExposeWeaknessSkill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int DISCARD = 1;
    private static final int UPGRADE_PLUS_DISCARD = -1;


    // /STAT DECLARATION/


    public ExposeWeaknessSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
       baseMagicNumber = magicNumber = DISCARD;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DiscardAction(p, p, magicNumber, false));

        AbstractDungeon.actionManager.addToBottom(
                new RemoveAllBlockAction(m, p));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DISCARD);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
