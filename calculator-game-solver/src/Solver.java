import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by finaris on 9/1/17.
 */

public class Solver {
    public Solver() {}

    /**
     * solve produces a solution for a given an operation string
     */
    public JTree solve(double start, int moves, double goal, String operations, JTree path) {
        if (start == goal)
            return path;
        if (moves <= 0)
            return null;
        for (Operation operation: parseOperations(operations)) {
                return solve(start, moves-1, goal, operations, );
        }
        return null;
    }

    /**
     * parseOperations attempts to parse the input String into a list of
     * corresponding Operations (separated by white space).
     */
    public ArrayList<Operation> parseOperations(String operations) {
        ArrayList<Operation> operationList = new ArrayList<>();
        for (String operation: operations.split(" ")) {
            operationList.add(new Operation(operation));
        }
        return operationList;
    }

    /**
     * applyOperation
     */
    public double applyOperation() {

    }
}
