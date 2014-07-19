/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ayf.ui;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import org.ayf.database.entities.BaseEntity;
import org.ayf.database.entities.Member;
import org.ayf.managers.DatabaseManager;
import org.ayf.managers.ResourceManager;
import org.ayf.util.DateTime;
import org.ayf.util.Toast;

/**
 *
 * @author om
 */
public class InformationPanel extends BackgroundPanel {

    /**
     * Creates new form PersonalInformationPanel
     */
    
    public static final String[] BloodGroups = {"A+", "B+", "AB+", "O+", "A-", "B-", "AB-", "O-"};
    
    public enum Context
    {
        Registeration,
        Update,
        Search,
        View
    }
    
    private Context currentContext;
    private Member member;
    
    
    public InformationPanel(Member member, Context context) {
         super(BackgroundStyle.GradientBlueGray);
        initComponents();
        setCurrentContext(context);
        setMember(member);
        
//        PromptSupport.setPrompt("Address Line 1", permanentAddrFirstLine);
//        PromptSupport.setPrompt("Pin Code", temporaryAddressPincodeTxt);
//        PromptSupport.setPrompt("Country", temporaryAddressCountryTxt);
//        PromptSupport.setPrompt("Address Line 2", permanentAddressSecondLine);
//        PromptSupport.setPrompt("City", permanentAddressCityTxt);
//        PromptSupport.setPrompt("Address Line 1", temporaryAddressFirstLine);
//        PromptSupport.setPrompt("Address Line 2", temporaryAddressSecondLine);
//        PromptSupport.setPrompt("City", temporaryAddressCityTxt);
//        PromptSupport.setPrompt("Mobile Number", mobileNumberTxt);
//        PromptSupport.setPrompt("Country", permanentAddressCountryTxt);
//        PromptSupport.setPrompt("Pincode", permanentAddressPincodeTxt);
//        PromptSupport.setPrompt("First Name", firstNameTxt);
//        PromptSupport.setPrompt("Last Name", lastNameTxt);
//        PromptSupport.setPrompt("Middle Name", middleNameTxt);
//        
//        PromptSupport.setPrompt("Middle Name", castTxt);
//        PromptSupport.setPrompt("Middle Name", dobDate);
//        PromptSupport.setPrompt("Middle Name", dobYear);
//        PromptSupport.setPrompt("Middle Name", educationTxt);
//        PromptSupport.setPrompt("Middle Name", emailAddressTxt);
    }

    public Context getCurrentContext() {
        return currentContext;
    }

    public void setCurrentContext(Context currentContext) {
        this.currentContext = currentContext;
        if(this.currentContext == Context.View)
        {
            disableControls();
        }
        else
        {
            enableControls();
        }
        
        updatePanel();
    }
    

    public void setProfessionTypes(Vector<String> professionTypes) {
        this.professionTypes = professionTypes;
        this.professionTypeCombo.setModel(new DefaultComboBoxModel(this.professionTypes));
    }

    public void setPositionTypes(Vector<String> positionTypes) {
        this.positionTypes = positionTypes;
        this.positionCombo.setModel(new DefaultComboBoxModel(this.positionTypes));
    }

    public void setBloodGroups(Vector<String> bloodGroups) {
        this.bloodGroups = bloodGroups;
        this.bloodGroupCombo.setModel(new DefaultComboBoxModel(this.bloodGroups));
    }
    
    public void setDateOfBirthMonthComboBox(Vector<String> months)
    {
        this.dateOfBirthMonths.setModel(new DefaultComboBoxModel(months));
    }
    
    public void setRegisterationMonthComboBox(Vector<String> months)
    {
        this.registerationMonth.setModel(new DefaultComboBoxModel(months));
    }
    
    void disableControls()
    {
        this.imageLabel.setEnabled(false);
            
        //basic information panel
        this.firstNameTxt.setEditable(false);
        this.middleNameTxt.setEditable(false);
        this.lastNameTxt.setEditable(false);

        this.dobDate.setEditable(false);
        this.dobYear.setEditable(false);
        
        this.registerationDay.setEditable(false);
        this.registerationYear.setEditable(false);

        this.genderFemaleButton.setEnabled(false);
        this.genderMaleButton.setEnabled(false);

        this.marritalStatusMarried.setEnabled(false);
        this.marritalStatusSingle.setEnabled(false);

        this.permanentAddrLine1.setEditable(false);
        this.permanentAddressLine2.setEditable(false);
        this.permanentAddressCityTxt.setEditable(false);
        this.permanentAddressState.setEditable(false);
        this.permanentAddressZipCode.setEditable(false);
        //TODO this.permanentAddressState.setText(member);
        //TODO this.permanentAddressZipCode.setText();

        this.temporaryAddressLine1.setEditable(false);
        this.temporaryAddressLine2.setEditable(false);
        this.temporaryAddressCityTxt.setEditable(false);
        this.temporaryAddressState.setEditable(false);
        this.temporaryAddressZipCode.setEditable(false);

        this.emailAddressTxt.setEditable(false);
        this.mobileNumberTxt.setEditable(false);

        this.subcastTxt.setEditable(false);
        this.castTxt.setEditable(false);
        this.educationTxt.setEditable(false);
        
        //No need to disable combo boxes
        //this.dateOfBirthMonths.setEnabled(false);
        //this.registerationMonth.setEnabled(false);
        //this.positionCombo.setEnabled(false);
        //this.professionTypeCombo.setEnabled(false);
        //this.bloodGroupCombo.setEnabled(false);
    }
    
