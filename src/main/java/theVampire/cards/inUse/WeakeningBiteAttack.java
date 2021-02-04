package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theVampire.DefaultMod;
import theVampire.cards.AbstractDynamicCard;
import theVampire.cards.tags.CustomTags;
import theVampire.characters.TheVampire;

import static theVampire.DefaultMod.makeCardPath;

// public class WeakeningBiteAttack extends AbstractDynamicCard
public class WeakeningBiteAttack extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(WeakeningBiteAttack.class.getSimpleName());
    public static final String IMG = makeCardPath("WeakeningBiteAttack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 4;
    private static final int WEAK = 1;
    private static final int UPGRADE_PLUS_WEAK = 1;

    // /STAT DECLARATION/


    public WeakeningBiteAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CustomTags.BITE);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = WEAK;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction (m, new DamageInfo (p, damage, damageTypeForTurn),
        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_WEAK);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
