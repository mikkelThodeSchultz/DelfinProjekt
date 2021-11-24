//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package domain;

import java.util.List;

public class Calculation {

    public Calculation() {
    }

    public double calculateContingent(int age, boolean isActive){ //sender double til controller
        int junior = 17;
        int senior = 18;
        int seniorDiscount = 60;
        double passiveMembership = 500;
        double activeJuniorMembership = 1000;
        double activeSeniorMembership = 1600;
        double discount = 0.75;
        double contingentSum = 0;

        if (!isActive){
            contingentSum = passiveMembership;
        } else if (age <= junior) {
            contingentSum = activeJuniorMembership;
        } else if (age > senior && age < seniorDiscount) {
            contingentSum = activeSeniorMembership;
        } else {
            contingentSum *= discount;
        }
        return contingentSum;
    }
}
