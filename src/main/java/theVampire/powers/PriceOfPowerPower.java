package theVampire.powers;

import basemod.interfaces.AlternateCardCostModifier;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVampire.DefaultMod;
import theVampire.actions.SpendBloodAction;
import theVampire.util.TextureLoader;

import static theVampire.DefaultMod.makePowerPath;


public class PriceOfPowerPower extends AbstractPower implements AlternateCardCostModifier {
    public AbstractCreature source;
    public AbstractPlayer p;

    public static final String POWER_ID = DefaultMod.makeID("PriceOfPowerPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // Texture Paths
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public PriceOfPowerPower(final AbstractCreature owner, final AbstractCreature source, final AbstractPlayer p) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;
        this.p = p;

        type = PowerType.BUFF;
        isTurnBased = false;

        // Load Textures
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public int getAlternateResource(AbstractCard abstractCard)
    {
        return 999;
    }

    @Override
    public boolean prioritizeAlternateCost(AbstractCard card)
    {
        return true;
    }

    @Override
    public int spendAlternateCost(AbstractCard abstractCard, int cardCost)
    {
        AbstractDungeon.actionManager.addToTop(new SpendBloodAction(p,  (cardCost * 3)));
        return 0;
    }

    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        AbstractDungeon.actionManager.addToBottom(
                new RemoveSpecificPowerAction(this.owner, this.owner, this.POWER_ID));
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
