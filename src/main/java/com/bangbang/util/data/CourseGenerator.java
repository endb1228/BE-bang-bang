package com.bangbang.util.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class CourseGenerator {

    private static final ObjectMapper mapper = new ObjectMapper();

    public CourseGenerator(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static class CourseData {
        @JsonProperty("name")
        public String courseName;
        @JsonProperty("heritage")
        public List<String> heritages;
    }

    final ResourceLoader resourceLoader;

    public List<CourseData> get() throws IOException {
        File file = Paths.get(resourceLoader.getResource("classpath:/course.json").getURI()).toFile();
        return mapper.readValue(file, new TypeReference<>() {});
    }
}
