package ua.epam.javacore.controller;

import ua.epam.javacore.model.Skill;
import ua.epam.javacore.repositories.SkillRepositoryImpl;

import java.util.ArrayList;

public class SkillController {

    private SkillRepositoryImpl skillRepository;

    public SkillController(SkillRepositoryImpl skillRepository) {
        this.skillRepository = skillRepository;
    }


    public boolean validateChoiceUser(int number, ArrayList<Skill> skills) {
        if (number <= skills.size()+1 && number > 0) {
            return true;
        }
        return false;
    }

    public boolean hasSkill(String skillStr) {
        ArrayList<Skill> allSkill = skillRepository.getAll();
        for (Skill s: allSkill) {
            if(s.getName().equals(skillStr)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Skill> getAllSkill() {
        return skillRepository.getAll();
    }

    public void save(String newSkill) {
        ArrayList<Skill> skillArrayList = skillRepository.getAll();
        Long id = (long) skillArrayList.size()+1;
        skillRepository.save(new Skill(id,newSkill));
    }
}
