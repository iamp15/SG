package mainPackage;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


public class Panel extends JPanel{
    //Atributos
    private  final JPanel laminaSuperior, laminaMedio, laminaInferior; 
    private JComboBox comboDep, comboReason, comboResponse, comboApptRequest, comboGenero;
    private String department, reason, response, apptRequest, clientName, clientPhone, clientEmail, agentName, 
            vYear, vMake, vModel, carPrice, payments, downPayment, apptTime, genero, carColor, clientVehicle;
    private JTextArea areaSummary;
    private JTextField counter;
    private final JCheckBox chkBox[] = new JCheckBox[5];
    private final JTextField fieldArray[] = new JTextField[13];
    private final Color colorFondo = new Color(46,117,182); //Creo el color de fondo de la app
    private int k, j,count;
    
    //Constructor
    public Panel(){
        k=0;
        j=0;
      
        try {
            count=Integer.parseInt(Memoria.loadCount());     
        } catch (IOException ex) {
            count=0;
        }
       
        
        clientName = "";
        clientPhone = ""; 
        clientEmail = "";
        agentName = "";
        clientVehicle = "";
        vYear = "";
        vMake = "";
        vModel = "";
        
        setLayout(new BorderLayout());          //Indico que la distribucion sera tipo BorderLayout
          
        laminaSuperior = new JPanel();   //Creo 3 laminas que se aregarán a las partes superior,
        laminaMedio = new JPanel();      //inferior y medio.
        laminaInferior = new JPanel();
        
        laminaSuperior.setBackground(Color.BLUE);
        
        laminaMedio.setLayout(null);        //será del tipo Grid
        laminaMedio.setBackground(colorFondo); 
        
        laminaInferior.setBackground(colorFondo);
        
        add(laminaSuperior,BorderLayout.NORTH);
        add(laminaMedio,BorderLayout.CENTER);
        add(laminaInferior,BorderLayout.SOUTH);
        
         
        initComponents();
       
                
        
    }
    
