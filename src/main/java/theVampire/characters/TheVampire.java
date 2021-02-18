package theVampire.characters;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import theVampire.DefaultMod;
import theVampire.cards.inUse.BasicCards.DefaultCommonAttack;
import theVampire.cards.inUse.BasicCards.DefaultCommonSkill;
import theVampire.cards.inUse.BasicCards.DirtNapSkill;
import theVampire.cards.inUse.BasicCards.FineWineSkill;
import theVampire.cards.inUse.PriceOfPowerSkill;
import theVampire.relics.DefaultClickableRelic;
import theVampire.relics.PlaceholderRelic;
import theVampire.relics.PlaceholderRelic2;
import theVampire.relics.VampireNightDayRelic;

import java.util.ArrayList;

import static theVampire.DefaultMod.*;
import static theVampire.characters.TheVampire.Enums.COLOR_GRAY;


public class TheVampire extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(DefaultMod.class.getName());

    // =============== CHARACTER ENUMERATORS =================

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_DEFAULT;
        @SpireEnum(name = "DEFAULT_GRAY_COLOR") 
        public static AbstractCard.CardColor COLOR_GRAY;
        @SpireEnum(name = "DEFAULT_GRAY_COLOR") @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    // =============== CHARACTER ENUMERATORS  =================


    // =============== BASE STATS =================

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 3;

    // =============== /BASE STATS/ =================


    // =============== STRINGS =================

    private static final String ID = makeID("DefaultCharacter");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    // =============== /STRINGS/ =================


    // =============== TEXTURES OF BIG ENERGY ORB ===============

    public static final String[] orbTextures = {
            "theVampireResources/images/char/defaultCharacter/orb/layer1.png",
            "theVampireResources/images/char/defaultCharacter/orb/layer2.png",
            "theVampireResources/images/char/defaultCharacter/orb/layer3.png",
            "theVampireResources/images/char/defaultCharacter/orb/layer4.png",
            "theVampireResources/images/char/defaultCharacter/orb/layer5.png",
            "theVampireResources/images/char/defaultCharacter/orb/layer6.png",
            "theVampireResources/images/char/defaultCharacter/orb/layer1d.png",
            "theVampireResources/images/char/defaultCharacter/orb/layer2d.png",
            "theVampireResources/images/char/defaultCharacter/orb/layer3d.png",
            "theVampireResources/images/char/defaultCharacter/orb/layer4d.png",
            "theVampireResources/images/char/defaultCharacter/orb/layer5d.png",};

    // =============== /TEXTURES OF BIG ENERGY ORB/ ===============

    // =============== CHARACTER CLASS START =================

    public TheVampire(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "theVampireResources/images/char/defaultCharacter/orb/vfx.png", null,
                new SpriterAnimation(
                        "theVampireResources/images/char/defaultCharacter/Spriter/theDefaultAnimation.scml"));


        // =============== TEXTURES, ENERGY, LOADOUT =================  

        initializeClass(null,
               
                THE_DEFAULT_SHOULDER_1, // campfire pose
                THE_DEFAULT_SHOULDER_2, // another campfire pose
                THE_DEFAULT_CORPSE, // dead corpse
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN)); // energy manager

        // =============== /TEXTURES, ENERGY, LOADOUT/ =================


        // =============== ANIMATIONS =================  

        loadAnimation(
                THE_DEFAULT_SKELETON_ATLAS,
                THE_DEFAULT_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        // =============== /ANIMATIONS/ =================


        // =============== TEXT BUBBLE LOCATION =================

        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 220.0F * Settings.scale); 

        // =============== /TEXT BUBBLE LOCATION/ =================

    }

    // =============== /CHARACTER CLASS END/ =================

    // Starting description and loadout
    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    // Starting Deck
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading starter Deck Strings");

        retVal.add(DefaultCommonAttack.ID);
        retVal.add(DefaultCommonSkill.ID);
        retVal.add(FineWineSkill.ID);
        retVal.add(DirtNapSkill.ID);
        retVal.add(PriceOfPowerSkill.ID);








        return retVal;
    }

    // Starting Relics	
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(VampireNightDayRelic.ID);

        UnlockTracker.markRelicAsSeen(PlaceholderRelic.ID);
        UnlockTracker.markRelicAsSeen(PlaceholderRelic2.ID);
        UnlockTracker.markRelicAsSeen(DefaultClickableRelic.ID);
        UnlockTracker.markRelicAsSeen(VampireNightDayRelic.ID);

        return retVal;
    }

    // character Select screen effect
    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f); // Sound Effect
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false); // Screen Effect
    }

    // character Select on-button-press sound effect
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    // Should return the card color enum
    @Override
    public AbstractCard.CardColor getCardColor() {
        return COLOR_GRAY;
    }

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return theVampire.DefaultMod.DEFAULT_GRAY;
    }


    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    // Should return class name as it appears in run history screen.
    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new DefaultCommonAttack();
    }

    // The class name in game
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheVampire(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return theVampire.DefaultMod.DEFAULT_GRAY;
    }


    @Override
    public Color getSlashAttackColor() {
        return theVampire.DefaultMod.DEFAULT_GRAY;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }


    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

  
    @Override
    public String getVampireText() {
        return TEXT[2];
    }

}
