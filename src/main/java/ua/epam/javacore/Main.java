package ua.epam.javacore;

import ua.epam.javacore.views.SkillsView;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            SkillsView skillsView = new SkillsView(new FileReader("skills.txt"),
                                    new FileWriter("skills.txt",true));
            System.out.println(skillsView.getSkillsFromView());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
