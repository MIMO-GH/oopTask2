package cs.vsu.oop2.panels;
import cs.vsu.oop2.FacultyDatabase;
import cs.vsu.oop2.FacultySchedule;
import cs.vsu.oop2.models.*;

import javax.swing.*;
import java.awt.*;

public class SchedulePanel extends JPanel {
    private final JComboBox<String> groupComboBox = new JComboBox<>();
    private final JComboBox<String> subjectComboBox = new JComboBox<>();
    private final JComboBox<String> teacherComboBox = new JComboBox<>();
    private final DefaultListModel<String> scheduleListModel = new DefaultListModel<>();

    public SchedulePanel(FacultySchedule schedule) {
        setLayout(new BorderLayout());

        JList<String> scheduleList = new JList<>(scheduleListModel);
        add(new JScrollPane(scheduleList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        JTextField timeSlotField = new JTextField();
        JButton addButton = new JButton("Add Schedule Entry");

        inputPanel.add(new JLabel("Group:"));
        inputPanel.add(groupComboBox);
        inputPanel.add(new JLabel("Subject:"));
        inputPanel.add(subjectComboBox);
        inputPanel.add(new JLabel("Teacher:"));
        inputPanel.add(teacherComboBox);
        inputPanel.add(new JLabel("Time Slot:"));
        inputPanel.add(timeSlotField);
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.SOUTH);

        // Обработчик кнопки добавления расписания
        addButton.addActionListener(e -> {
            String groupName = (String) groupComboBox.getSelectedItem();
            String subjectName = (String) subjectComboBox.getSelectedItem();
            String teacherName = (String) teacherComboBox.getSelectedItem();
            String timeSlot = timeSlotField.getText().trim();

            Group group = FacultyDatabase.findGroupByName(groupName);
            Subject subject = FacultyDatabase.findSubjectByName(subjectName);
            Teacher teacher = FacultyDatabase.findTeacherByName(teacherName);

            if (group != null && subject != null && teacher != null && timeSlot.matches(".+")) {
                ScheduleEntry entry = new ScheduleEntry(group, subject, teacher, timeSlot);
                schedule.addEntry(entry);
                scheduleListModel.addElement(entry.toString());
                timeSlotField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input. Ensure all fields are correctly filled.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void refreshData() {
        groupComboBox.removeAllItems();
        subjectComboBox.removeAllItems();
        teacherComboBox.removeAllItems();

        FacultyDatabase.getGroups().forEach(group -> groupComboBox.addItem(group.getName()));
        FacultyDatabase.getSubjects().forEach(subject -> subjectComboBox.addItem(subject.getName()));
        FacultyDatabase.getTeachers().forEach(teacher -> teacherComboBox.addItem(teacher.getName()));
    }
}

