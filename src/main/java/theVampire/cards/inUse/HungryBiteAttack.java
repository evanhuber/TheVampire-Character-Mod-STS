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
import theVampire.cards.tags.CustomTags;
import theVampire.characters.TheVampire;
import theVampire.powers.BloodPower;

import static theVampire.DefaultMod.makeCardPath;

// public class HungryBiteAttack extends AbstractDynamicCard
public class HungryBiteAttack extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(HungryBiteAttack.class.getSimpleName());
    public static final String IMG = makeCardPath("HungryBiteAttack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 4;
    private static final int BLOOD = 5;
    private static final int UPGRADE_PLUS_BLOOD = 3;

    // /STAT DECLARATION/


    public HungryBiteAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        tags.add(CustomTags.BITE);
        baseDamage = DAMAGE;
        bloodBaseVariable = bloodVariable = BLOOD;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction (m, new DamageInfo (p, damage, damageTypeForTurn),
        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new BloodPower(p, p, bloodVariable)));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBloodVariable(UPGRADE_PLUS_BLOOD);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
