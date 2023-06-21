import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Zack, Rohan.
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    @Test
    public void constructorTesting() {
        Map<String, String> test = this.constructorTest();
        Map<String, String> ref = this.constructorRef();
        assertEquals(ref, test);
    }

    /**
     * Helper method to test {@code add} method.
     *
     * @param key
     *            the key to be added in the pair.
     * @param value
     *            the value to be added in the pair.
     * @param elements
     *            key value pairs to construct the initial state of the map.
     */
    private void addTest(String key, String value, String... elements) {
        Map<String, String> test = this.createFromArgsTest(elements);
        Map<String, String> ref = this.createFromArgsRef(elements);

        test.add(key, value);
        ref.add(key, value);

        assertEquals(ref, test);
    }

    @Test
    public void addTest1() {
        this.addTest("", "");
    }

    @Test
    public void addTest2() {
        this.addTest("a", "b");
    }

    @Test
    public void addTest3() {
        this.addTest("b", "a");
    }

    @Test
    public void addTest4() {
        this.addTest("a", "", "", "");
    }

    @Test
    public void addTest5() {
        this.addTest("ab", "", "a", "b");
    }

    /**
     * Helper method to test {@code remove} method.
     *
     * @param key
     *            the key to be removed.
     * @param elements
     *            key value pairs in the initial map.
     */
    private void removeTest(String key, String... elements) {
        Map<String, String> test = this.createFromArgsTest(elements);
        Map<String, String> ref = this.createFromArgsRef(elements);

        Pair<String, String> outT = test.remove(key);
        Pair<String, String> outR = ref.remove(key);

        assertEquals(ref, test);
        assertEquals(outT, outR);
    }

    @Test
    public void removeTest1() {
        this.removeTest("a", "a", "b");
    }

    @Test
    public void removeTest2() {
        this.removeTest("", "", "a");
    }

    @Test
    public void removeTest3() {
        this.removeTest("b", "b", "b");
    }

    @Test
    public void removeTest4() {
        this.removeTest("b", "a", "b", "b", "a");
    }

    @Test
    public void removeTest5() {
        this.removeTest("ab", "a", "b", "ab", "b", "b", "a");
    }

    /**
     * Helper method to test {@code removeAny} method.
     *
     * @param elements
     *            key value pairs in the initial map.
     */
    private void removeAnyTest(String... elements) {
        Map<String, String> test = this.createFromArgsTest(elements);
        Map<String, String> ref = this.createFromArgsRef(elements);

        Pair<String, String> outT = test.removeAny();
        assertTrue(ref.hasKey(outT.key()));
        Pair<String, String> outR = ref.remove(outT.key());

        assertEquals(ref, test);
        assertEquals(outR, outT);
    }

    @Test
    public void removeAnyTest1() {
        this.removeAnyTest("", "a");
    }

    @Test
    public void removeAnyTest2() {
        this.removeAnyTest("a", "b", "b", "a");
    }

    @Test
    public void removeAnyTest3() {
        this.removeAnyTest("1", "a", "2", "a", "3", "b");
    }

    /**
     * Helper method to test {@code value} method.
     *
     * @param key
     *            the key used to query the value.
     * @param elements
     *            key value pairs in the initial map.
     */
    private void valueTest(String key, String... elements) {
        Map<String, String> test = this.createFromArgsTest(elements);
        Map<String, String> ref = this.createFromArgsRef(elements);

        String outT = test.value(key);
        String outR = ref.value(key);

        assertEquals(ref, test);
        assertEquals(outR, outT);
    }

    @Test
    public void valueTest1() {
        this.valueTest("a", "a", "b");
    }

    @Test
    public void valueTest3() {
        this.valueTest("", "", "a");
    }

    @Test
    public void valueTest2() {
        this.valueTest("b", "b", "b");
    }

    @Test
    public void valueTest4() {
        this.valueTest("b", "a", "b", "b", "a");
    }

    @Test
    public void valueTest5() {
        this.valueTest("ab", "a", "b", "ab", "b", "b", "a");
    }

    /**
     * Helper method to test {@code hasKey} method.
     *
     * @param key
     *            the key used to query in the method.
     * @param elements
     *            key value pairs in the initial map.
     */
    private void hasKeyTest(String key, String... elements) {
        Map<String, String> test = this.createFromArgsTest(elements);
        Map<String, String> ref = this.createFromArgsRef(elements);

        boolean hasKeyT = test.hasKey(key);
        boolean hasKeyR = ref.hasKey(key);

        assertEquals(ref, test);
        assertEquals(hasKeyR, hasKeyT);
    }

    @Test
    public void hasKeyTest1() {
        this.hasKeyTest("");
    }

    @Test
    public void hasKeyTest2() {
        this.hasKeyTest("a");
    }

    @Test
    public void hasKeyTest3() {
        this.hasKeyTest("a", "a", "b");
    }

    @Test
    public void hasKeyTest4() {
        this.hasKeyTest("b", "a", "b");
    }

    @Test
    public void hasKeyTest5() {
        this.hasKeyTest("", "a", "b");
    }

    @Test
    public void hasKeyTest6() {
        this.hasKeyTest("a", "a", "a", "b", "b", "ab", "");
    }

    @Test
    public void hasKeyTest7() {
        this.hasKeyTest("b", "a", "a", "b", "b", "ab", "");
    }

    @Test
    public void hasKeyTest8() {
        this.hasKeyTest("ab", "a", "a", "b", "b", "ab", "");
    }

    @Test
    public void hasKeyTest9() {
        this.hasKeyTest("aa", "a", "a", "b", "b", "ab", "");
    }

    /**
     * Helper method to test the {@code size} method.
     *
     * @param elements
     *            key value pairs in the initial map.
     */
    private void sizeTest(String... elements) {
        Map<String, String> test = this.createFromArgsTest(elements);
        Map<String, String> ref = this.createFromArgsRef(elements);

        int sizeT = test.size();
        int sizeR = ref.size();

        assertEquals(ref, test);
        assertEquals(sizeR, sizeT);
    }

    @Test
    public void sizeTest1() {
        this.sizeTest();
    }

    @Test
    public void sizeTest2() {
        this.sizeTest("", "");
    }

    @Test
    public void sizeTest3() {
        this.sizeTest("a", "a");
    }

    @Test
    public void sizeTest4() {
        this.sizeTest("a", "", "", "a");
    }

    @Test
    public void sizeTest5() {
        this.sizeTest("a", "a", "b", "b", "ab", "");
    }
}