    void enableControls()
    {
        this.imageLabel.setEnabled(true);
            
        //basic information panel
        this.firstNameTxt.setEditable(true);
        this.middleNameTxt.setEditable(true);
        this.lastNameTxt.setEditable(true);

        this.dobDate.setEditable(true);
        this.dobYear.setEditable(true);

        this.genderFemaleButton.setEnabled(true);
        this.genderMaleButton.setEnabled(true);

        this.marritalStatusMarried.setEnabled(true);
        this.marritalStatusSingle.setEnabled(true);

        this.permanentAddrLine1.setEditable(true);
        this.permanentAddressCityTxt.setEditable(true);
        //TODO this.permanentAddressState.setText(member);
        //TODO this.permanentAddressZipCode.setText();

        this.temporaryAddressLine1.setEditable(true);
        this.temporaryAddressCityTxt.setEditable(true);

        this.emailAddressTxt.setEditable(true);
        this.mobileNumberTxt.setEditable(true);

        this.subcastTxt.setEditable(true);
        this.castTxt.setEditable(true);
        this.educationTxt.setEditable(true);
        
        this.dateOfBirthMonths.setEnabled(true);
        this.registerationMonth.setEnabled(true);
        this.positionCombo.setEnabled(true);
        this.professionTypeCombo.setEnabled(true);
        this.bloodGroupCombo.setEnabled(true);
    }
    
