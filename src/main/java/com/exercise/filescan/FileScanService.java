package com.exercise.filescan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileScanService {

	private final static Logger LOGGER = LoggerFactory.getLogger(FileScanService.class);

	@Value("${service.config.location}")
	private String configLocation;

	private MimetypesFileTypeMap mimetypesFiletypeMap = new MimetypesFileTypeMap();

	public List<FileDTO> scanConfigLocation() {
		return scanLocation(configLocation);

	}

	public List<FileDTO> scanLocation(String location) {
		try (Stream<Path> paths = Files.list(Paths.get(location))) {

			List<File> files = paths.filter(Files::isRegularFile).map(p -> p.toFile()).collect(Collectors.toList());
			List<FileDTO> filesInLocation = new LinkedList<>();

			for (File f : files) {
				String name = f.getName();
				String mimeType = mimetypesFiletypeMap.getContentType(f);
				long size = f.length();
				String extn = FilenameUtils.getExtension(name);
				FileDTO fDTO = new FileDTO(name, mimeType, size, extn);
				LOGGER.debug("Scanned: {}", fDTO);
				filesInLocation.add(fDTO);
			}

			return filesInLocation;
		} catch (IOException e) {
			LOGGER.error("Error reading config location", e);
		}
		return null;
	}

	public List<FileDTO> scanLocationForMimeType(String location, String mimeType) {
		List<FileDTO> filesInLocation = scanLocation(location);
		if (filesInLocation != null) {
			return filesInLocation.stream().filter(f -> f.getMimeType().equalsIgnoreCase(mimeType))
					.collect(Collectors.toList());
		}
		
		return null;
	}
}
