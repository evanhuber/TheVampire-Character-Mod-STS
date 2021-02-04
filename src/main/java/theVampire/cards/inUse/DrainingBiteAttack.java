package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theVampire.DefaultMod;
import theVampire.cards.AbstractDynamicCard;
import theVampire.cards.tags.CustomTags;
import theVampire.characters.TheVampire;

import static theVampire.DefaultMod.makeCardPath;

// public class DrainingBiteAttack extends AbstractDynamicCard
public class DrainingBiteAttack extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(DrainingBiteAttack.class.getSimpleName());
    public static final String IMG = makeCardPath("DrainingBiteAttack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int HEALING = 3;

    private static final int STRENGTHDRAIN = 1;
    private static final int UPGRADE_PLUS_STRENGTHDRAIN = 1;

    private static final int DAMAGE = 10;
    private static final int UPGRADE_PLUS_DMG = 0;

    // /STAT DECLARATION/


    public DrainingBiteAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CustomTags.BITE);
        this.tags.add(CardTags.HEALING);
        baseMagicNumber = magicNumber = STRENGTHDRAIN;
        baseDamage = DAMAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction (m, new DamageInfo (p, damage, damageTypeForTurn),
        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        AbstractDungeon.actionManager.addToBottom(
                new HealAction(p, p, HEALING));

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new StrengthPower(m, -magicNumber)));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_STRENGTHDRAIN);
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
