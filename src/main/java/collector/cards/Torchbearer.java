package collector.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static collector.util.Wiz.atb;

public class Torchbearer extends AbstractCollectorCard {
    public final static String ID = makeID(Torchbearer.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 6, 2

    public Torchbearer() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AddTemporaryHPAction(p, p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}