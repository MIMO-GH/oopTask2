package cs.vsu.oop2.panels;
import cs.vsu.oop2.FacultyDatabase;
import cs.vsu.oop2.models.Group;
import cs.vsu.oop2.models.Student;

import javax.swing.*;
import java.awt.*;

public class GroupsPanel extends JPanel {
    private final DefaultListModel<String> groupListModel = new DefaultListModel<>();
    private final DefaultListModel<String> studentInGroupModel = new DefaultListModel<>();
    private final JComboBox<String> studentComboBox = new JComboBox<>();

    public GroupsPanel() {
        setLayout(new BorderLayout());

        // Список групп
        JList<String> groupList = new JList<>(groupListModel);
        add(new JScrollPane(groupList), BorderLayout.WEST);

        // Список студентов в группе
        JList<String> studentInGroupList = new JList<>(studentInGroupModel);
        add(new JScrollPane(studentInGroupList), BorderLayout.CENTER);

        // Панель ввода
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JTextField groupNameField = new JTextField();
        JButton addGroupButton = new JButton("Add Group");
        JButton addStudentToGroupButton = new JButton("Add Student to Group");

        inputPanel.add(new JLabel("Group Name:"));
        inputPanel.add(groupNameField);
        inputPanel.add(new JLabel());
        inputPanel.add(addGroupButton);
        inputPanel.add(new JLabel("Student:"));
        inputPanel.add(studentComboBox);
        inputPanel.add(new JLabel());
        inputPanel.add(addStudentToGroupButton);

        add(inputPanel, BorderLayout.SOUTH);



        // Добавление группы
        addGroupButton.addActionListener(e -> {
            String groupName = groupNameField.getText().trim();
            if (groupName.matches("[a-zA-Z\\s]+")) {
                Group group = new Group(groupName);
                FacultyDatabase.addGroup(group);
                groupListModel.addElement(groupName);
                groupNameField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid group name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Обновление студентов в группе
        addStudentToGroupButton.addActionListener(e -> {
            String selectedGroupName = groupList.getSelectedValue();
            String selectedStudentName = (String) studentComboBox.getSelectedItem();

            if (selectedGroupName == null || selectedStudentName == null) {
                JOptionPane.showMessageDialog(this, "Please select both a group and a student.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Group group = FacultyDatabase.findGroupByName(selectedGroupName);
            Student student = FacultyDatabase.findStudentByName(selectedStudentName);

            if (group != null && student != null) {
                group.addStudent(student);
                updateStudentInGroupList(group);
            } else {
                JOptionPane.showMessageDialog(this, "Group or student not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Обработка выбора группы
        groupList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedGroupName = groupList.getSelectedValue();
                if (selectedGroupName != null) {
                    Group group = FacultyDatabase.findGroupByName(selectedGroupName);
                    updateStudentInGroupList(group);
                }
            }
        });
    }
    public void refreshStudentComboBox() {
        studentComboBox.removeAllItems(); // Очищаем все элементы
        FacultyDatabase.getStudents().forEach(student -> studentComboBox.addItem(student.getName())); // Добавляем студентов из базы
    }
    // Обновление списка студентов в группе
    private void updateStudentInGroupList(Group group) {
        studentInGroupModel.clear();
        if (group != null) {
            group.getStudents().forEach(student -> studentInGroupModel.addElement(student.getName()));
        }
    }
}
