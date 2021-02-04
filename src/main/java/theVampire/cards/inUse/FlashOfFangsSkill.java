package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theVampire.DefaultMod;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;

import static theVampire.DefaultMod.makeCardPath;

// public class FlashOfFangsSkill extends AbstractDynamicCard
public class    FlashOfFangsSkill extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(FlashOfFangsSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("FlashOfFangsSkill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int UPGRADED_COST = 0;

    private static final int WEAK = 2;
    private static final int UPGRADE_PLUS_WEAK = 1;

    private static final int VULNERABLE = 1;
    private static final int UPGRADE_PLUS_VULN = 1;
    // /STAT DECLARATION/


    public FlashOfFangsSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = WEAK;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = VULNERABLE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new VulnerablePower(m, defaultSecondMagicNumber, false)));

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_WEAK);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_VULN);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
