package com.golfworld.jouletracker;

public class Entry {

    public  int Breakfast;
    public  int Lunch;
    public  int Dinner;
    public  int Snack;
    public  int Gym;
    public  int Sport;
    public  int Jogging;

    public int EnergyIntake;
    public int EnergyOut;
    public int TotalEnergy;

    public String date;

    public Entry() {
    }

    public int getBreakfast() {
        return Breakfast;
    }

    public void setBreakfast(int breakfast) {
        Breakfast = breakfast;
    }

    public int getLunch() {
        return Lunch;
    }

    public void setLunch(int lunch) {
        Lunch = lunch;
    }

    public int getDinner() {
        return Dinner;
    }

    public void setDinner(int dinner) {
        Dinner = dinner;
    }

    public int getSnack() {
        return Snack;
    }

    public void setSnack(int snack) {
        Snack = snack;
    }

    public int getGym() {
        return Gym;
    }

    public void setGym(int gym) {
        Gym = gym;
    }

    public int getSport() {
        return Sport;
    }

    public void setSport(int sport) {
        Sport = sport;
    }

    public int getJogging() {
        return Jogging;
    }

    public void setJogging(int jogging) {
        Jogging = jogging;
    }

    public int getEnergyIntake() {
        return EnergyIntake;
    }

    public void setEnergyIntake(int energyIntake) {
        EnergyIntake = energyIntake;
    }

    public int getEnergyOut() {
        return EnergyOut;
    }

    public void setEnergyOut(int energyOut) {
        EnergyOut = energyOut;
    }

    public int getTotalEnergy() {
        return TotalEnergy;
    }

    public void setTotalEnergy(int totalEnergy) {
        TotalEnergy = totalEnergy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
