# PRinheritansiJava2021

--
====================================
TUGAS PEMROGRAMAN BERORIENTASI OBJEK
====================================
Pendidikan Ilmu Komputer B 2019 Semester 4 Universitas Pendidikan Indonesia
Nama Anggota:
1. Dwi Fitria Al Husaeni	- 1903480
2. Mochamad Yendra Priambada 	- 1900056

Introduction Game:
Terdapat sebuah player yang disekap pada sebuah rumah tua.
Ia harus melalui dua buah ruangan untuk dapat keluar dari rumah tersebut.
Terdapat beberapa item yang harus ia dapatkan. Salah satunya adalah box Kayu
yang berisi kunci pintu keluar dari ruangan terakhir, Akan tetapi box Kayu
tersebut tidak bisa di buka dan memerlukan sebuah kunci untuk membukanya. 
Mari kita Bantu player untuk menemukan kunci box Kayu tersebut dan jangan
lupa untuk mengalahkan musuh yang ada di salah satu ruangan!!

Deskripsi modifikasi/tambahan dari kode sebelumnya:

1. List Objek baru:
   - Sapu = sebagai senjata untuk memukul musuh
   - Teflon = sebagai alat pertahanan / tameng untuk meredam serangan musuh
   - Palu = sebagai alat untuk mendobrak pintu yang handle nya rusak
   - Box Kayu = Box Kayu yang masih terkunci dan harus mencari kunci untuk membukanya
   - Pintu = ada 2 pintu, 1 di rungan 1 dan 1 lagi  di ruangan 2
2. Tambahan Class
   - Class Aksi 
   - Class Karakter
   - Class Musuh
   - Class Perisai
   - Class Pertarungan
   - Class Senjata
3. Tambahan Inheritance
   - Parent Class Karakter
     -- Class turunan : NPC, Musuh, dan Player
   - Parent Class Item
     -- Class turunan : Pintu, Senjata, dan Perisai
4. Tambahn Polymorphisme
   - class player method setObjGame info, turunan dari class karakter
   public class Player extends Karakter{
      @Override
       public void setObjGameInfo(GameInfo objGameInfo) {
           this.objGameInfo = objGameInfo;
           objItem.setObjGameInfo(objGameInfo);
       }
   }
   
   - class senjata dan perisai, parent class item, pada method item pakai dan item lepas
    //method pakai Item
    @Override
    public void itemPakai(Player player) {
        if (this.isStatusPemakaian() == false){
            this.setStatusPemakaian(true);
            player.setAtk(player.getAtk()+this.atk);
            System.out.println("Item berhasil digunakan");
        }
        else{
            System.out.println("Sudah ada Senjata yang digunakan");
        }

    }

    @Override
    //method lepas item
    public void itemLepas(Player player) {
        if (this.isStatusPemakaian() == true){
            this.setStatusPemakaian(false);
            player.setAtk(player.getAtk()-this.atk);
            System.out.println("Item berhasil dilepas");
        }

        else{
            System.out.println("Belum ada Senjata yang digunakan");
        }
    }
    
   - pada class pintu yang merupakan turunan kelas item terdapat konsep polymorphisme pada getAksi dan prosesAksi
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
    public ArrayList<String> getAksi() {
        return arrAksi;
    }
   
5. Perbaiki
   - Memisahkan method aksi dan beberapa method menuAksi, proses tampilan aksi-aksi  di setiap class 
     menjadi sebuah class baru yakni class aksi
   - Pada class player, proses aksi yang memiliki alur 
     seperti class ruangan diganti dengan memanggil 
     method di class aksi, begitupun di class ruangan dan class gameEngine
   - Menjadikan class pintu sebagai class turunan dari class item
   - Class NPC dan Class Player dijadikan class turunan dari class Karakter
   - Penambahan komentar pada kode agar lebih mudah dimengerti
   - Memperbaiki method dan atribut yang redundansi

Tambahan Fitur Pada Game:
1. Menambah 1 ruangan sehingga terdapat 2 ruangan
2. Menambah class musuh dengan atribut kesehatan, atk, defense, dan beberapa method lainnya
3. Menambah beberapa objek item di ruangan 1 
4. Adanya perubahan ketentuan untuk menyelesaikan misi
5. Menambahkan 1 item pintu di setiap ruangan (1 pintu ruangan 1 & 1 pintu ruangan 2)
6. Terdapat fitur pertarungan antara musuh dan player
7. Menambah story line misi
8. Menambahkan syarat khusus untuk mengambil suatu item maupun membuka pintu dan menghubungi NPC
9. Player dapat memakai dan melepaskan senjata dan perisai yang ia temukan dalam suatu ruangan
10. Menambahkan atribut baru pada player yakni atk dan defense

Ketentuan Pertarungan:
1. Dilakukan secara bergiliran antara musuh dan player
2. Dapat memilih arah serangan maupun arah penghindaran
3. Pengurangan kesehatan musuh = musuh - (atkPlayer - defenseMusuh)
4. Pengurangan kesehatan player = 
   - cek terlebih dahulu defense player, 
   - jika defense player >= atkMusuh maka kesehatan 
     player bisa jadi tidak akan berkurang.
   - Namun jika sebaliknya, atkMusuh > defensePlayer, makas
     kesehatan player - (atkMusuh - defensePlayer)
5. Player dapat memilih 2 pilihan dalam pertarungan, kabur dan menyerang.
