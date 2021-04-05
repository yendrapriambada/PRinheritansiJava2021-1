import java.util.ArrayList;

public class GameEngine{
    // user interface game
    Player objPlayer = new Player();
    Musuh objMusuh = new Musuh();
    Ruangan objRuangan1; // set ruangan pertama
    Ruangan objRuangan2; // set ruangan dua
    Aksi objAksi;
    GameInfo objGameInfo = new GameInfo();

    ArrayList<String> arrAksi = new ArrayList<>();

    public static void main(String[] args) {
        GameEngine objGameEngine;
        objGameEngine = new GameEngine();
        objGameEngine.mulai();
    }

    //constructor
    public GameEngine() {
        //init ruangannya
        objRuangan1 = new Ruangan(); // set ruangan pertama
        objRuangan2 = new Ruangan(); // set ruangan dua

        //init ruangan 1
        objRuangan1.setObjGameInfo(objGameInfo);
        objRuangan1.setDeskripsi("\"Ruangan pertama, Ruangan kecil, dengan satu pintu untuk masuk ke ruangan kedua dan jendela\"");

        //init ruangan 2
        objRuangan2.setObjGameInfo(objGameInfo);
        objRuangan2.setDeskripsi("\"Ruangan kedua, Ruangan besar, dengan satu pintu untuk keluar dari rumah tempat anda terkurung dan 2 jendela\"");
        objRuangan2.setObjMusuh(objMusuh); //set musuh yang ada di ruangan dua

        objPlayer.setRuanganAktif(objRuangan1);  //set ruangan aktif player = ruangan ke satu
        objMusuh.setObjGameInfo(objGameInfo);
        objPlayer.setObjGameInfo(objGameInfo);

        //isi array aksi game engine
        arrAksi.add("Aksi yang dapat dilakukan di ruangan pertama");
        arrAksi.add("Aksi yang dapat dilakukan di ruangan kedua");
        arrAksi.add("Aksi terhadap player");
        arrAksi.add("Keluar");
        objAksi = new Aksi(arrAksi);

        //init gameInfo
        objGameInfo.setObjPlayer(objPlayer);
        objGameInfo.setObjRuangan(objRuangan1); //set ruangan yang sedang aktif
    }

    private void aksi() {
        objGameInfo.getObjPlayer().setKesehatan(100);
        objAksi.printAksi();
        int pil = objAksi.inputPilihanInt();
        System.out.println("--");
        if (pil==4) {
            objGameInfo.setGameOver(true); //keluar
        } else if (pil==1) {
            objRuangan1.cekKondisiPintuRuangan(1, objRuangan1, objRuangan2); //masuk aksi ruangan pertama cek terlebih dahulu kondisi pintu masuk ke ruangan
        } else if (pil==2) {
            objRuangan2.cekKondisiPintuRuangan(2, objRuangan1, objRuangan2); //masuk aksi ruangan kedua cek terlebih dahulu kondisi pintu masuk ke ruangan
        }
        else if (pil==3) {
            objPlayer.pilihanAksi(); //masuk aksi player
        }
    }

    public void mulai() {
        while (!objGameInfo.getGameOver()) {
            aksi();
        }
    }
}
