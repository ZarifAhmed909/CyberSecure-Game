import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MyFrame extends JFrame {
    private ImageIcon matrixIcon;
    private JLabel myLabel;
    private JButton button1, button2, button3,button11,button22,button33, mcqButton1,mcqButton2,mcqButton3,mcqButton4;
    private String question, Option1,Option2,Option3,Option4;
    private int correctVal;
    private JPanel healthbarPlayer, healthbarAI;
    private JLabel questionText, answerChoices;
    private JProgressBar healthBarP, healthBarA;
    private int playerHealth = 100;
    private int hackerHealth = 100;
    private int chosenAnswer;
    private boolean alternating, PlayerDecision1, PlayerDecision2, PlayerDecision3, AIAttacks;
    private int AIChoice;
    RandomNumberGenerator rng = new RandomNumberGenerator();

    String[] Q = {
            "Which of the following best describes a Man-in-the-Middle (MitM) attack?",
            "What type of attack involves the unauthorized transmission of data from a system?",
            "Fill in the blank: _____ is the use of digital communications to trick people into revealing sensitive data or deploying malicious software",
            "What type of phishing uses electronic voice communications to obtain sensitive information or to impersonate a known source?",
            "5 Fill in the blank: ____ are malware that automatically duplicate and spread themselves across systems",
            "6 What is it called when someone's computing resources are illegally hijacked to mine cryptocurrencies",
            "7 Fill in the blank: _____ is a type of malicious attack where attackers encrypt an organization's data and demand payment to restore access.",
            "8 Which of the following actions can be taken to protect against cryptojacking?",
            "9 Fill in the blank: _____ are malicious code or behaviors that are used to take advantage of coding flaws in a web application.",
            "10 Cross-site scripting (XSS) attacks are often delivered by exploiting which of the following languages?",
            "11 Fill in the blank: A _____ is a coding technique that executes SQL statements before passing them onto the database.",
            "12 Fill in the blank: A(n) _____ is an attack that executes unexpected queries on a database.",
            "13 Fill in the blank: Threat modeling is a process that security teams use to _____ attacks.",
            "14 A threat modeling team creates a diagram that maps the threats to assets. What type of diagram is this known as?",
            "15 Who's been teaching us about the wonders of Cyber Security this year?",
            "16 PASTA is a popular _____ framework that's used across many industries.",
            "17 1. Fill in the blank: The four stages of a social engineering attack are to prepare, _____, use persuasion tactics, and disconnect from the target.",
            "18 What is the main difference between a vishing attack and a smishing attack?",
            "19 Which type of malware requires the user to make a payment to the attacker to regain access to their device?",
            "20 What is malicious code that is inserted into a vulnerable application called?"
    };

    String[] Ans1 = {
            "An attacker installs malware on a server to steal data over time",
            "Packet Classification",
            "Quid pro quo",
            "Tailgating",
            "5 Rootkits",
            "6 Trojan horse",
            "7 Spyware",
            "8 Hashing user passwords in a database",
            "9 Web-based exploits",
            "10 JavaScript",
            "11 botnet",
            "12 CVE",
            "13 conduct",
            "14 An attack vector",
            "15 Mr. Irimina",
            "16 threat modeling",
            "17 establish trust",
            "18 Vishing is used to target executives at an organization.",
            "19 Brute force attacks",
            "20 Social engineering"
    };
    String[] Ans2 = {
            "A user is tricked into clicking a malicious link in an email",
            "Data leak",
            "Phishing",
            "Smishing",
            "5 Worms ",
            "6 Spyware",
            "7 Ransomware",
            "8 Mining crypto coins on workstations",
            "9 Social engineering",
            "10 SQL",
            "11 phishing kit",
            "12 SQL injection",
            "13 remediate",
            "14 An attack tree",
            "15 Nikola Tesla",
            "16 attack tree",
            "17 distribute malicious email",
            "18 Vishing involves a widespread email campaign to steal information.",
            "19 Cryptojacking",
            "20 Injection attack"
    };
    String[] Ans3 = {
            "A system is overwhelmed with traffic to disrupt services",
            "Packet crafting",
            "Whaling",
            "Vishing",
            "5 Botnets",
            "6 Cryptojacking",
            "7 Worm",
            "8 Using malware blocking browser extension",
            "9 Command-line interface",
            "10 Python",
            "11 SQL injection",
            "12 virus",
            "13 engineer",
            "14 An attack surface",
            "15 Giga Chad",
            "16 asset classification",
            "17 perform open-box testing",
            "18 Vishing makes use of voice calls to trick targets.",
            "19 Botnets",
            "20 Input validation"
    };
    String[] Ans4 = {
            "An attacker intercepts communication between two parties without their knowledge",
            "Data Exfiltration",
            "Baiting",
            "Angler phishing",
            "5 Trojans",
            "6 Rootkit",
            "7 Phishing",
            "8 Reporting new CVEs",
            "9 Spear phishing",
            "10 Excel",
            "11 prepared statement",
            "12 malware",
            "13 anticipate",
            "14 An attacker mindset",
            "15 Michael from Vsauce",
            "16 vulnerability management",
            "17 obtain access credentials",
            "18 Vishing exploits social media posts to identify targets.",
            "19 Ransomware",
            "20 Cryptojacking"
    };
    int[] correctIndex = {4,4,2,4,2,3,2,1,1,1,3,2,4,2,1,1,1,3,4,2};


    Main main = new Main();



    public MyFrame() {
        //initializing alternation logic:
        alternating = true;
        //basic set up
        setTitle("Sigma Security Sim");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


        System.out.println(Q.length);
        System.out.println(correctIndex.length);


        //Title
        JLabel titleLabel = new JLabel("Sigma Security Sim");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // size 24, bold
        titleLabel.setForeground(Color.white);
        titleLabel.setBounds(275, 0, 700, 30); // x, y, width, height
        add(titleLabel);

        //Main text
        questionText = new JLabel();
        questionText.setText(question);
        questionText.setText("<html>You are a Cybersecurity specialist. Your goal is to protect your company (below) and DESTROY your enemies (above) <html> ");
        questionText.setBounds(60, 100, 690, 100);
        questionText.setFont(new Font("Arial", Font.BOLD, 18));
        add(questionText);





        answerChoices = new JLabel();
        answerChoices.setText("<html><b>1. Both parties can attack by phishing, DDos Attack, or Malware Injection<br/2.Both can also defend with filters, Traffic monitoring,and Endpoint Protection!<br/3.You can't see how the enemy intends to attack<br/4. Making an incorrect decision isn't the end, as you can still counterattack by answering a question</b></html>");
        answerChoices.setBounds(60, 200, 690, 200);
        answerChoices.setFont(new Font("Arial", Font.BOLD, 18));
        add(answerChoices);

        JPanel background = new JPanel();
        background.setBounds(50,125,700,400);
        add(background);

        //Health Bar Player
        healthbarPlayer = new JPanel();
        healthbarPlayer.setBounds(375,675,400,50);
        add(healthbarPlayer);

        healthBarP = new JProgressBar(0,100);
        healthBarP.setPreferredSize(new Dimension(400,50));
        healthBarP.setValue(100);
        healthBarP.setForeground(Color.green);
        healthbarPlayer.add(healthBarP);

        //Player health bar label
        JLabel playerHealth = new JLabel("HP");
        playerHealth.setFont(new Font("Arial", Font.BOLD, 15));
        playerHealth.setForeground(Color.white);
        playerHealth.setBounds(350, 690, 700, 30); // x, y, width, height
        add(playerHealth);


        //Health Bar AI
        healthbarAI = new JPanel();
        healthbarAI.setBounds(25,50,400,50);
        add(healthbarAI);

        healthBarA = new JProgressBar(0,100);
        healthBarA.setPreferredSize(new Dimension(400,50));
        healthBarA.setValue(100);
        healthBarA.setForeground(Color.red);
        healthbarAI.add(healthBarA);

        //AI health bar label
        JLabel AIHealth = new JLabel("Hacker HP");
        AIHealth.setFont(new Font("Arial", Font.BOLD, 15));
        AIHealth.setForeground(Color.white);
        AIHealth.setBounds(440, 65, 700, 30); // x, y, width, height
        add(AIHealth);

        //mcq option buttons
        mcqButton1 = new JButton(("1"));
        mcqButton1.setBounds(160,550,100,50);
        mcqButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chosenAnswer = 1;
                checkAnswer();
            }
        });
        add(mcqButton1);

        mcqButton2 = new JButton(("2"));
        mcqButton2.setBounds(285,550,100,50);
        mcqButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chosenAnswer = 2;
                checkAnswer();
            }
        });
        add(mcqButton2);


        mcqButton3 = new JButton(("3"));
        mcqButton3.setBounds(410,550,100,50);
        mcqButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chosenAnswer = 3;
                checkAnswer();
            }
        });
        add(mcqButton3);

        mcqButton4 = new JButton(("4"));
        mcqButton4.setBounds(535,550,100,50);
        mcqButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chosenAnswer = 4;
                checkAnswer();
            }
        });
        add(mcqButton4);

        mcqButtonTurnOff();

        //Defensive Choice Buttons
        button1 = new JButton("Email Filter");
        button1.setBounds(150, 625, 100, 30);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Choice1Picked(); // Call your method
            }
        });
        add(button1);

        button2 = new JButton("Traffic Monitoring");
        button2.setBounds(300, 625, 150, 30);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Choice2Picked(); // Call your method
            }
        });
        add(button2);

        button3 = new JButton("Endpoint Protection");
        button3.setBounds(500, 625, 150, 30);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Choice3Picked(); // Call your method
            }
        });
        add(button3);

        defendButtonTurnOff();

        //Offensive Choice Buttons
        button11 = new JButton("Phishing Email ");
        button11.setBounds(150, 625, 100, 30);
        button11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Choice1Picked(); // Call your method
            }
        });
        add(button11);

        button22 = new JButton("DDoS Attack");
        button22.setBounds(300, 625, 150, 30);
        button22.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Choice2Picked(); // Call your method
            }
        });
        add(button22);

        button33 = new JButton("Malware Injection");
        button33.setBounds(500, 625, 150, 30);
        button33.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Choice3Picked(); // Call your method
            }
        });
        add(button33);

        attackButtonTurnOff();


        //background
        matrixIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("theone.jpg")));
        myLabel = new JLabel("",matrixIcon,JLabel.LEFT);
        myLabel.setSize(1200,900);
        add(myLabel);

        Timer timer = new Timer(15000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enemyChoice();
            }
        });

        timer.setRepeats(false);
        timer.start();


    }


    //Major logic and Methods below

    public void enemyChoice(){
        int randomNum = (int) (Math.random() * 3) + 1;
        AIChoice = randomNum;
        //1 - Phishing
        //2 - DDoS Attack
        //3 - Malware Injection
        if (alternating) {
            questionText.setText("THEY INTEND TO ATTACK, DEFEND WISELY");
            answerChoices.setText("");
            AIAttacks = true;
            mcqButtonTurnOff();
            attackButtonTurnOff();
            defendButtonTurnOn();
            alternating = !alternating;
            //1 - Email Filter
            //2 - Traffic Monitoring
            //3 - Endpoint Security
        } else {
            questionText.setText("THEY INTEND TO DEFEND, ATTACK AND DESTROY");
            answerChoices.setText("");
            AIAttacks = false;
            mcqButtonTurnOff();
            attackButtonTurnOn();
            defendButtonTurnOff();
            alternating = !alternating;
        }
    }




    //Email Filter or Phishing
    public void Choice1Picked(){
        if (AIAttacks){
            if(AIChoice==1){
                questionText.setText("<html>THEY ATTEMPTED TO PHISH! But your filters made short work of it!</html>");
                answerChoices.setText("");
                damageInflicted();
                if (hackerHealth<=0) {
                    questionText.setText("YOU HAVE WON! ALL THE HACKERS WERE SENT TO DETROIT!");
                    wait3SecsEnd();
                }
                wait3SecsEnemy();
            }else if(AIChoice==2){
                questionText.setText("<html>THEY LAUNCHED A DDoS ATTACK! The filtering was subverted!</html>");
                answerChoices.setText("");
                wait3SecsQuestion();
            }else if(AIChoice==3){
                questionText.setText("<html>THEY ATTACKED VIA MALWARE INJECTION! The filtering was subverted!</html>");
                answerChoices.setText("");
                wait3SecsQuestion();
            }
        }else{
            if(AIChoice==1){
                questionText.setText("<html>DESPITE YOUR MASTER CRAFTED PHISHING EMAILS, THEY FILTERED IT ALL!</html>");
                answerChoices.setText("");
                wait3SecsQuestion();
            }else{
                questionText.setText("<html>THEY DIDN'T KNOW WHAT HIT EM! THEIR SPIIs ARE YOURS!</html>");
                answerChoices.setText("");
                damageInflicted();
                if (hackerHealth<=0) {
                    questionText.setText("YOU HAVE WON! ALL THE HACKERS WERE SENT TO DETROIT!");
                    wait3SecsEnd();
                }
                wait3SecsEnemy();
            }
        }
    }





    //Traffic Monitor or DDoS
    public void Choice2Picked(){
        if (AIAttacks){
            if(AIChoice==1){
                questionText.setText("<html>THEY TRY TO PHISH YOU! The monitoring is subverted!</html>");
                answerChoices.setText("");
                wait3SecsQuestion();
            }else if(AIChoice==2){
                questionText.setText("<html>THEY LAUNCHED A DDoS ATTACK! Yet you stopped it with your SIEM tools!</html>");
                answerChoices.setText("");
                damageInflicted();
                if (hackerHealth<=0) {
                    questionText.setText("YOU HAVE WON! ALL THE HACKERS WERE SENT TO DETROIT!");
                    wait3SecsEnd();
                }
                wait3SecsEnemy();
            }else if(AIChoice==3){
                questionText.setText("<html>THEY ATTACKED VIA MALWARE INJECTION! The monitoring was subverted!</html>");
                answerChoices.setText("");
                wait3SecsQuestion();
            }
        }else{
            if(AIChoice==2){
                questionText.setText("<html>DESPITE YOUR BOTS FLOODING THEM, THEY WERE ABLE TO HALT IT!</html>");
                answerChoices.setText("");
                wait3SecsQuestion();
            }else{
                questionText.setText("<html>THEY DIDN'T KNOW WHAT HIT EM! THEIR SYSTEMS ARE FLOODED AND DOWNED!</html>");
                answerChoices.setText("");
                damageInflicted();
                if (hackerHealth<=0) {
                    questionText.setText("YOU HAVE WON! ALL THE HACKERS WERE SENT TO DETROIT!");
                    wait3SecsEnd();
                }
                wait3SecsEnemy();
            }
        }
    }


    //Endpoint Protection or Malware Injection
    public void Choice3Picked() {
        if (AIAttacks) {
            if (AIChoice == 1) {
                questionText.setText("<html>THEY TRY TO PHISH YOU! Endpoint Security is subverted!</html>");
                answerChoices.setText("");
                wait3SecsQuestion();
            } else if (AIChoice == 2) {
                questionText.setText("<html>THEY LAUNCHED A DDoS ATTACK! Endpoint Security is subverted!</html>");
                answerChoices.setText("");
                wait3SecsQuestion();
            } else if (AIChoice == 3) {
                questionText.setText("<html>THEY ATTACKED VIA MALWARE INJECTION! However, Endpoint Security had a back up ready!</html>");
                answerChoices.setText("");
                damageInflicted();
                if (hackerHealth<=0) {
                    questionText.setText("YOU HAVE WON! ALL THE HACKERS WERE SENT TO DETROIT!");
                    wait3SecsEnd();
                }
                wait3SecsEnemy();
            }
        } else {
            if (AIChoice == 3) {
                questionText.setText("<html>DESPITE YOUR IMMACULATE MALWARE, THEY HAD ENDPOINT SECURITY!</html>");
                answerChoices.setText("");
                wait3SecsQuestion();
            } else {
                questionText.setText("<html>THEY DIDN'T KNOW WHAT HIT EM! YOUR MALWARE HAS CRIPPLED THEIR SYSTEMS!</html>");
                answerChoices.setText("");
                damageInflicted();
                if (hackerHealth<=0) {
                    questionText.setText("YOU HAVE WON! ALL THE HACKERS WERE SENT TO DETROIT!");
                    wait3SecsEnd();
                }
                wait3SecsEnemy();
            }
        }
    }





        public void attackButtonTurnOn(){
            button11.setVisible(true);
            button22.setVisible(true);
            button33.setVisible(true);
        }

        public void attackButtonTurnOff(){
            button11.setVisible(false);
            button22.setVisible(false);
            button33.setVisible(false);
        }

        public void defendButtonTurnOn(){
            button1.setVisible(true);
            button2.setVisible(true);
            button3.setVisible(true);
        }

        public void defendButtonTurnOff(){
            button1.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);
        }

        public void mcqButtonTurnOn(){
            mcqButton1.setVisible(true);
            mcqButton2.setVisible(true);
            mcqButton3.setVisible(true);
            mcqButton4.setVisible(true);
        }

        public void mcqButtonTurnOff(){
            mcqButton1.setVisible(false);
            mcqButton2.setVisible(false);
            mcqButton3.setVisible(false);
            mcqButton4.setVisible(false);
        }

    public void damageReceived(){
        playerHealth = playerHealth -40;
        healthBarP.setValue(playerHealth);
    }

    public void damageInflicted(){
        hackerHealth = hackerHealth - 10;
        healthBarA.setValue(hackerHealth);
    }

    public void nextQuestion(){
        correctVal = rng.getNext();
        question= Q[correctVal] ;
        Option1 = Ans1[correctVal];
        Option2 = Ans2[correctVal];
        Option3 = Ans3[correctVal];
        Option4 = Ans4[correctVal];
        questionText.setText("<html>"+ question +"</html>");
        answerChoices.setText("<html><b>1."+Option1+"<br/2."+Option2+"<br/3."+Option3+"<br/4."+Option4+"</b></html>");
    }

    public void questionProtocol(){
        attackButtonTurnOff();
        defendButtonTurnOff();
        mcqButtonTurnOn();
        nextQuestion();
    }

    public void checkAnswer(){
        if (chosenAnswer == correctIndex[correctVal]){
            damageInflicted();
            if (hackerHealth<=0) {
                questionText.setText("YOU HAVE WON! ALL THE HACKERS WERE SENT TO DETROIT!");
                wait3SecsEnd();
            }
            questionText.setText("CORRECT. THEY LOST HEALTH!");
            answerChoices.setText("");

        }else{
            damageReceived();
            if (playerHealth<=0) {
                questionText.setText("YOU HAVE LOST! THE COMPANY GOES BANKRUPT!");
                wait3SecsEnd();
            }else {
                questionText.setText("INCORRECT. YOU LOST HEALTH");
            }
            answerChoices.setText("");

        }



        wait3SecsEnemy();
    }

    public void wait3SecsQuestion(){
        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                questionProtocol();
            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    public void wait3SecsEnemy(){
        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enemyChoice();
            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    public void wait3SecsEnd(){
        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });

        timer.setRepeats(false);
        timer.start();
    }
}
