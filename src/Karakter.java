import java.util.ArrayList;

public class Karakter {
    protected Item objItem;
    protected ArrayList<String> arrAksi = new ArrayList<>();
    protected GameInfo objGameInfo;
    protected ArrayList<Item> arrItem = new ArrayList<>();
    protected Aksi objAksi;

    //set data karakter
    private String nama;
    private int kesehatan;
    private int atk;
    private int defense;

    //Setter Getter data karakter


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public void setKesehatan(int kesehatan) {
        this.kesehatan = kesehatan;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setObjGameInfo(GameInfo objGameInfo) {
        this.objGameInfo = objGameInfo;
//        objItem.setObjGameInfo(objGameInfo);
    }

    public Aksi getObjAksi() {
        return objAksi;
    }

    public void setObjAksi(Aksi objAksi) {
        this.objAksi = objAksi;
    }


}
