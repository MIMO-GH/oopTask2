package cs.vsu.oop2;
import cs.vsu.oop2.panels.*;
import javax.swing.*;
public class FacultyScheduleGUI extends JFrame {
    private final FacultySchedule schedule = new FacultySchedule();
    private final JTabbedPane tabbedPane = new JTabbedPane(); // Добавляем, если нет

    public FacultyScheduleGUI() {
        setTitle("Faculty Schedule Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane.addTab("Students", new StudentsPanel());
        tabbedPane.addTab("Groups", new GroupsPanel());
        tabbedPane.addTab("Subjects", new SubjectsPanel());
        tabbedPane.addTab("Teachers", new TeachersPanel());

        SchedulePanel schedulePanel = new SchedulePanel(schedule);
        tabbedPane.addTab("Schedule", schedulePanel);

        tabbedPane.addChangeListener(e -> {
            int selectedIndex = tabbedPane.getSelectedIndex();
            String selectedTab = tabbedPane.getTitleAt(selectedIndex);

            if ("Groups".equals(selectedTab)) {
                GroupsPanel groupsPanel = (GroupsPanel) tabbedPane.getComponentAt(selectedIndex);
                groupsPanel.refreshStudentComboBox(); // Обновляем список студентов
            } else if ("Schedule".equals(selectedTab)) {
                schedulePanel.refreshData();
            }
        });

        add(tabbedPane);
    }

    // Добавляем метод для получения панели GroupsPanel
    public GroupsPanel getGroupsPanel() {
        return (GroupsPanel) tabbedPane.getComponentAt(tabbedPane.indexOfTab("Groups"));
    }
}

