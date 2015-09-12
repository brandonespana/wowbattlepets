package com.brandonespana.wowbattlepets.pets;

/**
 * Copyright 2015 Brandon Espana
 * <p/>
 * Instructor and TA have the right to build and evaluate the software package
 *
 * @author Brandon Espana Brandon.Espana@asu.edu
 * @version August 22, 2015
 */
public class BattlePet {
    private String name;
    private String creatureName;
    private String family;
    private int level;

    public BattlePet(String name, String creatureName, int level){
        this.name = name;
        this.creatureName = creatureName;
        this.level = level;
    }

    public String getCreatureName() {
        return creatureName;
    }

    public void setCreatureName(String creatureName) {
        this.creatureName = creatureName;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "BattlePet{" +
                "name='" + name + '\'' +
                ", creatureName='" + creatureName + '\'' +
                ", family='" + family + '\'' +
                ", level=" + level +
                '}';
    }
}
