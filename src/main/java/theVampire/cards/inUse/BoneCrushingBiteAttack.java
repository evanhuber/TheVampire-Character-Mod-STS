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
import theVampire.cards.tags.CustomTags;
import theVampire.characters.TheVampire;
import theVampire.relics.VampireNightDayRelic;

import static theVampire.DefaultMod.makeCardPath;

// public class BoneCrushingBiteAttack extends AbstractDynamicCard
public class BoneCrushingBiteAttack extends AbstractDynamicCard {

    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(BoneCrushingBiteAttack.class.getSimpleName());
    public static final String IMG = makeCardPath("BoneCrushingBiteAttack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;

    private static final int DAMAGE = 15;
    private static final int UPGRADE_PLUS_DMG = 5;
    private static final int BLOOD = 3;
    private static final int UPGRADE_PLUS_BLOOD = 3;

    // /STAT DECLARATION/


    public BoneCrushingBiteAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        tags.add(CustomTags.BITE);
        baseDamage = DAMAGE;
        bloodVariable = bloodBaseVariable = BLOOD;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (VampireNightDayRelic.getIsNight() == true) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, (damage*2), damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        else{
            AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
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
