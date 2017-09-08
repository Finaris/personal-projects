/**
 * Operation defines the type of mathematical operation we want to perform on a
 * given input. For operations that support it, we may also provide a primary
 * value and/or a secondary value.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Operation {

    /**
     * Type refers to the type of mathematical operation associated with an
     * Operation.
     */
    public enum Type {
        ADDITION,
        SUBTRACTION,
        INSERT
    }

    /**
     * OPERATORS associates a Type with a String representation.
     */
    private final Map<Type, String> OPERATORS = new HashMap<Type, String>(){{
        put(Type.ADDITION, "+");
        put(Type.SUBTRACTION, "-");
    }};

    private Type type;
    private double primaryValue, secondaryValue;

    public Operation(String operation) {
        type = parseType(operation);
        primaryValue = parsePrimaryValue(type, operation);
    }

    /**
     * parseType attempts to retrieve a Type from an operation String.
     */
    private Type parseType(String operation) {
        for (Map.Entry<Type, String> entry : OPERATORS.entrySet())
            if (operation.contains(entry.getValue()))
                return entry.getKey();
        return Type.INSERT;
    }

    /**
     * parsePrimaryValue attempts to retrieve a primary value given a Type and
     * operation String.
     */
    private double parsePrimaryValue(Type type, String operation) {
        // TODO(jmtorres): Logic will be changed when swap operation is added.
        try {
            if (type != Type.INSERT)
                return Double.parseDouble(operation.replace(OPERATORS.get(type), ""));
            return Double.parseDouble(operation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public Type getType() {
        return type;
    }

    public double getPrimaryValue() {
        return primaryValue;
    }

    public double getSecondaryValue() {
        return secondaryValue;
    }
}
