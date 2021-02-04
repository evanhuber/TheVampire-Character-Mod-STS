package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVampire.DefaultMod;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;

import static theVampire.DefaultMod.makeCardPath;

// public class NarrowEscapeSkill extends AbstractDynamicCard
public class NarrowEscapeSkill extends AbstractDynamicCard {

    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(NarrowEscapeSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("NarrowEscapeSkill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int BLOCK = 15;
    private static final int UPGRADE_PLUS_BLOCK = 5;
    private static final int HEALTH_LOSS = 3;
    private static final int UPGRADE_HEALTH_LOSS = -1;

    // /STAT DECLARATION/


    public NarrowEscapeSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = HEALTH_LOSS;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Gain Block
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, block));
        //Lose Life
        AbstractDungeon.actionManager.addToBottom(
                new LoseHPAction(p, p, magicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.upgradeBaseCost(UPGRADED_COST);
            this.upgradeMagicNumber(UPGRADE_HEALTH_LOSS);
            initializeDescription();
        }
    }
}
