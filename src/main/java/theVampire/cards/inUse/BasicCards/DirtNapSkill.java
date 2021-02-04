package theVampire.cards.inUse.BasicCards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVampire.DefaultMod;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;

import static theVampire.DefaultMod.makeCardPath;

// public class DirtNapSkill extends AbstractDynamicCard
public class DirtNapSkill extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(DirtNapSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("DirtNapSkill.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 5;

    private static final int HEAL = 3;


    // /STAT DECLARATION/


    public DirtNapSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        tags.add(CardTags.HEALING);
        baseHeal = HEAL;
        baseBlock = BLOCK;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, block));
        AbstractDungeon.actionManager.addToBottom(
                new HealAction(p, p, baseHeal));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
