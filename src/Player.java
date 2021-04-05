import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.String.format;

public class Player extends Karakter{
    //item default yang dimiliki player
    private Ruangan ruanganAktif;  //ruangan tempat player saat ini berada

    private ArrayList<String> arrAksiSenjata = new ArrayList<>();
    private Aksi objAksiSenjata;
    private ArrayList<String> arrAksiLepasPakai = new ArrayList<>();
    private Aksi objAksiLepasPakai;

    private Scanner sc = new Scanner(System.in);

    public Player() {
        //set atribut player = jumlah player hanya satu
        this.setNama("budiwati");
        this.setKesehatan(100);
        this.setDefense(5);
        this.setAtk(15);

        arrItem = new ArrayList<>();
        objItem = new Item("Cincin Emas", 0);
        objItem.setDeskripsi("Cincin emas bertuliskan suatu kalimat..");
        arrItem.add(objItem);

        arrAksi.add("info player");
        arrAksi.add("Info Item Player");
        objAksi = new Aksi(arrAksi);

        arrAksiSenjata.add("Item Senjata");
        arrAksiSenjata.add("Item Perisai");
        arrAksiSenjata.add("Item Yang Digunakan");
        objAksiSenjata = new Aksi(arrAksiSenjata);

        arrAksiLepasPakai.add("Pakai Item");
        arrAksiLepasPakai.add("Lepas Item");
        objAksiLepasPakai = new Aksi(arrAksiLepasPakai);
    }

    //setter dan getter atribut player

    //cari item yang dimiliki oleh player, return TRUE jika ada
    //salah satu yg menggunakan: pintu untuk mengecek apakah player sudah punya kunci
    public boolean cariItem(String namaItem) {
        for (Item objItem:arrItem) {
             if (namaItem.equals(objItem.getNama())) {
                 return (true); // ketemu
            }
        }
        return(false); //tidak ketemu
    }

    // print nama dan deksripsi player
    public void printPlayer() {
        System.out.println("Nama Player:"+this.getNama());
        System.out.println("Kesehatan Player:"+this.getKesehatan());
        System.out.println("Atk : " +this.getAtk());
        System.out.println("Defense : " + this.getDefense());

    }

    // print item yang dimiliki player
    public void printItem() {
        System.out.println("Item milik player");
        int cc = 0;
        for (Item objItem:arrItem) {
            cc++;
            System.out.printf("%d. %s%n",cc,objItem.getNama());
            System.out.println(objItem.getDeskripsi());
        }
    }

    // hapus item di ruangan berdasarkan namanya
    // digunakan saat suatu item diambil oleh player misalnya
    public void hapusItem(Item objItem) {
        arrItem.remove(objItem);  //buang item
    }

    //Aksi untuk proses lepas dan pakai item
    public void prosesAksiItem(Item item, int pil) {
        if (pil == 1) {
            //pakai item
            item.itemPakai(objGameInfo.getObjPlayer());
        } else if (pil == 2) {
            //lepas item
            item.itemLepas(objGameInfo.getObjPlayer());
        }
    }

    public void pilihanAksi() {
        System.out.println("**** Pilihan Aksi pada Player ***");
        int urutPil = 0;
        int subPil = 0;

        // PRINT ITEM YANG DIMILIKI PLAYER
        int ar = objAksi.pilihanAksiSub("o> Item yang dimiliki player", arrItem, urutPil);
        urutPil = ar;

        // Aksi Player pada Senjata
        subPil = 0;
        int pilAksiSenjata = urutPil;


        //info seputar player
        urutPil++;
        subPil = 0;
        int pilInfoPlayer  = urutPil; //catat untuk pintu
        System.out.println("o> Info Player");
        int arPlayer = objAksi.pilihanAksiSub(urutPil);
        urutPil = arPlayer;

        //print pilihan aksi
        int arrPil[] = objAksi.inputPilihanUserSub();
        int pil = arrPil[0];
        subPil = arrPil[1];


        if (pil == pilInfoPlayer) {
            menuAksiItem(subPil);
        }
        else {
            Item objItemPilih = arrItem.get(pil-1);
            objItemPilih.prosesAksi(subPil); //aksi item
        }
    }

    //proses untuk pakai item oleh player
    public void pakaiItem(int kodeItem) {
        int urutPil = 0;
        boolean status = false;
        //senjata
        for (Item data: arrItem) {
            if(data.kodeItem == kodeItem) {
                //juka ada arrItem yang akan di pakai set true
                status = true;
            }
        }
        if (!status) {
            System.out.println("Data Item Kosong");
        } else {
            int pill = objAksiLepasPakai.pilihanAksiSub(arrItem, arrAksiLepasPakai, urutPil, kodeItem);
            urutPil = pill;
            //cek ada datanya atau tidak, jikaa tidak ada urutpil=0
            if (urutPil == 0) {
                System.out.println("Player belum memiliki Item");
            } else {
                int arrPil[] = objAksiLepasPakai.inputPilihanUserSub();
                int pil = arrPil[0];
                int subPil = arrPil[1];

                Item objItemPilih = arrItem.get(pil-1);
                prosesAksiItem(objItemPilih, subPil);
            }
        }
    }

    public void menuAksiItem(int pilihan) {
        if (pilihan == 1) {
            printPlayer();
        } else {
            if (arrItem.isEmpty()) {
                System.out.println("Player belum memiliki Item");
            } else {
                objAksiSenjata.printAksi(); // 1 : item senjata, 2: item perisai, 3: item yang digunakan
                int pilAksiSenjata = objAksiSenjata.inputPilihanInt();
                if (pilAksiSenjata == 1){ // item senjata
                    //senjata
                    pakaiItem(2);
                }
                else if (pilAksiSenjata == 2){ // item perisai
                    //perisai
                    pakaiItem(3);
                }
                else{
                    objItem.itemYangdigunakan(arrItem);
                }
            }
        }
    }

    @Override
    public void setObjGameInfo(GameInfo objGameInfo) {
        this.objGameInfo = objGameInfo;
        objItem.setObjGameInfo(objGameInfo);
    }

    public void addItem(Item objItem) {
        arrItem.add(objItem);
    }

    public void setRuanganAktif(Ruangan ruanganAktif) {
        this.ruanganAktif = ruanganAktif;
    }
    public Ruangan getRuanganAktif() {
        return ruanganAktif;
    }

    public GameInfo getObjGameInfo() {
        return objGameInfo;
    }

    //Method Kurangi Kesehatan Musuh
    public void serangKurangiHp(Musuh musuh) {
        //mengurangi kesehatan lawan berdasarkan senjata yang dimiliki
        if ( objGameInfo.getObjPlayer() == null) {
            System.out.println("null");
        } else {
            int kurangi = objGameInfo.getObjPlayer().getAtk() - musuh.getDefense();
            musuh.setKesehatan(musuh.getKesehatan() - kurangi);
        }
    }
}
