package com.wilsonfranca.busroute.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RouteLoader {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Routes routes;

    @Autowired
    public RouteLoader(Routes routes) {
        this.routes = routes;
    }

    public void loadFile(String path) {

        logger.info("Loading file: [{}]", path);

        Path file = Paths.get(path);

        Charset charset = Charset.forName("UTF-8");

        try (InputStream inputStream = Files.newInputStream(file, StandardOpenOption.READ);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line = null;

            int lineNumber = 0;
            int total = 0;

            while ((line = bufferedReader.readLine()) != null) {
                logger.debug(line);
                if (lineNumber == 0) {
                    total = Integer.valueOf(line);
                    logger.debug("First line of the file, total bus lines on file: [{}]", total);
                } else {
                    parseLine(line);
                }
                lineNumber++;
            }

            logger.debug("Routes: [{}]", Arrays.toString(routes.getRoutes().entrySet().toArray()));

        } catch (IOException e) {
            logger.error("Error on loading file [{}] [{}]", path, e.getMessage(), e);
        }
    }

    private void parseLine(final String line) {

        String[] strings = line.split(" ");

        List<String> stringList = IntStream.range(0, strings.length)
                .filter(i -> i > 0)
                .mapToObj(i -> strings[i])
                .filter(StringUtils::hasLength)
                .collect(Collectors.toList());

        for (int i = 0; i < stringList.size(); i++) {
            int from = Integer.valueOf(stringList.get(i));
            for (int j = i + 1; j < stringList.size(); j++) {
                int to = Integer.valueOf(stringList.get(j));
                routes.addRoute(from, to);
            }
        }

    }
}
