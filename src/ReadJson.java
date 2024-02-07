import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

// Program for print data in JSON format.
public class ReadJson {

    private JFrame mainFrame;
    private JButton next;
    private JButton previous;
    //if allies is text area
    private JTextArea allies;
    //if allies is label
    //private JLabel allies;
    private JLabel characterName;
    private JPanel buttons;
    private JPanel charInfo;
    private int WIDTH = 800;
    private int HEIGHT = 700;

    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", new Integer(1704310046));
        file.put("Tution Fees", new Double(65400));


        // To print in JSON format.
        System.out.print(file.get("Tution Fees"));
        pull();
        ReadJson readjson = new ReadJson();

    }

    public ReadJson() {
        prepareGUI();
        showEventDemo();
    }


    public static void pull() throws ParseException {
        String output = "abc";
        String totlaJson="";
        try {

            URL url = new URL("https://last-airbender-api.fly.dev/api/v1/characters");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONArray jsonObjectArray = (org.json.simple.JSONArray) parser.parse(totlaJson);
        System.out.println(jsonObjectArray);

        try {
//            for (int i=0; i<jsonObjectArray.size();i++){
//                JSONObject character = (JSONObject) jsonObjectArray.get(i);
//                System.out.println(character.get("name"));
//                JSONArray allies = (JSONArray) character.get("allies");
//                for (int j = 0; j < character.size(); i++){
//                    System.out.println(allies.get(j));
//                }
//            }
            JSONObject secretTunnelGuy = (JSONObject) jsonObjectArray.get(0);
            String name = (String)secretTunnelGuy.get("name");

//            String name = (String)jsonObjectArray.get("name");
//
//            org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonObjectArray.get("allies");
//            int n =   msg.size(); //(msg).length();
//            for (int i = 0; i < n; ++i) {
//                String test =(String) msg.get(i);
//                System.out.println(test);
//                // System.out.println(person.getInt("key"));
//            }
//            //String name= (String)jsonObjectArray.get("height");

//            System.out.println(name);
//            System.out.println(secretTunnelGuy.get("allies"));
//            JSONArray allies = (JSONArray)secretTunnelGuy.get("allies");
//            System.out.println(allies.get(0));
        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void prepareGUI(){
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        buttons = new JPanel();
        buttons.setLayout(new GridLayout(2,1));

        charInfo = new JPanel();
        charInfo.setLayout(new GridLayout(2,1));

        //if allies is label
//        allies = new JLabel("allies");
//        allies.setHorizontalAlignment(JLabel.CENTER);
        //if allies is text area
        allies = new JTextArea();
        allies.setBounds(50, 5, WIDTH-100, HEIGHT-50);
        JScrollPane scrollPane = new JScrollPane(allies);
        scrollPane.setPreferredSize(new java.awt.Dimension(250, 150));
        allies.setText("allies");

        characterName = new JLabel("character name");
        characterName.setHorizontalAlignment(JLabel.CENTER);

        charInfo.add(characterName);
        charInfo.add(allies);
        mainFrame.add(charInfo, BorderLayout.CENTER);
        mainFrame.add(buttons, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }

    private void showEventDemo() {

        JButton next = new JButton("next character");
        JButton previous = new JButton("previous character");


        buttons.add(previous);
        buttons.add(next);


        //mainFrame.add(topHalf);
        //mainFrame.add(results);
        //mainFrame.getContentPane().add(scrollPane);

        //mainFrame.add(button3, BorderLayout.SOUTH);
        mainFrame.setVisible(true);

//        go.setActionCommand("go");
//        go.addActionListener(new ButtonClickListener());

    }
}