    //Metodos
    private void initComponents(){
        
        int ancho=100, anchoLabel=70;
        int alto=25;
        
        //Barra de herramientas
        JMenuBar barraHerramientas = new JMenuBar();
        JMenu settings = new JMenu("Settings");
        barraHerramientas.add(settings);
        laminaSuperior.add(barraHerramientas);
        
        
        //Cuadros de texto y eqtiquetas
        
        //FILA 1------------------------------------------------------------------------------------------------
        
        //Department
        createLabel("Department:",20,20,anchoLabel,alto);
        comboDep = new JComboBox();
        comboDep.addItem("Sells");
        comboDep.addItem("Service/Parts");
        comboDep.addItem("Finance");
        comboDep.addItem("General");
        comboDep.addItem("Not Connected");
        comboDep.addItem("Other language");
        comboDep.setBounds(100,20,ancho,alto);
        
        ComboEvent departmentEvent = new ComboEvent();
        comboDep.addActionListener(departmentEvent);
              
        laminaMedio.add(comboDep);
        
        //Client name
        createLabel("Client name:",260,20,anchoLabel,alto);
        createTextField(340,20,ancho,alto);
        
        //Client phone (label & textField)
        createLabel("Client phone:",550,20,100, alto);
        createTextField(630,20,ancho,alto);
        
        //Client E-mail (label & textField)
        createLabel("Client email:",780,20,anchoLabel, alto);
        createTextField(860,20,ancho,alto);        
        
        
        //FILA 2-------------------------------------------------------------------------------------------------------
        
        //Agent response (combo box)
        createLabel("Agent resp:",20,80,anchoLabel, alto);
        comboResponse = new JComboBox();
        comboResponse.addItem("No car discussed");
        comboResponse.addItem("No response");
        comboResponse.addItem("Call back promised");
        comboResponse.addItem("Car is available");
        comboResponse.addItem("Car isn't available");
        comboResponse.setBounds(100,80,120,alto);
        
        laminaMedio.add(comboResponse);
                        
        //Vehicle (year, make, model) (label & textField)
        createLabel("Vehicle:", 260,80,anchoLabel,alto);       
        createTextField(320,80,ancho,alto);
        createTextField(430,80,ancho,alto);
        createTextField(540,80,ancho,alto);
        
        //Agent name (label & textField)
        createLabel("Agent name:",780,80,100, alto);
        createTextField(860,80,ancho,alto);
        
        
        //FILA 3-------------------------------------------------------------------------------------------------------------
        
        //Reason of the call (combo box)
        createLabel("Reason:",20,140,anchoLabel, alto);
        comboReason = new JComboBox();
        comboReason.addItem("Purchase");
        comboReason.addItem("Purchase /w trade in");
        comboReason.addItem("Lease");
        comboReason.addItem("Lease w/ trade in");
        comboReason.addItem("Sell");
        comboReason.setBounds(100,140,120,alto);
        
        laminaMedio.add(comboReason);
        
        
        //Appointment request (combo box) and appointment time   
        createLabel("Appt request:",260,140,100, alto);
        comboApptRequest = new JComboBox();
        comboApptRequest.addItem("No appt. requested");
        comboApptRequest.addItem("Appt. set for:");
        comboApptRequest.addItem("Appt. requested but not set");
        comboApptRequest.addItem("Appt. mentioned but not set");
        comboApptRequest.setBounds(340,140,150,alto);
        
        laminaMedio.add(comboApptRequest);
        
        //Car price (check box, label, texfield)
        createCheckBox("Car price:",550,140,ancho,alto);
        createTextField(650,140,ancho,alto);
        
        //Down payment (check box, label, texfield)
        createCheckBox("Down payment:",780,140,120,alto);
        createTextField(900,140,ancho,alto);
        
        
        //FILA 4----------------------------------------------------------------------------------------------------------------
        
        //Client vehicle
        createLabel("Clt. vehicle: ",20,200,anchoLabel, alto);
        createTextField(100,200,120,alto);
        
        //Time for appointment
        createLabel("Appt time: ",260,200,anchoLabel, alto);
        createTextField(340,200,150,alto);
         
        
        
        //Payments (check box, label, texfield)
        createCheckBox("Payments:",550,200,ancho,alto);
        createTextField(650,200,ancho,alto);
       
        //Car color
        createCheckBox("Car color:",780,200,ancho,alto);
        createTextField(900,200,ancho,alto);
        
        
        //FILA 5---------------------------------------------------------------------------------------------------------------------
        
        //Client gender 820,150
        createLabel("Clt. gender:",20,260,anchoLabel, alto);
        comboGenero = new JComboBox();
        comboGenero.addItem("");
        comboGenero.addItem("he");
        comboGenero.addItem("she");
        comboGenero.setBounds(100,260,ancho,alto);
        
        laminaMedio.add(comboGenero);
        
        //Follow up call
        createCheckBox("Follow up call",260,260, 150, alto);    
        
        
        //-------------------------------------------------------------------------------------------------------------------------------
        
        //CUADRO PARA EL RESUMEN
        createLabel("Summary:", 30, 345, ancho, alto);
        areaSummary = new JTextArea();
        areaSummary.setBounds(30, 370, 700, 100);
        areaSummary.setLineWrap(true);
        areaSummary.setWrapStyleWord(true);
        laminaMedio.add(areaSummary);
        
        //Botones
        JButton bGenerate = new JButton("Generate");
        JButton bCopy = new JButton("Copy");
        JButton bCleanAll = new JButton("Clean All");
        JButton bResetCount = new JButton("Rst");
        JButton bCountUp = new JButton("+");
        JButton bCountDw = new JButton("-");
        JButton bCleanArea = new JButton("Clean Text");
        
        bGenerate.setBounds(160, 490, ancho, alto);
        bCopy.setBounds(300, 490, ancho, alto);
        bCleanAll.setBounds(440, 490, ancho, alto);
        bCleanArea.setBounds(580, 490, ancho, alto);
        bResetCount.setBounds(960, 460, 55, 30);
        bCountUp.setBounds(960, 380, 55, 30);
        bCountDw.setBounds(960, 420, 55, 30);
        
        
        bCountUp.setFont(new Font("Arial",Font.PLAIN,25));
        bCountDw.setFont(new Font("Arial",Font.PLAIN,30));
        
        bGenerate.addActionListener(new ButtonEvent());
        bCopy.addActionListener(new ButtonEvent());
        bCleanAll.addActionListener(new ButtonEvent());
        bCountUp.addActionListener(new ButtonEvent());
        bCountDw.addActionListener(new ButtonEvent());
        bCleanArea.addActionListener(new ButtonEvent());
        
        laminaMedio.add(bGenerate);
        laminaMedio.add(bCopy);
        laminaMedio.add(bCleanAll);
        laminaMedio.add(bCountUp);
        laminaMedio.add(bCountDw);
        laminaMedio.add(bCleanArea);
        
        //Contador
        counter = new JTextField(""+count);
        counter.setFont(new Font("Arial",Font.PLAIN,80));
        counter.setBounds(820, 340, 120, 150);
        counter.setHorizontalAlignment(SwingConstants.CENTER);
        counter.setEditable(false);
        
        bResetCount.addActionListener(new ButtonEvent());
        
        laminaMedio.add(counter);
        laminaMedio.add(bResetCount);
    }
    
