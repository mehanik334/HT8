package ua.epam.javacore.view;

import ua.epam.javacore.model.Skill;
import ua.epam.javacore.repositories.SkillRepositoryImpl;
import ua.epam.javacore.controller.SkillController;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SkillView {

    public static final String FILE_PATH = "skills.txt";
    private SkillController skillController;

    public SkillView() {
        try {
            this.skillController = new SkillController(new SkillRepositoryImpl(new FileReader(FILE_PATH),
                    new FileWriter(FILE_PATH, true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Skill getChoiceFromView() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Skill> skills = skillController.getAllSkill();
        System.out.println("Select a skill from the list below, enter the corresponding number");
        showSkillsFromFile(skills);
        System.out.println("4-Add new skill");
        while (true) {
            int choiceNumber = scanner.nextInt();
            if (skillController.validateChoiceUser(choiceNumber, skills)) {
                if (choiceNumber == 4) {
                    if (saveNewSkill()) {
                        System.out.println("Add is complete");
                    }
                } else {
                    return skills.get(choiceNumber - 1);
                }
            } else {
                System.out.println("Wrong enter, try again");
            }
        }
    }

    private boolean saveNewSkill() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new skill");
        String newSkill = scanner.nextLine();
        if (!skillController.hasSkill(newSkill)) {
            skillController.save(newSkill);
            return true;
        } else {
            System.out.println("This skill are exist in list skill");
            return false;
        }
    }

    private void showSkillsFromFile(ArrayList<Skill> skillArrayList) {
        for (Skill s : skillArrayList) {
            System.out.println(s);
        }
    }
}
