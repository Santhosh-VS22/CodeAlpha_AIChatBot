import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AIChatBot extends JFrame {
   private JTextArea chatArea;
   private JTextField inputField;
   private JButton sendButton;
   private KnowledgeBase brain = new KnowledgeBase();
   public AIChatBot() {
      this.setTitle("AI ChatBot");
      this.setSize(600, 500);
      this.setDefaultCloseOperation(3);
      this.setLocationRelativeTo((Component)null);
      this.setLayout(new BorderLayout());
      this.chatArea = new JTextArea();
      this.chatArea.setEditable(false);
      this.chatArea.setFont(new Font("Arial", 0, 16));
      JScrollPane var1 = new JScrollPane(this.chatArea);
      this.add(var1, "Center");
      JPanel var2 = new JPanel(new BorderLayout());
      this.inputField = new JTextField();
      this.sendButton = new JButton("Send");
      var2.add(this.inputField, "Center");
      var2.add(this.sendButton, "East");
      this.add(var2, "South");
      this.sendButton.addActionListener((var1x) -> this.sendMessage());
      this.inputField.addActionListener((var1x) -> this.sendMessage());
      this.chatArea.append("Bot : Hello! Ask me anything.\n\n");
      this.setVisible(true);
   }

   private void sendMessage() {
      String var1 = this.inputField.getText().trim().toLowerCase();
      if (!var1.isEmpty()) {
         this.chatArea.append("You : " + var1 + "\n");
         String var2;
         if (var1.equals("date")) {
            var2 = "Today's Date : " + String.valueOf(LocalDate.now());
         } else if (var1.equals("time")) {
            var2 = "Current Time : " + String.valueOf(LocalTime.now());
         } else if (var1.equals("bye")) {
            var2 = "Goodbye!";
         } else if (this.brain.containsQuestion(var1)) {
            var2 = this.brain.getAnswer(var1);
         } else {
            String var3 = JOptionPane.showInputDialog(this, "I don't know.\nTeach me:");
            if (var3 != null && !var3.trim().isEmpty()) {
               this.brain.learn(var1, var3);
               var2 = "Thanks! I learned that.";
            } else {
               var2 = "No answer provided.";
            }
         }
         this.chatArea.append("Bot : " + var2 + "\n\n");
         this.inputField.setText("");
      }
   }
   public static void main(String[] var0) {
      SwingUtilities.invokeLater(() -> new AIChatBot());
   }
}