    //Metodo para crear JTextFields y ponerlos en alerta
    public void createTextField(int posx, int posy, int ancho, int alto){
        
        JTextField field = new JTextField();
        
        field.setBounds(posx, posy,ancho,alto);  
        Document doc = field.getDocument();
        doc.addDocumentListener(new TextFieldEvent(k));
        laminaMedio.add(field);
        fieldArray[k] = field;
        k++;
    }
    
    public void createLabel(String text, int posx, int posy, int ancho, int alto){
        
        JLabel label = new JLabel(text);
        label.setBounds(posx, posy,ancho,alto);
        label.setForeground(Color.WHITE);
        laminaMedio.add(label);
    }
    
    //Evento para crear elementos tipo JCheckBox
    public void createCheckBox(String text, int posx, int posy, int ancho, int alto){
        
        chkBox[j] = new JCheckBox(text);
        chkBox[j].setBounds(posx, posy,ancho,alto);
        chkBox[j].setBackground(colorFondo);
        chkBox[j].setForeground(Color.WHITE);
        laminaMedio.add(chkBox[j]);
        j++;
    }
    
    //genera el resumen segun las opciones seleccionadas
    public void summaryGenerator(){
        
        boolean thereIsCName =false;
        boolean thereIsAName = false;
        boolean thereIsCPhone = false;
        boolean thereIsCEmail = false;
        boolean noContactInfo = false;
        boolean thereIsvYear = false, thereIsvMake = false, thereIsvModel = false;
        
        if(!clientName.matches("")) thereIsCName = true;
        if(!agentName.matches("")) thereIsAName = true;
        if(!clientPhone.matches("")) thereIsCPhone = true;
        if(!clientEmail.matches("")) thereIsCEmail = true;
        if(!vYear.matches("")) thereIsvYear = true;
        if(!vMake.matches("")) thereIsvMake = true;
        if(!vModel.matches("")) thereIsvModel = true;
        
        if(!thereIsCEmail && !thereIsCName && !thereIsCPhone) noContactInfo = true;     
      
        
        switch(department){
            
            case "Sells":
                
                //Contact information
                if(noContactInfo){
                    areaSummary.append("The caller doesn't share his contact information. ");
                }
                else{
                    if(thereIsCName)  areaSummary.append("The caller's name is "+clientName+". ");
                    else areaSummary.append("The caller's name is not shared. ");
                    
                    if(!thereIsCPhone){
                        if(thereIsCName) areaSummary.append(clientName+"'s phone number is not shared. ");
                        else    areaSummary.append("The caller's phone number is not shared. ");
                    }
                    else areaSummary.append("Phone number "+clientPhone+". ");
                    
                    if(thereIsCEmail){
                        if(chkBox[1].isSelected()) areaSummary.append("Caller's email: "+clientEmail+". ");
                        else areaSummary.append(clientName+"'s email: "+clientEmail+". ");
                    }                   
                }
                
                //Follow up call
                if(chkBox[4].isSelected()){
                    areaSummary.append("This is a follow up call. ");
                }
                
                //Reason of the call
                switch(reason){
                    case "Purchase":
                        if(thereIsCName) areaSummary.append(clientName+" wants to purchase a car. " );
                        else areaSummary.append("The caller wants to purchase a car. " );
                        break;
                        
                    case "Purchase /w trade in":
                        if(!clientVehicle.matches("")){
                            if(thereIsCName) areaSummary.append(clientName+" wants to purchase a car and "+genero+" has a "+clientVehicle+" for trade in. " );
                            else areaSummary.append("The caller wants to purchase a vehicle and "+genero+" has a "+clientVehicle+" for trade in. " );
                            break;
                        }
                        else{
                            if(thereIsCName) areaSummary.append(clientName+" wants to purchase a car and "+genero+" has a car for trade in. " );
                            else areaSummary.append("The caller wants to purchase a vehicle and "+genero+" has a car for trade in. " );
                            break;
                        }
    
                    case "Lease":
                        if(thereIsCName) areaSummary.append(clientName+" wants to lease a car. " );
                        else areaSummary.append("The caller wants to lease a car. " );
                        break;
                        
                    case "Lease w/ trade in":
                        if(!clientVehicle.matches("")){
                            if(thereIsCName) areaSummary.append(clientName+" wants to lease a vehicle and "+genero+" has a "+clientVehicle+" for trade in. ");
                            else areaSummary.append("Caller wants to lease a vehicle and "+genero+" has a "+clientVehicle+" for trade in. " );
                            break;
                        }
                        else{
                            if(thereIsCName) areaSummary.append(clientName+" wants to lease a vehicle and "+genero+" has a car for trade in. ");
                            else areaSummary.append("Caller wants to lease a vehicle and "+genero+" has a car for trade in. " );
                            break;
                        }
                        
                        
                    case "Sell":
                        if(thereIsCName) areaSummary.append(clientName+" wants to sell a car. " );
                        else areaSummary.append("The caller wants to sell a car. " );
                        break;
                }

                
                
                //Vehicle
                
                if(response.matches("No car discussed")){
                    if(!thereIsCName) areaSummary.append("Caller does not mention any vehicle make or model that he would be interested in. ");
                    else  areaSummary.append(clientName+" does not mention any vehicle make or model that he would be interested in. ");
                }
                else{
                    if(genero.matches("he")) areaSummary.append("He is interested in a");
                    else areaSummary.append("She is interested in a");
                    if(thereIsvYear) areaSummary.append(" "+vYear);
                    if(!thereIsvMake && !thereIsvModel) areaSummary.append(" vehicle");
                    else{
                        if(thereIsvMake) areaSummary.append(" "+vMake);
                        if(thereIsvModel) areaSummary.append(" "+vModel);
                    }
                    
                    areaSummary.append(". ");
                }
                
                //Agent respose and agent name
                if(thereIsAName){
                    areaSummary.append("The agent name is "+agentName+". ");
                    if(response.matches("Call back promised")){
                        areaSummary.append(agentName+" says has to check the information and call back. ");
                    }
                    if(response.matches("Car isn't available")){
                        areaSummary.append(agentName+" says the car is not available. ");
                    } 
                    if(response.matches("Car is available")){
                        areaSummary.append(agentName+" says the car is available. ");
                    } 
                }
                else{
                    if(response.matches("Call back promised")){
                        areaSummary.append("The agent says has to check the information and call back. ");
                    }
                    if(response.matches("Car is available")){
                        areaSummary.append("The agent says the car is available. ");
                    }
                    if(response.matches("Car isn't available")){
                        areaSummary.append("The agent says the car is not available. ");
                    } 
                }
                
                //EXTRA INFORMATION
                //Car color
                if(chkBox[3].isSelected()){
                    if(thereIsCName) areaSummary.append(clientName+" says "+genero+" wants the car in "+carColor+". ");
                    else areaSummary.append("The caller says "+genero+" wants the car in "+carColor+". ");
                }
                
                //Car price
                if(chkBox[0].isSelected()){
                    areaSummary.append("The price of the car is $"+carPrice+". ");   
                }
                
                //Payments
                if(chkBox[2].isSelected()){
                    if(!thereIsCName && !thereIsAName) areaSummary.append("The caller asks for the payments of the car. The agent says "+genero +" has to pay $"+payments+"/month. ");
                    if(thereIsCName && !thereIsAName) areaSummary.append(clientName+" asks for the payments of the car. The agent says "+genero +" has to pay $"+payments+"/month. ");
                    if(!thereIsCName && thereIsAName) areaSummary.append("The caller asks for the payments of the car. "+agentName+ " says "+genero +" has to pay $"+payments+"/month. ");
                    if(thereIsCName && thereIsAName) areaSummary.append(clientName+" asks for the payments of the car. "+agentName+ " says "+genero +" has to pay $"+payments+"/month. ");
                }
                
                //Down payment
                if(chkBox[1].isSelected()){
                    if(thereIsCName) areaSummary.append(clientName+" says "+genero+" has $"+downPayment+" for down payment. ");
                    else areaSummary.append("The caller says "+genero+" has $"+downPayment+" for down payment. ");
                }
                
                //Appointment request
                if(thereIsAName){
                    if(apptRequest.matches("No appt. requested")){
                        if(thereIsCName) areaSummary.append(agentName+" doesn't invite "+clientName+" to come in or mention an appointment. ");
                        else areaSummary.append(agentName+" doesn't invite the client to come in or mention an appointment. ");
                    }
                    if(apptRequest.matches("Appt. set for:")){
                        if(thereIsCName) areaSummary.append(clientName+" says "+genero+" can come in to the dealer "+apptTime+". ");
                        else areaSummary.append("The client says "+genero+" can come in to the dealer "+apptTime+". ");
                    }
                    if(apptRequest.matches("Appt. requested but not set")){
                        if(thereIsCName) areaSummary.append(agentName+" invites "+clientName+" to come in but "+genero+ " declines. ");
                        else areaSummary.append(agentName+" invites the caller to come in but "+genero+ " declines. ");
                    }                       
                }
                else{
                    if(apptRequest.matches("No appt. requested")){
                        if(thereIsCName) areaSummary.append("The agent doesn't invite "+clientName+" to come in or mention an appointment. ");
                        else areaSummary.append("The agent doesn't invite the client to come in or mention an appointment. ");
                    }
                    if(apptRequest.matches("Appt. set for:")){
                        if(thereIsCName) areaSummary.append(clientName+" says "+genero+" can come in to the dealer "+apptTime+". ");
                        else areaSummary.append("The client says "+genero+" can come in to the dealer "+apptTime+". ");
                    }
                    if(apptRequest.matches("Appt. requested but not set")){
                        if(thereIsCName) areaSummary.append("The agent invites "+clientName+" to come in but "+genero+ " declines. ");
                        else areaSummary.append("The agent invites the caller to come in but "+genero+ " declines. ");
                    } 
                }
                if(apptRequest.matches("Appt. mentioned but not set")) areaSummary.append("An appointment is mentioned but not set. ");
                
                //Call closer
                if(thereIsCName) areaSummary.append(clientName+" says thanks and call ends. ");
                else areaSummary.append("The caller says thanks and call ends. ");
    
                break;
                
                
            case "Service/Parts":
                areaSummary.append("Call managed by the service/parts department. No car inventory is discussed. No new sell opportunity.");
                break;
                
            case "Finance":
                areaSummary.append("Call managed by the finance department. No car inventory is discussed. No new sell opportunity.");
                break;
                
            case "General":
                if(thereIsCName){
                    areaSummary.append("The caller's name is "+clientName+". "+clientName+" has a general conversation with the agent. "
                            + "No car inventory is discussed. No new sell opportunity.");
                }           
                else areaSummary.append("The caller has a general conversation with the agent. No car inventory is discussed. No new sell "
                        + "opportunity.");
                break;
                
            case "Not Connected":
                areaSummary.append("Caller not connected to the intended person. No car inventory is discussed. No new sell opportunity.");
                break;
                
            case "Other language":
                areaSummary.append("Caller and agent star a conversation in a language different than english or spanish. Call tagged "
                        + "as not conected.");
                break;
        }       
    }
    