    private void updatePanel()
    {
        if(this.member != null)
        {
            //header panel
            this.imageLabel.setIcon(ResourceManager.getExternalIcon(member.getImagePath(), this.imageLabel.getPreferredSize()));
            
            //basic information panel
            this.firstNameTxt.setText(member.getFirstName());
            this.middleNameTxt.setText(member.getMiddleName());
            this.lastNameTxt.setText(member.getLastName());
            
            this.dobDate.setText(Integer.toString(DateTime.getDay(member.getDateOfBirth())));
            Vector<String> dobMonth = new Vector<String>();
            if(getCurrentContext() == Context.View)
            {
                dobMonth.add(DateTime.Months[DateTime.getMonth(member.getDateOfBirth())]);
                setDateOfBirthMonthComboBox(dobMonth);
            }
            else
            {
                dobMonth.addAll(Arrays.asList(DateTime.Months));
                setDateOfBirthMonthComboBox(dobMonth);            
                this.dateOfBirthMonths.setSelectedIndex(DateTime.getMonth(member.getDateOfBirth()));
            }
            
            this.dobYear.setText(Integer.toString(DateTime.getYear(member.getDateOfBirth())));
            
            this.registerationDay.setText(Integer.toString(DateTime.getDay(member.getRegisterationDate())));
            Vector<String> registerationMonth = new Vector<String>(1);
            if(getCurrentContext() == Context.View)
            {
                registerationMonth.add(DateTime.Months[DateTime.getMonth(member.getRegisterationDate())]);
                setRegisterationMonthComboBox(registerationMonth);
            }
            else
            {
                registerationMonth.addAll(Arrays.asList(DateTime.Months));
                setRegisterationMonthComboBox(registerationMonth);
                this.registerationMonth.setSelectedIndex(DateTime.getMonth(member.getRegisterationDate()));
            }
            
            
            this.registerationYear.setText(Integer.toString(DateTime.getYear(member.getRegisterationDate())));
            
            
            this.genderFemaleButton.setSelected(false);
            this.genderMaleButton.setSelected(false);
            
            if(member.getGender() == Member.Gender.Male)
            {
                this.genderMaleButton.setSelected(true);
            }
            else
            {
                this.genderFemaleButton.setSelected(true);
            }
            
            this.marritalStatusMarried.setSelected(false);
            this.marritalStatusSingle.setSelected(false);
            
            if(member.getMaritalStatus() == Member.MaritalStatus.Married)
            {
                this.marritalStatusMarried.setSelected(true);
            }
            else
            {
                this.marritalStatusSingle.setSelected(true);
            }
            
            this.permanentAddrLine1.setText(member.getPermanentAddress());
            this.permanentAddressCityTxt.setText(member.getDistrict());
            //TODO this.permanentAddressState.setText(member);
            //TODO this.permanentAddressZipCode.setText();
            
            this.temporaryAddressLine1.setText(member.getTemporaryAddress());
            this.temporaryAddressCityTxt.setText(member.getDistrict());
           
            this.emailAddressTxt.setText(member.getEmailAddress());
            this.mobileNumberTxt.setText(member.getContactNumber());
                
            Vector<String> professions = new Vector<String>(1);
            if(getCurrentContext() == Context.View)
            {
                professions.add(member.getProfession());
                setProfessionTypes(professions);
            }
            else
            {
                professions.addAll(DatabaseManager.typesToStrings(DatabaseManager.getProfessionTypes()));
                setProfessionTypes(professions);
                professionTypeCombo.setSelectedItem(member.getProfession());
            }
            
            
            
            Vector<String> positions = new Vector<String>(1);
            if(getCurrentContext() == Context.View)
            {
                positions.add(member.getPosition());
                setPositionTypes(positions);
            }
            else
            {
                positions.addAll(DatabaseManager.typesToStrings(DatabaseManager.getPositionTypes()));
                setPositionTypes(positions);
                positionCombo.setSelectedItem(member.getPosition());
            }
            
            Vector<String> bloodGroups = new Vector<String>(1);
            if(getCurrentContext() == Context.View)
            {
                bloodGroups.add(member.getBloodGroup());
                setBloodGroups(bloodGroups);
            }
            else
            {
                bloodGroups.addAll(Arrays.asList(BloodGroups));
                setBloodGroups(bloodGroups);
                bloodGroupCombo.setSelectedItem(member.getBloodGroup());
            }
            
            this.subcastTxt.setText(member.getSubCast());
            this.castTxt.setText(member.getCast());
            this.educationTxt.setText(member.getEducation());
        }
        else
        {
            if(this.genderFemaleButton.isSelected())
            {
                this.imageLabel.setIcon(ResourceManager.getIcon("no_photo_women", this.imageLabel.getPreferredSize()));
            }
            else if(this.genderMaleButton.isSelected())
            {
                this.imageLabel.setIcon(ResourceManager.getIcon("no_photo_men", this.imageLabel.getPreferredSize()));
            }
            else
            {
                this.imageLabel.setIcon(ResourceManager.getIcon("no_photo", this.imageLabel.getPreferredSize()));
            }
            
            setBloodGroups(new Vector<String>(Arrays.asList(BloodGroups)));
            setRegisterationMonthComboBox(new Vector<String>(Arrays.asList(DateTime.Months)));
            setDateOfBirthMonthComboBox(new Vector<String>(Arrays.asList(DateTime.Months)));
            setPositionTypes(new Vector<String>(DatabaseManager.typesToStrings(DatabaseManager.getPositionTypes())));
            setProfessionTypes(new Vector<String>(DatabaseManager.typesToStrings(DatabaseManager.getProfessionTypes())));
        }
    }
    
    
    public void setMember(Member member)
    {
        this.member = member;
        updatePanel();
    }
    
