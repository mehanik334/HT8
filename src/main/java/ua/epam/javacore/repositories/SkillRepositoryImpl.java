package ua.epam.javacore.repositories;

import ua.epam.javacore.model.Skill;
import ua.epam.javacore.view.SkillView;

import java.io.*;
import java.util.ArrayList;

public class SkillRepositoryImpl implements CrudRepository<Skill> {

    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;

    public SkillRepositoryImpl(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.bufferedReader = new BufferedReader(fileReader);
        this.fileWriter = fileWriter;
    }

    @Override
    public Skill getById(Long id) {
        try {
            String skillStr = bufferedReader.readLine();
            while (skillStr != null) {
                Skill skill = transformStringToSkill(skillStr);
                if (id.equals(skill.getId())) {
                    return skill;
                }
                skillStr = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Skill> getAll() {
        ArrayList<Skill> skillList = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(SkillView.FILE_PATH));
            String skill = bufferedReader.readLine();
            while (skill != null) {
                skillList.add(transformStringToSkill(skill));
                skill = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return skillList;
    }

    public Skill transformStringToSkill(String skillStr) {
        String[] arrStr = skillStr.split("-");
        return new Skill(Long.parseLong(arrStr[0]), arrStr[1]);
    }

    @Override
    public void save(Skill obj) {
        try {
            fileWriter.append('\n');
            fileWriter.write(obj.toString());

            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Skill deleteById(Long id) {
        try {
            String skillStr = bufferedReader.readLine();
            ArrayList<Skill> skillList = new ArrayList<>();
            Skill skill = null;
            while (skillStr != null) {
                skill = transformStringToSkill(skillStr);
                skillList.add(skill);
                if (id.equals(skill.getId())) {
                    skillList.remove(skill);
                }
                skillStr = bufferedReader.readLine();
            }
            saveSkillsListToFile(skillList);
            return skill;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Skill skill) {
        try {
            String skillStr = bufferedReader.readLine();
            while (skillStr != null) {
                Skill skillFromFile = transformStringToSkill(skillStr);
                if (skill.getId().equals(skillFromFile.getId())) {
                    fileWriter.write(skill.toString());
                    fileWriter.append('\n');
                    fileWriter.flush();
                    return true;
                }
                skillStr = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void saveSkillsListToFile(ArrayList<Skill> skills) {
        for (Skill s : skills) {
            save(s);
        }
    }


}
