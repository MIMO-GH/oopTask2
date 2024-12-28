package cs.vsu.oop2;

import cs.vsu.oop2.models.ScheduleEntry;

import java.util.ArrayList;
import java.util.List;

public class FacultySchedule {
    private final List<ScheduleEntry> scheduleEntries = new ArrayList<>();

    public void addEntry(ScheduleEntry entry) {
        scheduleEntries.add(entry);
    }

    public List<ScheduleEntry> getScheduleEntries() {
        return scheduleEntries;
    }
}
