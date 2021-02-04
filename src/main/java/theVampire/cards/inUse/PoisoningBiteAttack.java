package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theVampire.DefaultMod;
import theVampire.cards.AbstractDynamicCard;
import theVampire.cards.tags.CustomTags;
import theVampire.characters.TheVampire;

import static theVampire.DefaultMod.makeCardPath;

// public class PoisoningBiteAttack extends AbstractDynamicCard
public class PoisoningBiteAttack extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(PoisoningBiteAttack.class.getSimpleName());
    public static final String IMG = makeCardPath("PoisoningBiteAttack.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 4;
    private static final int UPGRADE_PLUS_DMG = 2;

    private static final int POISON = 4;
    private static final int UPGRADE_PLUS_POISON = 4;

    // /STAT DECLARATION/


    public PoisoningBiteAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CustomTags.BITE);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = POISON;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction (m, new DamageInfo (p, damage, damageTypeForTurn),
        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new PoisonPower(m, p, magicNumber), magicNumber, AbstractGameAction.AttackEffect.POISON));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(UPGRADE_PLUS_POISON);
            initializeDescription();
        }
    }
}
