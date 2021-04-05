import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.String.format;

public class Ruangan {
    private Senjata objSapu;
    private Perisai objTeflon;
    private Pintu objPintu;
    private NPC objNPC;
    private Item objItem;
    private Item objRoti;
    private Item objPalu;
    private Item objBoxkayu;
    public ArrayList<Item> arrItem = new ArrayList<>(); // list item yang ada di ruangan
    private String deskripsi;
    private GameInfo objGameInfo;
    private Aksi objAksi;
    private Aksi objAksiRuangan2;
    private Pertarungan objPertarungan = new Pertarungan();
    private ArrayList<String> arrAksi = new ArrayList<>();
    private ArrayList<String> arrAksiRuangan2 = new ArrayList<>(); //kumpulan aksi di ruangan 2
    private Scanner sc = new Scanner(System.in);

    private Musuh objMusuh; //musuh yang ada di dalam ruangan

    //setter dan getter objMusuh


    public Musuh getObjMusuh() {
        return objMusuh;
    }

    public void setObjMusuh(Musuh objMusuh) {
        this.objMusuh = objMusuh;
    }

    //setter dan getter objPintu
    public Pintu getObjPintu() {
        return objPintu;
    }

    public void setObjPintu(Pintu objPintu) {
        this.objPintu = objPintu;
    }

    //objgame juga diset pada npc, pintu dan item2
    public void setObjGameInfo(GameInfo objGameInfo) {
        this.objGameInfo = objGameInfo;
        // set pintu
//        objPintu.setObjGameInfo(objGameInfo); //dikomen karena sudah masuk pada arrItem
        // set NPC
        objNPC.setObjGameInfo(objGameInfo);
        // set item"
        for (Item objItem:arrItem) {
            objItem.setObjGameInfo(objGameInfo);
        }
    }

    public Ruangan() {
        // init ruangan
        // init npc, pintu, kunci dan roti.
        objPintu = new Pintu();
        objNPC = new NPC();

        objRoti  = new Item("Roti", 0);
        objRoti.setDeskripsi("Roti rasa coklat dalam bungkusan plastik");
        objRoti.setObjRuangan(this);

        objPalu = new Item("Palu", 0);
        objPalu.setDeskripsi("Palu baja yang ujung tongkat nya sudah sedikit retak");
        objPalu.setObjRuangan(this);

        objBoxkayu = new Item("Box Kayu", 0);
        objBoxkayu.setDeskripsi("Sebuah box kayu yang digembok, didalamnya terdapat kunci untuk keluar dari rumah");
        objBoxkayu.setObjRuangan(this);

        objSapu = new Senjata("Sapu", 10, 2);
        objSapu.setDeskripsi("Sebuah sapu kokoh bisa digunakan untuk memukul musuh");
        objSapu.setObjRuangan(this);

        objTeflon = new Perisai("Teflon", 20, 3);
        objTeflon.setDeskripsi("Sebuah Teflon berdiameter besar, bisa digunakan untuk tameng bertahan dari serangan musuh");
        objTeflon.setObjRuangan(this);
        //init aksi utama
        arrAksi.add("Masuk Ruangan 1");
        arrAksi.add("Masuk Ruangan 2");
        objAksi = new Aksi(arrAksi);
        //init aksi di ruangan 2
        arrAksiRuangan2.add("Serang Musuh");
        arrAksiRuangan2.add("Menuju NPC");
        arrAksiRuangan2.add("Buka Box Kayu");
        arrAksiRuangan2.add("Buka Pintu");
        objAksiRuangan2 = new Aksi(arrAksiRuangan2);

        //tambah item ke array
        arrItem.add(objRoti);
        arrItem.add(objSapu);
        arrItem.add(objTeflon);
        arrItem.add(objPintu);
        arrItem.add(objBoxkayu);
        arrItem.add(objPalu);
    }

