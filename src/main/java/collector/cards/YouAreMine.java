package collector.cards;

import collector.powers.DoomPower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static collector.CollectorMod.makeID;
import static collector.util.Wiz.*;

public class YouAreMine extends AbstractCollectorCard {
    public final static String ID = makeID(YouAreMine.class.getSimpleName());
    // intellij stuff skill, enemy, rare, , , , , 4, 2

    public YouAreMine() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 4;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.currentBlock > 0) {
            int q = m.currentBlock;
            atb(new RemoveAllBlockAction(m, p));
            atb(new GainBlockAction(p, q));
        }
        if (m.hasPower(ArtifactPower.POWER_ID)) {
            int q2 = m.getPower(ArtifactPower.POWER_ID).amount;
            atb(new RemoveSpecificPowerAction(m, p, ArtifactPower.POWER_ID));
            applyToSelf(new ArtifactPower(p, q2));
        }
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        applyToEnemy(m, new DoomPower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}