    //Guarda el valor de cada campo en su variable correspondiente 
    public void getData(){
        
        department = (String)comboDep.getSelectedItem();
        reason = (String)comboReason.getSelectedItem();
        response = (String)comboResponse.getSelectedItem();
        apptRequest = (String)comboApptRequest.getSelectedItem();
        genero = (String)comboGenero.getSelectedItem();
        
        
    }
    
    //Aumenta la cuenta del contador en 1
    public void countUp(){
        count++;
        try {
            Memoria.saveCount(count);
        } catch (IOException ex) {
            System.out.println("Error: cound not find the specified file. Counting up");
        }
        counter.setText(""+count);
        
    }
    
    //Disminuye la cuenta del contador en 1
    public void countDown(){
        count--;
        try {
            Memoria.saveCount(count);
        } catch (IOException ex) {
            System.out.println("Error: cound not find the specified file. Counting down");
        }
        counter.setText(""+count);
        
    }

    //Maneja los eventos del tipo JComboBox
    private class ComboEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            getData();
        }
        
    }
    
    //Maneja los eventos del tipo JButton
    private class ButtonEvent implements ActionListener{        

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                JButton buttonclicked = (JButton)ae.getSource();
                String buttonName = buttonclicked.getText();
                
                switch(buttonName){
                    //Funciones al presionar el boton generar
                    case "Generate":
                        getData();
                        if(genero.matches("")){
                            JOptionPane.showMessageDialog(Panel.this, "Please select the client's gender");
                            break;
                        }
                        summaryGenerator();
                        areaSummary.selectAll();
                        areaSummary.copy();
                        countUp();
                        break;
                        
                    case "Copy":
                        areaSummary.selectAll();
                        areaSummary.copy();
                        break;
                        
                    case "Clean All":
                        cleanAll();
                        
                        break;
                        
                    case "Rst":
                        count = 0;
                        Memoria.saveCount(count);
                        counter.setText(""+count);
                        break;
                        
                    case "+":
                        countUp();
                        break;
                        
                    case "-":
                        countDown();
                        break;
                        
                    case "Clean Text":
                        areaSummary.setText("");
                        countDown();
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    //Metodo para reiniciar el formulario a su estado inicial
    public void cleanAll(){
        
        for(JTextField i: fieldArray){
            if(!i.getText().matches("")) i.setText("");
        }
        
        comboDep.setSelectedItem("Sells");
        comboApptRequest.setSelectedItem("No appt. requested");
        comboGenero.setSelectedItem("");
        comboReason.setSelectedItem("Purchase");
        comboResponse.setSelectedItem("No car discussed");
        
        for(JCheckBox i: chkBox){
            if(i.isSelected()) i.setSelected(false);
        }
        
        areaSummary.setText("");
    }
    
    
    //Metodo que maneja los eventos del tipo JTextField
    private class TextFieldEvent implements DocumentListener{
        int id;
        TextFieldEvent(int k){
            id =k;
        }

        @Override
        public void insertUpdate(DocumentEvent de) {
            
            Document doc = de.getDocument();           
            try {
                switch (id){
                    case 0:
                        clientName= doc.getText(0, doc.getLength());
                        break;
                        
                    case 1:
                        clientPhone = doc.getText(0, doc.getLength());
                        break;
                    
                    case 2:
                        clientEmail = doc.getText(0, doc.getLength());
                        break;
                        
                    case 3:
                        vYear = doc.getText(0, doc.getLength());
                        comboResponse.setSelectedItem("No response");
                        break;
                        
                    case 4:
                        vMake = doc.getText(0, doc.getLength());
                        comboResponse.setSelectedItem("No response");
                        break;
                        
                    case 5:
                        vModel = doc.getText(0, doc.getLength());
                        comboResponse.setSelectedItem("No response");
                        break;    
                                                
                    case 6:
                        agentName = doc.getText(0, doc.getLength());
                        break;
                        
                    case 7:
                        carPrice = doc.getText(0, doc.getLength());
                        
                    case 8:
                        downPayment = doc.getText(0, doc.getLength());
                        break;
                        
                    case 9:
                        clientVehicle = doc.getText(0, doc.getLength());
                        break;
                        
                    case 10:
                        apptTime = doc.getText(0, doc.getLength());
                        break;
                    
                    case 11:
                        payments = doc.getText(0, doc.getLength());
                        break;
                        
                    case 12:
                        carColor = doc.getText(0, doc.getLength());
                        break;
                }
                
            } catch (BadLocationException ex) {
                System.out.println("Bad selection. Exception "+ex);
            }
            
            
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
            Document doc = de.getDocument();           
            try {
                switch (id){
                    case 0:
                        clientName= doc.getText(0, doc.getLength());
                        break;
                        
                    case 1:
                        clientPhone = doc.getText(0, doc.getLength());
                        break;
                    
                    case 2:
                        clientEmail = doc.getText(0, doc.getLength());
                        break;
                        
                    case 3:
                        vYear = doc.getText(0, doc.getLength());
                        comboResponse.setSelectedItem("Car is available");
                        break;
                        
                    case 4:
                        vMake = doc.getText(0, doc.getLength());
                        comboResponse.setSelectedItem("Car is available");
                        break;
                        
                    case 5:
                        vModel = doc.getText(0, doc.getLength());
                        comboResponse.setSelectedItem("Car is available");
                        break;    
                                                
                    case 6:
                        agentName = doc.getText(0, doc.getLength());
                        break;
                        
                    case 7:
                        carPrice = doc.getText(0, doc.getLength());
                        
                    case 8:
                        downPayment = doc.getText(0, doc.getLength());
                        break;
                        
                    case 9:
                        clientVehicle = doc.getText(0, doc.getLength());
                        break;
                        
                    case 10:
                        apptTime = doc.getText(0, doc.getLength());
                        break;
                    
                    case 11:
                        payments = doc.getText(0, doc.getLength());
                        break;
                        
                    case 12:
                        carColor = doc.getText(0, doc.getLength());
                        break;
                }
                
            } catch (BadLocationException ex) {
                System.out.println("Bad selection. Exception "+ex);
            }
            
        }

        @Override
        public void changedUpdate(DocumentEvent de) {
            

        }
        
        
    }
    
    
}