    public Member getMember(boolean returnNULLIfInvalid, boolean showToast)
    {
        boolean isValidMember = true;
        //header panel
        String imagePath = this.imageLabel.getToolTipText();

        //basic information panel
        String firstName = this.firstNameTxt.getText();
        
        if(firstName.length() <= 0) 
        { 
            if(showToast) Toast.showToast(this.firstNameTxt, "First Name required", false); 
            if(returnNULLIfInvalid) isValidMember = false;; 
        }
        String middleName = this.middleNameTxt.getText();
        String lastName = this.lastNameTxt.getText();

        Date dateOfBirth = null;
        try
        {
            int day = Integer.parseInt(this.dobDate.getText());
            String month = (String)this.dateOfBirthMonths.getSelectedItem();
            int year = Integer.parseInt(this.dobYear.getText());
            dateOfBirth = DateTime.getDate(day, month, year);
        } catch(NumberFormatException ex)
        { 
            if(showToast) Toast.showToast(this.dobDate, "Valid Date of Birth required", false); 
            if(returnNULLIfInvalid) isValidMember = false;;
        }
        
        
        
        Date registerationDate = null;
        try
        {
            int day = Integer.parseInt(this.registerationDay.getText());
            String month = (String)this.registerationMonth.getSelectedItem();
            int year = Integer.parseInt(this.registerationYear.getText());
            registerationDate = DateTime.getDate(day, month, year);
        } catch(NumberFormatException ex)
        { 
            if(showToast) Toast.showToast(this.registerationDay, "Valid Registeration Date required", false);
            if(returnNULLIfInvalid) isValidMember = false;
        }
        
        BaseEntity.Gender gender = BaseEntity.Gender.Female;
        
        if(this.genderFemaleButton.isSelected())
        {
            gender = BaseEntity.Gender.Female;
        }
        
        if(!this.genderFemaleButton.isSelected() && !this.genderMaleButton.isSelected())
        {
            if(showToast) Toast.showToast(this.genderMaleButton, "Select Gender", false);
            if(returnNULLIfInvalid) isValidMember = false;
        }

        Member.MaritalStatus maritalStatus = Member.MaritalStatus.Married;
        
        if(this.marritalStatusSingle.isSelected())
        {
            maritalStatus = Member.MaritalStatus.Single;
        }

        if(!this.marritalStatusMarried.isSelected() && !this.marritalStatusSingle.isSelected())
        {
            if(showToast) Toast.showToast(this.marritalStatusSingle, "Select Marital Status", false);
            if(returnNULLIfInvalid) isValidMember = false;
        }
        
        String permanentAddress = this.permanentAddrLine1.getText();
        String permanentCity = this.permanentAddressCityTxt.getText();

        String temporaryAddress = this.temporaryAddressLine1.getText();
        String temporaryCity = this.temporaryAddressCityTxt.getText();

        String emailAddress = this.emailAddressTxt.getText();
        String contactNumber = this.mobileNumberTxt.getText();
        
        if(contactNumber.length() <= 0)
        {
            if(showToast) Toast.showToast(this.mobileNumberTxt, "Valid contact number required.", false);
            if(returnNULLIfInvalid) isValidMember = false;
        }
        
        String position = (String) this.positionCombo.getSelectedItem();
        
        String profession = (String) this.professionTypeCombo.getSelectedItem();

        String bloodGroup = (String) this.bloodGroupCombo.getSelectedItem();

        String subCast = this.subcastTxt.getText();
        String cast = this.castTxt.getText();
        String education = this.educationTxt.getText();
        
        if(isValidMember)
        {
            return new Member(Integer.MAX_VALUE, firstName, middleName, lastName, DateTime.toSQLDate(dateOfBirth), maritalStatus, cast, subCast, permanentCity, bloodGroup, gender, permanentAddress, temporaryAddress, contactNumber, emailAddress, education, profession, DateTime.toSQLDate(registerationDate), position, imagePath, Member.ActiveStatus.Unknown);
        }
        else
        {
            return null;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        personalInformationPanel = new javax.swing.JPanel();
        basicInformationPanel = new javax.swing.JPanel();
        dobYear = new javax.swing.JTextField();
        dobDate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        genderMaleButton = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        middleNameTxt = new javax.swing.JTextField();
        genderFemaleButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        firstNameTxt = new javax.swing.JTextField();
        lastNameTxt = new javax.swing.JTextField();
        dateOfBirthMonths = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        registerationYear = new javax.swing.JTextField();
        registerationMonth = new javax.swing.JComboBox();
        registerationDay = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        marritalStatusSingle = new javax.swing.JRadioButton();
        marritalStatusMarried = new javax.swing.JRadioButton();
        permanentAddressPanell = new javax.swing.JPanel();
        permanentAddressZipCode = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        permanentAddressState = new javax.swing.JTextField();
        permanentAddressLine2 = new javax.swing.JTextField();
        permanentAddressCityTxt = new javax.swing.JTextField();
        permanentAddrLine1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        temporaryAddressPanel = new javax.swing.JPanel();
        temporaryAddressZipCode = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        temporaryAddressState = new javax.swing.JTextField();
        temporaryAddressLine2 = new javax.swing.JTextField();
        temporaryAddressCityTxt = new javax.swing.JTextField();
        temporaryAddressLine1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        contactsPanel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        emailAddressTxt = new javax.swing.JTextField();
        mobileNumberTxt = new javax.swing.JTextField();
        headerPanel = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        otherInformationPanel = new javax.swing.JPanel();
        professionTypeCombo = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        positionCombo = new javax.swing.JComboBox();
        educationTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        bloodGroupCombo = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        castTxt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        subcastTxt = new javax.swing.JTextField();

        personalInformationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Personal Information"));

        basicInformationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Basic Information"));

        dobYear.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dobYearFocusGained(evt);
            }
        });

        dobDate.setMinimumSize(new java.awt.Dimension(6, 23));
        dobDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dobDateFocusGained(evt);
            }
        });

        jLabel2.setText("First Name");

        jLabel4.setText("Date Of Birth");

        genderMaleButton.setText("Male");
        genderMaleButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                genderMaleButtonFocusGained(evt);
            }
        });
        genderMaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderMaleButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Middle Name");

        middleNameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                middleNameTxtActionPerformed(evt);
            }
        });

        genderFemaleButton.setText("Female");
        genderFemaleButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                genderFemaleButtonFocusGained(evt);
            }
        });
        genderFemaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderFemaleButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Last Name");

        jLabel15.setText("Marital Status");

        jLabel5.setText("Gender");

        firstNameTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                firstNameTxtFocusGained(evt);
            }
        });
        firstNameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameTxtActionPerformed(evt);
            }
        });

        lastNameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameTxtActionPerformed(evt);
            }
        });

        dateOfBirthMonths.setMaximumRowCount(12);

        jLabel19.setText("Full Name");

        registerationYear.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                registerationYearFocusGained(evt);
            }
        });

        registerationMonth.setMaximumRowCount(12);

        registerationDay.setMinimumSize(new java.awt.Dimension(6, 23));
        registerationDay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                registerationDayFocusGained(evt);
            }
        });

        jLabel13.setText("Registration Date");

        marritalStatusSingle.setText("Single");
        marritalStatusSingle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                marritalStatusSingleFocusGained(evt);
            }
        });
        marritalStatusSingle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marritalStatusSingleActionPerformed(evt);
            }
        });

        marritalStatusMarried.setText("Married");
        marritalStatusMarried.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                marritalStatusMarriedFocusGained(evt);
            }
        });
        marritalStatusMarried.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marritalStatusMarriedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout basicInformationPanelLayout = new javax.swing.GroupLayout(basicInformationPanel);
        basicInformationPanel.setLayout(basicInformationPanelLayout);
        basicInformationPanelLayout.setHorizontalGroup(
            basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel4)
                    .addComponent(jLabel19)
                    .addComponent(jLabel5)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(basicInformationPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(basicInformationPanelLayout.createSequentialGroup()
                        .addComponent(firstNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(middleNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(basicInformationPanelLayout.createSequentialGroup()
                        .addGroup(basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(genderMaleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(marritalStatusSingle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(marritalStatusMarried, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                            .addComponent(genderFemaleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(basicInformationPanelLayout.createSequentialGroup()
                        .addGroup(basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(registerationDay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dobDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(registerationMonth, 0, 79, Short.MAX_VALUE)
                            .addComponent(dateOfBirthMonths, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dobYear, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(registerationYear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        basicInformationPanelLayout.setVerticalGroup(
            basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basicInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(middleNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(15, 15, 15)
                .addGroup(basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(basicInformationPanelLayout.createSequentialGroup()
                        .addGroup(basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dobDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateOfBirthMonths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(registerationDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(registerationMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(basicInformationPanelLayout.createSequentialGroup()
                        .addComponent(dobYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(registerationYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genderMaleButton)
                    .addComponent(genderFemaleButton))
                .addGap(11, 11, 11)
                .addGroup(basicInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(marritalStatusSingle)
                    .addComponent(marritalStatusMarried)
                    .addComponent(jLabel15))
                .addContainerGap())
        );

        permanentAddressPanell.setBorder(javax.swing.BorderFactory.createTitledBorder("Permanent Address"));

        permanentAddressZipCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                permanentAddressZipCodeFocusGained(evt);
            }
        });

        jLabel20.setText("City");

        permanentAddressState.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                permanentAddressStateFocusGained(evt);
            }
        });

        permanentAddressLine2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                permanentAddressLine2ActionPerformed(evt);
            }
        });

        permanentAddressCityTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                permanentAddressCityTxtFocusGained(evt);
            }
        });

        permanentAddrLine1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                permanentAddrLine1FocusGained(evt);
            }
        });
        permanentAddrLine1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                permanentAddrLine1ActionPerformed(evt);
            }
        });

        jLabel28.setText("Zip Code");

        jLabel21.setText("State");

        javax.swing.GroupLayout permanentAddressPanellLayout = new javax.swing.GroupLayout(permanentAddressPanell);
        permanentAddressPanell.setLayout(permanentAddressPanellLayout);
        permanentAddressPanellLayout.setHorizontalGroup(
            permanentAddressPanellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(permanentAddressPanellLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(permanentAddressPanellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, permanentAddressPanellLayout.createSequentialGroup()
                        .addGroup(permanentAddressPanellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, permanentAddressPanellLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(42, 42, 42))
                            .addGroup(permanentAddressPanellLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(11, 11, 11)))
                        .addGroup(permanentAddressPanellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(permanentAddressZipCode)
                            .addComponent(permanentAddressCityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(permanentAddressState, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(permanentAddressPanellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(permanentAddressLine2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                        .addComponent(permanentAddrLine1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        permanentAddressPanellLayout.setVerticalGroup(
            permanentAddressPanellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(permanentAddressPanellLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(permanentAddrLine1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(permanentAddressLine2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(permanentAddressPanellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(permanentAddressCityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(permanentAddressState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(permanentAddressPanellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(permanentAddressZipCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        temporaryAddressPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Temporary Address"));

        temporaryAddressZipCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                temporaryAddressZipCodeFocusGained(evt);
            }
        });

        jLabel30.setText("City");

        temporaryAddressState.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                temporaryAddressStateFocusGained(evt);
            }
        });

        temporaryAddressLine2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                temporaryAddressLine2FocusGained(evt);
            }
        });
        temporaryAddressLine2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temporaryAddressLine2ActionPerformed(evt);
            }
        });

        temporaryAddressCityTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                temporaryAddressCityTxtFocusGained(evt);
            }
        });

        temporaryAddressLine1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                temporaryAddressLine1FocusGained(evt);
            }
        });
        temporaryAddressLine1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temporaryAddressLine1ActionPerformed(evt);
            }
        });

        jLabel31.setText("Zip Code");

        jLabel32.setText("State");

        javax.swing.GroupLayout temporaryAddressPanelLayout = new javax.swing.GroupLayout(temporaryAddressPanel);
        temporaryAddressPanel.setLayout(temporaryAddressPanelLayout);
        temporaryAddressPanelLayout.setHorizontalGroup(
            temporaryAddressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(temporaryAddressPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(temporaryAddressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, temporaryAddressPanelLayout.createSequentialGroup()
                        .addGroup(temporaryAddressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, temporaryAddressPanelLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(42, 42, 42))
                            .addGroup(temporaryAddressPanelLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(11, 11, 11)))
                        .addGroup(temporaryAddressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(temporaryAddressZipCode)
                            .addComponent(temporaryAddressCityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(jLabel32)
                        .addGap(18, 18, 18)
                        .addComponent(temporaryAddressState, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(temporaryAddressLine1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(temporaryAddressLine2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        temporaryAddressPanelLayout.setVerticalGroup(
            temporaryAddressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(temporaryAddressPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(temporaryAddressLine1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(temporaryAddressLine2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(temporaryAddressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(temporaryAddressCityTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(temporaryAddressState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(temporaryAddressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(temporaryAddressZipCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        contactsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Contacts"));

        jLabel27.setText("Mobile No.");

        jLabel26.setText("Email-id:");

        emailAddressTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailAddressTxtFocusGained(evt);
            }
        });

        mobileNumberTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mobileNumberTxtFocusGained(evt);
            }
        });

        javax.swing.GroupLayout contactsPanelLayout = new javax.swing.GroupLayout(contactsPanel);
        contactsPanel.setLayout(contactsPanelLayout);
        contactsPanelLayout.setHorizontalGroup(
            contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel26))
                .addGap(62, 62, 62)
                .addGroup(contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mobileNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailAddressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contactsPanelLayout.setVerticalGroup(
            contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(emailAddressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(mobileNumberTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        imageLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 255, 204), 2, true));
        imageLabel.setMaximumSize(new java.awt.Dimension(90, 90));
        imageLabel.setMinimumSize(new java.awt.Dimension(90, 90));
        imageLabel.setName(""); // NOI18N
        imageLabel.setPreferredSize(new java.awt.Dimension(90, 90));
        imageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                imageLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imageLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout personalInformationPanelLayout = new javax.swing.GroupLayout(personalInformationPanel);
        personalInformationPanel.setLayout(personalInformationPanelLayout);
        personalInformationPanelLayout.setHorizontalGroup(
            personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(basicInformationPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(permanentAddressPanell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(personalInformationPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contactsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(temporaryAddressPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        personalInformationPanelLayout.setVerticalGroup(
            personalInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInformationPanelLayout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(basicInformationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(permanentAddressPanell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(temporaryAddressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contactsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        otherInformationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Other Information"));

        professionTypeCombo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                professionTypeComboFocusGained(evt);
            }
        });

        jLabel14.setText("Position");

        jLabel12.setText("Profession");

        positionCombo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                positionComboFocusGained(evt);
            }
        });

        educationTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                educationTxtFocusGained(evt);
            }
        });

        jLabel11.setText("Education");

        jLabel6.setText("Blood Group");

        bloodGroupCombo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                bloodGroupComboFocusGained(evt);
            }
        });

        jLabel16.setText("Cast");

        castTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                castTxtFocusGained(evt);
            }
        });

        jLabel17.setText("Subcast");

        subcastTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                subcastTxtFocusGained(evt);
            }
        });

        javax.swing.GroupLayout otherInformationPanelLayout = new javax.swing.GroupLayout(otherInformationPanel);
        otherInformationPanel.setLayout(otherInformationPanelLayout);
        otherInformationPanelLayout.setHorizontalGroup(
            otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(professionTypeCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bloodGroupCombo, 0, 165, Short.MAX_VALUE)
                    .addComponent(castTxt)
                    .addComponent(educationTxt))
                .addGap(42, 42, 42)
                .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(27, 27, 27)
                .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(positionCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subcastTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(otherInformationPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(476, Short.MAX_VALUE)))
        );
        otherInformationPanelLayout.setVerticalGroup(
            otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, otherInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(positionCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(professionTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bloodGroupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel17)
                    .addComponent(subcastTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(castTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(educationTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(otherInformationPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(123, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(personalInformationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(otherInformationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(personalInformationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(otherInformationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void genderMaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderMaleButtonActionPerformed
        this.genderFemaleButton.setSelected(false);
    }//GEN-LAST:event_genderMaleButtonActionPerformed

    private void genderFemaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderFemaleButtonActionPerformed
        this.genderMaleButton.setSelected(false);
    }//GEN-LAST:event_genderFemaleButtonActionPerformed

    private void permanentAddressLine2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_permanentAddressLine2ActionPerformed
        
    }//GEN-LAST:event_permanentAddressLine2ActionPerformed

    private void permanentAddrLine1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_permanentAddrLine1ActionPerformed
        
    }//GEN-LAST:event_permanentAddrLine1ActionPerformed

    private void firstNameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameTxtActionPerformed
        
    }//GEN-LAST:event_firstNameTxtActionPerformed

    private void lastNameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameTxtActionPerformed
        
    }//GEN-LAST:event_lastNameTxtActionPerformed

    private void middleNameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_middleNameTxtActionPerformed
        
    }//GEN-LAST:event_middleNameTxtActionPerformed

    private void temporaryAddressLine2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temporaryAddressLine2ActionPerformed
        
    }//GEN-LAST:event_temporaryAddressLine2ActionPerformed

    private void temporaryAddressLine1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temporaryAddressLine1ActionPerformed
        
    }//GEN-LAST:event_temporaryAddressLine1ActionPerformed

    private void marritalStatusSingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marritalStatusSingleActionPerformed
        this.marritalStatusMarried.setSelected(false);
    }//GEN-LAST:event_marritalStatusSingleActionPerformed

    private void marritalStatusMarriedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marritalStatusMarriedActionPerformed
        this.marritalStatusSingle.setSelected(false);
    }//GEN-LAST:event_marritalStatusMarriedActionPerformed

    private void imageLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageLabelMouseClicked
        
        if(this.currentContext != Context.View)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setMultiSelectionEnabled(false);
            
            int userOption = fileChooser.showOpenDialog(this);
            
            if(userOption == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                ImageIcon icon = ResourceManager.getIcon(file.getAbsolutePath());
                if(icon != null)
                {
                    this.imageLabel.setIcon(icon);
                    this.imageLabel.setToolTipText(file.getAbsolutePath());
                }
            }
        }
    }//GEN-LAST:event_imageLabelMouseClicked

    private void imageLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageLabelMouseEntered
        
        if(this.currentContext != Context.View)
        {
            this.imageLabel.setIcon(ResourceManager.getIcon("change_photo", this.imageLabel.getPreferredSize()));
        }
    }//GEN-LAST:event_imageLabelMouseEntered

    private void imageLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageLabelMouseExited
        
        if(this.currentContext != Context.View)
        {
            if(this.imageLabel.getToolTipText() != null)
            {
                this.imageLabel.setIcon(ResourceManager.getExternalIcon(this.imageLabel.getToolTipText(), this.imageLabel.getPreferredSize()));
            }
            else
            {
                if(this.genderFemaleButton.isSelected())
                {
                    this.imageLabel.setIcon(ResourceManager.getIcon("no_photo_women", this.imageLabel.getPreferredSize()));
                }
                else if(this.genderMaleButton.isSelected())
                {
                    this.imageLabel.setIcon(ResourceManager.getIcon("no_photo_men", this.imageLabel.getPreferredSize()));
                }
                else
                {
                    this.imageLabel.setIcon(ResourceManager.getIcon("no_photo", this.imageLabel.getPreferredSize()));
                }
            }
        }
    }//GEN-LAST:event_imageLabelMouseExited

    private void updateScrollViewWithEvent(java.awt.event.FocusEvent evt) {
        java.awt.Component focusedComponent = evt.getComponent();
        this.scrollRectToVisible(focusedComponent.getParent().getBounds());
        repaint();
    }
    
    private void emailAddressTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailAddressTxtFocusGained
        
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_emailAddressTxtFocusGained

    private void mobileNumberTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mobileNumberTxtFocusGained
        
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_mobileNumberTxtFocusGained

    private void professionTypeComboFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_professionTypeComboFocusGained
        
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_professionTypeComboFocusGained

    private void positionComboFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_positionComboFocusGained
        
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_positionComboFocusGained

    private void bloodGroupComboFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bloodGroupComboFocusGained
        
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_bloodGroupComboFocusGained

    private void subcastTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_subcastTxtFocusGained
        
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_subcastTxtFocusGained

    private void castTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_castTxtFocusGained
        
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_castTxtFocusGained

    private void educationTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_educationTxtFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_educationTxtFocusGained

    private void temporaryAddressLine1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_temporaryAddressLine1FocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_temporaryAddressLine1FocusGained

    private void temporaryAddressLine2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_temporaryAddressLine2FocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_temporaryAddressLine2FocusGained

    private void temporaryAddressCityTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_temporaryAddressCityTxtFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_temporaryAddressCityTxtFocusGained

    private void temporaryAddressStateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_temporaryAddressStateFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_temporaryAddressStateFocusGained

    private void temporaryAddressZipCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_temporaryAddressZipCodeFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_temporaryAddressZipCodeFocusGained

    private void permanentAddrLine1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_permanentAddrLine1FocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_permanentAddrLine1FocusGained

    private void permanentAddressCityTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_permanentAddressCityTxtFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_permanentAddressCityTxtFocusGained

    private void permanentAddressZipCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_permanentAddressZipCodeFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_permanentAddressZipCodeFocusGained

    private void permanentAddressStateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_permanentAddressStateFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_permanentAddressStateFocusGained

    private void firstNameTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameTxtFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_firstNameTxtFocusGained

    private void registerationDayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_registerationDayFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_registerationDayFocusGained

    private void registerationYearFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_registerationYearFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_registerationYearFocusGained

    private void dobDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobDateFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_dobDateFocusGained

    private void dobYearFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobYearFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_dobYearFocusGained

    private void genderMaleButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_genderMaleButtonFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_genderMaleButtonFocusGained

    private void genderFemaleButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_genderFemaleButtonFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_genderFemaleButtonFocusGained

    private void marritalStatusSingleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_marritalStatusSingleFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_marritalStatusSingleFocusGained

    private void marritalStatusMarriedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_marritalStatusMarriedFocusGained
        updateScrollViewWithEvent(evt);
    }//GEN-LAST:event_marritalStatusMarriedFocusGained


    private Vector<String> professionTypes;
    private Vector<String> positionTypes;
    private Vector<String> bloodGroups;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel basicInformationPanel;
    private javax.swing.JComboBox bloodGroupCombo;
    private javax.swing.JTextField castTxt;
    private javax.swing.JPanel contactsPanel;
    private javax.swing.JComboBox dateOfBirthMonths;
    private javax.swing.JTextField dobDate;
    private javax.swing.JTextField dobYear;
    private javax.swing.JTextField educationTxt;
    private javax.swing.JTextField emailAddressTxt;
    private javax.swing.JTextField firstNameTxt;
    private javax.swing.JRadioButton genderFemaleButton;
    private javax.swing.JRadioButton genderMaleButton;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField lastNameTxt;
    private javax.swing.JRadioButton marritalStatusMarried;
    private javax.swing.JRadioButton marritalStatusSingle;
    private javax.swing.JTextField middleNameTxt;
    private javax.swing.JTextField mobileNumberTxt;
    private javax.swing.JPanel otherInformationPanel;
    private javax.swing.JTextField permanentAddrLine1;
    private javax.swing.JTextField permanentAddressCityTxt;
    private javax.swing.JTextField permanentAddressLine2;
    private javax.swing.JPanel permanentAddressPanell;
    private javax.swing.JTextField permanentAddressState;
    private javax.swing.JTextField permanentAddressZipCode;
    private javax.swing.JPanel personalInformationPanel;
    private javax.swing.JComboBox positionCombo;
    private javax.swing.JComboBox professionTypeCombo;
    private javax.swing.JTextField registerationDay;
    private javax.swing.JComboBox registerationMonth;
    private javax.swing.JTextField registerationYear;
    private javax.swing.JTextField subcastTxt;
    private javax.swing.JTextField temporaryAddressCityTxt;
    private javax.swing.JTextField temporaryAddressLine1;
    private javax.swing.JTextField temporaryAddressLine2;
    private javax.swing.JPanel temporaryAddressPanel;
    private javax.swing.JTextField temporaryAddressState;
    private javax.swing.JTextField temporaryAddressZipCode;
    // End of variables declaration//GEN-END:variables
}
