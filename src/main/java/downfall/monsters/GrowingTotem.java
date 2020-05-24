package downfall.monsters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import downfall.downfallMod;
import downfall.vfx.TotemBeamEffect;

import java.util.ArrayList;

public class GrowingTotem extends AbstractTotemMonster {
    public static final String ID = downfallMod.makeID("GrowingTotem");
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    private static final MonsterStrings monsterStrings;
    public static Color totemColor = Color.GREEN;

    static {
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
        NAME = monsterStrings.NAME;
        MOVES = monsterStrings.MOVES;
        DIALOG = monsterStrings.DIALOG;

    }

    public Integer attackDmg;
    public Integer secondaryEffect;


    public GrowingTotem() {
        super(NAME, ID, downfallMod.assetPath("images/monsters/livingwall/Head3.png"));
        this.loadAnimation(downfallMod.assetPath("images/monsters/livingwall/Head3.atlas"), downfallMod.assetPath("images/monsters/livingwall/Head3.json"), 1.0F);

        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        if (AbstractDungeon.ascensionLevel >= 19) {
            this.attackDmg = 6;
            this.secondaryEffect = 2;
        } else if (AbstractDungeon.ascensionLevel >= 4) {
            this.attackDmg = 6;
            this.secondaryEffect = 1;
        } else {
            this.attackDmg = 5;
            this.secondaryEffect = 1;
        }
        this.damage.add(new DamageInfo(this, this.attackDmg));

        this.intentType = Intent.ATTACK_BUFF;

        this.totemLivingColor = totemColor;

    }

    @Override
    public void totemAttack() {
        // AbstractDungeon.actionManager.addToBottom(new ChangeStateAction(this, "ATTACK"));
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.GREEN)));
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new TotemBeamEffect(this.hb.cX + beamOffsetX, this.hb.cY + beamOffsetY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, Color.GOLD.cpy(), this.hb.cX + beamOffsetX2, this.hb.cY + beamOffsetY2), 0.1F));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.NONE));

        ArrayList<AbstractMonster> targets = new ArrayList<>();
        targets.addAll(AbstractDungeon.getMonsters().monsters);


        for (AbstractMonster m : targets) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this, new StrengthPower(m, this.secondaryEffect), this.secondaryEffect));

        }

    }

    public void getUniqueTotemMove() {
        this.setMove((byte) 1, intentType, this.attackDmg);
    }


}