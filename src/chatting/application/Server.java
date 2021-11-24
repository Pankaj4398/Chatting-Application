package chatting.application;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Server implements ActionListener{
    //Frame
    static JFrame f1 = new JFrame();
    //Header panel
    JPanel p1;
    //footer text field and send button
    JTextField t1;
    JButton b1;
    //mssg displaying panel
    static JPanel a1;

    //Creates a Box that displays its components from top to bottom(Expand vertically)
    static Box vertical = Box.createVerticalBox();

    //Socket Programming
    static ServerSocket skt;
    static Socket s;
    //Mssg Track -> Received and Sent Mssg
    static DataInputStream din;
    static DataOutputStream dout;

    Boolean typing;
    
    Server(){
        f1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //Header Panel //panels -> create divison same as div in html
        p1 = new JPanel();
        p1.setLayout(null);//Diff layouts to place our components in panel. by default -> Border Layout
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        f1.add(p1);//add panel directly on the frame

        //Image(TextField) load and put it into label directly can't put it into panel/frame
       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3.png"));//back icon
       Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
       ImageIcon i3 = new ImageIcon(i2);
       //1st Label Create -> back icon //note: we can put label directly on frame put we create divison using panel so we add it into panel
       JLabel l1 = new JLabel(i3);
       l1.setBounds(5, 17, 30, 30);
       p1.add(l1);//add label on the header panel
       //Add exit feature on back icon label
       l1.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent ae){
               System.exit(0);
           }
       });
       //image load into label then label put on panel
       ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/1.png"));//dp icon
       Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
       ImageIcon i6 = new ImageIcon(i5);
        //2nd Label Create -> dp icon
       JLabel l2 = new JLabel(i6);
       l2.setBounds(40, 5, 60, 60);
       p1.add(l2);
        //image load into label then label put on panel
       ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/video.png"));//video icon
       Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
       ImageIcon i9 = new ImageIcon(i8);
        //3rd Label Create -> video icon
       JLabel l5 = new JLabel(i9);
       l5.setBounds(290, 20, 30, 30);
       p1.add(l5);
        //image load into label then label put on panel
       ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/phone.png"));//voice call icon
       Image i12 = i11.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
       ImageIcon i13 = new ImageIcon(i12);
        //4th Label Create -> voice call icon
       JLabel l6 = new JLabel(i13);
       l6.setBounds(350, 20, 35, 30);
       p1.add(l6);
        //image load into label then label put on panel
       ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3icon.png"));//settings icon
       Image i15 = i14.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
       ImageIcon i16 = new ImageIcon(i15);
        //5th Label Create -> more settings icon
       JLabel l7 = new JLabel(i16);
       l7.setBounds(410, 20, 13, 25);
       p1.add(l7);

       //Text Add -> Label Use then put that label on panel
       JLabel l3 = new JLabel("Gaitonde");
       l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
       l3.setForeground(Color.WHITE);
       l3.setBounds(110, 15, 100, 18);
       p1.add(l3);
       //Text Add -> Label Use then put that label on panel
       JLabel l4 = new JLabel("Active Now");
       l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
       l4.setForeground(Color.WHITE);
       l4.setBounds(110, 35, 100, 20);
       p1.add(l4);
       //to change Status upon typing and stop typing after a delay we used timer object
       Timer t = new Timer(1, new ActionListener(){
           public void actionPerformed(ActionEvent ae){
               if(!typing){
                   l4.setText("Active Now");
               }
           }
       });
       
       t.setInitialDelay(2000);

       //Display send mssg and rec mssg
        //First we use textArea then shift to panel
       a1 = new JPanel();
       //a1.setBounds(5, 75, 440, 570);
       a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       //f1.add(a1);//add mssg display panel directly on the frame

        //scroll bar add to display mssg panel->a1 -> now we don't need setBounds and add to main frame a1 panel
        JScrollPane sp = new JScrollPane(a1);
        sp.setBounds(5, 75, 440, 570);
        sp.setBorder(BorderFactory.createEmptyBorder());//to remove border which bydefault scrool bar provide
        //f1.add(sp); shift after added ui
        //Scrool Bar UI chnage
        ScrollBarUI ui = new BasicScrollBarUI(){
            @Override
            protected JButton createDecreaseButton(int orientation){
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(new Color(7, 94, 84));
                button.setForeground(Color.WHITE);
                return button;
            }
            @Override
            protected JButton createIncreaseButton(int orientation){
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(new Color(7, 94, 84));
                button.setForeground(Color.WHITE);
                return button;
            }
        };
        sp.getVerticalScrollBar().setUI(ui);
        f1.add(sp);
       
       //Text Field where we type our message
       t1 = new JTextField();
       t1.setBounds(5, 655, 310, 40);
       t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f1.add(t1);//add text field directly on the frame
        //set placeholder
        if (t1.getText().length() == 0) {
            t1.setText("Type Message Here");
            t1.setForeground(Color.GRAY);
        }
        f1.setFocusable(true);// used to activate or deactivate the focus event
        //placeholder in text field ->focus gained -> click on textfield to edit mssg placeholder removes
        t1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(t1.getText().equals("Type Message Here")){
                    t1.setText("");

                }
                t1.setForeground(Color.BLACK);
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (t1.getText().equals("")) {
                    t1.setText("Type Message Here");
                }
                t1.setForeground(Color.GRAY);
            }
        });

       //Active Now Status -> Typing Status when we are typing on text field
       t1.addKeyListener(new KeyAdapter(){
           public void keyPressed(KeyEvent ke){

               l4.setText("typing...");
               
               t.stop();//timer stop as typing start
               
               typing = true;
           }
           
           public void keyReleased(KeyEvent ke){
               typing = false;
               
               if(!t.isRunning()){
                   t.start();//Starts the Timer, causing it to start sending action events to its listeners.
               }
           }
       });
       //Send Button
       b1 = new JButton("Send");
       b1.setBounds(320, 655, 123, 40);
       b1.setBackground(new Color(7, 94, 84));
       b1.setForeground(Color.WHITE);
       b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       b1.addActionListener(this);
       f1.add(b1); //add button directly on the frame

       //Frame Code
       f1.getContentPane().setBackground(Color.WHITE);//whole frame select then background color -> white
       f1.setLayout(null);//Diff layouts to place our components in frame. by default -> Border Layout
       f1.setSize(450, 700);
       f1.setLocation(200, 100);
       f1.setUndecorated(true);//remove status bar from above of frame
       f1.setVisible(true);//always at the end to display frame
        
    }

    //Button Send click call after action mssg display on panel
    @Override
    public void actionPerformed(ActionEvent ae){ //display sent mssg panel in display panel
        try{
            String out = t1.getText();//extract text from text field

            //add chats to txt file
            sendTextToFile(out);
            t1.setText("Type Message Here");//set back to placeholder
            t1.setForeground(Color.GRAY);
            //display mssg/data which we sent to right side of display panel
            JPanel p2 = formatLabel(out);//provide the format to mssg individual panel
            
            a1.setLayout(new BorderLayout());
            
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END); //right side
            //help to align mssg vertically
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));//use this method to force a certain amount of space between two components.

            //a1.add(p2);
            //now add vertical box not p2 individual mssg panel
            a1.add(vertical, BorderLayout.PAGE_START);

            //Writes a string to the underlying output stream
            dout.writeUTF(out);
            t1.setText("");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static JPanel formatLabel(String out){ //provide format to each individual mssg panel
        JPanel p3 = new JPanel();//create an individual panel for each mssg
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));//Y axis along grow means new panel comes along y-axis vertically
        //mssg/data put into label
        JLabel l1 = new JLabel("<html><p style = \"width : 150px\">"+out+"</p></html>");//in label wedon't have method to break text mssg in next line in case it goes big
        //therefore use html format here we define max width of paragraph each line after that it break into new line.
        l1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        l1.setBackground(new Color(37, 211, 102));
        l1.setOpaque(true);
        l1.setBorder(new EmptyBorder(15,15,15,50));//padding space create around other element
        
        Calendar cal = Calendar.getInstance();//Gets a calendar using the default time zone and locale.
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel l2 = new JLabel();//time -> label
        l2.setText(sdf.format(cal.getTime()));//current time
        
        p3.add(l1);//add both label into panel
        p3.add(l2);
        return p3;
    }
    //save chats to a txt file
    private void sendTextToFile(String mssg){
        //Passing true for the second parameter indicates that you want to append to the file;
        // passing false means that you want to overwrite the file.
        try(FileWriter f = new FileWriter("chat.txt", true);//Writes text to character files using a default buffer size.
            PrintWriter p = new PrintWriter(new BufferedWriter(f));)//Prints formatted representations of objects to a text-output stream.
        {
            p.println(Calendar.getInstance().getTime()+ " " + "Kalin Bhaiya : "+ mssg);//user name replace
        } catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new Server().f1.setVisible(true);
        
        String msginput = "";
        try{
            skt = new ServerSocket(6001);//server create only one
            while(true){
                s = skt.accept();//A socket is an endpoint for communication between two machines.-> all data/mssg comes with the help of socket
                din = new DataInputStream(s.getInputStream());//data/mssg which comes to us
                dout = new DataOutputStream(s.getOutputStream());//data/mssg which send
            
	        while(true){
                    //read mssg/data
                    msginput = din.readUTF();
                    //display mssg/data which comes to us in left side of display panel
                    JPanel p2 = formatLabel(msginput);//provide the format to mssg individual panel
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(p2, BorderLayout.LINE_START);//left side
                    //help to align mssg vertically
                    vertical.add(left);
                    f1.validate();//continuously adding panels to display panel as mssg rec or sent therefore each time refresh main frame to display that
            	}
                
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }    
}
