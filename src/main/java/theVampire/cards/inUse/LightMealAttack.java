package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVampire.DefaultMod;
import theVampire.actions.AddBloodAction;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;

import static theVampire.DefaultMod.makeCardPath;

// public class LightMealAttack extends AbstractDynamicCard
public class LightMealAttack extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(LightMealAttack.class.getSimpleName());
    public static final String IMG = makeCardPath("LightMealAttack.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 6;
    private static final int BLOOD = 2;
    private static final int UPGRADE_PLUS_BLOOD = 2;

    // /STAT DECLARATION/


    public LightMealAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        bloodVariable = bloodBaseVariable = BLOOD;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(
                new AddBloodAction(p, bloodVariable));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            upgradeBloodVariable(UPGRADE_PLUS_BLOOD);

            initializeDescription();
        }
    }
}
