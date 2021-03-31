package reskinContent.skinCharacter.skins.Automaton;

import automaton.AutomatonChar;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import reskinContent.skinCharacter.AbstractSkin;

public class BetaAutomaton extends AbstractSkin {

    public BetaAutomaton() {
        this.NAME = CardCrawlGame.languagePack.getUIString("reskinContent:ReSkinBronze").TEXT[1];
//        this.NAME = CardCrawlGame.languagePack.getUIString("reskinContent:ReSkin").TEXT[1];
        this.DESCRIPTION = CardCrawlGame.languagePack.getCharacterString(AutomatonChar.ID).TEXT[0];
        this.portraitStatic_IMG = ImageMaster.loadImage("bronzeResources/images/charSelect/charBG.png");
        this.SHOULDER1 = "bronzeResources/images/char/mainChar/shoulder.png";
        this.SHOULDER2 = "bronzeResources/images/char/mainChar/shoulderR.png";
        this.CORPSE = "bronzeResources/images/char/mainChar/corpse.png";
        this.atlasURL = "reskinContent/img/BronzeMod/BetaAutomaton/animation/BetaAutomaton.atlas";
        this.jsonURL = "reskinContent/img/BronzeMod/BetaAutomaton/animation/BetaAutomaton.json";
        this.renderscale = 1.6f;
    }
}

