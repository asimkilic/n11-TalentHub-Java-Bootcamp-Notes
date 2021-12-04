package shallowCopy;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Architect architect = new Architect();
        Home home = new Home();
        home.setId(1L);
        home.setSerialNumber(111L);
        home.setGrossSquareMeter(BigDecimal.valueOf(150));
        home.setNetSquareMeter((BigDecimal.valueOf(125)));
        home.setCountOfRoom(5L);
        home.setArchitect(architect);

        Home cloneHome = null;
        try {
            cloneHome = (Home) home.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        /* == checks memory references are same?
         *  for shallow copy it returns different
         * */
        if (home == cloneHome) {
            System.out.println("Same");
        } else {
            System.out.println("Different");
        }
        /* For shallow copy it returns same*/
        if (home.getArchitect() == cloneHome.getArchitect()) {
            System.out.println("Same");
        } else {
            System.out.println("Different");
        }
    }
}
