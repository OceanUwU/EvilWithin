package collector.actions;

import collector.CollectorCollection;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class SoulHarvestAction
                    extends AbstractGameAction {
    public int[] damage;

    public SoulHarvestAction(AbstractCreature source, int[] amount, DamageInfo.DamageType type,
                             AbstractGameAction.AttackEffect effect) {
        setValues(null, source, damage[0]);
        this.damage = amount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.damageType = type;
        this.attackEffect = effect;
    }

    public void update() {
        boolean playedMusic;
        int i;
        if (this.duration == 0.5F) {
            playedMusic = false;
            int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
            for (i = 0; i < temp; i++) {
                if ((!AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isDying) &&
                        (AbstractDungeon.getCurrRoom().monsters.monsters.get(i).currentHealth > 0) &&
                        (!AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isEscaping)) {
                    AbstractDungeon.effectList.add(new BiteEffect(AbstractDungeon.getCurrRoom().monsters.monsters.get(i).hb.cX, AbstractDungeon.getCurrRoom().monsters.monsters.get(i).hb.cY));
                    if (playedMusic) {
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(

                                AbstractDungeon.getCurrRoom().monsters.monsters.get(i).hb.cX,
                                AbstractDungeon.getCurrRoom().monsters.monsters.get(i).hb.cY, this.attackEffect, true));
                    } else {
                        playedMusic = true;
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(

                                AbstractDungeon.getCurrRoom().monsters.monsters.get(i).hb.cX,
                                AbstractDungeon.getCurrRoom().monsters.monsters.get(i).hb.cY, this.attackEffect));
                    }
                }
            }
        }
        tickDuration();
        if (this.isDone) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                p.onDamageAllEnemies(this.damage);
            }
        }
        for (i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); i++) {
            AbstractMonster target = (AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if (!target.isDying && target.currentHealth > 0 && !target.isEscaping) {
                target.damage(new DamageInfo(this.source, this.damage[i], this.damageType));
                if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead) {
                    CollectorCollection.GetCollectible(target);
                }
            }
        }

        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.1F));
    }
}