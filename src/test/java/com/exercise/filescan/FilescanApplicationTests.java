package com.exercise.filescan;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilescanApplicationTests {

	@Autowired
	private FileScanService fileScanService;
	
	/*@Test
	public void contextLoads() {
	}*/
	
	// Assessment Part 1
	// 1. Scan configured directory in file system which will return this information -> filename, file mime type, file size, file extension
	// 2. Use a directory containing a reasonably large number of files, minimum 10.
	@Test
	public void testConfigScan() {
		System.out.println("------ Scanning directory for file information (filename, file mime type, file size, file extension) ------");
		List<FileDTO> filesInLocation = fileScanService.scanConfigLocation();
		assertNotNull(filesInLocation);			// Verify the folder is not empty (some files must exists)
		assertEquals(10, filesInLocation.size());
	}
	
	// Assessment Part 1
	// 3. Provide a way to retrieve certain supported mime type files: configure excel and csv are supported currently
	@Test
	public void testConfigScanWithMime() {
		// Scan file(s) of image mime type
		System.out.println("------ Scanning directory for image mime type file ------");
		List<FileDTO> filesInLocation = fileScanService.scanLocationForMimeType("tmp/exercise", "image/jpeg");
		assertNotNull(filesInLocation);
		assertEquals(1, filesInLocation.size());
		
		// Scan file(s) of text mime type
		System.out.println("------ Scanning directory for text mime type file ------");
		filesInLocation = fileScanService.scanLocationForMimeType("tmp/exercise", "text/plain");
		assertNotNull(filesInLocation);
		assertEquals(1, filesInLocation.size());
		
		
	}

}
