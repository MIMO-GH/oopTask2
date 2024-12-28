package cs.vsu.oop2.models;


public class ScheduleEntry {
    private final Group group;
    private final Subject subject;
    private final Teacher teacher;
    private final String timeSlot;

    public ScheduleEntry(Group group, Subject subject, Teacher teacher, String timeSlot) {
        this.group = group;
        this.subject = subject;
        this.teacher = teacher;
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return String.format("Group: %s, Subject: %s, Teacher: %s, Time: %s",
                group.getName(), subject.getName(), teacher.getName(), timeSlot);
    }
}