    //aksi yang dapat dilakukan di ruangan
    //agak kompleks tapi jadi fleksibel, logic aksi ada di masing2 item (bukan di game engine)
    //hardcode menu dikurangi
    //Aksi untuk diruangan 1
    public void pilihanAksiRuanganSatu() {
        System.out.println("==== Pilihan Aksi pada Ruangan Pertama ===");
        int urutPil = 0;  //item, pintu, npc
        int subPil;   //aksinya  apa yang dipilih

        //PRINT ITEM YANG ADA DI RUANGAN
        int ar = objAksi.pilihanAksiSub("Item yang dimiliki Ruangan", arrItem, urutPil);
        urutPil = ar;


        // Input pilihan user
        int arrPil[] = objAksi.inputPilihanUserSub();
        int pil = arrPil[0];
        subPil = arrPil[1];

            Item objItemPilih = arrItem.get(pil-1);
            objItemPilih.prosesAksi(subPil); //aksi item

    }

    //method untuk menu menuju NPC diruangan 2
    public void menuNPC() {
        if(objMusuh.isDie()) {
            // aksi2 NPC
            int subPil = 0;
            int pilNPC  = 1; //catat untuk NPC
            System.out.println("NPC");
            int arNPC = objNPC.getObjAksi().pilihanAksiSub(pilNPC);

            // Input pilihan user
            int arrPil[] = objAksi.inputPilihanUserSub();
            int pil = arrPil[0]; // digit pertama pilihan user
            subPil = arrPil[1]; //digit kedua pilihan user

            objNPC.prosesAksi(subPil);
        } else {
            System.out.println("Kalahkan musuh terlebih dahulu");
        }
    }

    //aksi untuk diruangan 2
    public void pilihanAksiRuanganDua(){
        System.out.println("==== Selamat Datang di Ruangan Kedua ====");
        //tampilkan menu
        objAksiRuangan2.printAksi();
        //tampung masukan untuk pilihan menu dari user
        int pil = objAksiRuangan2.inputPilihanInt();

        switch (pil) {
            case 1:
                System.out.println("==== Serang Musuh ====");
                Pertarungan objPertarungan = new Pertarungan();
                objPertarungan.setGameOver(false);              // ubah kembali pertarungan ke titik awal

                objGameInfo.getObjPlayer().printPlayer();
                System.out.println("==========================================");
                objPertarungan.cekKondisiMusuh(objGameInfo.getObjPlayer(), objMusuh);
                break;
            case 2:
                System.out.println("==== Menuju NPC ====");
                menuNPC();
                break;
            case 3:
                System.out.println("==== Buka Box Kayu ====");
                objBoxkayu.bukaBoxKayu();
                break;
            case 4:
                System.out.println("==== Buka Pintu ====");
                objPintu.bukaPintu();
                break;
            default:
                System.out.println("Masukan anda tidak tersedia");
                break;


        }

    }

    public void cekKondisiPintuRuangan(int pil, Ruangan ruangan1, Ruangan ruangan2) {

        if (pil == 1) {
            //selalu langsung bisa masuk ke ruangan satu
            pilihanAksiRuanganSatu();
        } else {
            //cek kondisi pintu ruangan 2 terbuka atau belum
            if (ruangan1.getObjPintu().isPintuTerbuka()) {
                //pintu ruangan dua terbuka
                //masuk misi ruangan dua
                objGameInfo.setObjRuangan(ruangan2); //set ruangan yang sedang aktif menjadi ruangan 2
                pilihanAksiRuanganDua();
            } else {
                //pintu ruangan dua terkunci
                System.out.println("Pintu Masuk ke Ruangan Terkunci");
            }
        }
    }


    // hapus item di ruangan berdasarkan namanya
    // digunakan saat suatu item diambil oleh player misalnya
    public void hapusItem(Item objItem) {
        arrItem.remove(objItem);  //buang item
    }
    public void addItem(Item objItem) {
        arrItem.add(objItem);
    }
    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    public ArrayList<Item> getArrItem() {
        return arrItem;
    }

}
