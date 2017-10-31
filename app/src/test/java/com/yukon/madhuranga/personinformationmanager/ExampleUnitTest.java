package com.yukon.madhuranga.personinformationmanager;

import com.yukon.madhuranga.personinformationmanager.persistence.MockDatabase;
import com.yukon.madhuranga.personinformationmanager.repository.MockDataSource;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static MockDatabase mockDb = new MockDatabase();

    /**
     * A variable to hold the data source reference to be injected to test harness.
     */
    private TestCollection testHarness = new TestCollection(mockDb);

    @Test
    public void addEntity_isCorrect() throws Exception {
        assertEquals(testHarness.testAddEntity(), true);
    }

    @Test
    public void fetchAllEntities_isCorrect() throws Exception {
        testHarness.testAddEntity();
        assertEquals(testHarness.testFetchAllEntities(), true);
    }

    @Test
    public void FetchEntityById_isCorrect() throws Exception {
        testHarness.testAddEntity();
        assertEquals(testHarness.testFetchEntityById(1), true);
    }

    @Test
    public void removeEntity_isCorrect() throws Exception {
        testHarness.testAddEntity();
        assertEquals(testHarness.testRemoveEntity(), true);
    }
}