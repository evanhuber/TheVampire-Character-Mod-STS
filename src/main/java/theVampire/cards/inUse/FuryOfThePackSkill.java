package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theVampire.DefaultMod;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;
import theVampire.powers.WolfPower;

import static theVampire.DefaultMod.makeCardPath;

// public class FuryOfThePackSkill extends AbstractDynamicCard
public class FuryOfThePackSkill extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(FuryOfThePackSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("FuryOfThePackSkill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;




    // /STAT DECLARATION/

    @Override
    public void applyPowers(){
        AbstractPower WolfStacks = AbstractDungeon.player.getPower(WolfPower.POWER_ID);
        if (WolfStacks != null) {
            magicNumber = baseMagicNumber = (WolfStacks.amount/2) + 1;
        }
        else{
            magicNumber = baseMagicNumber = 1;
        }
        super.applyPowers();

    }


    public FuryOfThePackSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        magicNumber = baseMagicNumber = 0;
    }




    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new WolfPower(p, p, 2)));

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber))
        );
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
