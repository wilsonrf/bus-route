package com.wilsonfranca.busroute.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static java.nio.file.StandardOpenOption.READ;

@Component
public class RouteLoader {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Routes routes;

    @Autowired
    public RouteLoader(Routes routes) {
        this.routes = routes;
    }

    protected Path readFile(final String path) {
        Assert.hasLength(path, "Path cannot be null");
        Path file = Paths.get(path);
        if (Files.isRegularFile(file) && Files.isReadable(file)) {
            return file;
        } else {
            throw new IllegalStateException("File is not readable or not exists!");
        }
    }

    public void load(final String path) {

        logger.info("Loading file: [{}]", path);

        Path file = readFile(path);

        try (FileChannel fileChannel = FileChannel.open(file, READ)) {

            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, Files.size(file));

            byte [] buffer = new byte[(int) Files.size(file)];

            mappedByteBuffer.get(buffer);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(buffer)));

            String line;
            int lineNumber = 0;
            int total;

            RouteLineParser routeLineParser  = new RouteLineParser();

            while ((line = bufferedReader.readLine()) != null) {
                logger.info("Line number [{}]", lineNumber);
                if (lineNumber == 0) {
                    total = Integer.valueOf(line);
                    logger.info("First line of the file, total bus lines on file: [{}]", total);
                } else {
                    Map<Integer, int[]> lineParsed = routeLineParser.parse(line);
                    routes.addAll(lineParsed);
                }
                lineNumber++;
            }

        } catch (IOException e) {
            logger.error("Error on reading the file. [{}]", e);
        }
    }

    public Routes getRoutes() {
        return this.routes;
    }
}
