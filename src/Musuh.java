public class Musuh extends Karakter {
    private boolean isDie = false; // kondisi apakah musuh sudah mati atau belum

    public Musuh() {
        //set atribut musuh = jumlah player hanya satu di ruangan 2
        this.setNama("Rakarta");
        this.setKesehatan(100);
        this.setAtk(20);
    }

    public boolean isDie() {
        return isDie;
    }

    public void setDie(boolean die) {
        isDie = die;
    }

    public void serangKurangiHp() {
        //mengurangi kesehatan lawan
        if ( objGameInfo.getObjPlayer() == null) {
            System.out.println("null");
        } else {
//            if (objGameInfo.getObjPlayer().getDefense() >= this.getAtk() ){
                int ketahanan = objGameInfo.getObjPlayer().getDefense() - this.getAtk(); // cek apakah defense lebih besar dari pada serangan musuh
//            }
//            else {
//                int ketahanan = this.getAtk() - objGameInfo.getObjPlayer().getDefense();
//            }

            if (ketahanan <= 0){ // jika atk musuh lebih besar dari defense player, maka kurangi kesehatan
                objGameInfo.getObjPlayer().setKesehatan(ketahanan + objGameInfo.getObjPlayer().getKesehatan());
            }
//
//            int kurangi = objGameInfo.getObjPlayer().getKesehatan()+objGameInfo.getObjPlayer().getDefense();
//            objGameInfo.getObjPlayer().setKesehatan(kurangi - this.getAtk());
        }
    }
}
