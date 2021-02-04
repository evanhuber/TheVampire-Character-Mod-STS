package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theVampire.DefaultMod;
import theVampire.actions.SpendBloodAction;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;

import static theVampire.DefaultMod.makeCardPath;

// public class YoungAgainSkill extends AbstractDynamicCard
public class YoungAgainSkill extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(YoungAgainSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("YoungAgainSkill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int UPGRADED_COST = 0;

    private static final int STR_INC = 1;
    private static final int UPGRADE_PLUS_STR_INC = 1;

    // /STAT DECLARATION/


    public YoungAgainSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        magicNumber = baseMagicNumber = STR_INC;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new SpendBloodAction(p, 4));

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            this.exhaust = false;
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_STR_INC);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
