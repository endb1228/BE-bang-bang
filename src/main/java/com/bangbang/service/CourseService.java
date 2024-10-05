package com.bangbang.service;

import com.bangbang.domain.Course;
import com.bangbang.domain.CourseHeritage;
import com.bangbang.domain.Heritage;
import com.bangbang.repository.CourseHeritageRepository;
import com.bangbang.repository.CourseRepository;
import com.bangbang.repository.HeritageRepository;
import com.bangbang.util.data.CourseGenerator;
import com.bangbang.util.data.CourseGenerator.CourseData;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final HeritageRepository heritageRepository;
    private final CourseHeritageRepository courseHeritageRepository;
    private final CourseGenerator courseGenerator;

    public CourseService(CourseRepository courseRepository, HeritageRepository heritageRepository,
                         CourseHeritageRepository courseHeritageRepository, CourseGenerator courseGenerator) {
        this.courseRepository = courseRepository;
        this.heritageRepository = heritageRepository;
        this.courseHeritageRepository = courseHeritageRepository;
        this.courseGenerator = courseGenerator;
    }

    public void initCourse() {
        try {
            List<CourseData> courseData = courseGenerator.get();
            courseData.forEach(c -> {
                Course course = new Course(c.courseName);
                courseRepository.save(course);
                List<Heritage> heritages = heritageRepository.findByNameIn(c.heritages);
                ArrayList<CourseHeritage> courseHeritages = new ArrayList<>();
                long order = 1;
                for (Heritage heritage : heritages) {
                    courseHeritages.add(new CourseHeritage(course, heritage, order++));
                }
                courseHeritageRepository.saveAll(courseHeritages);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Course> getCourseList() {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            initCourse();
            courses = courseRepository.findAll();
        }
        return courses;
    }
}
