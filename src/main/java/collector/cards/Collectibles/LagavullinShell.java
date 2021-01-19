package collector.cards.Collectibles;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static collector.CollectorMod.makeID;

public class LagavullinShell extends AbstractCollectibleCard {
    public final static String ID = makeID("LagavullinShell");

    public LagavullinShell() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseDamage = 1;
        block = baseBlock = 10;
        baseMagicNumber = 0;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(4);
    }
}
