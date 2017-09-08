/**
 * OperationTest contains tests for the functions found in Operation.
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

// TODO(jmtorres): Make these tests neater.
public class OperationTest {
    private Operation plusOne = new Operation("+1");
    private Operation minusTwo = new Operation("-2");
    private Operation three = new Operation("3");

    @Test
    public void testGetType() {
        Assert.assertEquals(Operation.Type.ADDITION, plusOne.getType());
        Assert.assertEquals(Operation.Type.SUBTRACTION, minusTwo.getType());
        Assert.assertEquals(Operation.Type.INSERT, three.getType());
    }

    @Test
    public void testGetValue() {
        Assert.assertEquals(1, plusOne.getPrimaryValue(), 0);
        Assert.assertEquals(2, minusTwo.getPrimaryValue(), 0);
        Assert.assertEquals(3, three.getPrimaryValue(), 0);
    }

    @Test
    public void testParseOperations() {
        ArrayList<Operation> toCheck = new ArrayList<Operation>(){{
            add(plusOne);
            add(minusTwo);
            add(three);
        }};

    }
}
