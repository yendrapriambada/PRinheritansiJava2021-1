import java.util.ArrayList;

public class Item {
    protected String deskripsi;
    protected String nama;
    protected ArrayList<String> arrAksi = new ArrayList<>(); //pilihan aksi untuk item
    protected Ruangan objRuangan;  //ruangan tempat item, jika null artinya item dipegang npc atau plyaer
    protected GameInfo objGameInfo;
    protected int kodeItem; // kode: 0 = Item Biasa, 1 = Pintu, 2 = Senjata, 3 = Perisai

    protected boolean statusPemakaian; //set false artinnya semua item di set belum ada yang dipakai

    //constructor
    public Item(String nama, int kodeItem) {
        this.nama = nama;
        this.kodeItem = kodeItem;
        // -- dipindahkan karena dinamik tergantung diambil atau dibuang
        //  arrAksi.add("Deskripsi Item");
        //  arrAksi.add("Ambil item");
    }

    public void prosesAksi(int pil) {
        //pilihan user untuk aksi yang akan diambil
        //urutan harus sama dengan isi arrAksi
        if (pil==1) {
            System.out.println(deskripsi);
        } else  if (pil==2) {  //bisa ambil atau buang
            if (objRuangan==null) {
               //dipegang player, buang ke ruangan
                dibuang();
            } else {
                //ada di ruangan, diambil player
                //kondisi ini untuk check syarat bahwa urutan yang harus di ambil yakni Senjata terlebih dahulu,
                //lalu box kayu, dan terakhir Palu
                if (this.nama.equals("Box Kayu")){
                    checkSyaratBoxKayu(); // apabila yang di ambil box maka check senjata harus sudah ada
                }
                else if (this.nama.equals("Palu")){
                    checkSyaratPalu(); // apabila yang di ambil palu maka check boxKayu sudah ada
                }
                else {
                    diambil();
                }
            }
        }
    }

    public void bukaBoxKayu() {
        if (objGameInfo == null) {
            System.out.println("GameInfo kosong");
        } else {
            if (objGameInfo.getObjPlayer().cariItem("Box Kayu")) {
                if (objGameInfo.getObjPlayer().cariItem("Kunci Box")) {
                    Item kunciPintu = new Item("Kunci Pintu",0);
                    objGameInfo.getObjPlayer().addItem(kunciPintu);
                    System.out.println("Selamat player sudah mendapatkan item kunci pintu keluar dari ruangan dua");
                } else {
                    System.out.println("Player belum memiliki kunci untuk membuka BOX KAYU, ambil kunci di NPC");
                }
            } else {
                System.out.println("Player belum memiliki BOX KAYU, ambil Box Kayu di Ruangan 1");
            }
        }
    }

    private void dibuang() {
        //player membuang item ke ruangan
        System.out.println("Item dibuang player ke ruangan");
        objGameInfo.getObjPlayer().hapusItem(this); //hapus dari player
        objGameInfo.getObjRuangan().addItem(this);  //tambah ke ruangan
        objRuangan = objGameInfo.getObjRuangan(); // set ruangan
    }

    //pindahkan item dari ruangan ke player
    private void diambil() {
        System.out.println("Item diambil player");
        objGameInfo.getObjPlayer().addItem(this);     //tambahkan  objek ini (this) pada player
        objRuangan.hapusItem(this);                    //hapus dari ruangan
        objRuangan = null;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public ArrayList<String> getAksi() {
        //aksi dinamik tergantung ada di ruangan atau dipegang player/npc
        ArrayList<String> arrOut = new ArrayList<>();
        if (objRuangan==null) {
            //ada di player, ada opsi buang
            arrOut.add("Deskripsi Item");
            arrOut.add("Buang item");
        } else {
            //ada di ruangan ada opsi ambil
            arrOut.add("Deskripsi Item");
            arrOut.add("Ambil item");
        }
        return(arrOut);
    }

    public boolean isStatusPemakaian() {
        return statusPemakaian;
    }

    public void setStatusPemakaian(boolean statusPemakaian) {
        this.statusPemakaian = statusPemakaian;
    }

    public void setObjGameInfo(GameInfo objGameInfo) {
        this.objGameInfo = objGameInfo;
    }
    public void setObjRuangan(Ruangan objRuangan) {
        this.objRuangan = objRuangan;
    }
    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getKodeItem() {
        return kodeItem;
    }

    public void setKodeItem(int kodeItem) {
        this.kodeItem = kodeItem;
    }

    //Method untuk cek item apa saja yang digunakan oleh player
    public void itemYangdigunakan(ArrayList<Item> arrItem) {
        boolean status = false;
        int no=0;
        for (Item n: arrItem) {
            if (n.isStatusPemakaian() == true) {
                no++;
                System.out.println(no+". "+n.getNama());
                status = true;
            }
        }
        if (status == false) {
            System.out.println("Tidak ada Item yang sedang anda pakai");
        }
    }

    //method pakai Item
    public void itemPakai(Player player) {
        this.setStatusPemakaian(true);
    }

    //method lepas item
    public void itemLepas(Player player) {
        this.setStatusPemakaian(false);
    }

    public void checkSyaratBoxKayu() {
        // method untuk check syarat ambil box kayu harus sudah ambil senjata (sapu)
        if (objGameInfo.getObjPlayer().cariItem("Sapu")) {
            diambil();  // jika sudah ada senjata maka bisa di ambil
        }
        else {
            System.out.println("Player belum memiliki Senjata Sapu, ambil Senjata Sapu terlebih dahulu di Ruangan 1");
        }
    }

    public void checkSyaratPalu() {
        // method untuk check syarat palu harus sudah ambil box kayu
        if (objGameInfo.getObjPlayer().cariItem("Box Kayu")) {
            diambil(); // jika sudah ada box kayu maka bisa di ambil
        }
        else {
            System.out.println("Player belum memiliki Box Kayu, ambil Box Kayu terlebih dahulu di Ruangan 1");
        }
    }


}
