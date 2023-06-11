package collector.cards;

import automaton.actions.EasyXCostAction;
import collector.powers.ReservePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static collector.util.Wiz.applyToSelfTop;
import static collector.util.Wiz.atb;

public class AshesAndDust extends AbstractCollectorCard {
    public final static String ID = makeID(AshesAndDust.class.getSimpleName());
    // intellij stuff attack, all_enemy, uncommon, 5, , , , 1, 1

    public AshesAndDust() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 5;
        isMultiDamage = true;
        baseMagicNumber = magicNumber = 1;
    }

    public static boolean exhaustedThisTurn = false;

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            if (exhaustedThisTurn) {
                applyToSelfTop(new ReservePower(magicNumber));
            }
            for (int i = 0; i < effect; i++) {
                allDmgTop(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            }
            return true;
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}