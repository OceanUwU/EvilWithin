package hermit.cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import hermit.HermitMod;
import hermit.actions.EyeOfTheStormAction;
import hermit.characters.hermit;
import hermit.powers.SnipePower;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import static hermit.HermitMod.makeCardPath;

public class EyeOfTheStorm extends AbstractDynamicCard {

    //
    /*
     * SNAPSHOT: Deals 12/16 damage, Dead-On makes it free.
     */


    // TEXT DECLARATION

    public static final String ID = HermitMod.makeID(EyeOfTheStorm.class.getSimpleName());
    
    // /TEXT DECLARATION/
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = hermit.Enums.COLOR_YELLOW;

    private static final int COST = 1;


    // /STAT DECLARATION/

    public EyeOfTheStorm() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust=true;
        magicNumber=baseMagicNumber=0;
            }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new EyeOfTheStormAction(p,this, getLogicalCardCost(this)));
        /*
        if (upgraded)
            this.addToBot(new DrawCardAction(1));
        if (isDeadOn()) {
            onDeadOn();

            this.addToBot(new EyeOfTheStormAction(p,this, getLogicalCardCost(this)));
            this.addToTop(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, SnipePower.POWER_ID, 1));
        }
        */
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractDynamicCard.BLUE_BORDER_GLOW_COLOR.cpy();
//         if (isDeadOnPos()) {
//             this.glowColor = AbstractDynamicCard.GOLD_BORDER_GLOW_COLOR.cpy();
//         }
    }

    private static int getLogicalCardCost(AbstractCard ccc) {
        if (ccc.costForTurn > 0 && !ccc.freeToPlayOnce) {
            return ccc.costForTurn;
        }
        return 0;
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
            initializeDescription();
        }
    }
}
