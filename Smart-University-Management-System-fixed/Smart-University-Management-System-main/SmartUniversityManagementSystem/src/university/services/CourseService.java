package university.services;

import java.util.ArrayList;

import university.models.Course;
import university.models.Professor;

public class CourseService {

    private ArrayList<Course> courses;

    public CourseService() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        if (course == null) {
            System.out.println("Cannot add a null course.");
            return;
        }

        if (searchCourse(course.getCourseId()) != null) {
            System.out.println("Course with ID " + course.getCourseId() + " already exists.");
            return;
        }

        courses.add(course);
        System.out.println("Course added successfully: " + course.getCourseName());
    }

    public Course searchCourse(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equalsIgnoreCase(courseId)) {
                return course;
            }
        }
        return null;
    }

    public Course searchCourse(String courseName, boolean byName) {
        if (!byName) {
            return searchCourse(courseName);
        }

        for (Course course : courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                return course;
            }
        }
        return null;
    }

    public void updateCourse(String courseId, Course updatedCourse) {
        if (updatedCourse == null) {
            System.out.println("Updated course cannot be null.");
            return;
        }

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equalsIgnoreCase(courseId)) {
                courses.set(i, updatedCourse);
                System.out.println("Course updated successfully: " + courseId);
                return;
            }
        }
        System.out.println("Course not found: " + courseId);
    }

    public void deleteCourse(String courseId) {
        Course course = searchCourse(courseId);
        if (course != null) {
            courses.remove(course);
            System.out.println("Course deleted successfully: " + courseId);
        } else {
            System.out.println("Course not found: " + courseId);
        }
    }

    public ArrayList<Course> getAllCourses() {
        return courses;
    }

    public void displayCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        System.out.println("============ All Courses ============");
        for (Course course : courses) {
            course.printDetails();
        }
    }

    public void assignProfessor(String courseId, Professor professor) {
        Course course = searchCourse(courseId);
        if (course == null) {
            System.out.println("Course not found: " + courseId);
            return;
        }
        if (professor == null) {
            System.out.println("Professor cannot be null.");
            return;
        }

        course.setProfessor(professor);
        System.out.println("Professor " + professor.getName()
                + " assigned to course " + course.getCourseName());
    }
}
