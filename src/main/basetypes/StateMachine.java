package src.main.basetypes;

public class StateMachine {
    // Manages states of GameObjects
    private State state;
    private boolean enabled = true;

    public StateMachine(State initialState) {
        state = initialState;
    }

    public void disable() {
        enabled = false;
    }

    public void enable() {
        enabled = true;
    }
    
    public State getState() {
        return state;
    }

    public void onEvent(String event) {
        if (enabled) {
            State newState = state.onEvent(event);
            if (!newState.equals(state)) {
                state.onExit();
                state = newState;
                state.onEnter(event);
            }
        }
    }

    public void update() {
        if (enabled) {
            state.update();
        }
    }
}