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

// public class SerratedStrikeAttack extends AbstractDynamicCard
public class SerratedStrikeAttack extends AbstractDynamicCard {


    // TEXT DECLARATION


    public static final String ID = DefaultMod.makeID(SerratedStrikeAttack.class.getSimpleName());
    public static final String IMG = makeCardPath("SerratedStrikeAttack.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int HEMORRHAGE = 4;
    private static final int UPGRADE_PLUS_HEM = 1;

    // /STAT DECLARATION/


    public SerratedStrikeAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CardTags.STRIKE);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = HEMORRHAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction (m, new DamageInfo (p, damage, damageTypeForTurn),
        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new HemorrhagePower(m, p, magicNumber), magicNumber)
        );
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(UPGRADE_PLUS_HEM);
            initializeDescription();
        }
    }
}
