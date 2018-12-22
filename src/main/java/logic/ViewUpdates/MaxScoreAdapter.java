package logic.ViewUpdates;

import logic.update.MaxLevelScoreReachedUpdate;

/**
 * An adapter of the MaxLevelScoreReached so can the view use it.
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public class MaxScoreAdapter implements LevelChangesUpdate {

    private MaxLevelScoreReachedUpdate max;

    public MaxScoreAdapter(MaxLevelScoreReachedUpdate max){
        this.max=max;
    }

    @Override
    public void accept(LevelChangesUpdateReceiver update) {
        update.levelChanges(this);
    }
}
