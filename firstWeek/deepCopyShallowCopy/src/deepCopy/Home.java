package deepCopy;

import java.math.BigDecimal;

public class Home implements Cloneable {
    private Long id;
    private Long serialNumber;
    private BigDecimal grossSquareMeter;
    private BigDecimal netSquareMeter;
    private Long countOfRoom;
    private Architect architect;

    public Architect getArchitect() {
        return architect;
    }

    public void setArchitect(Architect architect) {
        this.architect = architect;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getGrossSquareMeter() {
        return grossSquareMeter;
    }

    public void setGrossSquareMeter(BigDecimal grossSquareMeter) {
        this.grossSquareMeter = grossSquareMeter;
    }

    public BigDecimal getNetSquareMeter() {
        return netSquareMeter;
    }

    public void setNetSquareMeter(BigDecimal netSquareMeter) {
        this.netSquareMeter = netSquareMeter;
    }

    public Long getCountOfRoom() {
        return countOfRoom;
    }

    public void setCountOfRoom(Long countOfRoom) {
        this.countOfRoom = countOfRoom;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Home cloneHome = (Home)super.clone();

        Architect architect=cloneHome.getArchitect();
        Architect cloneArchitect = (Architect) architect.clone();
        cloneHome.setArchitect(cloneArchitect);

        return cloneHome;
    }
}
