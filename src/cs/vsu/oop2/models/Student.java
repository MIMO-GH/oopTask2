package cs.vsu.oop2.models;

public class Student {
    private final String id;
    private final String name;
    private Group group;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}
