package com.cryptoStreamLoader.common;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;

import static com.cryptoStreamLoader.common.FileReaderUtil.createNewFile;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FileReaderUtilTest {

    private static  String fileDataDirectory = "/tmp/elastic/util/";

    public static String test_Data_File_1 = "/testdata/testData_Shakespeare_Part_1.txt";

    public String testFile_1 = null;

    Path testFilePath_1 = null;

    @Before
    public void setup(){
        if(testFilePath_1!=null) {
            testFilePath_1.toFile().deleteOnExit();
        }
    }

    @After
    public void cleanUp(){
        if(testFilePath_1!=null) {
            testFilePath_1.toFile().deleteOnExit();
        }
    }


    @Test
    public void test_Assert_Create_New_File() throws Exception{

        //given
        testFile_1 = fileDataDirectory+ "targetData.log";

        //when
        testFilePath_1 = createNewFile(testFile_1);

        //then
        assertNotNull(testFilePath_1);
        assertTrue(testFilePath_1.toFile().exists());
    }

}
