package ua.epam.javacore.views;

import ua.epam.javacore.model.Skill;
import ua.epam.javacore.repositories.SkillRepositoriesImpl;
import ua.epam.javacore.controllers.SkillsController;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SkillsView {

    private FileReader fileReader;
    private FileWriter fileWriter;

    public SkillsView(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    public Skill getSkillsFromView() {
        Scanner scanner = new Scanner(System.in);
        SkillsController skillsController = new SkillsController();
        SkillRepositoriesImpl skillRepositories = new SkillRepositoriesImpl(fileReader,fileWriter);
        ArrayList<Skill> skills = skillRepositories.getAll();
        System.out.println("Select a skill from the list below, enter the corresponding number");
        skillRepositories.showSkillsFromFile(skills);
        while (true) {
        int choiceNumber = scanner.nextInt();

            if (skillsController.validateChoiceUser(choiceNumber, skills)) {
                return skills.get(choiceNumber - 1);
            } else {
                System.out.println("Wrong enter, try again");
            }
        }
    }
}
