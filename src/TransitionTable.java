import java.util.HashMap;

public class TransitionTable {
    private HashMap<State, HashMap<String, State>> transitionTable = new HashMap<>();

    public void addTransition(State inputTransition, String alphabet, State outputTransition) {
        if (transitionTable.containsKey(inputTransition))
            addTransitionForExistingState(inputTransition, alphabet, outputTransition);
        else
            addNewTransition(inputTransition, alphabet, outputTransition);

    }

    private void addNewTransition(State inputTransition, String alphabet, State outputTransition) {
        HashMap<String, State> alphabetToTransition = new HashMap<>();
        alphabetToTransition.put(alphabet, outputTransition);
        transitionTable.put(inputTransition, alphabetToTransition);
    }

    private void addTransitionForExistingState(State inputTransition, String alphabet, State outputTransition) {
        transitionTable.get(inputTransition).put(alphabet, outputTransition);
    }

    public State nextState(State initialState, String s) {
        return transitionTable.get(initialState).get(s);
    }
}
