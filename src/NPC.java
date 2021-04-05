import java.util.ArrayList;

public class NPC extends Karakter{
    //kunci dipindahkan dari ruangan
    private boolean isKenal = false;

    public NPC() {
        //init kunci
        objItem = new Item("Kunci Box", 0);
        objItem.setDeskripsi("Sebuah kunci kecil yang sudah agak berkarat untuk membuka Box Kayu");
        arrItem.add(objItem);

        //aksi npc
        arrAksi.add("Perkenalan dgn NPC");
        arrAksi.add("Minta kunci");

        objAksi = new Aksi(arrAksi);

    }

    public void prosesAksi(int subPil) {
        //1: perkenalan dengan npc
        //2: buka pintu
        if (subPil == 1) {
            System.out.println("Halo saya penjaga pintu ini");
            isKenal = true;
        }
        else if (subPil == 2) {
            if (isKenal) {
                //berikan kunci pada player
                if (objGameInfo.getObjPlayer().cariItem("Kunci Box")) {
                    System.out.println("Masa lupa, kunci kan sudah saya berikan!");
                }
                else {
                    if (objGameInfo.getObjPlayer().cariItem("Cincin Emas")) {
                        System.out.println("Kunci Box kayu diberikan pada player");
                        //pindahkan objek kunci dari NPC ke Player
                        objGameInfo.getObjPlayer().addItem(objItem);     //tambahkan  objek ini pada player
                        //set objItem null
                        objItem = null;
                        for (Item n: objGameInfo.getObjPlayer().arrItem) {
                            // Cari dari array item player Cincin Emas
                            if (objGameInfo.getObjPlayer().cariItem("Cincin Emas")) {
                                //isi objek item di NPC dengan Cincin Emas
                                objItem = n;
                            } else {
                                System.out.println("Player tidak memiliki Cincin Emas untuk ditukar dengan Kunci Pintu");
                            }
                        }
                    } else {
                        System.out.println("Player tidak memiliki Cincin Emas untuk ditukar dengan Kunci Pintu");
                    }
                }

            }
            else {
                System.out.println("Siapa anda? kenalan dulu dong");
            }
        }
    }
}
