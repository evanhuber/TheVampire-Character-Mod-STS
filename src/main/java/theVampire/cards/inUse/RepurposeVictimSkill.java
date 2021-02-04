package theVampire.cards.inUse;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVampire.DefaultMod;
import theVampire.actions.AddBloodAction;
import theVampire.cards.AbstractDynamicCard;
import theVampire.characters.TheVampire;


import static theVampire.DefaultMod.makeCardPath;

public class RepurposeVictimSkill extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(RepurposeVictimSkill.class.getSimpleName());
    public static final String IMG = makeCardPath("RepurposeVictimSkill.png");



    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheVampire.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 1;

    private static final int BLOCK = 8;
    private static final int UPGRADE_PLUS_BLOCK = 0;

    private static final int BLOOD = 4;
    private static final int UPGRADE_PLUS_BLOOD = 2;

    // /STAT DECLARATION/


    public RepurposeVictimSkill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        bloodVariable = bloodBaseVariable = BLOOD;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p, p, block));
        AbstractDungeon.actionManager.addToBottom(
                new AddBloodAction(p, bloodVariable));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            upgradeBaseCost(UPGRADED_COST);
            upgradeBloodVariable(UPGRADE_PLUS_BLOOD);
            initializeDescription();
        }
    }
}
