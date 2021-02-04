package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVampire.DefaultMod;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;
import theVampire.powers.HemorrhagePower;

import static theVampire.DefaultMod.makeCardPath;

// public class ThrowToTheWolvesAttack extends AbstractDynamicCard
public class ThrowToTheWolvesAttack extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(ThrowToTheWolvesAttack.class.getSimpleName());
    public static final String IMG = makeCardPath("ThrowToTheWolvesAttack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int HEMORRAGE = 2;
    private static final int UPGRADE_PLUS_HEM= 2;

    // /STAT DECLARATION/


    public ThrowToTheWolvesAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = HEMORRAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction (m, new DamageInfo (p, damage, damageTypeForTurn),
        AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new HemorrhagePower(m, p, magicNumber), this.magicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_HEM);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
