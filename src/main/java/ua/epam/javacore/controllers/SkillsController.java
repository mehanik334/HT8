package ua.epam.javacore.controllers;

import ua.epam.javacore.model.Skill;

import java.util.ArrayList;

public class SkillsController {

    public boolean validateChoiceUser(int number, ArrayList<Skill> skills) {
        if(number <= skills.size() && number > 0) {
            return true;
        }
        return false;
    }
}
