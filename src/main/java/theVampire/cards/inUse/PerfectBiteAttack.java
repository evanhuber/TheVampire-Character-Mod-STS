package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theVampire.DefaultMod;
import theVampire.cards.AbstractDynamicCard;
import theVampire.cards.tags.CustomTags;
import theVampire.characters.TheVampire;

import java.util.Iterator;

import static theVampire.DefaultMod.makeCardPath;

// public class PerfectBiteAttack extends AbstractDynamicCard
public class PerfectBiteAttack extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(PerfectBiteAttack.class.getSimpleName());
    public static final String IMG = makeCardPath("PerfectBiteAttack.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 3;

    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DMG = 2;

    private static final int VULNERABLE = 3;
    private static final int WEAK = 3;
    private static final int POISON = 8;

    // /STAT DECLARATION/


    public PerfectBiteAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    public static int countCards() {
        int count = 0;
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (isBite(c)) {
                ++count;
            }
        }

        var1 = AbstractDungeon.player.drawPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (isBite(c)) {
                ++count;
            }
        }

        var1 = AbstractDungeon.player.discardPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (isBite(c)) {
                ++count;
            }
        }

        return count;
    }

    public static boolean isBite(AbstractCard c) {
        return c.hasTag(CustomTags.BITE);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        for (int i = 0; i < countCards(); i++){
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction (m, new DamageInfo (p, damage, damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new VulnerablePower(m, VULNERABLE, false)));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new WeakPower(m, WEAK, false)));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new PoisonPower(m, p, POISON)));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
