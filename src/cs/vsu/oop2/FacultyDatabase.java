package cs.vsu.oop2;
import cs.vsu.oop2.models.*;

import java.util.*;


public class FacultyDatabase {
    private static final List<Student> students = new ArrayList<>();
    private static final List<Group> groups = new ArrayList<>();
    private static final List<Subject> subjects = new ArrayList<>();
    private static final List<Teacher> teachers = new ArrayList<>();

    public static void addStudent(Student student) {
        students.add(student);
    }
    public static List<Student> getStudents() {
        return students;
    }

    public static Student findStudentByName(String name) {
        return students.stream().filter(student -> student.getName().equals(name)).findFirst().orElse(null);
    }



    public static void addGroup(Group group) {
        groups.add(group);
    }

    public static void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public static void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public static List<Group> getGroups() {
        return groups;
    }

    public static List<Subject> getSubjects() {
        return subjects;
    }

    public static List<Teacher> getTeachers() {
        return teachers;
    }

    public static Group findGroupByName(String name) {
        return groups.stream().filter(group -> group.getName().equals(name)).findFirst().orElse(null);
    }

    public static Subject findSubjectByName(String name) {
        return subjects.stream().filter(subject -> subject.getName().equals(name)).findFirst().orElse(null);
    }

    public static Teacher findTeacherByName(String name) {
        return teachers.stream().filter(teacher -> teacher.getName().equals(name)).findFirst().orElse(null);
    }
}

