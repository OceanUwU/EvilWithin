package theHexaghost.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import downfall.downfallMod;
import theHexaghost.HexaMod;
import theHexaghost.actions.RetractAction;
import theHexaghost.powers.PastPower;

public class GhostOfSpirePast extends AbstractHexaCard {

    public final static String ID = makeID("GhostOfSpirePast");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 1;

    public GhostOfSpirePast() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(HexaMod.GHOSTWHEELCARD);
        this.tags.add(downfallMod.BANNEDFORSNECKO);
        HexaMod.loadJokeCardImage(this, "GhostOfSpirePast.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PastPower(1));
        atb(new RetractAction());
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }
}