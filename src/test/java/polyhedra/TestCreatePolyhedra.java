package polyhedra;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;

import static polyhedra.CreatePolyhedra.createDivider;
import static polyhedra.CreatePolyhedra.duplicateAndScale;
import static polyhedra.CreatePolyhedra.printPolyhedra;


/**
 *
 * This is technically an Integration &amp; System Test
 *
 * 1 - Does this piece of code perform the operations
 *     it was designed to perform?
 *
 * 2 - Does this piece of code do something it was not
 *     designed to perform?
 *
 * 1 Test per mutator
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestCreatePolyhedra {

    /**
     * Polyhedra objects corresponding to simple input.
     */
    List<Polyhedron> complexList;

    @BeforeEach
    public void setUp()
    {
        complexList = new ArrayList<>();

        complexList.add(new Cylinder(3, 2));
        complexList.add(new Sphere(3));
        complexList.add(new Sphere(5));
        complexList.add(new Sphere(7));
        complexList.add(new Cylinder(2, 1));
        complexList.add(new Sphere(5));
        complexList.add(new Sphere(3));
    }

    @Test
    public void testCreateDivider()
    {
        assertThat(createDivider('-', 4), equalTo("----"));
        assertThat(createDivider('-', 8), equalTo("--------"));
        assertThat(createDivider('*', 16), equalTo("****************"));
        assertThat(createDivider('*', 18), equalTo("******************"));
        assertThat(createDivider('?', 12), equalTo("????????????"));
        assertThat(createDivider('!', 2), equalTo("!!"));
        assertThat(createDivider('@', 3), equalTo("@@@"));
        assertThat(createDivider('#', 3), equalTo("###"));
    }

    @Test
    public void testPrintPolyhedra()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream testStandardOut = new ByteArrayOutputStream();

        // Test with output capture
        System.setOut(new PrintStream(testStandardOut));

        printPolyhedra(complexList, "Test Output");

        System.setOut(originalOut);
        // End Output capture

        String resultStr = testStandardOut.toString();

        assertThat(resultStr, containsString("Test Output"));

        List<String> complexAsStrings = complexList.stream()
                                      .map(Polyhedron::toString)
                                      .collect(toList());

        assertThat(resultStr, stringContainsInOrder(complexAsStrings));
    }
}

