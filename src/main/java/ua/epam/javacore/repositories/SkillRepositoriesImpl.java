package ua.epam.javacore.repositories;

import ua.epam.javacore.model.Skill;

import java.io.*;
import java.util.ArrayList;

public class SkillRepositoriesImpl implements CrudRepositories<Skill> {

    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;

    public SkillRepositoriesImpl(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.bufferedReader = new BufferedReader(fileReader);
        this.fileWriter = fileWriter;
    }

    @Override
    public Skill getById(Long id) {
        ArrayList<Skill> skillList = getAll();
        for (int i = 0; i < skillList.size(); i++) {
            if (id.equals(skillList.get(i).getId())) {
                return skillList.get(i);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Skill> getAll() {
        ArrayList<Skill> skillList = new ArrayList<>();
        try {
            String skill = bufferedReader.readLine();
            String[] arr = new String[2];
            while (skill != null) {
                arr = skill.split("-");
                skillList.add(new Skill(Long.parseLong(arr[0]), arr[1]));
                skill = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skillList;
    }

    @Override
    public void save(Skill obj) {
        try {
            fileWriter.write(obj.toString());
            fileWriter.append('\n');
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Skill deleteById(Long id) {
        ArrayList<Skill> skills = getAll();
        for (int i = 0; i < skills.size(); i++) {
            if (id.equals(skills.get(i).getId())) {
                skills.remove(i);
            }
        }
        saveSkillsListToFile(skills);
        return null;
    }

    @Override
    public boolean update(Skill obj) {
        ArrayList<Skill> skills = getAll();
        for (int i = 0; i < skills.size(); i++) {
            if (obj.getId().equals(skills.get(i).getId())) {
                skills.get(i).setName(obj.getName());
                return true;
            }
        }
        return false;
    }

    private void saveSkillsListToFile(ArrayList<Skill> skills) {
        for (Skill s : skills) {
            save(s);
        }
    }

    public void showSkillsFromFile(ArrayList<Skill> skills) {
        for (Skill s:skills) {
            System.out.println(s);
        }
    }
}
