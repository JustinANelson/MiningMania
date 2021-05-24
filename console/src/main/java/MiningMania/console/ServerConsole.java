package MiningMania.console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultCaret;

import MiningMania.helpers.ServerVars;

public class ServerConsole {

    public static JTextArea svrMonitor = new JTextArea();
    static JTextField svrInput = new JTextField();
    static JTabbedPane tabPane = new JTabbedPane();
    static JPanel logPane = new JPanel();

    public static void main(String[] args) throws InterruptedException {
        ServerConsole.create();
        ServerVars.serverWindow.setVisible(true);
        ServerConsole.svrMonitor.setCaretPosition(ServerConsole.svrMonitor.getDocument().getLength());
    }

    public static void create() {
        ServerVars.serverWindow = new JFrame();
        ServerVars.serverWindow.setLayout(null);
        ServerVars.serverWindow.setBounds(0, 0, 516, 239);
        ServerVars.serverWindow.setTitle("Mining Mania");
        ServerVars.serverWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon();
        tabPane.addTab("Log", icon, logPane, "Server Log");

        svrMonitor.setBounds(0, 0, 350, 200);
        svrMonitor.setLineWrap(true);
        svrMonitor.setWrapStyleWord(true);
        svrMonitor.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        svrMonitor.setEditable(false);

        JScrollPane scrlMonitor = new JScrollPane(svrMonitor);
        scrlMonitor.setBounds(10, 10, 350, 100);

        tabPane.setBounds(0, 0, 500, 200);
        logPane.setBounds(0, 0, 500, 200);
        logPane.setLayout(new BoxLayout(logPane, BoxLayout.PAGE_AXIS));
        logPane.add(scrlMonitor);

        svrInput.setBounds(10, 120, 350, 30);
        DefaultCaret caret = (DefaultCaret)svrMonitor.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        ServerVars.serverWindow.add(tabPane);
        ServerConsole.svrMonitor.append("Console loaded" + "\n");
    }

    public static void svrLog(String text){
        if (!text.matches("Saved players")) {
            ServerConsole.svrMonitor.append(new SimpleDateFormat("dd MMM yy  HH:mm:ss: ").format(new Date()) + text + "\n");
        }

        try {

            String absoPath = new File("").getAbsolutePath();

            File yourFile = new File(absoPath + "\\rtoserver\\data\\logs\\server_log.txt");
            yourFile.getParentFile().mkdirs();
            yourFile.createNewFile(); // if file already exists will do nothing

            String fileName = absoPath + "\\rtoserver\\data\\logs\\server_log.txt";
            String s = System.lineSeparator() + new SimpleDateFormat("dd MMM yy  HH:mm:ss: ").format(new Date()) + text;
            Files.write(Paths.get(fileName), s.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            //file not found message
        }

    }
}
