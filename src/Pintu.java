import java.util.ArrayList;
//belum menggunakan inheritance, idealnya turunan dari Item

public class Pintu extends Item{

    private boolean isPintuTerbuka = false; //cek kondisi apakah pintu sudah terbuka atau belum

    //    ArrayList<String> arrAksi = new ArrayList<>(); // untuk menyimpan menu pilihan aksi pada pintu
    //    GameInfo objGameInfo;

    //constrcutor
    public Pintu() {
        //init pilihan
        super("pintu", 1);
        arrAksi.add("Deskripsikan pintu");
        arrAksi.add("Coba buka pintu");
    }

    //get data isPintuTerbuka
    public boolean isPintuTerbuka() {
        return isPintuTerbuka;
    }
    //set data apakah pintu sudah terbuka atau tidak
    public void setPintuTerbuka(boolean pintuTerbuka) {
        isPintuTerbuka = pintuTerbuka;
    }

    public void prosesAksi(int subPil) {
        //1: deskripsikan
        //2: buka pintu
        if (subPil==1) {
            System.out.println("Pintu tua dengan pegangan pintunya yang sudah rusak");
        }
        else if (subPil==2) {
            //cek apakah mempunyai kunci
            if (objGameInfo.getObjPlayer().cariItem("Palu")) {
                //kunci ada, pintu terbuka
                System.out.println("Player menggunakan Palu untuk mendobrak pintu dan pintu terbuka!");

                //objGameInfo.setGameOver(true);

                //set keterangan pintu di ruangan 1 untuk masuk ke ruangan kedua kebuka
                objGameInfo.getObjRuangan().getObjPintu().setPintuTerbuka(true); //set isPintuTerbuka = true

            }
            else {
                //kunci tidak ada
                System.out.println("Player mencoba membuka pintu. Pintu Tidak bisa terbuka! Karena pegangan pintu sudah rusak");
            }
        }
    }

    public void bukaPintu() {
        if (objGameInfo == null) {
            System.out.println("GameInfo kosong");
        } else {
            if (objGameInfo.getObjPlayer().cariItem("Kunci Pintu")) {
                objGameInfo.getObjRuangan().getObjPintu().setPintuTerbuka(true); //set isPintuTerbuka = true
                System.out.println("Selamat player berhasil keluar dari rumah sekapan");
                objGameInfo.setGameOver(true);
            } else {
                System.out.println("Player mencoba membuka pintu. Pintu Tidak bisa terbuka! Karena tidak memiliki kunci");
            }
        }
    }

    public ArrayList<String> getAksi() {
        return arrAksi;
    }

}
